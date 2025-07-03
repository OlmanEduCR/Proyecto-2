public class ListaEnlazada{
    //Atributos
    NodoLista cabeza;
    private int tamano;

    //Metodo Constructor
    public ListaEnlazada(){
        this.cabeza = new NodoLista(null);
        this.tamano = 0;
    }

    //Métodos 
    public void agregar(Mascota datosP){
        NodoLista nuevoNodo = new NodoLista(datosP);
        if(cabeza == null){
            cabeza = nuevoNodo;
        } else {
            NodoLista actual = cabeza;
            while(actual.siguiente != null){
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamano++;
    }

    public boolean atender(Mascota datosP) {
    if (cabeza == null) return false;
    if (cabeza.datosP.equals(datosP)) {
        cabeza = cabeza.siguiente;
        tamano--;
        return true;
    }
    return false;
}
    
    public void imprimir(){
        NodoLista actual = cabeza;
        while(actual != null){
            
        }
    }
}
