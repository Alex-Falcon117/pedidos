/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import Conexion_BD.Conexion_BD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Ver_precioController implements Initializable {
    
    private int[] verPrecios = new int[4];

    @FXML
    private Button btnCerrar;
    
    @FXML
    private Label lbPrecioSO, lbPrecioProgramas, lbPrecioJuegos;
    
    
    @FXML
    private void onCerrar(){
        
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        verPrecios = Conexion_BD.mostrarDatosPrecios();
        
        if(verPrecios[3] == 1){
            lbPrecioSO.setText("$"+verPrecios[0]);
            lbPrecioProgramas.setText("$"+verPrecios[1]);
            lbPrecioJuegos.setText("$"+verPrecios[2]);
        
        }
        
    }    
    
}
