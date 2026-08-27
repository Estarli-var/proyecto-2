/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import modelos.Cliente;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

/**
 *
 * @author Zachary Ruiz
 */
public class ControladorCliente {

    private static ControladorCliente instancia;

    private final ArrayList<Cliente> clientes;

    // Constructor privado
    private ControladorCliente() {
        clientes = new ArrayList<>();
    }

    // Método para obtener la única instancia
    public static ControladorCliente getInstancia() {
        if (instancia == null) {
            instancia = new ControladorCliente();
        }

        return instancia;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public int calcularEdad(Cliente cliente) {

        LocalDate fechaNacimiento = cliente.getFechaNacimiento();

        if (fechaNacimiento == null) {
            throw new IllegalArgumentException(
                "La fecha de nacimiento es obligatoria."
            );
        }

        LocalDate hoy = LocalDate.now();

        if (fechaNacimiento.isAfter(hoy)) {
            throw new IllegalArgumentException(
                "La fecha no puede ser futura"
            );
        }

        return Period.between(fechaNacimiento, hoy).getYears();
    }

    public boolean validarIdentificacion(String identificacion) {
        Cliente cliente = buscarCliente(identificacion);
        return cliente != null;
    }

    public Cliente buscarCliente(String identificacion) {
        try {
            for (Cliente cliente : clientes) {
                if (cliente.getIdentificacion().equals(identificacion)) {
                    return cliente;
                }
            }

            throw new Exception("No se encontro el cliente");

        } catch (Exception e) {
            return null;
        }
    }

    public void actualizarCliente(
            String identificacion,
            int numero,
            String nombre,
            String correo) {

        Cliente cliente = buscarCliente(identificacion);

        cliente.setNumero(numero);
        cliente.setCorreo(correo);
        cliente.setNombre(nombre);
    }

    public void quitarCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }
}
