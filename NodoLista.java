public class NodoLista {
    //Atributos 
    NodoLista siguiente;
    Mascota datos;
    
    //Métodos Constructores
    public NodoLista(Mascota datosP){
        setSiguiente(null);
        setDatos(datosP);
    }

    //Getters
    public NodoLista getSiguiente(){
        return siguiente;
    }

    public Mascota getDatos(){
        return datos;
    }

    //Setters
    public void setSiguiente(NodoLista siguienteP){
        this.siguiente = siguienteP;
    }

    public void setDatos(Mascota datosP){
        this.datos = datosP;
    }
}