/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;
import excepciones.fechaInvalidaException;
import excepciones.cambioEstadoInvalidoException;
import Enums.EstadoContrato;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import modelos.Cliente;
import modelos.Servicio;
/**
 *
 * @author Aaron Diaz
 */
public class Contrato {
    private static int contador = 1;
    private double impuest = 0.13;
    private int NumeroContrato;
    private Date FechaInicio;
    private Date FechaFinal;
    private EstadoContrato Estado;
    
    private Cliente cliente;
    private Espacio espacio;
    private ArrayList<Servicio> servicios;
    
    private double SubTotal;
    private double Impuesto;
    private double Total;
    
    public Contrato(Date fechaInicio, Date fechaFin) throws fechaInvalidaException {
    validarFechas(fechaInicio, fechaFin);
    this.NumeroContrato = contador++;
    this.FechaInicio = fechaInicio;
    this.FechaFinal = fechaFin;
    this.Estado = EstadoContrato.PENDIENTE;
    this.servicios = new ArrayList<>();
    this.SubTotal = 0;
    this.Impuesto = 0;
    this.Total = 0;
    }
    private void validarFechas(Date Inicio, Date Fin) throws fechaInvalidaException{
        if (Inicio == null || Fin == null) {
            throw new fechaInvalidaException("La fecha no pueden estar vacías");
        }
        if (!Fin.after(Inicio)) {
            throw new fechaInvalidaException("La fecha final debe ser posterior a la fecha de inicio.");
        }
    }
    
    public void asignarCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
    public Cliente getCliente(){
        return cliente;
    }
    
    public void asignarEspacio(Espacio espacio){
        this.espacio = espacio;
    }
    
    public Espacio getEspacio(){
        return espacio;
    }
    
    public int calcularDias(){
    LocalDate Inicio = FechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate Fin = FechaFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return (int) ChronoUnit.DAYS.between(Inicio, Fin);
    }
    
    public int calcularPeriodos(){
        int dias = calcularDias();
        return (int)Math.ceil(dias / 30.0);
    }
    
    public void calcularCostos(){
    double precioServicios = 0;
    double precioEspacio = 0;
        for (Servicio serv : servicios) {
            precioServicios += serv.getPrecio();
        }
        if (espacio != null) {
            precioEspacio = espacio.getPrecio() * calcularPeriodos();
        }
        
        double totalConImpuesto = precioServicios + precioEspacio;
        this.SubTotal = totalConImpuesto / (1 + impuest);
        this.Impuesto = totalConImpuesto - this.SubTotal;
        this.Total = totalConImpuesto;
    }
    
    public boolean agregarServicio(Servicio servi){
        return servicios.add(servi);
    }
    
    public boolean quitarServicio(Servicio servi){
        return servicios.remove(servi);
    }
    
    public ArrayList<Servicio> getServicios(){
        return servicios;
    }
    
    public void activar() throws cambioEstadoInvalidoException{
        if (Estado != EstadoContrato.PENDIENTE) {
            throw new cambioEstadoInvalidoException("Solo un contrato Pendiente puede activarse. Estado actual: "
            + Estado);
        }
        this.Estado = EstadoContrato.ACTIVO;
        if (espacio != null) {
            espacio.setOcupado(true);
        }
    }
        
    public void finalizar() throws cambioEstadoInvalidoException{
        if (Estado != EstadoContrato.ACTIVO) {
            throw new cambioEstadoInvalidoException("Solo un contrato Activo puede finalizarse. Estado actual: "
            + Estado);
        }
        this.Estado = EstadoContrato.FINALIZADO;
        if (espacio != null) {
            espacio.setOcupado(false);
        }
    }
    
    public void cancelar() throws cambioEstadoInvalidoException{
        if (Estado != EstadoContrato.PENDIENTE) {
            throw new cambioEstadoInvalidoException("Solo un contrato Pendiente puede cancelarse. Estado actual: "
            + Estado);
        }
        this.Estado = EstadoContrato.CANCELADO;
          if (espacio != null) {
            espacio.setOcupado(false);
        }
    }
    
    public int getNumeroContrato() {
        return NumeroContrato;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public Date getFechaFinal() {
        return FechaFinal;
    }

    public EstadoContrato getEstado() {
        return Estado;
    }

    public double getSubTotal() {
        return SubTotal;
    }

    public double getImpuesto() {
        return Impuesto;
    }

    public double getTotal() {
        return Total;
    }
}