public class Lista {
    private Nodo inicio;
    private Nodo fin;
    private int tamanio;

    public Lista() {
        this.inicio = null;
        this.fin = null;
        this.tamanio = 0;
    }

    public boolean esVacia() {
        return inicio == null;
    }

    // 1. Agregar al final
    public void agregar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.setDerecha(nuevo);
            nuevo.setIzquierda(fin);
            fin = nuevo;
        }
        tamanio++;
    }

    // 2. Agregar al inicio
    public void agregarAlInicio(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setDerecha(inicio);
            inicio.setIzquierda(nuevo);
            inicio = nuevo;
        }
        tamanio++;
    }

    // 3. Agregar en posición específica (base 0)
    public void agregarEnPosicion(int indice, Object dato) {
        if (indice < 0 || indice > tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        if (indice == 0) {
            agregarAlInicio(dato);
        } else if (indice == tamanio) {
            agregar(dato);
        } else {
            Nodo nuevo = new Nodo(dato);
            Nodo actual = inicio;
            for (int i = 0; i < indice; i++) {
                actual = actual.getDerecha();
            }
            Nodo anterior = actual.getIzquierda();
            anterior.setDerecha(nuevo);
            nuevo.setIzquierda(anterior);
            nuevo.setDerecha(actual);
            actual.setIzquierda(nuevo);
            tamanio++;
        }
    }

    // 4. Eliminar el primer elemento
    public Object eliminarPrimero() {
        if (esVacia()) {
            return null;
        }
        Object datoEliminado = inicio.getDato();
        if (inicio == fin) {
            inicio = null;
            fin = null;
        } else {
            inicio = inicio.getDerecha();
            inicio.setIzquierda(null);
        }
        tamanio--;
        return datoEliminado;
    }

    // 5. Eliminar el último elemento
    public Object eliminarUltimo() {
        if (esVacia()) {
            return null;
        }
        Object datoEliminado = fin.getDato();
        if (inicio == fin) {
            inicio = null;
            fin = null;
        } else {
            fin = fin.getIzquierda();
            fin.setDerecha(null);
        }
        tamanio--;
        return datoEliminado;
    }

    // 6. Eliminar en posición específica (base 0)
    public Object eliminarEnPosicion(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        if (indice == 0) {
            return eliminarPrimero();
        }
        if (indice == tamanio - 1) {
            return eliminarUltimo();
        }
        Nodo actual = inicio;
        for (int i = 0; i < indice; i++) {
            actual = actual.getDerecha();
        }
        Object datoEliminado = actual.getDato();
        Nodo anterior = actual.getIzquierda();
        Nodo siguiente = actual.getDerecha();
        anterior.setDerecha(siguiente);
        siguiente.setIzquierda(anterior);
        tamanio--;
        return datoEliminado;
    }

    // 7. Buscar dato por índice
    public Object buscarDato(int indice) {
        if (indice < 0 || indice >= tamanio) {
            return null;
        }
        Nodo actual = inicio;
        for (int i = 0; i < indice; i++) {
            actual = actual.getDerecha();
        }
        return actual.getDato();
    }

    // 8. Buscar dato por objeto (usando equals)
    public Object buscarDato(Object dato) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual.getDato();
            }
            actual = actual.getDerecha();
        }
        return null;
    }

    // 9. Verificar si contiene un dato
    public boolean contiene(Object dato) {
        return buscarDato(dato) != null;
    }

    // 10. Contar elementos
    public int cuentaElementos() {
        return tamanio;
    }

    // 11. Limpiar toda la lista
    public void limpiar() {
        inicio = null;
        fin = null;
        tamanio = 0;
    }

    // 12. Mostrar de inicio a fin
    public void mostrarAdelante() {
        if (esVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo actual = inicio;
        int i = 1;
        while (actual != null) {
            System.out.println(i + ". " + actual.getDato());
            actual = actual.getDerecha();
            i++;
        }
    }

    // 13. Mostrar de fin a inicio
    public void mostrarAtras() {
        if (esVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo actual = fin;
        int i = tamanio;
        while (actual != null) {
            System.out.println(i + ". " + actual.getDato());
            actual = actual.getIzquierda();
            i--;
        }
    }
}