class ArbolBinarioBusqueda {
    //Atributos
    NodoABB raiz;

    //Método Constructor
    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    // Inserta una mascota en el arbol, ordenando por nombre
    public void insertar(Mascota mascota) {
        raiz = insertarRec(mascota, raiz);
    }

    public NodoABB insertarRec(Mascota mascota, NodoABB raiz ) {
        if (raiz == null) {
            return new NodoABB(mascota);
        }
        int cmp = mascota.getNombre().compareToIgnoreCase(raiz.mascota.getNombre());
        if (cmp < 0)
            raiz.izquierdo = insertarRec(mascota ,raiz.izquierdo);
        else if (cmp > 0)
            raiz.derecho = insertarRec(mascota, raiz.derecho);
        // Si es igual, no inserta duplicados (puedes cambiar esto si lo deseas)
        return raiz;
    }

    public void inordenRec(NodoABB raiz) {
        if (raiz != null) {
            inordenRec(raiz.izquierdo);
            System.out.print(raiz.mascota.getNombre() + " ");
            inordenRec(raiz.derecho);
        }
    }

    // Metodo extraer: imprime en orden los nombres de las mascotas
    public void extraer() {
        extraerRec(raiz);
    }

    public void extraerRec(NodoABB nodo) {
        if (nodo != null) {
            extraerRec(nodo.izquierdo);
            System.out.println(nodo.mascota.getNombre());
            extraerRec(nodo.derecho);
        }
    }

    //Eliminar nodo
    public void eliminar (String nombreMascota){
        raiz = eliminarRec(raiz, nombreMascota);
    }

    private NodoABB eliminarRec(NodoABB nodo, String nombreMascota){
        if(nodo == null){
            return null;
        }
        int cmp = nombreMascota.compareToIgnoreCase(nodo.mascota.getNombre());
        if (cmp < 0){
            nodo.izquierdo = eliminarRec(nodo.izquierdo, nombreMascota);
        } else if (cmp > 0 ) {
            nodo.derecho = eliminarRec(nodo.derecho, nombreMascota);
        } else {
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            } else if (nodo.derecho == null) {
                return nodo.izquierdo;
            }
            nodo.mascota = minValor(nodo.derecho);
            nodo.derecho = eliminarRec(nodo.derecho, nodo.mascota.getNombre());
        }
        return nodo;
    }

    private Mascota minValor(NodoABB nodo){
        NodoABB actual = nodo;
        while(actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.mascota;
    }
}

