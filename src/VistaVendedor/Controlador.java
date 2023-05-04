/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaVendedor;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edin1
 */
public class Controlador {

    public int Nofactura(Connection cn) {
        int Nofactura = 1;
        try {
            String consulta = "Select idFactura from generalfactura ORDER BY idFactura desc limit 1;";
            Statement ps = cn.createStatement();
            ResultSet rs = ps.executeQuery(consulta);
            if (rs.next()) {
                Nofactura = rs.getInt("idFactura");
                Nofactura+=1;
                System.out.println(Nofactura);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Nofactura;
    }

    public void pdf(Connection cn, String codigo) throws FileNotFoundException, DocumentException {

        FileOutputStream gen = new FileOutputStream("Reporte-detallefactura-" + codigo + ".pdf");
        Document documento = new Document();

        PdfWriter.getInstance(documento, gen);
        documento.open();

        String consulta = "select v.caja,f.idFactura,c.nit,c.nombre,DATE_FORMAT(f.fecha,'%d-%m-%y'),f.total from generalfactura f join cliente c on (f.codigoCliente=c.codigo) join vendedores v on(f.codigoVendedor=v.codigo) WHERE f.idFactura='" + codigo + "'; ";
        try {
            Statement pr = cn.createStatement();
            ResultSet rs = pr.executeQuery(consulta);
            if (rs.next()) {
                Paragraph parrafo = new Paragraph("Blue Mall\nCaja: " + rs.getInt(1) + "\nNúmero de factura: " + rs.getInt(2) + "\nNIT: " + rs.getInt(3) + "\nCliente: " + rs.getString(4)
                        + "\nFecha " + rs.getString(5) + "\nTotal venta: " + rs.getDouble(6));
                parrafo.setAlignment(1);
                documento.add(parrafo);
                documento.add(new Paragraph("\n"));
            }
            pr.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("aqui");
        }

        String consulta1 = "select * FROM detallefactura WHERE idFactura='"+codigo+"'";
        try {
            Statement pr = cn.createStatement();
            ResultSet rs = pr.executeQuery(consulta1);
            while (rs.next()) {
                documento.add(new Paragraph("Código: " + rs.getInt("codigoProducto")));
                documento.add(new Paragraph("Nombre: " + rs.getString("nombre")));
                documento.add(new Paragraph("Cantidad: " + rs.getInt("cantidad")));
                documento.add(new Paragraph("Precio: " + rs.getDouble("precio")));
                documento.add(new Paragraph("Subtotal: " + rs.getDouble("subtotal")));
                documento.add(new Paragraph("\n\n"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        documento.close();
        JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");
        try {
            File productos_doc = new File("Reporte-detallefactura-" + codigo + ".pdf");
            Desktop.getDesktop().open(productos_doc);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void guardarGeneral(int idFactura, int codigoCliente, double total, int vendedor, Connection cn) {
        try {
            String Consulta = "INSERT INTO generalfactura (idFactura, codigoCliente,total,codigoVendedor) VALUES(?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(Consulta);
            pst.setInt(1, idFactura);
            pst.setInt(2, codigoCliente);
            pst.setDouble(3, total);
            pst.setInt(4, vendedor);
            pst.executeUpdate();
            pst.close();
            JOptionPane.showMessageDialog(null, "Guardado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Guardar");
            e.printStackTrace();
        }

    }

    public void detalleFactura(int idFactura, int codigoProducto, int cantidad, double precio, double subtotal, String nombre, Connection cn) {
        try {
            String Consulta = "INSERT INTO detallefactura (idFactura, codigoProducto,cantidad,precio,subtotal,nombre) VALUES(?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(Consulta);
            pst.setInt(1, idFactura);
            pst.setInt(2, codigoProducto);
            pst.setInt(3, cantidad);
            pst.setDouble(4, precio);
            pst.setDouble(5, subtotal);
            pst.setString(6, nombre);
            pst.executeUpdate();
            pst.close();
            JOptionPane.showMessageDialog(null, "Guardado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Guardar");
            e.printStackTrace();
        }

    }

    DefaultTableModel TablaFactura(Connection cn, int factura, String nombre, int nit) {
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            String consulta = "select f.idFactura,c.nit,c.nombre,f.total,DATE_FORMAT(f.fecha,'%d-%m-%y') AS fecha FROM generalfactura f JOIN cliente c on (f.codigoCliente=c.codigo) where c.nombre='" + nombre + "' or c.nit='" + nit + "' or f.idFactura='" + factura + "'";
            ArrayList<Modelo> Datos = new ArrayList<>();
            Statement pr = cn.createStatement();
            ResultSet rs = pr.executeQuery(consulta);
            while (rs.next()) {
                Datos.add(new Modelo(rs.getInt("idFactura"), rs.getInt("nit"), rs.getDouble("total"), rs.getString("nombre"), rs.getString("fecha")));
            }
            rs.close();
            pr.close();
            String[] Titulo = {"idFactura", "nit", "total", "nombre", "fecha"};
            modelo.setColumnIdentifiers(Titulo);
            for (int x = 0; x < Datos.size(); x++) {
                String[] datos = new String[5];
                datos[0] = String.valueOf(Datos.get(x).getIdFactura());;
                datos[1] = String.valueOf(Datos.get(x).getNit());
                datos[2] = String.valueOf(Datos.get(x).getTotal());
                datos[3] = Datos.get(x).getNombre();
                datos[4] = Datos.get(x).getFecha();
                modelo.addRow(datos);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return modelo;
    }
}
