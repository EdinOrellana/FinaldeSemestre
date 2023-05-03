/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Producto;


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

    

   public void guardar(int cantidad, double precio, String nombre, String descripcion, Connection cn) {
        try {
            String Consulta = "INSERT INTO producto (nombre, descripcion,cantidad,precio) VALUES(?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(Consulta);
            pst.setString(1, nombre);
            pst.setString(2, descripcion);
            pst.setInt(3, cantidad);
            pst.setDouble(4, precio);
            pst.executeUpdate();
            pst.close();
            JOptionPane.showMessageDialog(null, "Guardado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Guardar");
            e.printStackTrace();
        }

    }

    void Actualizar(int codigo, int cantidad, double precio, String nombre, String descripcion, Connection cn) {
        try {
            String Consulta = "update producto set cantidad='" + cantidad + "',precio='" + precio + "',nombre='" + nombre + "',descripcion='" + descripcion + "' where codigo='" + codigo + "'";
            PreparedStatement pr = cn.prepareStatement(Consulta);
            pr.executeUpdate();
            pr.close();
            JOptionPane.showMessageDialog(null, "Actualizado");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No Actualizado");
        }
    }
    
    public ArrayList obtener(Connection cn, String Nombre, String descripcion) {
        ArrayList<Modelo> Datos = new ArrayList();
        try {
            String consulta = "SELECT * FROM producto WHERE nombre = '" + Nombre + "' and descripcion = '" + descripcion + "'";
            Statement ps = cn.createStatement();
            ResultSet rs = ps.executeQuery(consulta);
            if (rs.next()) {
                Datos.add(new Modelo(rs.getInt("codigo"), rs.getInt("cantidad"), rs.getDouble("precio"),rs.getString("nombre"), rs.getString("descripcion")));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Datos;
    }
   }
