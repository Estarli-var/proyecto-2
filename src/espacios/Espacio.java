/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espacios;

public class Espacio {
    private int numero;
    private TipoHabitacion tipo;
    private double tamano;
    private double precio;
    private boolean ocupado;

    public Espacio(int numero, TipoHabitacion tipo){
        this.numero = numero;
        this.tipo = tipo;
        this.tamano = tipo.getTamano();
        this.precio = tipo.getPrecio();
        this.ocupado = false; 
    }

    public Espacio(int numero, TipoHabitacion tipo, double tamano, double precio) {
        this.numero = numero;
        this.tipo = tipo;
        this.tamano = tamano;
        this.precio = precio;
        this.ocupado = false;
    }

    public int getNumero() {
        return numero;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public double getTamano() {
        return tamano;
    }

    public double getPrecio() {
        return precio;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public void setTamano(double tamano) {
        this.tamano = tamano;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isOcupado(){ 
        return ocupado; 
    }
    
    public void setOcupado(boolean ocupado){
        this.ocupado = ocupado; 
    }
    
}
