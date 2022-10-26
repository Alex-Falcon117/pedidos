/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import Conexion_BD.Conexion_BD;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

public class Agregar_juegosController implements Initializable {

    Nuevo_clienteController nuevo_clienteController;

    @FXML
    private ListView<String> listJuegos;

    @FXML
    private Button btn_AplicarCerrar;

    @FXML
    private Label lbCategoriaRequisitos, lbTamano, lbSO, lbProcesadorMinimo, lbRamMinimo, lbProcesadorRecomendado, lbRamRecomendado;

    @FXML
    private ComboBox cbJuegos;

    private byte iJuegos;

    //Cuando se preciona el boton agregar "+" ----------------------------------
    @FXML
    private void onAgregar() {

        //El contenido del ComboBox(cbSO) se guarda en el ListView(listSO)  
        if (iJuegos <= 9) {//Solo se pueden agregar 10 elementos a la lista
            String juegos = (String) cbJuegos.getValue();
            if (juegos == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un Juegos");
            } else {
                listJuegos.getItems().add(juegos);
                iJuegos++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Solo se pueden agregar 10 actividades a la lista");
        }
    }

    @FXML
    private void onEliminar() {

        //Comprueba si hay un elemento de la lista seleccionado
        if (listJuegos.getSelectionModel().getSelectedIndex() >= 0) {
            iJuegos--;

            //Obtine el indice del elemento seleccionado y lo elimina de la lista
            int indice = listJuegos.getSelectionModel().getSelectedIndex();
            listJuegos.getItems().remove(indice);

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un elemento de la lista");
        }
    }

    //Obtiene los datos de la BD y los coloca en los labels
    @FXML
    private void onJuegos() {

        String[] datos = new String[7];

        String juegos = (String) cbJuegos.getValue();
        datos = Conexion_BD.mostrarDatosAplicaciones("'" + juegos + "'");

        lbCategoriaRequisitos.setText(datos[0]);
        lbTamano.setText(datos[1]);
        lbSO.setText(datos[2]);
        lbProcesadorMinimo.setText(datos[3]);
        lbRamMinimo.setText(datos[4]);
        lbProcesadorRecomendado.setText(datos[5]);
        lbRamRecomendado.setText(datos[6]);

    }

    @FXML
    private void onAplicarCerrar() {
        //Combierte todo los elementos del ListView(listSO) a tipo Object
        ObservableList lista = FXCollections.observableArrayList();
        
        lista = listJuegos.getItems();
        String str = listJuegos.getItems().toString();//Obtenemos la misma lista de juegos pero convertido a String

        nuevo_clienteController.recibirJuegos(lista, str);

        Stage stage = (Stage) btn_AplicarCerrar.getScene().getWindow();
        stage.close();

    }

    //Recibe la instancia de la clase Nuevo_ClienteController y la lista de Objetos para rellenar el ListView
    public void recibirParametro(Nuevo_clienteController nc, ObservableList listaJuegos) {

        if (listaJuegos != null) {
            for (Object item : listaJuegos) {//Recorre cada elemento de "obj" y los agraga a "listSO"
                listJuegos.getItems().add(item.toString());
            }
        }

        nuevo_clienteController = nc;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Agrega la lista de juegos que se enciuentran en la BD al cbJuejos
        ObservableList<String> juegos = FXCollections.observableArrayList();
        juegos = Conexion_BD.mostrarNombreAplicaciones("'Juego'");

        cbJuegos.setItems(juegos);
    }

}
