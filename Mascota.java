public class Mascota{
    //Atributos
    private String nombre;
    private String id;

    //Metodo constructor
    public Mascota(String nombreP, String idP){
        this.nombre = nombreP;
        this.id = idP;
    }

    //Getters
    public String getNombre(){
        return nombre;
    }

    public String getId(){
        return id;
    }

    //Setters
    public void setId(String idP){
        this.id = idP;
    }
} 