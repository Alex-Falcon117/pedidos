/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import Conexion_BD.Conexion_BD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javax.swing.JOptionPane;

public class TerminadasController implements Initializable {

    @FXML
    private ListView<String> lvTerminadas;

    private String[] datos = new String[6];

    @FXML
    private void onClickItem() {//Cuando se precione un item del ListView
        //Obtiene el nombre seleccionado
        String nombreCliente = lvTerminadas.getSelectionModel().getSelectedItem();

        if (nombreCliente != null) {
            datos = Conexion_BD.mostrarAplicacionesCliente("'" + nombreCliente + "'");
            JOptionPane.showMessageDialog(null, "Tareas terminadas notifique al cliente: " + "\n"+"\n"+ "Nombre: "+datos[4]+"\n"+"Celular: "+datos[5]+"\n"+"Total: $"+datos[3]);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Obtine la lista de nombres de usuario marcados como 'terminados' y los aagrega al ListView
        ObservableList<String> terminados = FXCollections.observableArrayList();
        terminados = Conexion_BD.mostrarNombreClientes("'terminado'");
        lvTerminadas.getItems().addAll(terminados);
    }

}
