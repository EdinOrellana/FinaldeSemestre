/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author edin1
 */
public class conexion {

    String Usuario = "root";
    String Contraseña ="";
    String Servidor = "localhost";
    Connection connection = null;

    public Connection Conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + Servidor + "/intecapfinal", Usuario, Contraseña);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection Desconectar() {
        connection = null;
        if (connection != null) {
            JOptionPane.showMessageDialog(null, "No se Pudo desconectar");
        }
        return connection;
    }
}
