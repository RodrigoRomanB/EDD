package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        public T elemento;
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nodo con un elemento. */
        public Nodo(T elemento) {
            this.elemento = elemento;// Aquí va su código.
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nuevo iterador. */
        public Iterador() {
            siguiente = cabeza;// Aquí va su código.
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return siguiente != null;// Aquí va su código.
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            if(!hasNext())
            throw new NoSuchElementException();
            else {
               T elem = siguiente.elemento;
               anterior = siguiente;
               siguiente = siguiente.siguiente;
               return elem;
            }// Aquí va su código.
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            return anterior != null;// Aquí va su código.
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            if(!hasPrevious()) throw new NoSuchElementException();
             else{
               T elem = anterior.elemento;
               siguiente = anterior;
               anterior = anterior.anterior;
               return elem;
             }// Aquí va su código.
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            anterior = null;
            siguiente = cabeza;// Aquí va su código.
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            siguiente = null;
            anterior = rabo;// Aquí va su código.
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;// Aquí va su código.
    }

    /**
     * Regresa el número elementos en la lista. El método es idéntico a {@link
     * #getLongitud}.
     * @return el número elementos en la lista.
     */
    @Override public int getElementos() {
        return longitud;// Aquí va su código.
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
        return longitud == 0;// Aquí va su código.
    }

    /**
     * Agrega un elemento a la lista. Si la lista no tiene elementos, el
     * elemento a agregar será el primero y último. El método es idéntico a
     * {@link #agregaFinal}.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
        if(elemento == null) throw new IllegalArgumentException("El elemento es null");
         Nodo nodo = new Nodo(elemento);
         if(esVacia()){
           cabeza = nodo;
           rabo = cabeza;
          cabeza.anterior = null;
           rabo.siguiente = null;
         }
         else {
          nodo.anterior = rabo;
           rabo.siguiente = nodo;
           rabo = nodo;
         }
         longitud++;// Aquí va su código.
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
         if(elemento == null) throw new IllegalArgumentException("El elemento es null");
         Nodo nodo = new Nodo(elemento);
         if(esVacia()){
           cabeza = nodo;
           rabo = cabeza;
          cabeza.anterior = null;
           rabo.siguiente = null;
         }
         else {
          nodo.anterior = rabo;
           rabo.siguiente = nodo;
           rabo = nodo;
         }
         longitud++;// Aquí va su código.
        }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if(elemento == null) throw new IllegalArgumentException("El elemento es null");
        Nodo nodo = new Nodo(elemento);
        if(esVacia())
        cabeza = rabo = nodo;
        else {
          nodo.siguiente = cabeza;
          cabeza.anterior = nodo;
          cabeza = nodo;
        }
        longitud++;// Aquí va su código.
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
         if(elemento == null) throw new IllegalArgumentException("El elemento es null");
         else if(i <= 0) agregaInicio(elemento);
         else if(longitud <= i) agregaFinal(elemento);
         else {
           Nodo nodo = getNodo(get(i));
           Nodo nuevoNodo = new Nodo(elemento);
 
           nodo.anterior.siguiente = nuevoNodo;
           nuevoNodo.anterior = nodo.anterior;
           nodo.anterior = nuevoNodo;
           nuevoNodo.siguiente = nodo;
           longitud++;
         }// Aquí va su código.
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        deleteNodo(getNodo(elemento));// Aquí va su código.
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        if(esVacia()) throw new NoSuchElementException("La lista es vacía");
        Nodo eliminado = cabeza;
        if(longitud == 1) limpia();
        else {
          cabeza = cabeza.siguiente;
          cabeza.anterior = null;
          longitud--;
        }
        return eliminado.elemento;        // Aquí va su código.
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
         if(esVacia()) throw new NoSuchElementException("La lista es vacía");
         Nodo eliminado = rabo;
         if(longitud == 1) limpia();
         else {
           rabo = rabo.anterior;
           rabo.siguiente = null;
           longitud--;
         }
         return eliminado.elemento;        // Aquí va su código.
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        return getNodo(elemento) != null;// Aquí va su código.
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
         Lista<T> lista = new Lista<T>();
         Nodo nodo = rabo;
         while (nodo != null) {
           lista.agregaFinal(nodo.elemento);
           nodo = nodo.anterior;
         }
         return lista;// Aquí va su código.
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
         Nodo nodo = cabeza;
         Lista <T> copiaLista = new Lista<T>();
         if(esVacia()) return copiaLista;
         while (nodo != null) {
           copiaLista.agregaFinal(nodo.elemento);
           nodo = nodo.siguiente;
         }
         return copiaLista;// Aquí va su código.
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    @Override public void limpia() {
        cabeza = rabo = null;
        longitud = 0;// Aquí va su código.
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        if(longitud != 0) return cabeza.elemento;
        else throw new NoSuchElementException("La lista es vacía");// Aquí va su código.
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        if(longitud != 0) return rabo.elemento;
        else throw new NoSuchElementException("La lista es vacía");// Aquí va su código.
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
         if(i < 0 || i >= longitud) throw new ExcepcionIndiceInvalido("El indice es inválido");
         Nodo n = cabeza;
         while (i-- > 0) {
           n = n.siguiente;
         }
         return n.elemento;        // Aquí va su código.
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
         Nodo n = cabeza;
         int indice = 0;
         while (n != null) {
           if(n.elemento.equals(elemento)) return indice;
           n = n.siguiente;
           indice++;
         }
         return -1;        // Aquí va su código.
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        if(esVacia()) return "[]";
        String s = "[";
        for (int i = 0; i < longitud-1; i++)
            s += String.format("%s, ", get(i));
        s += String.format("%s]", get(longitud-1));
        return s;        // Aquí va su código.
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
         if(lista == null) return false;
         else if(lista.getLongitud() != longitud) return false;
         else if(lista.getLongitud() == 0 && longitud == 0) return true;
         Nodo nodo = cabeza;
         int i = 0;
         while (nodo != null) {
           if(nodo.elemento.equals(lista.get(i)) == false) return false;
           nodo = nodo.siguiente;
           i++;
         }
         return true;// Aquí va su código.
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    /**
    * Método getNodo, regresa el nodo en donde se el elemento buscado.
    * @param e el elemento buscado.
    **/
    private Nodo getNodo(T e){
      Nodo n = cabeza;
      if(e == null) return null;
      while(n != null){
        if(n.elemento.equals(e)) return n;
        n = n.siguiente;
      }
      return null;
    }
    /**
    * Método deleteNodo elimina el nodo seleccionado.
    * @param n el nodo que se eliminará.
    **/
    private void deleteNodo(Nodo n){
       if(n == null) return;
       else if(longitud == 1 && cabeza.elemento.equals(n.elemento))limpia();
       else if(getNodo(n.elemento) == null) return;
       else if(n.equals(rabo)) eliminaUltimo();
       else if(n.equals(cabeza)) eliminaPrimero();
       else{
         n.anterior.siguiente = n.siguiente;
         n.siguiente.anterior = n.anterior;
         longitud--;
       }
     }
}
