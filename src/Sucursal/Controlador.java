/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sucursal;



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


   public void guardar( String nombre, String direccion, String correo, String telefono, Connection cn) {
        try {
            String Consulta = "INSERT INTO sucursales (nombre, direccion,correo,telefono) VALUES(?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(Consulta);
            pst.setString(1, nombre);
            pst.setString(2, direccion);
            pst.setString(3, correo);
            pst.setString(4, telefono);
            pst.executeUpdate();
            pst.close();
            JOptionPane.showMessageDialog(null, "Guardado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Guardar");
            e.printStackTrace();
        }

    }

    void Actualizar(int codigo, String nombre, String direccion, String correo, String telefono, Connection cn) {
        try {
            String Consulta = "update sucursales set nombre='" + nombre + "',direccion='" + direccion + "',correo='" + correo + "',telefono='" + telefono + "' where codigo='" + codigo + "'";
            PreparedStatement pr = cn.prepareStatement(Consulta);
            pr.executeUpdate();
            pr.close();
            JOptionPane.showMessageDialog(null, "Actualizado");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No Actualizado");
        }
    }
    
    public ArrayList obtener(Connection cn, String Nombre, String Direccion) {
        ArrayList<Modelo> Datos = new ArrayList();
        try {
            String consulta = "SELECT * FROM sucursales WHERE nombre = '" + Nombre + "' and direccion = '" + Direccion + "'";
            Statement ps = cn.createStatement();
            ResultSet rs = ps.executeQuery(consulta);
            if (rs.next()) {
               Datos.add(new Modelo(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("direccion"), rs.getString("correo"), rs.getString("telefono")));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Datos;
    }
   }
