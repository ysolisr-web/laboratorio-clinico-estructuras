public class Pila {
    private Nodo tope;
    private int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    // 1. Apilar (insertar en la cima de la pila)
    public void apilar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (!esVacia()) {
            nuevo.setDerecha(tope);
            tope.setIzquierda(nuevo);
        }
        tope = nuevo;
        tamanio++;
    }

    // 2. Desapilar (sacar el elemento más reciente de la cima)
    public Object desapilar() {
        if (esVacia()) {
            return null;
        }
        Object dato = tope.getDato();
        tope = tope.getDerecha();
        if (tope != null) {
            tope.setIzquierda(null);
        }
        tamanio--;
        return dato;
    }

    // 3. Peek (ver el último procesado sin sacarlo)
    public Object peek() {
        if (esVacia()) {
            return null;
        }
        return tope.getDato();
    }

    // 4. Tamaño actual de la pila
    public int tamanio() {
        return tamanio;
    }

    // 5. Verificar si está vacía
    public boolean esVacia() {
        return tope == null;
    }

    // 6. Verificar si contiene un dato
    public boolean contiene(Object dato) {
        return buscar(dato) != -1;
    }

    // 7. Vaciar la pila
    public void limpiar() {
        tope = null;
        tamanio = 0;
    }

    // 8. Mostrar historial desde el más reciente hasta el más antiguo
    public void mostrar() {
        if (esVacia()) {
            System.out.println("El historial de muestras procesadas está vacío.");
            return;
        }
        Nodo actual = tope;
        int i = 1;
        while (actual != null) {
            System.out.println(i + ". " + actual.getDato());
            actual = actual.getDerecha();
            i++;
        }
    }

    // 9. Buscar posición desde el tope (1 = cima, -1 = no encontrado)
    public int buscar(Object dato) {
        Nodo actual = tope;
        int posicion = 1;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return posicion;
            }
            actual = actual.getDerecha();
            posicion++;
        }
        return -1;
    }
}