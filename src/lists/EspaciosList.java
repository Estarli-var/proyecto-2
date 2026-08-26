/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lists;

import estructuras.KeyDynamicsLists;
import java.util.ArrayList;
import java.util.Iterator;
import modelos.Espacio;
/**
 *
 * @author emalv
 */

public class EspaciosList implements KeyDynamicsLists<Espacio, Integer> {
    private ArrayList<Espacio> espacios;

    public EspaciosList() {
        this.espacios = new ArrayList<>();
    }

    @Override
    public boolean add(Espacio item) {
        if (get(item.getNumero()) != null) {
            return false;
        }
        return espacios.add(item);
    }

    @Override
    public Iterator<Espacio> getAll() {
        return espacios.iterator();
    }

    @Override
    public int size() {
        return espacios.size();
    }

    @Override
    public boolean isEmpty() {
        return espacios.isEmpty();
    }

    @Override
    public Espacio get(Integer id) {
        for (Espacio espacio : espacios) {
            if (espacio.getNumero() == id) {
                return espacio;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Integer id) {
        Espacio espacio = get(id);
        if (espacio != null) {
            return espacios.remove(espacio);
        }
        return false;
    }

    public ArrayList<Espacio> getList() {
        return new ArrayList<>(espacios);
    }

    public ArrayList<Espacio> buscarPorTexto(String texto) {
        ArrayList<Espacio> resultados = new ArrayList<>();
        if (texto == null || texto.trim().isEmpty()) {
            return getList();
        }
        String filtro = texto.toLowerCase().trim();
        for (Espacio e : espacios) {
            String numeroStr = String.valueOf(e.getNumero());
            String tipoStr = (e.getTipo() != null) ? e.getTipo().name().toLowerCase() : "";
            
            if (numeroStr.contains(filtro) || tipoStr.contains(filtro)) {
                resultados.add(e);
            }
        }
        return resultados;
    }
}