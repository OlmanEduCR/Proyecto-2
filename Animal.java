public class Animal{
    //Atributos
    private int id;
    private String nombre;

    //Método constructor
    public Animal(int idP, String nombreP){
        this.id = idP;
        this.nombre = nombreP;
    }

    //Getters
    public String getNombre(){
        return nombre;
    }

    public int getId(){
        return id;
    }

    //Setters
    public void setId(int idP){
        this.id = idP;
    }
} 