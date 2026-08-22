/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;

import modelos.Contrato;
import estructuras.KeyDynamicsLists;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Aaron Diaz
 */
public class contratoList implements KeyDynamicsLists<Contrato, Integer>{
      HashMap<Integer,Contrato> contratos;

    public contratoList() {
        this.contratos = new HashMap<>();
    }
    public Contrato get(Integer numero){
        return contratos.get(numero);
    }
    
    public boolean remove(Integer numero){
        Contrato contra = this.get(numero);
        if (contra == null) {
            return false;
        }
        return contratos.remove(numero) != null;
    }
    
    public boolean add(Contrato item){
        if (contratos.containsKey(item.getNumeroContrato())) {
            return false;
        }
        return contratos.put(item.getNumeroContrato(), item) == null;
    }
    
      @Override
    public Iterator getAll(){
        if (contratos.isEmpty()) {
            return null;
        }
        return contratos.values().iterator();
    }
    
      @Override
    public int size(){
        return contratos.size();
    }
    
      @Override
    public boolean isEmpty(){
        return contratos.isEmpty();
    }
}