public class Cola {
    private Nodo frente;
    private Nodo fin;
    private int tamanio;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    // 1. Encolar (agregar al final de la cola)
    public void encolar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setDerecha(nuevo);
            nuevo.setIzquierda(fin);
            fin = nuevo;
        }
        tamanio++;
    }

    // 2. Desencolar (atender y sacar el primero que llegó)
    public Object desencolar() {
        if (esVacia()) {
            return null;
        }
        Object dato = frente.getDato();
        frente = frente.getDerecha();
        if (frente != null) {
            frente.setIzquierda(null);
        } else {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    // 3. Peek (ver el siguiente en turno sin sacarlo)
    public Object peek() {
        if (esVacia()) {
            return null;
        }
        return frente.getDato();
    }

    // 4. Tamaño actual de la cola
    public int tamanio() {
        return tamanio;
    }

    // 5. Verificar si está vacía
    public boolean esVacia() {
        return frente == null;
    }

    // 6. Verificar si contiene un elemento
    public boolean contiene(Object dato) {
        Nodo actual = frente;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getDerecha();
        }
        return false;
    }

    // 7. Vaciar la cola
    public void limpiar() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    // 8. Mostrar todos los elementos en orden de atención
    public void mostrar() {
        if (esVacia()) {
            System.out.println("No hay muestras pendientes en la cola.");
            return;
        }
        Nodo actual = frente;
        int turno = 1;
        while (actual != null) {
            System.out.println("Turno #" + turno + " -> " + actual.getDato());
            actual = actual.getDerecha();
            turno++;
        }
    }
}

