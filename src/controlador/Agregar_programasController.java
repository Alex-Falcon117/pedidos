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

public class Agregar_programasController implements Initializable {

    Nuevo_clienteController nuevo_clienteController;

    @FXML
    private ListView<String> listPrograma;

    @FXML
    private Button btn_AplicarCerrar;

    @FXML
    private Label lbCategoriaRequisitos, lbTamano, lbSO, lbProcesadorMinimo, lbRamMinimo, lbProcesadorRecomendado, lbRamRecomendado;

    @FXML
    private ComboBox cbProgramas;

    private byte iSO;

    //Cuando se preciona el boton agregar "+" ----------------------------------
    @FXML
    private void onAgregar() {

        //El contenido del ComboBox(cbSO) se guarda en el ListView(listSO)  
        if (iSO <= 9) {//Solo se pueden agregar 10 elementos a la lista
            String so = (String) cbProgramas.getValue();
            if (so == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un Programa");
            } else {
                listPrograma.getItems().add(so);
                iSO++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Solo se pueden agregar 10 actividades a la lista");
        }
    }

    @FXML
    private void onEliminar() {

        //Comprueba si hay un elemento de la lista seleccionado
        if (listPrograma.getSelectionModel().getSelectedIndex() >= 0) {
            iSO--;

            //Obtine el indice del elemento seleccionado y lo elimina de la lista
            int indice = listPrograma.getSelectionModel().getSelectedIndex();
            listPrograma.getItems().remove(indice);

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un elemento de la lista");
        }
    }

    @FXML
    private void onAplicarCerrar() {
        //Combierte todo los elementos del ListView(listSO) a tipo Object
         ObservableList lista = FXCollections.observableArrayList();
        
        lista = listPrograma.getItems();
        String str = listPrograma.getItems().toString();

        nuevo_clienteController.recibirProgramas(lista, str);

        Stage stage = (Stage) btn_AplicarCerrar.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void onProgramas() {
        String[] datos = new String[7];
        String programa = (String) cbProgramas.getValue();

        datos = Conexion_BD.mostrarDatosAplicaciones("'" + programa + "'");

        lbCategoriaRequisitos.setText("" + datos[0]);
        lbTamano.setText("" + datos[1]);
        lbSO.setText("" + datos[2]);
        lbProcesadorMinimo.setText(datos[3]);
        lbRamMinimo.setText(datos[4]);
        lbProcesadorRecomendado.setText(datos[5]);
        lbRamRecomendado.setText("" + datos[6]);

    }

    //Recibe la instancia de la clase_Nuevo ClienteController y la lista de Objetos para rellenar el ListView
    public void recibirParametro(Nuevo_clienteController nc, ObservableList obList) {

        if (obList != null) {
            for (Object item : obList) {//Recorre cada elemento de "obj" y los agraga a "listSO"
                listPrograma.getItems().add(item.toString());
            }
        }

        nuevo_clienteController = nc;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Rellena con datos el ComboBox
        ObservableList<String> programas = FXCollections.observableArrayList();
        programas = Conexion_BD.mostrarNombreAplicaciones("'Programa'");

        cbProgramas.setItems(programas);
    }

}
