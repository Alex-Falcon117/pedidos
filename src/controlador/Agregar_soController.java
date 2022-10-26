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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Agregar_soController implements Initializable {

    Nuevo_clienteController nuevo_clienteController;

    @FXML
    private ListView<String> listSO;

    @FXML
    private Button btn_AplicarCerrar;

    @FXML
    private Label lbCategoriaRequisitos, lbTamano, lbProcesadorMinimo, lbRamMinimo, lbProcesadorRecomendado, lbRamRecomendado;

    @FXML
    private ComboBox cbSO;

    private byte iSO;

    //Cuando se preciona el boton agregar "+" ----------------------------------
    @FXML
    private void onAgregar() {

        //El contenido del ComboBox(cbSO) se guarda en el ListView(listSO)  
        if (iSO <= 9) {//Solo se pueden agregar 10 elementos a la lista
            String so = (String) cbSO.getValue();
            if (so == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un Sistema Operativo");
            } else {
                listSO.getItems().add(so);
                iSO++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Solo se pueden agregar 10 actividades a la lista");
        }
    }

    @FXML
    private void onEliminar() {

        //Comprueba si hay un elemento de la lista seleccionado
        if (listSO.getSelectionModel().getSelectedIndex() >= 0) {
            iSO--;

            //Obtine el indice del elemento seleccionado y lo elimina de la lista
            int indice = listSO.getSelectionModel().getSelectedIndex();
            listSO.getItems().remove(indice);

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un elemento de la lista");
        }
    }

    @FXML
    private void onAplicarCerrar() {
        //Combierte todo los elementos del ListView(listSO) a tipo Object
        ObservableList lista = FXCollections.observableArrayList();
        
        lista = listSO.getItems();
        String str = listSO.getItems().toString();

        nuevo_clienteController.recibirSO(lista, str);

        Stage stage = (Stage) btn_AplicarCerrar.getScene().getWindow();
        stage.close();

    }
    
    @FXML
    private void onSO(){
        
        String[] datos = new String[7];
        
        String so = (String) cbSO.getValue();
        datos = Conexion_BD.mostrarDatosAplicaciones("'"+so+"'");
        
        lbCategoriaRequisitos.setText("" + datos[0]);
        lbTamano.setText("" + datos[1]);
        lbProcesadorMinimo.setText(datos[3]);
        lbRamMinimo.setText(datos[4]);
        lbProcesadorRecomendado.setText(datos[5]);
        lbRamRecomendado.setText("" + datos[6]);
    
    }

    //Recibe la instancia de la clase_Nuevo ClienteController y la lista de Objetos para rellenar el ListView
    public void recibirParametro(Nuevo_clienteController nc, ObservableList listaSO) {

        if (listaSO != null) {
            for (Object item : listaSO) {//Recorre cada elemento de "obj" y los agraga a "listSO"
                listSO.getItems().add(item.toString()); 
            }
        }

        nuevo_clienteController = nc;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Rellena con datos el COmboBox
        ObservableList<String> so = FXCollections.observableArrayList();
        so = Conexion_BD.mostrarNombreAplicaciones("'Sistema Operativo'");
        cbSO.setItems(so);
    }

}
