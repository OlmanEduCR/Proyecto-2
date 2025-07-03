public class ListaEnlazada{
    //Atributos
    NodoLista cabeza;
    private int tamano;

    //Metodo Constructor
    public ListaEnlazada(){
        this.cabeza = null;
        this.tamano = 0;
    }

    //Métodos 




     public void removerPrimero() {
        if (cabeza != null) {
            cabeza = cabeza.siguiente;
        } else {
            System.out.println("La lista está vacía, no se puede remover.");
        }
    }





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

        NodoLista actual = cabeza;
        while (actual.siguiente != null){
            if(actual.siguiente.datosP.equals(datosP)){
                actual.siguiente = actual.siguiente.siguiente;
                tamano --;
                return true;
            }
            actual = actual.siguiente;
            
        }
        return false;
    }
    
    public Mascota[] obtenerTodas() {
        Mascota[] arreglo = new Mascota[tamano];
        NodoLista actual = cabeza;
        int i = 0;
        while (actual != null){
            arreglo[i] = actual.datosP;
            actual = actual.siguiente;
            i++;
        }
        return arreglo;
    }


    
    public void imprimir() {
        NodoLista actual = cabeza;
        System.out.println("--- Contenido de la Lista Enlazada ---");
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        while (actual != null) {
            System.out.println("Mascota: " + actual.datosP.getNombre() + " (ID: " + actual.datosP.getId() + ")");
            actual = actual.siguiente;
        }
        System.out.println("------------------------------------");
    }

    public int getTamano() {
        return tamano;
    }
}
