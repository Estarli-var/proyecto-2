/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import estructuras.KeyDynamicsLists;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author estar
 */
public class ServiciosList implements KeyDynamicsLists<Servicio, Integer> {
    private ArrayList<Servicio>servicios;
    
    public ServiciosList() {
        this.servicios = new ArrayList<>();
    }
       
    @Override
    public boolean add(Servicio item) {
        if (get(item.getCodigo())!= null) {
            return false;
        }
        return servicios.add(item);
    }

    @Override
    public Iterator<Servicio> getAll() {
        return servicios.iterator();
    }

    @Override
    public int size() {
        return servicios.size();
    }

    @Override
    public boolean isEmpty() {
        return servicios.isEmpty();
    }
    
    @Override
    public Servicio get(Integer id) {
        for (Servicio servicio : servicios) {
            if (servicio.getCodigo() == id) {
                return servicio;
            }
        }
        return null;
    }
    
    @Override
    public boolean remove(Integer id) {
        Servicio servicio = get(id);
        if (servicio != null) {
            return servicios.remove(servicio);
        }
        return false;
    }
    
    public ArrayList<Servicio> getList() {
        return new ArrayList<>(servicios);
    }
    
    public ArrayList<Servicio> buscarPorTexto(String texto) {
        ArrayList<Servicio> resultados = new ArrayList<>();
        if (texto == null || texto.trim().isEmpty()) {
            return getList();
        }
        String filtro = texto.toLowerCase().trim();
        for (Servicio s : servicios) {
            if (s.getNombre().toLowerCase().contains(filtro) || 
                s.getDescripcion().toLowerCase().contains(filtro)) {
                resultados.add(s);
            }
        }
        return resultados;
    }
}
