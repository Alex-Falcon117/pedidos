/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class Ventana_principalController implements Initializable {

    private boolean estado_NCliente = true;
    private boolean estado_Tareas = false;
    private boolean estado_EProceso = false;
    private boolean estado_Terminados = false;
    private boolean estado_Precios = false;
    private boolean estado_Anadir = false;
    
    String botonEstilo ="-fx-background-color: #8E33FF; -fx-border-width: 0px 0px 0px 5px; -fx-border-color: #ffffff; -fx-border-radius: 0; -fx-background-radius: 0;";
    String botonEstilo2 = "-fx-background-color: #8E33FF; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #ffffff; -fx-border-radius: 0; -fx-background-radius: 0;";
    
    @FXML
    Button btn_NCliente, btn_Tareas, btn_EProceso, btn_Terminadas, btn_Precios, btn_Anadir;

    @FXML
    private BorderPane borderPane;
    
    @FXML
    ImageView ivCliente, ivPendientes, ivEnProceso, ivTerminados, ivAplicaciones, ivPrecios;

    //Boton Nuevo CLiente-------------------------------------------------------
    @FXML
    private void onBtnNCliente() throws IOException {
        cambioPantalla("nuevo_cliente");
        estadoBotones(true, false, false, false, false, false);
        //Cuando se selecciona el boton se establese el siguiente estilo
        btn_NCliente.setStyle(botonEstilo);

    }

    @FXML
    private void onMEntNCLiente() {
        if (estado_NCliente == false) {//Evita que el botn se establesca este esl¿tilo cuando ya esta seleccionado
            btn_NCliente.setStyle(botonEstilo2);
        }
    }

    @FXML
    private void onMExitNCliente() {

        if (estado_NCliente == false) {//Evita que el botn se establesca este esl¿tilo cuando ya esta seleccionado
            btn_NCliente.setStyle("-fx-background-color:  #6A00EE");
        }
    }

    //Boton Tareas--------------------------------------------------------------
    @FXML
    private void onBtnTareas() throws IOException {
        cambioPantalla("tareas");
        estadoBotones(false, true, false, false, false, false);
        btn_Tareas.setStyle(botonEstilo);
    }

    @FXML
    private void onMEntTareas() {
        if (estado_Tareas == false) {
            btn_Tareas.setStyle(botonEstilo2);
        }
    }

    @FXML
    private void onMExitTareas() {
        if (estado_Tareas == false) {
            btn_Tareas.setStyle("-fx-background-color:  #6A00EE");
        }
    }

    //Boton En proceso----------------------------------------------------------
    @FXML
    private void onBtnEProceso() throws IOException {
        cambioPantalla("en_proceso");
        estadoBotones(false, false, true, false, false, false);
        btn_EProceso.setStyle(botonEstilo);

    }

    @FXML
    private void onMEntEProceso() {
        if (estado_EProceso == false) {
            btn_EProceso.setStyle(botonEstilo2);
        }
    }

    @FXML
    private void onMExitEProceso() {
        if (estado_EProceso == false) {
            btn_EProceso.setStyle("-fx-background-color:  #6A00EE");
        }
    }

    //Boton Terminadas----------------------------------------------------------
    @FXML
    private void onBtnTerminadas() throws IOException {
        cambioPantalla("terminadas");
        estadoBotones(false, false, false, true, false, false);
        btn_Terminadas.setStyle(botonEstilo);

    }

    @FXML
    private void onMEntTerminadas() {
        if (estado_Terminados == false) {
            btn_Terminadas.setStyle(botonEstilo2);
        }
    }

    @FXML
    private void onMExitTerminadas() {
        if (estado_Terminados == false) {
            btn_Terminadas.setStyle("-fx-background-color:  #6A00EE");
        }
    }

    //Boton Precios-------------------------------------------------------------
    @FXML
    private void onBtnPrecios() throws IOException {
        cambioPantalla("precios");
        estadoBotones(false, false, false, false, true, false);
        btn_Precios.setStyle(botonEstilo);

    }

    @FXML
    private void onMEntPrecios() {
        if (estado_Precios == false) {
            btn_Precios.setStyle(botonEstilo2);
        }
    }

    @FXML
    private void onMExitPrecios() {
        if (estado_Precios == false) {
            btn_Precios.setStyle("-fx-background-color:  #6A00EE");
        }
    }
    
    //Boton Añadir Programas-------------------------------------------------------------
    @FXML
    private void onBtnAnadir() throws IOException {
        cambioPantalla("disponibles");
        estadoBotones(false, false, false, false, false, true);
        btn_Anadir.setStyle(botonEstilo);

    }

    @FXML
    private void onMEntAnadir() {
        if (estado_Anadir == false) {
            btn_Anadir.setStyle(botonEstilo2);
        }
    }

    @FXML
    private void onMExitAnadir() {
        if (estado_Anadir == false) {
            btn_Anadir.setStyle("-fx-background-color:  #6A00EE");
        }
    }

    //--------------------------------------------------------------------------
    public void cambioPantalla(String pagina) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/" + pagina + ".fxml"));
        borderPane.setCenter(root);

    }

    private void estadoBotones(boolean btn1, boolean btn2, boolean btn3, boolean btn4, boolean btn5, boolean btn6) {
        estado_NCliente = btn1;
        estado_Tareas = btn2;
        estado_EProceso = btn3;
        estado_Terminados = btn4;
        estado_Precios = btn5;
        estado_Anadir = btn6;

        //Establece un color de fondo a un boton cuando se deselecciona
        if (estado_NCliente == false) {
            btn_NCliente.setStyle("-fx-background-color:  #6A00EE");
        }
        if (estado_Tareas == false) {
            btn_Tareas.setStyle("-fx-background-color:  #6A00EE");
        }
        if (estado_EProceso == false) {
            btn_EProceso.setStyle("-fx-background-color:  #6A00EE");
        }
        if (estado_Terminados == false) {
            btn_Terminadas.setStyle("-fx-background-color:  #6A00EE");
        }
        if (estado_Precios == false) {
            btn_Precios.setStyle("-fx-background-color:  #6A00EE");
        }
        
        if (estado_Anadir == false) {
            btn_Anadir.setStyle("-fx-background-color:  #6A00EE");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cambioPantalla("nuevo_cliente");
        } catch (IOException ex) {
            Logger.getLogger(Ventana_principalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Agregar imagenes a los ImageView
        ivCliente.setImage(new Image("/imagenes/ic_agregar_cliente.png"));
        ivPendientes.setImage(new Image("/imagenes/ic_pendientes.png"));
        ivEnProceso.setImage(new Image("/imagenes/ic_en_proceso.png"));
        ivTerminados.setImage(new Image("/imagenes/ic_terminados.png"));
        ivAplicaciones.setImage(new Image("/imagenes/ic_agregar.png"));
        ivPrecios.setImage(new Image("/imagenes/ic_precios.png"));
    }

}
