/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enums;

/**
 *
 * @author estar
 */
public enum PuestoEmpleado {
    ADMINISTRADOR("Administrador", 950000),
    ENCARGADO_BODEGA("Encargado de bodega", 700000),
    MANTENIMIENTO("Mantenimiento", 650000),
    RECEPCIONISTA("Recepcionista", 600000),
    OPERARIO_CARGA("Operario de carga", 575000);
    
    private final String nombre;
    private final double salarioBase;

    public String getNombre() {
        return nombre;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    private PuestoEmpleado(String nombre, double salarioBase) {
        this.nombre = nombre;
        this.salarioBase = salarioBase;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
    
}
