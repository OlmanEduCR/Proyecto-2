import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javafx.scene.layout.Border;

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

    private crearComponente(){
        //Busqueda
        JPanel panelSuperior = new JPanel(new flowLayout());
        campoBusqueda = new JTextField(30);
        botonBuscar = new JTextField("Buscar por ID");
        botonCargar = JButton("Cargar Datos");

        panelSuperior.add(new JLabel("Buscar por ID:"));
        panelSuperior.add(campoBusqueda);
        panelSuperior.add(botonBuscar);
        panelSuperior.add(botonCargar);
        add(panelSuperior, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Historial");

        tablaMascotas = new DefaultTableModel(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaMascotas);
        add(scrollPane, BorderLayout.CENTER);

        //panel inferior
        JPanel panelInferior = new JPanel();
        panelInferior.add(new JLabel("Mascotas registradas : 0"));
        add(panelInferior, BorderLayout.SOUTH);

        botonBuscar.addActionListener(e -> buscarPorID());
        botonCargar.addActionListener(e -> cargarDatos());
        campoBusqueda.addActionListener(e -> buscarPorID());
    } 

    private void cargarDatos(){
        modeloTabla.setRowCount(0);
        arbolMascotas = new ArbolBinarioBusqueda();
        contadorMascotas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("Datos_Marcota.txt"))){

            String linea;
            while ((linea = br.readLine()) != null){
                String[] datos = linea.split (",");
                if (datos.length >= 3 ){
                    try {
                        int id = Integer.parseInt(datos[1].trim());
                        Mascota mascota = new Mascota( datos[0].trim, id, datos[2].trim());

                        arbolMascotas.insertar(mascota);
                        contadorMarcota ++;

                        modeloTabla.addRow(new Object[]{mascota.getId(), mascota.getNombre(), mascota.getHistorial()});
                    } catch (NumberFormatException e){
                        System.err.println("Error en formato de ID;" +  datos[1]);

                    }
                }
            } 
            actualizarContador();
            JOptionPane.showMessageDialog(this, "Datos exitosamente cargada");
        } catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(this, "No se encontro archivo", "ERROR" , JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lectura del archivo erronea; " +e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }


















    



}