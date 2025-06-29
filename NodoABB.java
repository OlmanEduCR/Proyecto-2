class NodoABB {
    Mascota mascota;
    NodoABB derecho, izquierdo ;

    public NodoABB(Mascota mascota) {
        this.mascota = mascota;
        this.derecho = this.izquierdo = null;
    }
}