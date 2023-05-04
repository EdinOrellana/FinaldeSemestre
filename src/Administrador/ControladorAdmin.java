/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador;

import Cliente.Controlador;
import Cliente.Modelo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author edin1
 */
public class ControladorAdmin {

    DefaultTableModel TablaClientes(Connection cn, String Buscar) {
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            String consulta = "SELECT * from cliente where nombre like '%" + Buscar + "%'";
            ArrayList<Modelo> Datos = new ArrayList<>();
            Statement pr = cn.createStatement();
            ResultSet rs = pr.executeQuery(consulta);
            while (rs.next()) {
                Datos.add(new Modelo(rs.getInt("codigo"), rs.getInt("nit"), rs.getString("nombre"), rs.getString("correo"), rs.getString("genero")));
            }
            rs.close();
            pr.close();
            String[] Titulo = {"Codigo", "Nombre", "NIT", "correo", "Genero"};
            modelo.setColumnIdentifiers(Titulo);
            for (int x = 0; x < Datos.size(); x++) {
                String[] datos = new String[5];
                datos[0] = String.valueOf(x + 1);
                datos[1] = Datos.get(x).getNombre();
                datos[2] = String.valueOf(Datos.get(x).getNit());
                datos[3] = Datos.get(x).getCorreo();
                datos[4] = Datos.get(x).getGenero();
                modelo.addRow(datos);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return modelo;
    }

    DefaultTableModel TablaProducto(Connection cn, String Buscar) {
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            String consulta = "SELECT * from producto where nombre like '%" + Buscar + "%'";
            ArrayList<Producto.Modelo> Datos = new ArrayList<>();
            Statement pr = cn.createStatement();
            ResultSet rs = pr.executeQuery(consulta);
            while (rs.next()) {
                Datos.add(new Producto.Modelo(rs.getInt("codigo"), rs.getInt("cantidad"), rs.getDouble("precio"), rs.getString("nombre"), rs.getString("descripcion")));
            }
            rs.close();
            pr.close();
            String[] Titulo = {"Codigo", "nombre", "descripcion", "cantidad", "precio"};
            modelo.setColumnIdentifiers(Titulo);
            for (int x = 0; x < Datos.size(); x++) {
                String[] datos = new String[5];
                datos[0] = String.valueOf(x + 1);
                datos[1] = Datos.get(x).getNombre();
                datos[2] = Datos.get(x).getDescripcion();
                datos[3] = String.valueOf(Datos.get(x).getCantidad());
                datos[4] = String.valueOf(Datos.get(x).getPrecio());
                modelo.addRow(datos);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return modelo;
    }

    DefaultTableModel TablaSurcursal(Connection cn, String Buscar) {
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            String consulta = "SELECT * from sucursales where nombre like '%" + Buscar + "%'";
            ArrayList<Sucursal.Modelo> Datos = new ArrayList<>();
            Statement pr = cn.createStatement();
            ResultSet rs = pr.executeQuery(consulta);
            while (rs.next()) {
                Datos.add(new Sucursal.Modelo(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("direccion"), rs.getString("correo"), rs.getString("telefono")));
            }
            rs.close();
            pr.close();
            String[] Titulo = {"Codigo", "Nombre", "Direccion", "correo", "Telefono"};
            modelo.setColumnIdentifiers(Titulo);
            for (int x = 0; x < Datos.size(); x++) {
                String[] datos = new String[5];
                datos[0] = String.valueOf(x + 1);
                datos[1] = Datos.get(x).getNombre();
                datos[2] = Datos.get(x).getDireccion();
                datos[3] = Datos.get(x).getCorreo();
                datos[4] = Datos.get(x).getTelefono();
                modelo.addRow(datos);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return modelo;
    }

    DefaultTableModel TablaVendedores(Connection cn, String Buscar) {
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            String consulta = "SELECT * from vendedores where nombre like '%" + Buscar + "%' and rol!=1";
            ArrayList<Vendedor.Modelo> Datos = new ArrayList<>();
            Statement pr = cn.createStatement();
            ResultSet rs = pr.executeQuery(consulta);
            while (rs.next()) {
                Datos.add(new Vendedor.Modelo(rs.getInt("codigo"), rs.getInt("caja"), rs.getInt("ventas"), rs.getString("nombre"), rs.getString("genero"), rs.getString("password")));
            }
            rs.close();
            pr.close();
            String[] Titulo = {"Codigo", "Nombre", "caja", "ventas", "Genero"};
            modelo.setColumnIdentifiers(Titulo);
            for (int x = 0; x < Datos.size(); x++) {
                String[] datos = new String[5];
                datos[0] = String.valueOf(x + 1);
                datos[1] = Datos.get(x).getNombre();
                datos[2] = String.valueOf(Datos.get(x).getCaja());
                datos[3] = String.valueOf(Datos.get(x).getVentas());
                datos[4] = Datos.get(x).getGenero();
                modelo.addRow(datos);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return modelo;
    }

    void Eliminar(int codigo, String Tabla, Connection cn) {
        try {
            String consulta = "DELETE from " + Tabla + " WHERE codigo =" + codigo;
            PreparedStatement pst = cn.prepareStatement(consulta);
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
    public void pdf(Connection cn, String Tabla) throws FileNotFoundException, DocumentException {

        FileOutputStream gen = new FileOutputStream("Reporte-" + Tabla + ".pdf");
        Document documento = new Document();

        PdfWriter.getInstance(documento, gen);
        documento.open();

        Paragraph parrafo = new Paragraph("Reporte de " + Tabla + " Base de datos");
        parrafo.setAlignment(1);
        documento.add(parrafo);
        documento.add(new Paragraph("\n"));

        String consulta = "select * from " + Tabla;
        try {

            Statement pr = cn.createStatement();
            ResultSet rs = pr.executeQuery(consulta);
            switch (Tabla) {
                case "cliente":
                    while (rs.next()) {
                        documento.add(new Paragraph("Código: " + rs.getInt(1)));
                        documento.add(new Paragraph("Nombre: " + rs.getString(2)));
                        documento.add(new Paragraph("NIT: " + rs.getInt(3)));
                        documento.add(new Paragraph("Correo: " + rs.getInt(4)));
                        documento.add(new Paragraph("Genero: " + rs.getString(5)));
                        documento.add(new Paragraph("\n\n"));
                    }
                    break;
                case "producto":
                    while (rs.next()) {
                        documento.add(new Paragraph("Código: " + rs.getInt(1)));
                        documento.add(new Paragraph("Nombre: " + rs.getString(2)));
                        documento.add(new Paragraph("Descripcion: " + rs.getString(3)));
                        documento.add(new Paragraph("Cantidad: " + rs.getInt(4)));
                        documento.add(new Paragraph("Precio: " + rs.getDouble(5)));
                        documento.add(new Paragraph("\n\n"));
                    }
                    break;
                case "sucursales":
                    while (rs.next()) {
                        documento.add(new Paragraph("Código: " + rs.getInt(1)));
                        documento.add(new Paragraph("Nombre: " + rs.getString(2)));
                        documento.add(new Paragraph("Direccion: " + rs.getString(3)));
                        documento.add(new Paragraph("correo: " + rs.getString(4)));
                        documento.add(new Paragraph("Telefono: " + rs.getString(5)));
                        documento.add(new Paragraph("\n\n"));
                    }
                    break;
                case "vendedores":
                    while (rs.next()) {
                        documento.add(new Paragraph("Código: " + rs.getInt(1)));
                        documento.add(new Paragraph("Nombre: " + rs.getString(2)));
                        documento.add(new Paragraph("Caja: " + rs.getInt(3)));
                        documento.add(new Paragraph("Ventas: " + rs.getInt(4)));
                        documento.add(new Paragraph("Genero: " + rs.getString(5)));
                        documento.add(new Paragraph("\n\n"));
                    }
                    break;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        documento.close();
        JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");
        try {
            File productos_doc = new File("Reporte-" + Tabla + ".pdf");
            Desktop.getDesktop().open(productos_doc);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
      public void carga_masiva(String Tabla, Connection cn) throws FileNotFoundException, IOException, ParseException {

        String archivo_retorno = leerarchivo();

        JsonParser parse = new JsonParser();
        JsonArray matriz = parse.parse(archivo_retorno).getAsJsonArray();

        for (int i = 0; i < matriz.size(); i++) {
            JsonObject objeto = matriz.get(i).getAsJsonObject();
            switch (Tabla) {
                case "cliente":
                    Controlador controladorCliente = new Controlador();
                    controladorCliente.guardar(objeto.get("nit").getAsInt(), objeto.get("nombre").getAsString(), objeto.get("correo").getAsString(), objeto.get("Genero").getAsString(), cn);
                    break;
                case "b":
                    break;
                case "c":
                    break;
                case "d":
                    break;
            }
        }

    }

    private String leerarchivo() {

        JPanel c1 = new JPanel();
        JFileChooser fc = new JFileChooser();
        int op = fc.showOpenDialog(c1);
        String content = "";
        if (op == JFileChooser.APPROVE_OPTION) {

            File pRuta = fc.getSelectedFile();
            String ruta = pRuta.getAbsolutePath();
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;

            try {
                archivo = new File(ruta);
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);
                String linea = "";

                while ((linea = br.readLine()) != null) {

                    content += linea + "\n";
                }
                return content;

            } catch (FileNotFoundException ex) {
                String resp = (String) JOptionPane.showInputDialog(null, "No se encontro el archivo");
            } catch (IOException ex) {
                String resp = (String) JOptionPane.showInputDialog(null, "No se pudo abrir el archivo");
            } finally {
                try {
                    if (null != fr) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    String resp = (String) JOptionPane.showInputDialog(null, "No se encontro el archivo");
                    return "";
                }

            }
            return content;

        }
        return null;

    }

}
