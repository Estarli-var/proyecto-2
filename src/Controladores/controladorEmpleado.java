/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Enums.PuestoEmpleado;
import java.util.LinkedList;
import lists.EmpleadoList;
import modelos.Empleado;

/**
 *
 * @author estar
 */
public class controladorEmpleado {
    private final EmpleadoList listaEmpleados;
    
    public controladorEmpleado(EmpleadoList listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }
    
    public String[] obtenerNomDisp() {
        PuestoEmpleado[] puestos = PuestoEmpleado.values();
        String[] nombres = new String[puestos.length];
        for (int i = 0; i < puestos.length; i++) {
            nombres[i] = puestos[i].getNombre();
        }
        return nombres;
    }
    
    public String obtenerSalario(int indice) {
        PuestoEmpleado[] puestos = PuestoEmpleado.values();
        if (indice >= 0 && indice < puestos.length) {
            return String.format("₡%.2f", puestos[indice].getSalarioBase());
        }
        return "₡0.00";
    }
    
    public Object[][] obtenertodos() {
        return convertirMatriz(listaEmpleados.getList());
    }
    
    public Object[][] buscarTexto(String texto) {
        LinkedList<Empleado> lista = listaEmpleados.buscarPorTexto(texto);
        return convertirMatriz(lista);
    }
    
    public Object[] buscarEmpleados(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return null;
        }
        Empleado emp = listaEmpleados.get(cedula.trim());
        if (emp == null) {
            return null;
        }
        int indicePuesto = emp.getPuesto() != null ? emp.getPuesto().ordinal() : 0;
        return new Object[]{ emp.getCedula(), emp.getNombre(), emp.getTelefono(), indicePuesto };
    }
    private PuestoEmpleado obtenerPuesto(int indice) {
        PuestoEmpleado[] puestos = PuestoEmpleado.values();
        if (indice >= 0 && indice < puestos.length) {
            return puestos[indice];
        }
        return null;
    }

    private Object[][] convertirMatriz(LinkedList<Empleado> lista) {
        if (lista == null || lista.isEmpty()) {
            return new Object[0][5];
        }
        Object[][] matriz = new Object[lista.size()][5];
        for (int i = 0; i < lista.size(); i++) {
            Empleado emp = lista.get(i);
            matriz[i][0] = emp.getCedula();
            matriz[i][1] = emp.getNombre();
            matriz[i][2] = emp.getTelefono();
            matriz[i][3] = (emp.getPuesto() != null) ? emp.getPuesto().name() : "";
            matriz[i][4] = String.format("₡%.2f", emp.getSalario());
        }
        return matriz;
    }
    
    public void agregarEmpleado(String cedula, String nombre, String telefono, int indicePuesto) 
            throws IllegalArgumentException {
        PuestoEmpleado puesto = obtenerPuesto(indicePuesto);
        validarCamposObligatorios(cedula, nombre, telefono, puesto);
        
        if (listaEmpleados.get(cedula) != null) {
            throw new IllegalArgumentException("Ya existe un empleado registrado con la cédula: " + cedula);
        }
        Empleado nuevoEmpleado = new Empleado(cedula.trim(), nombre.trim(), telefono.trim(), puesto);
        boolean registrado = listaEmpleados.add(nuevoEmpleado);
        if (!registrado) {
            throw new IllegalArgumentException("No se pudo agregar el empleado al sistema.");
        }
    }
    
    public void actualizarEmpleado(String cedula, String nombre, String telefono, int indicePuesto) 
            throws IllegalArgumentException {
        PuestoEmpleado puesto = obtenerPuesto(indicePuesto);
        validarCamposObligatorios(cedula, nombre, telefono, puesto);

        Empleado emp = listaEmpleados.get(cedula);
        if (emp == null) {
            throw new IllegalArgumentException("No se encontró ningún empleado con la cédula: " + cedula);
        }

        emp.setNombre(nombre.trim());
        emp.setTelefono(telefono.trim());
        emp.setPuesto(puesto);
    }
    
    public void eliminarEmpleado(String cedula) throws IllegalArgumentException {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe indicar la cédula del empleado a eliminar.");
        }

        boolean eliminado = listaEmpleados.remove(cedula.trim());
        if (!eliminado) {
            throw new IllegalArgumentException("No se encontró ningún empleado registrado con la cédula: " + cedula);
        }
    }

    
    private void validarCamposObligatorios(String cedula, String nombre, String telefono, PuestoEmpleado puesto) {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("La cédula es un campo obligatorio.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es un campo obligatorio.");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es un campo obligatorio.");
        }
        if (puesto == null) {
            throw new IllegalArgumentException("Debe seleccionar un puesto de trabajo válido.");
        }
    }
}
