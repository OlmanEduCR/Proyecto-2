public class ListaEnlazada{
    //Atributos
    NodoLista cabeza;

    //Método Constructor
    public ListaEnlazada(){
        this.cabeza = new NodoLista(null);
    }

    //Métodos 
    public void agregar(Mascota datos){
        NodoLista nuevoNodo = new NodoLista(datos);
        if(cabeza == null){
            cabeza = nuevoNodo;
        } else {
            NodoLista actual = cabeza;
            while(actual.siguiente != null){
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    public boolean atender(Mascota datos){
        if (cabeza == null) return false;            
        if (cabeza.datos.equals(datos)) {
            cabeza = cabeza.siguiente;
        } return true;
    }
    
    public void imprimir(){
        NodoLista actual = cabeza;
        while(actual != null){
            
        }
    }
}
