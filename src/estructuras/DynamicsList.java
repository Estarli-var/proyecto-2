/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package estructuras;

import java.util.Iterator;

/**
 *
 * @author estar
 * @param <T>
 */
public interface DynamicsList<T> {
    public boolean add(T item);
    public Iterator<T> getAll();
    public int size();
    public boolean isEmpty();
}
