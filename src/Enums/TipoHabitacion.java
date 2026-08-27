/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enums;

/**
 *
 * @author emalv
 */
public enum TipoHabitacion {
    
    PEQUENO("Pequeño", 5.0, 25000),
    MEDIANO("Mediano", 10.0, 45000),
    GRANDE("Grande", 20.0, 70000);
    
    private final String nombre;
    private final double tamano;
    private final double precio;

    TipoHabitacion(String nombre, double tamano, double precio) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.precio = precio;
    }

    public String getNombre(){ 
        return nombre;
    }
    public double getTamano(){
        return tamano; 
    }
    public double getPrecio(){
        return precio; 
    }
    
    @Override
    public String toString() {
    return nombre; 
    }

}