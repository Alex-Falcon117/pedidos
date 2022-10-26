package controlador;

import Conexion_BD.Conexion_BD;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.IntPredicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Kelyx
 */
public class Ver_lista1Controller implements Initializable {

    TareasController tareasController;

    @FXML
    private Button btnCerrar;

    @FXML
    private Label lbNombre, lbTotal, lbSO, lbProgramas, lbJuegos;
    
    private String[] pedidos = new String[5];
    
    private int Indice;

    @FXML
    private void onCerrar() {

        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    //Metodos//////////////////////////////////////////////////////////////////
    @FXML
    private void onComenzar() {
        
        Conexion_BD.acrualizarTarea("'"+pedidos[4]+"'", "en proceso");
        JOptionPane.showMessageDialog(null, "Tarea en proceso, rebice la seccion de En proceso");
        tareasController.recibirParametros(Indice);

    }

    public void recibirParametros(TareasController tController, String[] datos, int indice) {
        Indice = indice;
        tareasController = tController;
        pedidos = datos;
        informacion();
       
    }
    
    private void informacion(){     
         lbSO.setText(pedidos[0]);
         lbProgramas.setText(pedidos[1]);
         lbJuegos.setText(pedidos[2]);
         lbTotal.setText("$"+pedidos[3]);
         lbNombre.setText(pedidos[4]);
    
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
       
    }

}
