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
            raiz = insert (raiz, dato);
    }
    
    private NodeB insert(NodeB nodo, int dato) {
        if (nodo == null) { //caso base
            nodo = new NodeB (dato);
            return nodo;
        } else if (dato < nodo.dato)
            nodo.izq = insert (nodo.izq, dato);
        else if (dato > nodo.dato)
            nodo.der = insert (nodo.der, dato);
        return nodo;//regresa nodo no cambiado
    }
    
    public void delete (int dato) {
        raiz = delete (raiz, dato);
    }

    private NodeB delete(NodeB nodo, int dato) {
        if (nodo == null) //caso base
            return nodo;
        if (dato < nodo.dato)
            nodo.izq = delete (nodo.izq, dato);
        else if (dato > nodo.dato)
            nodo.der = delete (nodo.der, dato);
        else {//nodo a eliminar
            if (nodo.izq == null)//caso 1 hijo der
                return nodo.der;
            else if (nodo.der == null) //caso 2 hijo izq, caso 3 hoja
                return nodo.izq;
            //caso 4 dos hijos
            //como hay dos hijos tomo el minimo del subarbol der
            //y cambio el valor del nodo a eliminar por el minimo
            nodo.dato = valorMinimo(nodo.der);
            //ahora el nodo esta duplicado entonces continuo por la derecha
            //hasta encontrar el duplicado (minimo original) que al ser minimo 
            //solo tendra 1 o 0 hijos y sera eliminado mas facilmente
            nodo.der = delete(nodo.der, nodo.dato);
        }
        return nodo;
    }
    
    private int valorMinimo (NodeB nodo) { //la funcion encuentra el valor mini
        //mo del subarbol der
        int minimo = nodo.dato;
        while (nodo.izq != null) {
            minimo = nodo.izq.dato;
            nodo = nodo.izq;
        }
        return minimo;
    }
    
    public void inorder() 
    { 
        inorder(raiz); 
    } 
  
    private void inorder(NodeB nodo) 
    { 
        if (nodo != null) 
        { 
            inorder(nodo.izq); 
            System.out.print(nodo.dato + " "); 
            inorder(nodo.der); 
        } 
    } 
  
}

    
