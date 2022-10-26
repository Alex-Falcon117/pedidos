package main;

/**
 *
 * @author Kelyx
 */
import java.awt.Image;
import java.io.IOException;
import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application {
    
   // Image icon = new Image("/imagenes/icon.png");
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
       
        scene = new Scene(loadFXML("ventana_principal"), 600, 400);  
        stage.setTitle("Pedidos");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new javafx.scene.image.Image("/imagenes/icon.png"));
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppMain.class.getResource("/vistas/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
