class NodoABB {
    //Atributos
    Mascota mascota;
    NodoABB derecho, izquierdo ;

    //Metodo Constructor
    public NodoABB(Mascota mascota) {
        this.mascota = mascota;
        this.derecho = this.izquierdo = null;
    }
}