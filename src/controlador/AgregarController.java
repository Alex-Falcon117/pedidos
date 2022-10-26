/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javax.swing.JOptionPane;


public class AgregarController implements Initializable {

    @FXML
    private ListView<String> listSO, listProgramas, listJuegos;

    @FXML
    private ComboBox cbSO, cbProgramas, cbJuegos;

    byte iSO, iProgramas, iJuegos = 0;

    String[] arraySO = new String[10];
    String[] arrayProgramas = new String[10];
    String[] arrayJuegos = new String[10];

    //Cuando se selecciona un item del ComboBox Sistema Operativo---------------
    @FXML
    private void onSO() {

        //Se obtine el texto seleccionado y se guarda en una posicion del Array
        if (iSO <= 9) {
            String so = (String) cbSO.getValue();
            arraySO[iSO] = so;
            listSO.getItems().addAll(so);
            iSO++;
        } else {
            JOptionPane.showMessageDialog(null, "Solo se pueden agregar 10 actividades a la lista");
        }
    }

    //Cuando se selecciona un item del ComboBox Programas-----------------------
    @FXML
    private void onProgramas() {
        //Se obtine el texto seleccionado y se guarda en una posicion del Array
        if (iProgramas <= 9) {
            String programas = (String) cbProgramas.getValue();
            arrayProgramas[iProgramas] = programas;
            listProgramas.getItems().addAll(programas);
            iProgramas++;
        } else {
            JOptionPane.showMessageDialog(null, "Solo se pueden agregar 10 actividades a la lista");
        }
    }
    
     //Cuando se selecciona un item del ComboBox Juegos-------------------------
    @FXML
    private void onJuegos() {
        //Se obtine el texto seleccionado y se guarda en una posicion del Array
        if (iJuegos <= 9) {
            String juegos = (String) cbJuegos.getValue();
            arrayJuegos[iJuegos] = juegos;
            listJuegos.getItems().addAll(juegos);
            iJuegos++;
        } else {
            JOptionPane.showMessageDialog(null, "Solo se pueden agregar 10 actividades a la lista");
        }
    }

    @FXML
    private void c(){
        /*int id = ListView.getSelectionModel.getSelectedIdex();
        listview.getItems().remove(id);*/
    }
    
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> items = FXCollections.observableArrayList("Single", "Double", "Suite", "Family App");
        items.add("5");

        //list.getItems().addAll(item);
        // list.setItems(items);
        cbSO.setItems(items);
        cbProgramas.setItems(items);
        cbJuegos.setItems(items);

       
    }

}
