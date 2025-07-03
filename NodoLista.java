public class NodoLista {
    //Atributos 
    NodoLista siguiente;
    Mascota datosP;
    
    //Métodos Constructores
    public NodoLista(Mascota datosP){
        setSiguiente(null);
        setDatosP(datosP);
    }

    //Getters
    public NodoLista getSiguiente(){
        return siguiente;
    }

    public Mascota getDatosP(){
        return datosP;
    }

    //Setters
    public void setSiguiente(NodoLista siguienteP){
        this.siguiente = siguienteP;
    }

    public void setDatosP(Mascota datosP){
        this.datosP = datosP;
    }
}