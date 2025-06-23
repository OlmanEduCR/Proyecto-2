public class Mascota{
    //Atributos
    private String nombre;
    private int id;
    private String especie;
    private String historial;
    private boolean urgente;


    //Metodo constructor
    public Mascota(String nombreP, int idP, String especieP, String historialP, boolean urgenteP){
        this.nombre = nombreP;
        this.id = idP;
        this.especie = especieP;
        this.historial = historialP;
        this.urgente = uergenteP;

    }

    //Getters
    public String getNombre(){
        return nombre;
    }

    public int getId(){
        return id;
    }
    public String getEspecie(){
        return especie;
    }

    public String getHistorial(){
        return historial;
    }
    
    public boolean isUrgente() { 
        return urgente; 
    }

    //Setters
    public void setId(int idP){
        this.id = idP;
    }
} 