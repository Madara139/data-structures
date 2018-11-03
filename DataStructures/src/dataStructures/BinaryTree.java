/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

/**
 *
 * @author juanh
 * 
 * arbol binario de busqueda donde para todo nodo en el se cumple que 
 * los valores almacenados en su subarbol izq son menores
 * y los almacenados en el subarbol der son mayores
 * este arbol no admitira valores repetidos y trabajara con enteros
 * sus atributos seran publicos
 */
public class BinaryTree {
    public NodeB raiz;
    
    public BinaryTree (){
        raiz = null;
    }
    
    public BinaryTree (int dato) {
        raiz = new NodeB (dato);
    }
    
    public void insert (int dato) {
        if (raiz == null) 
            raiz = new NodeB (dato);
        else
            insert (raiz, dato);
    }
    
    private NodeB insert(NodeB nodo, int dato) {
        if (nodo == null) {
            nodo = new NodeB (dato);
            return nodo;
        } else if (dato < nodo.dato)
            nodo.izq = insert (nodo.izq, dato);
        else if (dato > nodo.dato)
            nodo.der = insert (nodo.der, dato);
        return nodo;
    }
    
    
    
    
}

    
