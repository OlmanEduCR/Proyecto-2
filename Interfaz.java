import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.*;

public class Interfaz extends JFrame{

    private ArbolBinarioBusqueda arbolMascotas;
    private ListaEnlazada listaMascotas = new ListaEnlazada();
    private ListaEnlazada colaEspera = new ListaEnlazada();
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
            NodoLista actual = colaEspera.getCabeza();
            if(actual == null){
                JOptionPane.showMessageDialog(null, "no hay mascotas en espera", "Cola de espera", JOptionPane.INFORMATION_MESSAGE);
            } else {
                StringBuilder contenido = new StringBuilder("Mascotas en lista de espera \n");
                while (actual != null){
                    Mascota m = actual.datosP;
                    contenido.append("- ").append(m.getNombre()).append(" ID ").append(m.getId()).append("\n");
                    actual = actual.siguiente;
                }
                JOptionPane.showMessageDialog(null, contenido.toString(), "Cola de espera", JOptionPane.INFORMATION_MESSAGE);
            }       
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

        //Ingresar a la Cola de Espera
        JButton botonIngresar = new JButton("Ingresar al Sistema");
        botonIngresar.addActionListener(e ->{
            String mascotaNombre = campoNombre.getText();
            String mascotaId = campoId.getText();

            if(mascotaNombre.isEmpty() || mascotaId.isEmpty()){
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.");
                return;
            }

            Mascota mascotaPorIngresar = new Mascota(mascotaNombre, mascotaId);
            try{
            listaMascotas.agregar(mascotaPorIngresar);
            colaEspera.agregar(mascotaPorIngresar);
            JOptionPane.showMessageDialog(null, "Mascota ingresada exitosamente.");

            campoNombre.setText("");
            campoId.setText("");

            guardarMascotasEnArchivo("registro_pacientes.txt");
            actualizarTablaMascotas();
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelInformacion.add(botonIngresar);

        //Atender Mascota
        JButton botonAtender = new JButton("Atender mascota");
        botonAtender.addActionListener(e -> {
            if (colaEspera.getTamano() > 0) {
                Mascota atendida = colaEspera.obtenerPrimera();
                colaEspera.removerPrimero();
                JOptionPane.showMessageDialog(null, "Mascota atendida \n" + atendida.getNombre() + " ID " + atendida.getId() + " \n Atencion terminada");
                guardarMascotasEnArchivo("registro_pacientes.txt");
                actualizarTablaMascotas();

                JOptionPane.showMessageDialog(null, "Mascota atendida \n" + atendida.getNombre() + " ID " + atendida.getId() + " \nAtencion terminada");
            } else { 
                JOptionPane.showMessageDialog(null, "Lista de espera vacia", "cola vacia", JOptionPane.WARNING_MESSAGE);
            }
        });
        panelInformacion.add(botonAtender);
    }

    private void guardarMascotasEnArchivo(String nombreArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            NodoLista actual = listaMascotas.getCabeza();
            if (actual == null){
                bw.write ("No hay mascotas en la lista.");
            } else {
                while (actual != null) {
                    Mascota mascota = actual.datosP;
                    bw.write(mascota.getNombre() + "," + mascota.getId());
                    bw.newLine();
                    actual = actual.siguiente;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
        }
    }
    
    public void cargarMascotasDesdeArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                StringBuilder nombreBuilder = new StringBuilder();
                StringBuilder idBuilder = new StringBuilder();
                boolean comaEncontrada = false;
                for(int i = 0; i<linea.length(); i++){
                    char c = linea.charAt(i);
                    if (!comaEncontrada){
                        if (c == ','){
                            comaEncontrada = true;
                        } else {
                            nombreBuilder.append(c);
                        }
                    } else {
                        idBuilder.append(c);
                    } 

                }
                if(comaEncontrada){
                    String nombre = nombreBuilder.toString().trim();
                    String id = idBuilder.toString().trim();
                    try{
                        listaMascotas.agregar(new Mascota(nombre, id));
                    } catch (Exception e){
                        System.out.println("Error al agregar mascota con ID " + id + ": " + e.getMessage());
                    }
                } else {
                    System.out.println("Error en la linea" + linea);
                }
            }
            System.out.println("Carga de mascotas finalizada.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } 
    }

    public void actualizarTablaMascotas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");

        NodoLista actual = listaMascotas.getCabeza();
        while (actual != null){
            java.util.Vector<Object> fila = new java.util.Vector<>();
            fila.add(actual.getDatosP().getNombre());
            fila.add(actual.getDatosP().getId());
            modelo.addRow(fila);
            actual = actual.getSiguiente();
        }
        tablaMascotas.setModel(modelo);
    }
  
    public static void main(String[] args) {
       Interfaz interfaz = new Interfaz();
       interfaz.setVisible(true);
    }
}