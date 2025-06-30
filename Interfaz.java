import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Interfaz extends JFrame{


    
    private JTable tablaMascotas;
    private DefaultTableModel modeloTabla;
    private JTextField campoBusqueda;
    private JButton botonCargar;
    private JButton botonBuscar;
    private ArbolBinarioBusqueda arbolMascotas;
    private ListaEnlazada listaMascotas;
    private int contadorMascotas;
    

    public Interfaz (){
        setTitle("Gestión de Mascotas Pacientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // estructura de busqueda
        arbolMascotas = new ArbolBinarioBusqueda();
        listaMascotas = new ListaEnlazada();
        contadorMascotas = 0;


        crearComponente();
        setVisible(true);

    }

    
}