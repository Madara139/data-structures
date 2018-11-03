/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

/**
 *
 * @author juanh
 */
public class NodeAVL {
    public int dato, altura;
    public NodeAVL izq, der;
    
    public NodeAVL(int dato) {
        this.dato = dato;
        izq = der = null;
        altura = 1;
    }

    public int getDato() {
        return dato;
    }
    
    @Override
    public String toString() {
        return dato + "";
    }
}
