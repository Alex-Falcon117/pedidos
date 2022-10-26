
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

public class TareasController implements Initializable {
    
    String[] datos = new String[4];

    @FXML
    private ListView<String> lvClientes;

    @FXML
    private void onClickItem() throws IOException {

        String nombreCliente = lvClientes.getSelectionModel().getSelectedItem();
        int indice = lvClientes.getSelectionModel().getSelectedIndex();

        if (nombreCliente != null) {
            datos = Conexion_BD.mostrarAplicacionesCliente("'"+nombreCliente+"'");

            Stage tareas = new Stage();
            FXMLLoader cargar = new FXMLLoader();
            AnchorPane root = (AnchorPane) cargar.load(getClass().getResource("/vistas/ver_lista.fxml").openStream());

            Ver_lista1Controller ver_listaController = (Ver_lista1Controller) cargar.getController();
            ver_listaController.recibirParametros(this, datos, indice);

            Scene scene = new Scene(root);
            tareas.setScene(scene);
            tareas.setResizable(false);
            tareas.setTitle("Tareas");
            tareas.getIcons().add(new javafx.scene.image.Image("/imagenes/icon.png"));
            tareas.initModality(Modality.APPLICATION_MODAL);
            tareas.show();
        }

    }

    public void recibirParametros(int indice) {
        lvClientes.getItems().remove(indice);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> nombreClientes = FXCollections.observableArrayList();

        nombreClientes = Conexion_BD.mostrarNombreClientes("'pendiente'");

        lvClientes.getItems().addAll(nombreClientes);
        
    }

}
