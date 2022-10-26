/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import Conexion_BD.Conexion_BD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class DisponiblesController implements Initializable {

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField tfNombre, tfTamaño, tfSO, tfProcesadorMin, tfProcesadorRecom;

    @FXML
    private ComboBox<String> cbTipo, cbCatREquisito, cbRamMin, cbRamRecom;

    @FXML
    private void onGuardar() {
        boolean conexion;

        //Obtine todo los datos 
        String nombre = tfNombre.getText().trim();
        String tipo = cbTipo.getValue();
        String categoriaRequisito = cbCatREquisito.getValue();
        String tamano = tfTamaño.getText().trim();
        String SO = tfSO.getText().trim();
        String procesadorMin = tfProcesadorMin.getText().trim();
        String ramMin = cbRamMin.getValue();
        String procesadorRecom = tfProcesadorRecom.getText().trim();
        String ramRecom = cbRamRecom.getValue();

        if (!nombre.isEmpty() && !tamano.isEmpty() && !procesadorMin.isEmpty() && !procesadorRecom.isEmpty() && tipo != null && categoriaRequisito != null && ramMin != null && ramRecom != null) {

            conexion = Conexion_BD.insertarDatosAplicacion(nombre, tipo, categoriaRequisito, tamano, SO, procesadorMin, ramMin, procesadorRecom, ramRecom);
            if (conexion == true) {
                limpiarCampos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Falta información");
        }

    }

    @FXML
    private void onAction() {
        String tipo = cbTipo.getValue();
        if (tipo == "Sistema Operativo") {
            tfSO.setDisable(true);
            tfSO.setText("");

        } else {
            tfSO.setDisable(false);
        }

    }

    private void limpiarCampos() {
        tfNombre.setText("");
        tfTamaño.setText("");
        tfProcesadorMin.setText("");
        tfProcesadorRecom.setText("");
        tfSO.setText("");
        cbCatREquisito.setValue(null);
        cbRamMin.setValue(null);
        cbRamRecom.setValue(null);
        cbTipo.setValue(null);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Carga informacion a los ComboBox
        ObservableList<String> tipo = FXCollections.observableArrayList("Sistema Operativo", "Programa", "Juego");
        ObservableList<String> categoriaRequisito = FXCollections.observableArrayList("Alto", "Medio", "Bajo");
        ObservableList<String> ram = FXCollections.observableArrayList("512 MB", "1 GB", "2 GB", "4 GB", "6 GB", "8 GB", "16 GB", "+32 GB");

        cbTipo.setItems(tipo);
        cbCatREquisito.setItems(categoriaRequisito);
        cbRamMin.setItems(ram);
        cbRamRecom.setItems(ram);

    }

}
