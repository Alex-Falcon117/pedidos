/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import Conexion_BD.Conexion_BD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Kelyx
 */
public class Nuevo_clienteController implements Initializable {

    @FXML
    private Label lbSO, lbProgramas, lbJuegos, lbTotal;

    @FXML
    private TextField tfNombreApellido, tfCelular;

    private String SO, Programas, Juegos;

    private int precioSO, precioProgramas, precioJuegos, total;

    private Object[] objectSO, objectProgramas, objectJuegos;

    ObservableList<String> jList, soList, pList;

    private int[] precios = new int[4];

    @FXML
    private void agregarSO() throws IOException {
        Stage agregarSO = new Stage();
        FXMLLoader cargar = new FXMLLoader();
        AnchorPane root = (AnchorPane) cargar.load(getClass().getResource("/vistas/agregar_so.fxml").openStream());

        //Se envia una instancia de esta clase a la clase Agregar_soController
        Agregar_soController agregar_soController = (Agregar_soController) cargar.getController();
        agregar_soController.recibirParametro(this, soList);

        Scene scene = new Scene(root);
        agregarSO.setScene(scene);
        agregarSO.setResizable(false);
        agregarSO.setTitle("Agregar Sistema Operativo");
        agregarSO.getIcons().add(new javafx.scene.image.Image("/imagenes/icon.png"));
        agregarSO.initModality(Modality.APPLICATION_MODAL);
        agregarSO.show();

    }

    @FXML
    private void agregarProgramas() throws IOException {
        Stage agregarPrograma = new Stage();
        FXMLLoader cargar = new FXMLLoader();
        AnchorPane root = (AnchorPane) cargar.load(getClass().getResource("/vistas/agregar_programas.fxml").openStream());

        //Se envia una instancia de esta clase a la clase Agregar_soController
        Agregar_programasController agregar_programasController = (Agregar_programasController) cargar.getController();
        agregar_programasController.recibirParametro(this, pList);

        Scene scene = new Scene(root);
        agregarPrograma.setScene(scene);
        agregarPrograma.setResizable(false);
        agregarPrograma.setTitle("Agregar Programas");
        agregarPrograma.getIcons().add(new javafx.scene.image.Image("/imagenes/icon.png"));
        agregarPrograma.initModality(Modality.APPLICATION_MODAL);
        agregarPrograma.show();

    }

    @FXML
    private void agregarJuegos() throws IOException {
        Stage agregarJuego = new Stage();
        FXMLLoader cargar = new FXMLLoader();
        AnchorPane root = (AnchorPane) cargar.load(getClass().getResource("/vistas/agregar_juegos.fxml").openStream());

        //Se envia una instancia de esta clase a la clase Agregar_soController
        Agregar_juegosController agregar_juegosController = (Agregar_juegosController) cargar.getController();
        agregar_juegosController.recibirParametro(this, jList);

        Scene scene = new Scene(root);
        agregarJuego.setScene(scene);
        agregarJuego.setResizable(false);
        agregarJuego.setTitle("Agregar Juegos");
        agregarJuego.getIcons().add(new javafx.scene.image.Image("/imagenes/icon.png"));
        agregarJuego.initModality(Modality.APPLICATION_MODAL);
        agregarJuego.show();

    }

    //Cuando se preciona el boton guardar
    @FXML
    private void guardarDatos() {
        String nombreApellido = tfNombreApellido.getText().trim();
        String celular = tfCelular.getText().trim();
        boolean conexion;

        if (!nombreApellido.isEmpty() || !celular.isEmpty()) {
            calcularPrecio();
            conexion = Conexion_BD.insertarDatosCliente("pendiente", nombreApellido, celular, SO, Programas, Juegos, total);
            if (conexion == true) {
                limpiarCampos();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre y número de celular");

        }
    }

    @FXML
    private void calcularPrecio() {

        int totalSO = 0;
        int totalProgramas = 0;
        int totalJuegos = 0;

        if (soList != null) {
            int multiplicar = soList.size();

            totalSO = precioSO * multiplicar;

            lbTotal.setText("$");
        }
        if (pList != null) {

            int multiplicar = pList.size();

            totalProgramas = precioProgramas * multiplicar;

        }
        if (jList != null) {
            int multiplicar = jList.size();

            totalJuegos = precioJuegos * multiplicar;

        }

        total = totalSO + totalProgramas + totalJuegos;

        lbTotal.setText("$" + total);

    }

    //Cuando se preciona el Boton Ver precios
    @FXML
    private void onVerPrecios() throws IOException {
        Stage agregarSO = new Stage();
        FXMLLoader cargar = new FXMLLoader();
        AnchorPane root = (AnchorPane) cargar.load(getClass().getResource("/vistas/ver_precios.fxml").openStream());

        Scene scene = new Scene(root);
        agregarSO.setScene(scene);
        agregarSO.setResizable(false);
        agregarSO.setTitle("Ver precios");
        agregarSO.initModality(Modality.APPLICATION_MODAL);
        agregarSO.show();

    }

    //Estos metodos reciben la lista de elementos seleccionadas convertida a STRING para mostrarla en el Label(l_SO)
    public void recibirSO(ObservableList soLista, String listaSO) {

        soList = soLista;
        SO = listaSO;

        lbSO.setText(listaSO);

    }

    public void recibirProgramas(ObservableList objLista, String listaProgramas) {

        pList = objLista;
        Programas = listaProgramas;

        lbProgramas.setText(listaProgramas);
        // Programas = listaSeleccionada;
    }

    public void recibirJuegos(ObservableList juegoLista, String listaJuegos) {

        jList = juegoLista;
        Juegos = listaJuegos;

        lbJuegos.setText(listaJuegos);//Esa lista convertida a string se le asigna al Label

    }

    private void limpiarCampos() {

        tfCelular.setText("");
        tfNombreApellido.setText("");
        lbSO.setText("");
        lbProgramas.setText("");
        lbJuegos.setText("");
        lbTotal.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        precios = Conexion_BD.mostrarDatosPrecios();

        if (precios[3] == 1) {

            precioSO = precios[0];
            precioProgramas = precios[1];
            precioJuegos = precios[2];

        }

    }

}
