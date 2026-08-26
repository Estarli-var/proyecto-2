/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import modelos.Espacio;
import Enums.TipoHabitacion;
import estructuras.KeyDynamicsLists;
import lists.EspaciosList;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author emalv
 */
public class ControladorEspacio {
    
    private KeyDynamicsLists<Espacio, Integer> listaEspacio;

    public ControladorEspacio(KeyDynamicsLists<Espacio, Integer> listaEspacio) {
        this.listaEspacio = listaEspacio;
    }

    public Espacio buscarNumero(int numero) {
        return listaEspacio.get(numero);
    }

    public void agregarEspacio(int numero, TipoHabitacion tipo, double tamano, double precio) throws Exception {
        Espacio buscado = buscarNumero(numero);
        if (buscado != null) {
            throw new Exception("El número de espacio se encuentra ocupado.");
        }
        
        Espacio nuevo = new Espacio(numero, tipo, tamano, precio);
        listaEspacio.add(nuevo);
    }

    public void actualizarEspacio(int numero, TipoHabitacion tipo, double tamano, double precio) throws Exception {
        Espacio actualizado = buscarNumero(numero);
        if (actualizado == null) {
            throw new Exception("El espacio no existe.");
        }
        actualizado.setTipo(tipo);
        actualizado.setTamano(tamano);
        actualizado.setPrecio(precio);
    }

    public void eliminarEspacio(int numero) throws Exception {
        Espacio eliminado = buscarNumero(numero);
        
        if (eliminado == null) {
            throw new Exception("El espacio no existe.");
        }
        
        if (eliminado.isOcupado()) {
            throw new Exception("No se puede eliminar un espacio ocupado.");
        }
        
        listaEspacio.remove(numero);
    }

    public KeyDynamicsLists<Espacio, Integer> getListaEspacio() {
        return listaEspacio;
    }

    public ArrayList<Espacio> filtrarPorTipo(TipoHabitacion tipo) {
        ArrayList<Espacio> resultado = new ArrayList<>();
        Iterator<Espacio> it = listaEspacio.getAll();
        
        while (it.hasNext()) {
            Espacio espa = it.next();
            if (espa.getTipo() == tipo) {
                resultado.add(espa);
            }
        }
        
        return resultado;
    }

    public ArrayList<Espacio> buscarPorTexto(String texto) {
        if (listaEspacio instanceof EspaciosList) {
            return ((EspaciosList) listaEspacio).buscarPorTexto(texto);
        }
        
        ArrayList<Espacio> resultado = new ArrayList<>();
        Iterator<Espacio> it = listaEspacio.getAll();
        String filtro = (texto == null) ? "" : texto.toLowerCase().trim();
        
        while (it.hasNext()) {
            Espacio e = it.next();
            if (filtro.isEmpty() || 
                String.valueOf(e.getNumero()).contains(filtro) || 
                (e.getTipo() != null && e.getTipo().name().toLowerCase().contains(filtro))) {
                resultado.add(e);
            }
        }
        return resultado;
    }
}