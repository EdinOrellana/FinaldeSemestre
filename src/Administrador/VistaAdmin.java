/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador;

import static Administrador.Principal.Escritorio;
import Conexion.conexion;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author edin1
 */
public class VistaAdmin extends javax.swing.JInternalFrame {

    /**
     * Creates new form Vista
     */
    public VistaAdmin() {
        initComponents();
        cargarTabla();
    }
    Vendedor.Controlador controladorVendedor = new Vendedor.Controlador();
    Sucursal.Controlador controladorSucursal = new Sucursal.Controlador();
    Cliente.Controlador controladorClinte = new Cliente.Controlador();
    Producto.Controlador controladorProducto = new Producto.Controlador();
    ControladorAdmin controladoradmin = new ControladorAdmin();
    conexion cc = new conexion();
    Connection cn = cc.Conectar();

    void TablaClientes(String Buscar) {
        TablaClientes.setModel(controladoradmin.TablaClientes(cn, Buscar));
    }

    void TablaProducto(String Buscar) {
        TablaProducto.setModel(controladoradmin.TablaProducto(cn, Buscar));
    }

    void TablaSurcursal(String Buscar) {
        TablaSurcursal.setModel(controladoradmin.TablaSurcursal(cn, Buscar));
    }

    void TablaVendedores(String Buscar) {
        TablaVendedores.setModel(controladoradmin.TablaVendedores(cn, Buscar));
    }

    void Cerrarsesion() {
        Escritorio.remove(this);
        Principal.Bandera = true;
    }

    void cargarTabla() {
        TablaClientes("");
        TablaProducto("");
        TablaSurcursal("");
        TablaVendedores("");
    }

    void EditarCliente(boolean bandera) {
        int fila = TablaClientes.getSelectedRow();
        if (fila < -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Fila");
        } else {
            ArrayList<Cliente.Modelo> Datos = controladorClinte.obtener(cn, TablaClientes.getValueAt(fila, 1).toString(), TablaClientes.getValueAt(fila, 2).toString());
            if (Datos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cliente No Encontrado");
            } else {
                if (bandera) {
                    controladoradmin.Eliminar(Datos.get(0).getCodigo(), "cliente", cn);
                    TablaClientes("");
                } else if (BanderaBotonCliente) {
                    Escritorio.add(ClienteCrear);
                    ClienteCrear.show();
                    //   actualizarCliente(TablaClientes.getSelectedRow());
                    BanderaBotonCliente = false;
                    BanderaGuardarActualizarCliente = false;
                    ClienteCrear.lblTitulo.setText("Actualizar Vendedor");
                    ClienteCrear.jbtnGuardar_Actualizar.setText("Actualizar");

                    ClienteCrear.jtflcodigo.setText(String.valueOf(Datos.get(0).getCodigo()));
                    ClienteCrear.jtflNombre.setText(Datos.get(0).getNombre());
                    ClienteCrear.jtflGenero.setText(Datos.get(0).getGenero());
                    ClienteCrear.jtflCorreo.setText(Datos.get(0).getCorreo());
                    ClienteCrear.jtflNIT.setText(String.valueOf(Datos.get(0).getNit()));

                }
            }
        }
    }

    void EditarProducto(boolean bandera) {
        int fila = TablaProducto.getSelectedRow();
        if (fila < -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Fila");
        } else {
            ArrayList<Producto.Modelo> Datos = controladorProducto.obtener(cn, TablaProducto.getValueAt(fila, 1).toString(), TablaProducto.getValueAt(fila, 2).toString());
            if (Datos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Producto No Encontrado");
            } else {
                if (bandera) {
                    controladoradmin.Eliminar(Datos.get(0).getCodigo(), "producto", cn);
                    TablaProducto("");
                } else if (BanderaBotonProducto) {
                    Escritorio.add(ProductoCrear);
                    ProductoCrear.show();
                    BanderaBotonProducto = false;
                    BanderaGuardarActualizarProducto = false;
                    ProductoCrear.lblTitulo.setText("Actualizar Producto");
                    ProductoCrear.jbtnGuardar_Actualizar.setText("Actualizar");

                    ProductoCrear.jtflcodigo.setText(String.valueOf(Datos.get(0).getCodigo()));
                    ProductoCrear.jtflNombre.setText(Datos.get(0).getNombre());
                    ProductoCrear.jtflCantidad.setText(String.valueOf(Datos.get(0).getCantidad()));
                    ProductoCrear.jtflPrecio.setText(String.valueOf(Datos.get(0).getPrecio()));
                    ProductoCrear.jtflDescripcion.setText(Datos.get(0).getDescripcion());

                }
            }
        }
    }

    void EditarSucursal(boolean bandera) {
        int fila = TablaSurcursal.getSelectedRow();
        if (fila < -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Fila");
        } else {
            ArrayList<Sucursal.Modelo> Datos = controladorSucursal.obtener(cn, TablaSurcursal.getValueAt(fila, 1).toString(), TablaSurcursal.getValueAt(fila, 2).toString());
            if (Datos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Sucursal No Encontrada");
            } else {
                if (bandera) {
                    controladoradmin.Eliminar(Datos.get(0).getCodigo(), "sucursales", cn);
                    TablaSurcursal("");
                } else if (BanderaBotonSucursal) {
                    Escritorio.add(SucursalCrear);
                    SucursalCrear.show();
                    //  actualizarSucursales(TablaSurcursal.getSelectedRow());
                    BanderaBotonSucursal = false;
                    BanderaGuardarActualizarSucursal = false;
                    SucursalCrear.lblTitulo.setText("Actualizar Sucursal");
                    SucursalCrear.jbtnGuardar_Actualizar.setText("Actualizar");

                    SucursalCrear.jtflcodigo.setText(String.valueOf(Datos.get(0).getCodigo()));
                    SucursalCrear.jtflNombre.setText(Datos.get(0).getNombre());
                    SucursalCrear.jtflDireccion.setText(String.valueOf(Datos.get(0).getDireccion()));
                    SucursalCrear.jtflTelefono.setText(String.valueOf(Datos.get(0).getTelefono()));
                    SucursalCrear.jtflCorreo.setText(Datos.get(0).getCorreo());
                }
            }
        }
    }

    void EditarVendedor(boolean bandera) {
        int fila = TablaVendedores.getSelectedRow();
        if (fila < -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Fila");
        } else {
            ArrayList<Vendedor.Modelo> Datos = controladorVendedor.obtener(cn, TablaVendedores.getValueAt(fila, 1).toString(), TablaVendedores.getValueAt(fila, 2).toString());
            if (Datos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vendedor No Encontrada");
            } else {
                if (bandera) {
                    controladoradmin.Eliminar(Datos.get(0).getCodigo(), "vendedores", cn);
                    TablaVendedores("");
                } else if (BanderaBotonVendedor) {
                    Escritorio.add(vendedorCrear);
                    vendedorCrear.show();
                    BanderaBotonVendedor = false;
                    BanderaGuardarActualizarVendedor = false;
                    vendedorCrear.lblTitulo.setText("Actualizar Vendedor");
                    vendedorCrear.jbtnGuardar_Actualizar.setText("Actualizar");

                    vendedorCrear.jtflcodigo.setText(String.valueOf(Datos.get(0).getCodigo()));
                    vendedorCrear.jtflNombre.setText(Datos.get(0).getNombre());
                    vendedorCrear.jtflCaja.setText(String.valueOf(Datos.get(0).getCaja()));
                    vendedorCrear.jtflVentas.setText(String.valueOf(Datos.get(0).getVentas()));
                    vendedorCrear.jtflGenero.setText(Datos.get(0).getGenero());

                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpanelVendedor = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnCrearVendedor = new javax.swing.JButton();
        jbtnActualizaVendedor = new javax.swing.JButton();
        jbtnEliminarVendedor = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaVendedores = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jpanelCliente = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        btnCrearCliente = new javax.swing.JButton();
        jbtnActualizarCliente = new javax.swing.JButton();
        jbtnEliminarCliente = new javax.swing.JButton();
        jbtnClientes = new javax.swing.JButton();
        scrollPane2 = new java.awt.ScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaClientes = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jpanelSucursal = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        btnCrearSucursal = new javax.swing.JButton();
        jbtnActualizarSucursal = new javax.swing.JButton();
        jbtnEliminarSucursal = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        scrollPane3 = new java.awt.ScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaSurcursal = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jpanelProducto = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        btnCrearProducto = new javax.swing.JButton();
        jbtnActualizarProducto = new javax.swing.JButton();
        jbtnEliminarProducto = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        scrollPane4 = new java.awt.ScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaProducto = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();

        jpanelVendedor.setBackground(new java.awt.Color(153, 204, 255));
        jpanelVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpanelVendedorMouseEntered(evt);
            }
        });

        jButton1.setText("Carga Maxima");

        btnCrearVendedor.setText("Crear");
        btnCrearVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearVendedorActionPerformed(evt);
            }
        });

        jbtnActualizaVendedor.setText("Actualizar");
        jbtnActualizaVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizaVendedorActionPerformed(evt);
            }
        });

        jbtnEliminarVendedor.setText("Eliminar");
        jbtnEliminarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarVendedorActionPerformed(evt);
            }
        });

        jButton5.setText("Exportar Listado a PDF");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        TablaVendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TablaVendedores);

        scrollPane1.add(jScrollPane1);

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelVendedorLayout = new javax.swing.GroupLayout(jpanelVendedor);
        jpanelVendedor.setLayout(jpanelVendedorLayout);
        jpanelVendedorLayout.setHorizontalGroup(
            jpanelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelVendedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelVendedorLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpanelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpanelVendedorLayout.createSequentialGroup()
                                    .addComponent(btnCrearVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpanelVendedorLayout.createSequentialGroup()
                                    .addComponent(jbtnActualizaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jbtnEliminarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jpanelVendedorLayout.setVerticalGroup(
            jpanelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelVendedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanelVendedorLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpanelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrearVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnActualizaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnEliminarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(212, 212, 212)
                        .addComponent(jButton2))
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Vendedores", jpanelVendedor);

        jpanelCliente.setBackground(new java.awt.Color(153, 204, 255));
        jpanelCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpanelClienteMouseEntered(evt);
            }
        });

        jButton6.setText("Carga Maxima");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        btnCrearCliente.setText("Crear");
        btnCrearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearClienteActionPerformed(evt);
            }
        });

        jbtnActualizarCliente.setText("Actualizar");
        jbtnActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarClienteActionPerformed(evt);
            }
        });

        jbtnEliminarCliente.setText("Eliminar");
        jbtnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarClienteActionPerformed(evt);
            }
        });

        jbtnClientes.setText("Exportar Listado a PDF");
        jbtnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClientesActionPerformed(evt);
            }
        });

        TablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TablaClientes);

        scrollPane2.add(jScrollPane2);

        jButton10.setText("Salir");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelClienteLayout = new javax.swing.GroupLayout(jpanelCliente);
        jpanelCliente.setLayout(jpanelClienteLayout);
        jpanelClienteLayout.setHorizontalGroup(
            jpanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelClienteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpanelClienteLayout.createSequentialGroup()
                                    .addComponent(btnCrearCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpanelClienteLayout.createSequentialGroup()
                                    .addComponent(jbtnActualizarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jbtnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jpanelClienteLayout.setVerticalGroup(
            jpanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanelClienteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrearCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnActualizarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(212, 212, 212)
                        .addComponent(jButton10))
                    .addComponent(scrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Clientes", jpanelCliente);

        jpanelSucursal.setBackground(new java.awt.Color(153, 204, 255));
        jpanelSucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpanelSucursalMouseEntered(evt);
            }
        });

        jButton7.setText("Carga Maxima");

        btnCrearSucursal.setText("Crear");
        btnCrearSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearSucursalActionPerformed(evt);
            }
        });

        jbtnActualizarSucursal.setText("Actualizar");
        jbtnActualizarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarSucursalActionPerformed(evt);
            }
        });

        jbtnEliminarSucursal.setText("Eliminar");
        jbtnEliminarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarSucursalActionPerformed(evt);
            }
        });

        jButton11.setText("Exportar Listado a PDF");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        TablaSurcursal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(TablaSurcursal);

        scrollPane3.add(jScrollPane3);

        jButton12.setText("Salir");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelSucursalLayout = new javax.swing.GroupLayout(jpanelSucursal);
        jpanelSucursal.setLayout(jpanelSucursalLayout);
        jpanelSucursalLayout.setHorizontalGroup(
            jpanelSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelSucursalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanelSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelSucursalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpanelSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpanelSucursalLayout.createSequentialGroup()
                                    .addComponent(btnCrearSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpanelSucursalLayout.createSequentialGroup()
                                    .addComponent(jbtnActualizarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jbtnEliminarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jpanelSucursalLayout.setVerticalGroup(
            jpanelSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelSucursalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanelSucursalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpanelSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrearSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanelSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnActualizarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnEliminarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(212, 212, 212)
                        .addComponent(jButton12))
                    .addComponent(scrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sucursales", jpanelSucursal);

        jpanelProducto.setBackground(new java.awt.Color(153, 204, 255));
        jpanelProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpanelProductoMouseEntered(evt);
            }
        });

        jButton8.setText("Carga Maxima");

        btnCrearProducto.setText("Crear");
        btnCrearProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearProductoActionPerformed(evt);
            }
        });

        jbtnActualizarProducto.setText("Actualizar");
        jbtnActualizarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarProductoActionPerformed(evt);
            }
        });

        jbtnEliminarProducto.setText("Eliminar");
        jbtnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarProductoActionPerformed(evt);
            }
        });

        jButton13.setText("Exportar Listado a PDF");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        TablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(TablaProducto);

        scrollPane4.add(jScrollPane4);

        jButton14.setText("Salir");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelProductoLayout = new javax.swing.GroupLayout(jpanelProducto);
        jpanelProducto.setLayout(jpanelProductoLayout);
        jpanelProductoLayout.setHorizontalGroup(
            jpanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelProductoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelProductoLayout.createSequentialGroup()
                                .addComponent(btnCrearProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpanelProductoLayout.createSequentialGroup()
                                .addComponent(jbtnActualizarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelProductoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton14)))
                .addContainerGap())
        );
        jpanelProductoLayout.setVerticalGroup(
            jpanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanelProductoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrearProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnActualizarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(212, 212, 212)
                        .addComponent(jButton14))
                    .addComponent(scrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Productos", jpanelProducto);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 751, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnCrearVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearVendedorActionPerformed

        if (BanderaBotonVendedor) {
            Escritorio.add(vendedorCrear);
            vendedorCrear.show();
            BanderaBotonVendedor = false;
            vendedorCrear.lblTitulo.setText("Nuevo Vendedor");
            vendedorCrear.jbtnGuardar_Actualizar.setText("Guardar");
        }

    }//GEN-LAST:event_btnCrearVendedorActionPerformed

    private void jbtnActualizaVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActualizaVendedorActionPerformed
        EditarVendedor(false);
    }//GEN-LAST:event_jbtnActualizaVendedorActionPerformed


    private void jbtnEliminarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarVendedorActionPerformed
        EditarVendedor(true);
    }//GEN-LAST:event_jbtnEliminarVendedorActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Cerrarsesion();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnCrearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearClienteActionPerformed
        if (BanderaBotonCliente) {
            Escritorio.add(ClienteCrear);
            ClienteCrear.show();
            BanderaBotonCliente = false;
            ClienteCrear.lblTitulo.setText("Nuevo Cliente");
            ClienteCrear.jbtnGuardar_Actualizar.setText("Guardar");
        }
    }//GEN-LAST:event_btnCrearClienteActionPerformed

    private void jbtnActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActualizarClienteActionPerformed
        EditarCliente(false);
    }//GEN-LAST:event_jbtnActualizarClienteActionPerformed

    private void jbtnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarClienteActionPerformed
        EditarCliente(true);

    }//GEN-LAST:event_jbtnEliminarClienteActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        Cerrarsesion();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnCrearSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearSucursalActionPerformed
        if (BanderaBotonSucursal) {
            Escritorio.add(SucursalCrear);
            SucursalCrear.show();
            BanderaBotonSucursal = false;
            SucursalCrear.lblTitulo.setText("Nueva sucursal");
            SucursalCrear.jbtnGuardar_Actualizar.setText("Guardar");
        }
    }//GEN-LAST:event_btnCrearSucursalActionPerformed

    private void jbtnActualizarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActualizarSucursalActionPerformed
        EditarSucursal(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnActualizarSucursalActionPerformed

    private void jbtnEliminarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarSucursalActionPerformed
        EditarSucursal(true);
    }//GEN-LAST:event_jbtnEliminarSucursalActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        Cerrarsesion();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnCrearProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearProductoActionPerformed
        if (BanderaBotonProducto) {
            Escritorio.add(ProductoCrear);
            ProductoCrear.show();
            BanderaBotonProducto = false;
            ProductoCrear.lblTitulo.setText("Nuevo Producto");
            ProductoCrear.jbtnGuardar_Actualizar.setText("Guardar");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnCrearProductoActionPerformed

    private void jbtnActualizarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActualizarProductoActionPerformed
        EditarProducto(false);
    }//GEN-LAST:event_jbtnActualizarProductoActionPerformed

    private void jbtnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarProductoActionPerformed
        EditarProducto(true);
    }//GEN-LAST:event_jbtnEliminarProductoActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Cerrarsesion();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            controladoradmin.carga_masiva("cliente", cn);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jbtnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClientesActionPerformed
        try {
            controladoradmin.pdf(cn, "cliente");
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnClientesActionPerformed

    private void jpanelClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelClienteMouseEntered
        if (!BanderaTablaCliente) {
            TablaClientes("");
            BanderaTablaCliente = true;
        }
    }//GEN-LAST:event_jpanelClienteMouseEntered

    private void jpanelProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelProductoMouseEntered
        if (!BanderaTablaProducto) {
            TablaProducto("");
            BanderaTablaProducto = true;
        }
    }//GEN-LAST:event_jpanelProductoMouseEntered

    private void jpanelSucursalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelSucursalMouseEntered
        if (!BanderaTablasucursal) {
            TablaSurcursal("");
            BanderaTablasucursal = true;
        } // TODO add your handling code here:
    }//GEN-LAST:event_jpanelSucursalMouseEntered

    private void jpanelVendedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelVendedorMouseEntered
        if (!BanderaTablaVendedor) {
            TablaVendedores("");
            BanderaTablaVendedor = true;
        }
    }//GEN-LAST:event_jpanelVendedorMouseEntered

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        try {
            controladoradmin.pdf(cn, "producto");
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
            controladoradmin.pdf(cn, "sucursales");
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            controladoradmin.pdf(cn, "vendedores");
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_jButton5ActionPerformed
    public static int movimiento = 0;
    public static boolean BanderaTablaCliente = true, BanderaTablaProducto = true, BanderaTablasucursal = true, BanderaTablaVendedor = true;
    public static boolean BanderaBotonVendedor = true, BanderaGuardarActualizarVendedor = true;
    Vendedor.Modificar_Guardar vendedorCrear = new Vendedor.Modificar_Guardar();
    Vendedor.Controlador vendedorcontrolador = new Vendedor.Controlador();
    public static boolean BanderaBotonCliente = true, BanderaGuardarActualizarCliente = true;
    Cliente.Modificar_Guardar ClienteCrear = new Cliente.Modificar_Guardar();
    Cliente.Controlador clientecontrolador = new Cliente.Controlador();
    public static boolean BanderaBotonSucursal = true, BanderaGuardarActualizarSucursal = true;
    Sucursal.Modificar_Guardar SucursalCrear = new Sucursal.Modificar_Guardar();
    Sucursal.Controlador Sucursalcontrolador = new Sucursal.Controlador();
    public static boolean BanderaBotonProducto = true, BanderaGuardarActualizarProducto = true;
    Producto.Modificar_Guardar ProductoCrear = new Producto.Modificar_Guardar();
    Producto.Controlador Productocontrolador = new Producto.Controlador();


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaClientes;
    private javax.swing.JTable TablaProducto;
    private javax.swing.JTable TablaSurcursal;
    private javax.swing.JTable TablaVendedores;
    private javax.swing.JButton btnCrearCliente;
    private javax.swing.JButton btnCrearProducto;
    private javax.swing.JButton btnCrearSucursal;
    private javax.swing.JButton btnCrearVendedor;
    public static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    public static javax.swing.JButton jButton6;
    public static javax.swing.JButton jButton7;
    public static javax.swing.JButton jButton8;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JButton jbtnActualizaVendedor;
    public static javax.swing.JButton jbtnActualizarCliente;
    public static javax.swing.JButton jbtnActualizarProducto;
    public static javax.swing.JButton jbtnActualizarSucursal;
    private javax.swing.JButton jbtnClientes;
    private javax.swing.JButton jbtnEliminarCliente;
    private javax.swing.JButton jbtnEliminarProducto;
    private javax.swing.JButton jbtnEliminarSucursal;
    private javax.swing.JButton jbtnEliminarVendedor;
    private javax.swing.JPanel jpanelCliente;
    private javax.swing.JPanel jpanelProducto;
    private javax.swing.JPanel jpanelSucursal;
    private javax.swing.JPanel jpanelVendedor;
    private java.awt.ScrollPane scrollPane1;
    private java.awt.ScrollPane scrollPane2;
    private java.awt.ScrollPane scrollPane3;
    private java.awt.ScrollPane scrollPane4;
    // End of variables declaration//GEN-END:variables
}
