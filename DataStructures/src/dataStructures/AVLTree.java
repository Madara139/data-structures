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
public class AVLTree {
     public NodeAVL raiz;
     
     public int height(NodeAVL N) {//funcion que calcula altura de un arbol
         if (N != null)
             return N.altura;
         return 0;
     }
     
     public int max(int a, int b) {//funcion que da el maximo de 2 enteros
         return (a > b) ? a : b;
     }
     
     public NodeAVL rightRotate (NodeAVL y) {
         //rotacion simple hacia la derecha de subarbol con raiz 'y'
         //arbol cargado a la izq t1-x-t2-y-t3-z-t4
         
         //rotacion
         NodeAVL x = y.izq;
         NodeAVL t2 = x.der;
         x.der = y;
         y.izq = t2;
         
         //actualizacion de alturas
        x.altura = max (height(x.izq), height(x.der)) + 1;
        y.altura = max (height(y.izq), height(y.der)) + 1;
         
         //nueva raiz del subarbol
         return x;
     }
     
     public NodeAVL leftRotate (NodeAVL x) {
         //rotacion simple hacia la izquierda de subarbol con raiz 'x'
         //arbol cargado a la der t1-x-t2-y-t3-z-t4
         
         //rotacion
         NodeAVL y = x.der;
         NodeAVL t2 = y.izq;
         y.izq = x;
         x.der = t2;
         
         //actualizacion de alturas
         x.altura = max (height(x.izq), height(x.der)) + 1;
         y.altura = max (height(y.izq), height(y.der)) + 1;
         
         //nueva raiz del subarbol
         return y;
     }
     
     public int getBalance(NodeAVL N) {//funcion que da el factor de equilibrio
         //si es positivo significa cargado a la izq, negativo es a la derecha
         if (N != null)
             return (height(N.izq) - height(N.der));
         return 0;
     }
     
     public void insert (int dato) { 
        raiz = insert(raiz, dato);
     }

    private NodeAVL insert(NodeAVL nodo, int dato) {
        //caso base
        if (nodo == null)
            return new NodeAVL (dato);
        if (dato < nodo.dato) //insercion como en binarySearchTree
            nodo.izq = insert(nodo.izq, dato);
        else
            nodo.der = insert(nodo.der, dato);
        
        //actualizacion de altura del nodo padre
        nodo.altura = max(height(nodo.izq), height(nodo.der)) + 1;
        //se verifica si el nodo padre esta desbalanceado
        int balance = getBalance(nodo);
        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && dato < nodo.izq.dato) 
            return rightRotate(nodo); 
  
        // Right Right Case 
        if (balance < -1 && dato > nodo.der.dato) 
            return leftRotate(nodo); 
  
        // Left Right Case 
        if (balance > 1 && dato > nodo.izq.dato) { 
            nodo.izq = leftRotate(nodo.izq); 
            return rightRotate(nodo); 
        } 
  
        // Right Left Case 
        if (balance < -1 && dato < nodo.der.dato) { 
            nodo.der = rightRotate(nodo.der); 
            return leftRotate(nodo); 
        } 
  
        /* return the (unchanged) node pointer */
        return nodo; 
    }
    
    public void inorder() 
    { 
        inorder(raiz); 
    } 
  
    private void inorder(NodeAVL nodo) 
    { 
        if (nodo != null) 
        { 
            inorder(nodo.izq); 
            System.out.print(nodo.dato + " "); 
            inorder(nodo.der); 
        } 
    } 
    
    public void preorder () {
        preorder(raiz);
    }
    
    private void preorder(NodeAVL nodo) { 
        if (nodo != null) { 
            System.out.print(nodo.dato + " "); 
            preorder(nodo.izq); 
            preorder(nodo.der); 
        } 
    } 
    
    public void delete (int dato) {
            raiz = delete (raiz, dato);
    }

    private NodeAVL delete(NodeAVL nodo, int dato) {
        if (nodo == null)
            return nodo;
        if (dato < nodo.dato)
            nodo.izq = delete(nodo.izq, dato);
        else if (dato > nodo.dato)
            nodo.der = delete(nodo.der, dato);
        else {
            if (nodo.izq == null) //caso 1 hijo der
                return nodo.der;
            else if (nodo.der == null) //caso 2 hijos o 1 hijo izq
                return nodo.izq;
            //caso 4 no hijos
            nodo.dato = min(nodo.der);
            nodo.der = delete(nodo.der, nodo.dato);
        }
        
        if (nodo == null)  
            return nodo;
        //actualizo la altura
        nodo.altura = max(height(nodo.der), height(nodo.izq)) + 1;
        
        //calculo factor de equilibrio
        int balance = getBalance(nodo);
        
        
        
        // caso izq izq 
        if (balance > 1 && getBalance(nodo.izq) >= 0)  
            return rightRotate(nodo);  
  
        // caso izq der 
        if (balance > 1 && getBalance(nodo.izq) < 0)  
        {  
            nodo.izq = leftRotate(nodo.izq);  
            return rightRotate(nodo);  
        }  
  
        // caso der der
        if (balance < -1 && getBalance(nodo.der) <= 0)  
            return leftRotate(nodo);  
  
        // caso der izq
        if (balance < -1 && getBalance(nodo.der) > 0)  
        {  
            nodo.der = rightRotate(nodo.der);  
            return leftRotate(nodo);  
        }  
  
        return nodo;  
    }
    
    private int min(NodeAVL nodo) { //la funcion encuentra el valor mini
        //mo del subarbol der
        int minimo = nodo.dato;
        while (nodo.izq != null) {
            minimo = nodo.izq.dato;
            nodo = nodo.izq;
        }
        return minimo;
    }
}
