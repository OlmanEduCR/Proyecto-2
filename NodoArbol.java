class NodoArbol {
    Mascota mascota;
    Nodo izquierdo, derecho;

    public Nodo(Mascota mascota) {
        this.mascota = mascota;
        izquierdo = derecho = null;
    }
}