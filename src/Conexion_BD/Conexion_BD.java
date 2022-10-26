package Conexion_BD;

import controlador.Agregar_juegosController;
import java.io.IOException;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.management.InstanceAlreadyExistsException;
import javax.swing.JOptionPane;
import main.AppMain;

public class Conexion_BD {

    static final String URL = "jdbc:mysql://localhost/clientes_zonatecno";
    static final String USUARIO = "root";
    static final String CONTRASENA = "";
    //Conexión Local

    //Insertar los datos a la BD Clientes
    public static boolean insertarDatosCliente(String estado, String nombreApellido, String celular, String so, String programas, String juegos, int total) {
        try {
            Connection CN = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            PreparedStatement PS = CN.prepareStatement("insert into clientes values(?,?,?,?,?,?,?,?)");

            PS.setString(1, "0");
            PS.setString(2, estado);
            PS.setString(3, nombreApellido);
            PS.setString(4, celular);
            PS.setString(5, so);
            PS.setString(6, programas);
            PS.setString(7, juegos);
            PS.setInt(8, total);

            PS.executeUpdate();

            JOptionPane.showMessageDialog(null, "Datos guardados");
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible conectarse a la Base de Datos" + "\n" + "Verifique si los servicios de Apache y MySQL estan activos");
            return false;

        }

    }

    //Muestra los datos de los clientes
    public static void mostrarDatosCliente() {
        try {
            Connection CN = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            PreparedStatement pst = CN.prepareStatement("select * from clientes where Estado = ?");

            pst.setString(2, "pendiente");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nombreCliente = rs.getString("NombreApellido");
                // Object[] so = rs.getObject(CONTRASENA, type);
                //txt_grupo.setText(rs.getString("Grupo"));
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);

        }

    }

    //Actualiza los datos de los clientes
    /* public static void actualizarDatosCliente(String nombreApellido, Object[] so, Object[] programas, Object[] juegos) {

        try {
            Connection CN = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            PreparedStatement pst = CN.prepareStatement("update clientes set instalSO=?, instalProgramas=?, instalJuegos=? where NombreApellido=" + nombreApellido);

            pst.setObject(1, so);
            pst.setObject(2, programas);
            pst.setObject(3, juegos);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Datos actualizados con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }

    }*/

 /* public static void eliminarDatosClientes(String nombreApellido) {

        try {
            Connection CN = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            PreparedStatement pst = CN.prepareStatement("delete from clientes where NombreApellido=?");

            pst.setString(3, nombreApellido);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Datos eliminados con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }*/
    /////////////////////////////////Precios////////////////////////////////////
    //Igresa los precios
    public static void insertarDatosPrecio(int so, int programas, int juegos) {
        try {
            Connection CN = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            PreparedStatement PS = CN.prepareStatement("insert into precios values(?,?,?,?)");

            PS.setString(1, "1");
            PS.setInt(2, so);
            PS.setInt(3, programas);
            PS.setInt(4, juegos);

            PS.executeUpdate();

            JOptionPane.showMessageDialog(null, "Datos guardados");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible conectarse a la Base de Datos" + "\n" + "Verifique si los servicios de Apache y MySQL estan activos");

        }
    }

    //Actualiza los precios
    public static void actualizarDatosPrecios(int so, int programas, int juegos) {

        try {
            Connection CN = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            PreparedStatement pst = CN.prepareStatement("update precios set precioSO=?, precioProgramas=?, precioJuegos=? where ID=1");

            pst.setObject(1, so);
            pst.setObject(2, programas);
            pst.setObject(3, juegos);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Precios actualizados con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No es posible conectarse a la Base de Datos" + "\n" + "Verifique si los servicios de Apache y MySQL estan activos");

        }

    }

    //Muestra los precios
    public static int[] mostrarDatosPrecios() {
        int[] precios = new int[4];
        try {
            Connection CN = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            PreparedStatement pst = CN.prepareStatement("select * from precios where ID = 1");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                precios[0] = rs.getInt("precioSO");
                precios[1] = rs.getInt("precioProgramas");
                precios[2] = rs.getInt("precioJuegos");
                precios[3] = 1;

                // return precios;
            } else {
                precios[3] = 0;
                JOptionPane.showMessageDialog(null, "Precios no disponible");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No es posible conectarse a la Base de Datos" + "\n" + "Verifique si los servicios de Apache y MySQL estan activos");

        }
        return precios;
    }

    //////////////////Aplicaciones//////////////////////////////////////////////
    //Ingresa toda la informacion de una aplicacion
    public static boolean insertarDatosAplicacion(String nombre, String tipo, String cateRequisito, String tamaño, String so, String minProcesador, String minRam, String recomProcesador, String recomRam) {
        try {
            Connection CN = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            PreparedStatement PS = CN.prepareStatement("insert into aplicaciones values(?,?,?,?,?,?,?,?,?,?)");

            PS.setString(1, "0");
            PS.setString(2, nombre);
            PS.setString(3, tipo);
            PS.setString(4, cateRequisito);
            PS.setString(5, tamaño);
            PS.setString(6, so);
            PS.setString(7, minProcesador);
            PS.setString(8, minRam);
            PS.setString(9, recomProcesador);
            PS.setString(10, recomRam);

            PS.executeUpdate();

            JOptionPane.showMessageDialog(null, "Datos guardados");
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible conectarse a la Base de Datos" + "\n" + "Verifique si los servicios de Apache y MySQL estan activos");

            return false;
        }
    }

    //Obtine una lista de todos los nombre de las aplicaciones en la BD. Dependiendo del tipo que reciba como parametro
    public static ObservableList<String> mostrarNombreAplicaciones(String tipo) {
        ObservableList<String> nombresUsuario = FXCollections.observableArrayList();
        try {
            Connection CN = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            PreparedStatement pst = CN.prepareStatement("select * from aplicaciones where tipo =" + tipo);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                nombresUsuario.add(rs.getString("nombre"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);

        }

        return nombresUsuario;
    }

    //Obtiene toda la informacion de la BD aplicaciones. Recibe como parametro el nombre de una aplicacion
    public static String[] mostrarDatosAplicaciones(String nombre) {
        String[] aplicaciones = new String[7];
        try {
            Connection CN = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            PreparedStatement pst = CN.prepareStatement("select * from aplicaciones where nombre =" + nombre);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                aplicaciones[0] = rs.getString("categoriaRequisito");
                aplicaciones[1] = rs.getString("tamanoGB");
                aplicaciones[2] = rs.getString("SO");
                aplicaciones[3] = rs.getString("minProcesador");
                aplicaciones[4] = rs.getString("minRAM");
                aplicaciones[5] = rs.getString("recomProcesador");
                aplicaciones[6] = rs.getString("recomRAM");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }

        return aplicaciones;
    }

    ///////////////////Tareas///////////////////////////////////////////////////
    //Retorna la lista de nombres de usuario dependiendo del estado de la tarea
    public static ObservableList<String> mostrarNombreClientes(String estado) {
        ObservableList<String> nombresUsuario = FXCollections.observableArrayList();
        try {
            Connection CN = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            PreparedStatement pst = CN.prepareStatement("select NombreApellido from clientes where Estado =" + estado);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                nombresUsuario.add(rs.getString("NombreApellido"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No es posible conectarse a la Base de Datos" + "\n" + "Verifique si los servicios de Apache y MySQL estan activos");

        }

        return nombresUsuario;
    }

    //Actualiza el estado de la tarea
    public static void acrualizarTarea(String nombre, String estado) {
        try {
            Connection CN = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            PreparedStatement pst = CN.prepareStatement("update clientes set Estado=? where NombreApellido=" + nombre);

            pst.setString(1, estado);
            pst.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }

    }

    //Retorna un arreglo con toda la informacion del cliente dependiendo de que reciba como parametro
    //'pendiente', 'en proceso' o 'terminados'
    public static String[] mostrarAplicacionesCliente(String nombre) {

        String[] pedidos = new String[6];
        try {
            Connection CN = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            PreparedStatement pst = CN.prepareStatement("select NombreApellido, instalSO, instalProgramas, instalJuegos, Total, celular from clientes where NombreApellido =" + nombre);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                pedidos[0] = rs.getString("instalSO");
                pedidos[1] = rs.getString("instalProgramas");
                pedidos[2] = rs.getString("instalJuegos");
                pedidos[3] = rs.getString("Total");
                pedidos[4] = rs.getString("NombreApellido");
                pedidos[5] = rs.getString("celular");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }

        return pedidos;
    }

}
