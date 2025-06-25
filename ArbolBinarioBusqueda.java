class ArbolBinarioBusqueda {
    Nodo raiz;

    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    // Inserta una mascota en el arbol, ordenando por nombre
    void insertar(Mascota mascota) {
        raiz = insertarRec(raiz, mascota);
    }

    Nodo insertarRec(Mascota mascota, Nodo raiz ) {
        if (raiz == null) {
            return new Nodo(mascota);
        }
        int cmp = mascota.getNombre().compareToIgnoreCase(raiz.mascota.getNombre());
        if (cmp < 0)
            raiz.izquierdo = insertarRec(raiz.izquierdo, mascota);
        else if (cmp > 0)
            raiz.derecho = insertarRec(raiz.derecho, mascota);
        // Si es igual, no inserta duplicados (puedes cambiar esto si lo deseas)
        return raiz;
    }

    void inordenRec(Nodo raiz) {
        if (raiz != null) {
            inordenRec(raiz.izquierdo);
            System.out.print(raiz.mascota.getNombre() + " ");
            inordenRec(raiz.derecho);
        }
    }

    // Metodo extraer: imprime en orden los nombres de las mascotas
    void extraer() {
        extraerRec(raiz);
    }

    void extraerRec(Nodo nodo) {
        if (nodo != null) {
            extraerRec(nodo.izquierdo);
            System.out.println(nodo.mascota.getNombre());
            extraerRec(nodo.derecho);
        }
    }
}

