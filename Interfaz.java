import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Dimension;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.*;

public class Interfaz extends JFrame{

    private ArbolBinarioBusqueda arbolMascotas;
    private ListaEnlazada listaMascotas = new ListaEnlazada();
    private int contadorMascotas;
    private JTable tablaMascotas = new JTable();
    
    public Interfaz(){
        setTitle("Gestion de Mascotas Pacientes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Barra de Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuPrincipal = new JMenu("Principal");
        JMenuItem salirItem = new JMenuItem("Salir");
        JMenuItem verColaDeEspera = new JMenuItem("Ver Cola");

        menuBar.add(menuPrincipal);
        menuPrincipal.add(verColaDeEspera);
        menuPrincipal.add(salirItem);
        setJMenuBar(menuBar);

        //Funcion Salir
        salirItem.addActionListener(e -> System.exit(0));

        //Funcion ver la cola
        verColaDeEspera.addActionListener(e -> {
            StringBuilder contenido = new StringBuilder("Mascotas en lista de espera \n");
            Mascota[] mascotas = listaMascotas.obtenerTodas();
            for(Mascota m : mascotas) {
                contenido.append("- ").append(m.getNombre()).append(" ID ").append(m.getId()).append("\n");
            }    
            JOptionPane.showMessageDialog(null, contenido.toString(), "Cola de espera", JOptionPane.INFORMATION_MESSAGE);        
        });

        //Panel de Informacion
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


        //tabla al panel
        JScrollPane scrollTabla = new JScrollPane(tablaMascotas);
        scrollTabla.setPreferredSize(new Dimension (350,120));
        panelInformacion.add(scrollTabla);

        add(panelInformacion);

        //Lectura Archivo .txt
        File registro = new File("registro_pacientes.txt");
        if(registro.exists()){
            cargarMascotasDesdeArchivo("registro_pacientes.txt");
        }
        actualizarTablaMascotas();

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
            guardarMascotasEnArchivo("registro_pacientes.txt");
            actualizarTablaMascotas();
        });
        panelInformacion.add(botonIngresar);

        //Atender Mascota
        JButton botonAtender = new JButton("Atender mascota");
        botonAtender.addActionListener(e -> {
            if (listaMascotas.getTamano() > 0) {
                Mascota atendida = listaMascotas.obtenerPrimera();
                listaMascotas.removerPrimero();
                guardarMascotasEnArchivo("registro_pacientes.txt");
                actualizarTablaMascotas();

                JOptionPane.showMessageDialog(null, "Mascota atendida \n" + atendida.getNombre() + " ID " + atendida.getId() + "Atencion terminada");
            } else { 
                JOptionPane.showMessageDialog(null, "Lista de espera vacia", "cola vacia", JOptionPane.WARNING_MESSAGE);
            }
        });
        panelInformacion.add(botonAtender);
    }

    private void guardarMascotasEnArchivo(String nombreArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            Mascota[] mascotas = listaMascotas.obtenerTodas();
            for (Mascota mascota : mascotas) {
                bw.write(mascota.getNombre() + "," + mascota.getId());
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
        }
    }
    
    private void cargarMascotasDesdeArchivo(String nombreArchivo){
        File registro = new File(nombreArchivo);
        if (registro.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(registro))){
                String linea;
                while((linea = br.readLine()) != null ) {
                    String[] partes = linea.split(",");
                    if(partes.length == 2) {
                        String nombre = partes[0].trim();
                        String id = partes[1].trim();
                        listaMascotas.agregar(new Mascota(nombre, id));
                    }
                }
            } catch (IOException e){
                JOptionPane.showMessageDialog(null, "Error en lectura de archivo" + e.getMessage());
            }
        }
    }

    public void actualizarTablaMascotas() {
        String[] columnas = {"Nombre", "ID"};
        Mascota[] mascotas = listaMascotas.obtenerTodas(); 
        Object[][] datos = new Object[mascotas.length][2]; 
        for (int i = 0; i < mascotas.length; i++) { 
            datos[i][0] = mascotas[i].getNombre(); 
            datos[i][1] = mascotas[i].getId(); 
        }
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
        tablaMascotas.setModel(modelo);
    }
  
    public static void main(String[] args) {
       Interfaz interfaz = new Interfaz();
       interfaz.setVisible(true);
    }
}