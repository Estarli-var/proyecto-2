/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorServicio;

import excepciones.SException;
import java.util.ArrayList;
import modelos.Servicio;
import modelos.ServiciosList;

/**
 *
 * @author estar
 */
public class controladorServicios {
    private ServiciosList serviciosList;
    public controladorServicios() {
        this.serviciosList = new ServiciosList();
    }
    public controladorServicios(ServiciosList serviciosList) {
        this.serviciosList = serviciosList;
    }


    public Servicio agregarServicio(String nombre, String descripcion, String precioStr) throws SException {
        if (nombre == null || nombre.trim().isEmpty() || 
            descripcion == null || descripcion.trim().isEmpty() || 
            precioStr == null || precioStr.trim().isEmpty()) {
            throw new SException("Todos los campos son obligatorios.");
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr.trim());
        } catch (NumberFormatException e) {
            throw new SException("El precio debe ser un valor numérico válido.");
        }

        if (precio <= 0) {
            throw new SException("El precio debe ser mayor a 0.");
        }

        Servicio nuevoServicio = new Servicio(nombre.trim(), descripcion.trim(), precio);
        if (!serviciosList.add(nuevoServicio)) {
            throw new SException("No se pudo agregar el servicio.");
        }

        return nuevoServicio;
    }

    public void actualizarServicio(int codigo, String nombre, String descripcion, String precioStr) throws SException {
        Servicio servicioExistente = serviciosList.get(codigo);
        if (servicioExistente == null) {
            throw new SException("No se encontró el servicio con el código " + codigo);
        }

        if (nombre == null || nombre.trim().isEmpty() || 
            descripcion == null || descripcion.trim().isEmpty() || 
            precioStr == null || precioStr.trim().isEmpty()) {
            throw new SException("Todos los campos son obligatorios.");
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr.trim());
        } catch (NumberFormatException e) {
            throw new SException("El precio debe ser un valor numérico válido.");
        }

        if (precio <= 0) {
            throw new SException("El precio debe ser mayor a 0.");
        }

        servicioExistente.setNombre(nombre.trim());
        servicioExistente.setDescripcion(descripcion.trim());
        servicioExistente.setPrecio(precio);
    }

    public void eliminarServicio(int codigo) throws SException {
        Servicio servicioExistente = serviciosList.get(codigo);
        if (servicioExistente == null) {
            throw new SException("El servicio con código " + codigo + " no existe.");
        }

        boolean eliminado = serviciosList.remove(codigo);
        if (!eliminado) {
            throw new SException("Error al eliminar el servicio de la lista.");
        }
    }

// para concultar
    public Servicio buscarServicio(int codigo) {
        return serviciosList.get(codigo);
    }

    public ArrayList<Servicio> obtenerTodos() {
        return serviciosList.getList();
    }

    public ArrayList<Servicio> filtrarServicios(String texto) {
        return serviciosList.buscarPorTexto(texto);
    }
}
