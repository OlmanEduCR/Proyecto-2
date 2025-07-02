import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class Interfaz extends JFrame{

    private ArbolBinarioBusqueda arbolMascotas;
    private ListaEnlazada listaMascotas;
    private int contadorMascotas;
    
    public Interfaz(){
        setTitle("Gestión de Mascotas Pacientes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Barra de Menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuPrincipal = new JMenu("Principal");
        JMenuItem salirItem = new JMenuItem("Salir");
        JMenuItem verColaDeEspera = new JMenuItem("Ver Cola");

        menuBar.add(menuPrincipal);
        menuPrincipal.add(verColaDeEspera);
        menuPrincipal.add(salirItem);
        setJMenuBar(menuBar);

        //Función Salir
        salirItem.addActionListener(e -> System.exit(0));

        //Función ver la cola
        verColaDeEspera.addActionListener(e -> {
            
        });

        //Panel de Información
        JPanel panelInformacion = new JPanel();
        panelInformacion.setLayout(new BoxLayout(panelInformacion, BoxLayout.Y_AXIS));

        JLabel etiquetaNombre = new JLabel("Digite el nombre de la Mascota: ");
        JTextField campoNombre = new JTextField(20);       

        JLabel etiquetaId = new JLabel("Digite su Id:");
        JTextField campoId = new JTextField(20);


        panelInformacion.add(etiquetaNombre);
        panelInformacion.add(campoNombre);
        panelInformacion.add(etiquetaId);
        panelInformacion.add(campoId);
        add(panelInformacion);

        //Lectura Archivo .txt
        File registro = new File("registro_pacientes.txt");
        if(registro.exists()){
           
        }

        //Ingresar Mascota
        JButton botonIngresar = new JButton("Ingresar al Sistema");

        botonIngresar.addActionListener(e ->{
            String mascotaNombre = campoNombre.getText();
            String mascotaId = campoId.getText();
            if(mascotaNombre.isEmpty() || mascotaId.isEmpty()){
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.");
                return;
            }
            Mascota mascotaPorIngresar = new Mascota(mascotaNombre, mascotaId);
            listaMascotas.agregar(mascotaPorIngresar);
            JOptionPane.showMessageDialog(null, "Mascota ingresada exitosamente.");
            campoNombre.setText("");
            campoId.setText("");
        });panelInformacion.add(botonIngresar);

        //Atender Mascota

        

    }

       
    public static void main(String[] args) {
       Interfaz interfaz = new Interfaz();
       interfaz.setVisible(true);
    }
}