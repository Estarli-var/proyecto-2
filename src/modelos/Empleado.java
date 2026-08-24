/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import Enums.PuestoEmpleado;

/**
 *
 * @author estar
 */
public class Empleado {
    private String cedula;
    private String nombre;
    private String telefono;
    private PuestoEmpleado puesto;
    private double salario;
    
    public void setPuesto(PuestoEmpleado puesto) {
        this.puesto = puesto;
        this.salario = puesto.getSalarioBase();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public Empleado(String cedula, String nombre, String telefono, PuestoEmpleado puesto) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        setPuesto(puesto);
    }
}
