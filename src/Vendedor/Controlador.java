/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vendedor;

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

   public void guardar( int caja, int ventas, String nombre, String genero, String password, Connection cn) {
        try {
            String Consulta = "INSERT INTO vendedores (nombre, genero,ventas,caja,password) VALUES(?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(Consulta);
            pst.setString(1, nombre);
            pst.setString(2, genero);
            pst.setInt(3, ventas);
            pst.setInt(4, caja);
            pst.setString(5, password);
            pst.executeUpdate();
            pst.close();
            JOptionPane.showMessageDialog(null, "Guardado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Guardar");
            e.printStackTrace();
        }

    }

    void Actualizar(int codigo, int caja, int ventas, String nombre, String genero, Connection cn) {
        try {
            String Consulta = "update vendedores set caja='" + caja + "',ventas='" + ventas + "',nombre='" + nombre + "',genero='" + genero + "' where codigo='" + codigo + "'";
            PreparedStatement pr = cn.prepareStatement(Consulta);
            pr.executeUpdate();
            pr.close();
            JOptionPane.showMessageDialog(null, "Actualizado");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No Actualizado");
        }
    }
    
    public ArrayList obtener(Connection cn, String Nombre, String caja) {
        ArrayList<Modelo> Datos = new ArrayList();
        try {
            String consulta = "SELECT * FROM vendedores WHERE nombre = '" + Nombre + "' and caja = '" + caja + "'";
            Statement ps = cn.createStatement();
            ResultSet rs = ps.executeQuery(consulta);
            if (rs.next()) {
                 Datos.add(new Modelo(rs.getInt("codigo"), rs.getInt("caja"), rs.getInt("ventas"), rs.getString("nombre"), rs.getString("genero"), rs.getString("password")));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Datos;
    }
   }
