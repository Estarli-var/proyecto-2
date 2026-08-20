/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package estructuras;

/**
 *
 * @author estar
 */
public interface KeyDynamicsLists<T, K> extends DynamicsList<T>{
    public T get(K id);
    public boolean remove(K id);
}
