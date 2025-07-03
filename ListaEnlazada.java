public class ListaEnlazada{
    //Atributos
    NodoLista cabeza;
    private int tamano;

    //Metodo Constructor
    public ListaEnlazada(){
        this.cabeza = null;
        this.tamano = 0;
    }

    //Metodos 
    //Método para remover a la primer mascota que fue atendida.
     public void removerPrimero() {
        if (cabeza != null) {
            cabeza = cabeza.siguiente;
            tamano --;
        } else {
            System.out.println("La lista está vacía, no se puede remover.");
        }
    }

    //Método para agregar una nueva mascota al final de la lista (último en la cola).
    public void agregar(Mascota datosP){
        NodoLista nuevoNodo = new NodoLista(datosP);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoLista actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamano++;
    }

    //Método para obtener todas las mascotas de la lista en un arreglo.
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
    
    //Método para obtener la primera mascota de la lista (primero en la cola, sin removerla).
    public Mascota obtenerPrimera(){
        if(cabeza == null){
            return null;
        }
        return cabeza.datosP;
    }
    
    //Método para imprimir todas las mascotas de la lista.
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

    //Getters
    public int getTamano() {
        return tamano;
    }
}