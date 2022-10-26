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
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Kelyx
 */
public class PreciosController implements Initializable {

    private int[] precios = new int[4];

    @FXML
    private TextField tfSO, tfProgramas, tfJuegos;

    @FXML
    private Button btnGuardar, btnActualizar;

    @FXML
    private void onGuardar() { //Guarda los precios

        String precioSO = tfSO.getText().trim();
        String precioProgramas = tfProgramas.getText().trim();
        String precioJuegos = tfJuegos.getText().trim();

        if (!precioSO.isEmpty() || !precioProgramas.isEmpty() || !precioJuegos.isEmpty()) {
            int preciosSO = Integer.parseInt(precioSO);
            int preciosProgramas = Integer.parseInt(precioProgramas);
            int preciosJuegos = Integer.parseInt(precioJuegos);

            btnGuardar.setDisable(true);
            btnActualizar.setDisable(false);

            Conexion_BD.insertarDatosPrecio(preciosSO, preciosProgramas, preciosJuegos);

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese los precios");
        }

    }

    @FXML
    private void onActualizar() {//Aactualiza los precios

        try {

            int preciosSO = Integer.parseInt(tfSO.getText().trim());
            int preciosProgramas = Integer.parseInt(tfProgramas.getText().trim());
            int preciosJuegos = Integer.parseInt(tfJuegos.getText().trim());

            if (preciosSO > 0 || preciosProgramas > 0 || preciosJuegos > 0) {

                Conexion_BD.actualizarDatosPrecios(preciosSO, preciosProgramas, preciosJuegos);

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese los precios");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese solo números");

        }

    }

    private void mostrarPrecios(int[] precios) {

        tfSO.setText(precios[0] + "");
        tfProgramas.setText(precios[1] + "");
        tfJuegos.setText(precios[2] + "");

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Recibe el array con los precios
        precios = Conexion_BD.mostrarDatosPrecios();

        //Si existen precios en la BD
        if (precios[3] == 1) {
            btnGuardar.setDisable(true);
            mostrarPrecios(precios);
        } else {
            btnActualizar.setDisable(true);
        }

    }

}
