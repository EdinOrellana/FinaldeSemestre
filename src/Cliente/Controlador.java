/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author edin1
 */
public class Controlador {

    public void guardar(int nit, String nombre, String correo, String genero, Connection cn) {
        try {
            String Consulta = "INSERT INTO cliente (nombre, nit,correo,genero) VALUES(?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(Consulta);
            pst.setString(1, nombre);
            pst.setInt(2, nit);
            pst.setString(3, correo);
            pst.setString(4, genero);
            pst.executeUpdate();
            pst.close();
            JOptionPane.showMessageDialog(null, "Guardado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Guardar");
            e.printStackTrace();
        }

    }

    void Actualizar(int codigo, int nit, String nombre, String correo, String genero, Connection cn) {
        try {
            String Consulta = "update cliente set nit='" + nit + "',nombre='" + nombre + "',correo='" + correo + "',genero='" + genero + "' where codigo='" + codigo + "'";
            PreparedStatement pr = cn.prepareStatement(Consulta);
            pr.executeUpdate();
            pr.close();
            JOptionPane.showMessageDialog(null, "Actualizado");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No Actualizado");
        }
    }

    public ArrayList obtener(Connection cn, String Nombre, String nit) {
        ArrayList<Modelo> Datos = new ArrayList();
        try {
            String consulta = "SELECT * FROM cliente WHERE nombre = '" + Nombre + "' and nit = '" + nit + "'";
            Statement ps = cn.createStatement();
            ResultSet rs = ps.executeQuery(consulta);
            if (rs.next()) {
                Datos.add(new Modelo(rs.getInt("codigo"), rs.getInt("nit"), rs.getString("nombre"), rs.getString("correo"), rs.getString("genero")));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Datos;
    }

}
