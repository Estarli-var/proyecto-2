/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import Enums.EstadoContrato;
import Enums.TipoHabitacion;
import excepciones.cambioEstadoInvalidoException;
import excepciones.espacioNoDisponibleException;
import excepciones.fechaInvalidaException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import lists.contratoList;
import modelos.Contrato;
import modelos.Espacio;
import modelos.Servicio;
/**
 *
 * @author Aaron Diaz
 */
public class controladorContrato {
    private contratoList contratoList;
    private static controladorContrato instancia;
    private ControladorEspacio controladorEspacio;
    
    private controladorContrato(){
        this.contratoList = new contratoList();
    }
    
    public static controladorContrato getInstance(){
        if (instancia == null) {
            instancia = new controladorContrato();
        }
        return instancia;
    }
    
    // llamar a este método una vez que exista EspaciosList
    public void setControladorEspacio(ControladorEspacio controladorEspacio) {
        this.controladorEspacio = controladorEspacio;
    }
    
    public Contrato crearContrato(Date fechaInicio, Date fechaFin) throws fechaInvalidaException{
        Contrato nuevo = new Contrato(fechaInicio, fechaFin);
        contratoList.add(nuevo);
        return nuevo;
    }
    
    public void validarCliente(String identificacion){
        return ;
    }
    
    public ArrayList<Espacio> buscarEspacioDisponible(TipoHabitacion tipo, Date fechaInicio, Date fechaFin){
        ArrayList<Espacio> candidatos = controladorEspacio.filtrarPorTipo(tipo);
        ArrayList<Espacio> disponible = new ArrayList();
        for (Espacio espa : candidatos) {
            if (!hayConflicto(espa, fechaInicio,fechaFin)) {
                disponible.add(espa);
            }
        }
        return disponible;
    }
    
    public void asignarEspacio(Contrato contrato, Espacio espacio) throws espacioNoDisponibleException{
        if (hayConflicto(espacio, contrato.getFechaInicio(), contrato.getFechaFinal())) {
            throw new espacioNoDisponibleException("El espacio " + espacio.getNumero() +
                "no esta disponible en las fechas seleccionadas");
        }
        contrato.asignarEspacio(espacio);
    }
    
    private boolean hayConflicto(Espacio espacio, Date inicio, Date fin){
        for (Contrato contra : listarTodos()) {
            boolean mismoEspacio = contra.getEspacio() != null && contra.getEspacio().getNumero() == espacio.getNumero();
            boolean estado = contra.getEstado() == EstadoContrato.PENDIENTE || contra.getEstado() == EstadoContrato.ACTIVO;
            if (mismoEspacio && estado) {
                if (inicio.before(contra.getFechaFinal()) && fin.after(contra.getFechaInicio())) {
                  return true;  
                }
            }
        }
        return false;
    }
    
    public boolean agregarServicio(Contrato contrato, Servicio servicio){
        return contrato.agregarServicio(servicio);
    }
    
    public boolean quitarServicio(Contrato contrato, Servicio servicio){
        return contrato.quitarServicio(servicio);
    }
    
    public int obtenerDias(Contrato contrato){
        return contrato.calcularDias();
    }
    
    public int obtenerPeriodos(Contrato contrato){
        return contrato.calcularPeriodos();
    }
    
    public void calcularCostos(Contrato contrato){
        contrato.calcularCostos();
    }
    
    public void activarContrato(Contrato contrato) throws cambioEstadoInvalidoException{
        contrato.activar();
    }
    
    public void finalizarContrato(Contrato contrato) throws cambioEstadoInvalidoException{
        contrato.finalizar();
    }
    
    public void cancelarContrato(Contrato contrato) throws cambioEstadoInvalidoException{
        contrato.cancelar();
    }
    
    public Contrato buscarPorNumero(int numero){
        return contratoList.get(numero);
    }
    
    public ArrayList<Contrato> listarTodos(){
        ArrayList<Contrato> resultado = new ArrayList<>();
        Iterator<Contrato> iter = contratoList.getAll();
        
        if (iter != null) {
            iter.forEachRemaining(resultado::add);
        }
        return resultado;
    }
    
    public ArrayList<Contrato>filtrarPorEstado(String estado){
        ArrayList<Contrato> resultado = new ArrayList<>();
        
        for (Contrato contra : listarTodos()) {
            if (contra.getEstado().toString().equalsIgnoreCase(estado)) {
                resultado.add(contra);
            }
        }
        return resultado;
    }
    
    public ArrayList<Contrato>filtrarPorEspacio(int numeroEspacio){
        ArrayList<Contrato> resultado = new ArrayList<>();
        
        for (Contrato contra : listarTodos()) {
            if (contra.getEspacio() != null && contra.getEspacio().getNumero() ==numeroEspacio) {
               resultado.add(contra);
            }
        }
        return resultado;
    }
    
    public ArrayList<Contrato>filtrarPorFecha(Date fecha){
        ArrayList<Contrato> resultado = new ArrayList<>();
        
        for (Contrato contra : listarTodos()) {
            if (!fecha.before(contra.getFechaInicio()) && !fecha.after(contra.getFechaFinal())){
               resultado.add(contra);
            }
        }
        return resultado;
    }
    
    public ArrayList<Contrato>filtrarPorCliente(String texto){
         ArrayList<Contrato> resultado = new ArrayList<>();
         
         for (Contrato contra : listarTodos()) {
             if (contra.getCliente() == null && (contra.getCliente().getNombre().toLowerCase().contains(texto.toLowerCase())
                    || contra.getCliente().getIdentificacion().contains(texto))) {
                 resultado.add(contra);
             }
        }
         return resultado;
    }
    
}