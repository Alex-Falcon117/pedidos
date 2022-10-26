/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import Conexion_BD.Conexion_BD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class En_procesoController implements Initializable {

    @FXML
    private ListView<String> lvEnProceso;
    
    private String[] datos = new String[4];

    @FXML
    private void onClickItem() throws IOException {

        String nombreCliente = lvEnProceso.getSelectionModel().getSelectedItem();
        int indice = lvEnProceso.getSelectionModel().getSelectedIndex();
        
        if (nombreCliente != null) {
            datos = Conexion_BD.mostrarAplicacionesCliente("'"+nombreCliente+"'");

            Stage enProceso = new Stage();
            FXMLLoader cargar = new FXMLLoader();
            AnchorPane root = (AnchorPane) cargar.load(getClass().getResource("/vistas/lista.fxml").openStream());
            
            ListaController listaController = (ListaController)cargar.getController();
            listaController.recibirParametros(this, datos, indice);

            Scene scene = new Scene(root);
            enProceso.setScene(scene);
            enProceso.setResizable(false);
            enProceso.setTitle("En proceso");
            enProceso.getIcons().add(new javafx.scene.image.Image("/imagenes/icon.png"));
            enProceso.initModality(Modality.APPLICATION_MODAL);
            enProceso.show();

        }

    }
    
    public void recibirParametro(int indice){
        
        lvEnProceso.getItems().remove(indice);
    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList enProceso = FXCollections.observableArrayList();
        
        enProceso = Conexion_BD.mostrarNombreClientes("'en proceso'");
        lvEnProceso.getItems().addAll(enProceso);
    }

}
