package controlador;

import Conexion_BD.Conexion_BD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ListaController implements Initializable {

    En_procesoController eController;

    @FXML
    private Button btnCerrar;

    @FXML
    private Label lbNombre, lbSO, lbProgramas, lbJuegos, lbTotal;

    private String[] pedidos = new String[5];
    
    private int Indice;

    @FXML
    private void onCerrar() {

        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    //Metodos//////////////////////////////////////////////////////////////////
    @FXML
    private void onTerminar() {
        eController.recibirParametro(Indice);
        Conexion_BD.acrualizarTarea("'" + pedidos[4] + "'", "terminado");
        JOptionPane.showMessageDialog(null, "Tarea terminada, rebice la seccion de Terminados");
    }

    //Recibe una instancia de la clase En Proceso y un arreglo con toda la informacion de la BD
    public void recibirParametros(En_procesoController epController, String[] datos, int indice) {
        eController = epController;
        Indice = indice;
        pedidos = datos;
        informacion();

    }

    private void informacion() {
        lbSO.setText(pedidos[0]);
        lbProgramas.setText(pedidos[1]);
        lbJuegos.setText(pedidos[2]);
        lbTotal.setText("$" + pedidos[3]);
        lbNombre.setText(pedidos[4]);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
