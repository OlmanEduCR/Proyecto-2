public class Animal{
    //Atributos
    private int id;
    private String nombre;
    private String historial;

    //Método constructor
    public Animal(int idP, String nombreP, String historialP){
        this.id = idP;
        this.nombre = nombreP;
        this.historial = historialP;
    }

    //Getters
    public String getNombre(){
        return nombre;
    }

    public int getId(){
        return id;
    }

    public String getHistorial(){
        return historial;
    }

    //Setters
    public void setId(int idP){
        this.id = idP;
    }
} 