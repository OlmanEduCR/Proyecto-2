class NodoABB {
    Mascota mascota;
    Nodo derecho, izquierdo ;

    public NodoABB(Mascota mascota) {
        this.mascota = mascota;
        this.derecho = this.izquierdo = null;
    }
}