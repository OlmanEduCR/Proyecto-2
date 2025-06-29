public class Mascota{
    //Atributos
    private String nombre;
    private int id;
    private String historial;


    //Metodo constructor
    public Mascota(String nombreP, int idP, String historialP){
        this.nombre = nombreP;
        this.id = idP;
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