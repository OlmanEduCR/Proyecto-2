public class NodoLista {
    //Atributos 
    NodoLista anterior;
    Animal datos;
    
    //Métodos Constructores
    public NodoLista(){
        setAnterior(null);
        setDatos(null);
    }

    public NodoLista(Animal datosP){
        setAnterior(null);
        setDatos(datosP);
    }
    
    public NodoLista(NodoLista anteriorP, Animal datosP){
        setAnterior(anteriorP);
        setDatos(datosP);
    }

    //Getters
    public NodoLista getAnterior(){
        return anterior;
    }

    public Animal getDatos(){
        return datos;
    }

    //Setters
    public void setAnterior(NodoLista anteriorP){
        this.anterior = anteriorP;
    }

    public void setDatos(Animal datosP){
        this.datos = datosP;
    }
}