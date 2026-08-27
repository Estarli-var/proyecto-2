/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lists;

import estructuras.KeyDynamicsLists;
import java.util.Iterator;
import java.util.LinkedList;
import modelos.Empleado;

/**
 *
 * @author estar
 */
public class EmpleadoList implements KeyDynamicsLists<Empleado, String>{
   private final LinkedList<Empleado> empleados; 
   
   public EmpleadoList() {
        this.empleados = new LinkedList<>();
    }
   
   @Override
    public boolean add(Empleado item) {
        if (item == null || item.getCedula() == null) {
            return false;
        }
        if (get(item.getCedula()) != null) {
            return false;
        }
        return empleados.add(item);
    }

    @Override
    public Iterator<Empleado> getAll() {
        return empleados.iterator();
    }

    @Override
    public int size() {
        return empleados.size();
    }

    @Override
    public boolean isEmpty() {
        return empleados.isEmpty();
    }

    @Override
    public Empleado get(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return null;
        }
        for (Empleado emp : empleados) {
            if (emp.getCedula().equalsIgnoreCase(cedula.trim())) {
                return emp;
            }
        }
        return null;
    }

    @Override
    public boolean remove(String cedula) {
        Empleado emp = get(cedula);
        if (emp != null) {
            return empleados.remove(emp);
        }
        return false;
    }
    
    public LinkedList<Empleado> getList() {
        return new LinkedList<>(empleados);
    }
    
    public LinkedList<Empleado> buscarPorTexto(String texto) {
        LinkedList<Empleado> resultados = new LinkedList<>();
        if (texto == null || texto.trim().isEmpty()) {
            return getList();
        }
        
        String filtro = texto.toLowerCase().trim();
        for (Empleado emp : empleados) {
            String cedulaStr = (emp.getCedula() != null) ? emp.getCedula().toLowerCase() : "";
            String nombreStr = (emp.getNombre() != null) ? emp.getNombre().toLowerCase() : "";
            
            if (cedulaStr.contains(filtro) || nombreStr.contains(filtro)) {
                resultados.add(emp);
            }
        }
        return resultados;
    }
}
