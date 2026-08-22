/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espacios;

import java.util.ArrayList;

/**
 *
 * @author emalv
 */
public class ControladorEspacio {
    private ArrayList<Espacio> listaEspacio;

    public ControladorEspacio() {
        this.listaEspacio = new ArrayList<>();
    }
    
    public Espacio buscarNumero(int numero){
        for (Espacio espa : listaEspacio){
            if (espa.getNumero() == numero){
                return espa;
            }
        }
        return null;
    }
    
    public void agregarEspacio(int numero, TipoHabitacion tipo, double tamano, double precio)throws Exception{
        Espacio buscado = buscarNumero(numero);
        if (buscado != null){
            throw new Exception("Numero de espacio ocupado");
        }
            Espacio nuevo = new Espacio(numero,tipo,tamano,precio);
            listaEspacio.add(nuevo);        
    }
    
    public void actualizarEspacio(int numero, TipoHabitacion tipo, double tamano, double precio)throws Exception{
        Espacio actualizado = buscarNumero(numero);
        if (actualizado == null){
            throw new Exception("El espacio no existe");
        }
        actualizado.setTipo(tipo);
        actualizado.setTamano(tamano);
        actualizado.setPrecio(precio);
    }
    
    public void eliminarEspacio(int numero)throws Exception{
        Espacio eliminado = buscarNumero(numero);
        if (eliminado == null ){
            throw new Exception("El espacio no existe");
        }
        if (eliminado.isOcupado() == true){
            throw new Exception("No se puede eliminar un espacio ocupado");
        }
        listaEspacio.remove(eliminado);
    }
    
    public ArrayList<Espacio> getListadeEspacios(){
        return listaEspacio;
    }
    
    public ArrayList<Espacio> filtrarPorTipo(TipoHabitacion tipo) {
    ArrayList<Espacio> resultado = new ArrayList<>();
    for (Espacio espa : listaEspacio) {
        if (espa.getTipo() == tipo) {
            resultado.add(espa);
        }
    }
    return resultado;
    }
}
