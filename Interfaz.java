import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class Interfaz extends JFrame{

    private ArbolBinarioBusqueda arbolMascotas;
    private ListaEnlazada listaMascotas = new ListaEnlazada();
    private ListaEnlazada colaEspera = new ListaEnlazada();
    private int contadorMascotas;
    private JTable tablaMascotas = new JTable();
    
    public Interfaz(){
        arbolMascotas = new ArbolBinarioBusqueda();

        setTitle("Gestion de Mascotas Pacientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Barra de Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuPrincipal = new JMenu("Principal");
        JMenuItem salirItem = new JMenuItem("Salir");
        JMenuItem verColaDeEspera = new JMenuItem("Ver Cola");
        JMenuItem verArbolMascotas = new JMenuItem("Ver Arbol de mascotas");
        JMenuItem verListaMascotas = new JMenuItem("Ver lista de mascotas");

        menuBar.add(menuPrincipal);
        menuPrincipal.add(verColaDeEspera);
        menuPrincipal.add(verListaMascotas);
        menuPrincipal.add(verArbolMascotas);
        menuPrincipal.add(salirItem);
        setJMenuBar(menuBar);

        //Funcion Salir
        salirItem.addActionListener(e -> System.exit(0));
        verArbolMascotas.addActionListener(e -> mostrarTablaArbol());

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

        // tabla de arbol lista mascota
        verListaMascotas.addActionListener(e -> mostrarTablaListaMascotas());

        // tabla de arbol mascota

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        JLabel etiquetaNombre = new JLabel("Digite el nombre de la Mascota: ");
        JTextField campoNombre = new JTextField(20); 
        JLabel etiquetaId = new JLabel("Digite su Id:");
        JTextField campoId = new JTextField(20);

        panelCentral.add(etiquetaNombre);
        panelCentral.add(campoNombre);
        panelCentral.add(Box.createVerticalStrut(10));
        panelCentral.add(etiquetaId);
        panelCentral.add(campoId);
        panelCentral.add(Box.createVerticalStrut(10));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        // Panel Menu
        
        JButton botonIngresar = new JButton("Ingresar al Sistema");
        JButton botonAtender = new JButton("Atender mascota");

        panelBotones.add(botonIngresar);
        panelBotones.add(botonAtender);

        panelCentral.add(panelBotones);
        add(panelCentral, BorderLayout.CENTER);

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel etiquetaBuscar = new JLabel("Buscar mascota por nombre:");
        JTextField campoBuscar = new JTextField(15);
        JButton botonBuscar = new JButton("Buscar");

        panelBusqueda.add(etiquetaBuscar);
        panelBusqueda.add(campoBuscar);
        panelBusqueda.add(botonBuscar);
        add(panelBusqueda, BorderLayout.SOUTH);

        //agragar a listas
        
        botonIngresar.addActionListener(e -> {
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
            arbolMascotas.insertar(mascotaPorIngresar);
            JOptionPane.showMessageDialog(null, "Mascota ingresada exitosamente.");

            campoNombre.setText("");
            campoId.setText("");

            guardarMascotasEnArchivo("registro_pacientes.txt");
            actualizarTablaMascotas();
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
 
        //Atender Mascota
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
       
        botonBuscar.addActionListener(e -> {
            String nombreBusqueda = campoBuscar.getText().trim();
            if (nombreBusqueda.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre para buscar.", "Buscar Mascota", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Mascota resultado = arbolMascotas.buscar(nombreBusqueda);
            if (resultado != null) {
                JOptionPane.showMessageDialog(null,
                "Mascota encontrada \n Nombre: " + resultado.getNombre() + "\n ID " + resultado.getId(), "Resultado de busqueda", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Mascota con nombre " + nombreBusqueda + " no encontrada.", "Resultado de busqueda", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //Lectura Archivo .txt
        File registro = new File("registro_pacientes.txt");
        if(registro.exists()){
            cargarMascotasDesdeArchivo("registro_pacientes.txt");
        }
        actualizarTablaMascotas(); 
    }
    
    private void llenarModeloArbol(NodoABB nodo, DefaultTableModel modelo) {
        if( nodo != null) {
            llenarModeloArbol(nodo.izquierdo, modelo);
            java.util.Vector<Object> fila = new java.util.Vector<>();
            fila.add(nodo.mascota.getNombre());
            fila.add(nodo.mascota.getId());
            modelo.addRow(fila);
            llenarModeloArbol(nodo.derecho, modelo);
        }
    }

    //tabla lista arbol
    private void mostrarTablaArbol(){
        JFrame frame = new JFrame("Registro en arbol");
        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);
        JTable tablaArbol = new JTable();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        
        llenarModeloArbol(arbolMascotas.raiz, modelo);

        tablaArbol.setModel(modelo);
        JScrollPane scroll = new JScrollPane(tablaArbol);
        frame.add(scroll);
        frame.setVisible(true);
    }

    // mostrar Tabla lista
    private void mostrarTablaListaMascotas(){
        JFrame frame = new JFrame("Lista de mascotas");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        JTable tablaLista = new JTable();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        NodoLista actual = listaMascotas.getCabeza();
        while (actual != null) {
            java.util.Vector<Object> fila = new java.util.Vector<>();
            fila.add(actual.getDatosP().getNombre());
            fila.add(actual.getDatosP().getId());
            modelo.addRow(fila);
            actual = actual.getSiguiente();
        }
        tablaLista.setModel(modelo);
        JScrollPane scroll = new JScrollPane(tablaLista);
        frame.add(scroll);
        frame.setVisible(true);
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
                        arbolMascotas.insertar(new Mascota (nombre, id));
                        
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