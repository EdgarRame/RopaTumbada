/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Admin_BD.Conexion;
import Implements.DAOClienteImpl;
import Implements.DAODetalleVentaImpl;
import Implements.DAOProductoImpl;
import Implements.DAOProveedorImpl;
import Implements.DAOVentaImpl;

import Model.Cliente;
import Model.DetalleVenta;
import Model.Producto;
import Model.Proveedor;
import Model.Venta;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author osbau
 */
public class Sistema extends javax.swing.JFrame {

    private Conexion conexion;
    private DefaultTableModel modeloTablaProducto = null;
    private DefaultTableModel modeloTablaVentas = null;
    private DefaultTableModel modeloTablaVenta = null;
    private DefaultTableModel modeloTablaCliente = null;
    private DefaultTableModel modeloTablaProveedor = null;

    private Cliente cliente;
    private Proveedor proveedor;
    private Producto producto;
    private Venta venta;
    private DetalleVenta detalle;

    private DAOClienteImpl clienteImpl = new DAOClienteImpl();
    private DAODetalleVentaImpl detalleVentaImpl = new DAODetalleVentaImpl();
    private DAOProductoImpl productoImpl = new DAOProductoImpl();
    private DAOProveedorImpl proveedorImpl = new DAOProveedorImpl();
    private DAOVentaImpl ventaImpl = new DAOVentaImpl();

    List<Proveedor> proveedores;

    /**
     * Creates new form Sistema
     *
     * @throws java.sql.SQLException
     */
    public Sistema() throws SQLException {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);

        clienteImpl.setConnection(conexion.getConnection());
        detalleVentaImpl.setConnection(conexion.getConnection());
        productoImpl.setConnection(conexion.getConnection());
        proveedorImpl.setConnection(conexion.getConnection());
        ventaImpl.setConnection(conexion.getConnection());
        this.modeloTablaProveedor = (DefaultTableModel) this.TableProv.getModel();
        this.cargarProveedores();
        this.TableProv.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = TableProv.getSelectedRow();

                    if (selectedRow != -1) {
                        // Obtener los valores de la fila seleccionada
                        String valor1 = TableProv.getValueAt(selectedRow, 0).toString();
                        String valor2 = TableProv.getValueAt(selectedRow, 1).toString();
                        String valor3 = TableProv.getValueAt(selectedRow, 2).toString();
                        String valor4 = TableProv.getValueAt(selectedRow, 3).toString();
                        String valor5 = TableProv.getValueAt(selectedRow, 4).toString();

                        txtIdProv.setText(valor1);
                        txtNombreProv.setText(valor2);
                        txtTelefonoProv.setText(valor3);
                        txtDireccionProv.setText(valor4);
                        txtCorreoProv.setText(valor5);
                    }
                }
            }
        });

        this.modeloTablaCliente = (DefaultTableModel) this.TableCli.getModel();
        this.cargarCliente();
        this.TableCli.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = TableCli.getSelectedRow();

                    if (selectedRow != -1) {
                        // Obtener los valores de la fila seleccionada
                        String valor1 = TableCli.getValueAt(selectedRow, 0).toString();
                        String valor2 = TableCli.getValueAt(selectedRow, 1).toString();
                        String valor3 = TableCli.getValueAt(selectedRow, 2).toString();
                        String valor4 = TableCli.getValueAt(selectedRow, 3).toString();
                        String valor5 = TableCli.getValueAt(selectedRow, 4).toString();

                        txtIdCli.setText(valor1);
                        txtNombreCli.setText(valor2);
                        txtApellidosCli.setText(valor3);
                        txtTelefonoCli.setText(valor4);
                        txtCiudadCli.setText(valor5);
                    }
                }
            }
        });

        proveedores = this.proveedorImpl.consultar();
        DefaultComboBoxModel<String> cbxModel = (DefaultComboBoxModel<String>) this.cbxProveedorProd.getModel();
        for (Proveedor prov : proveedores) {
            cbxModel.addElement(prov.getNombre());
        }
        this.cbxProveedorProd.setModel(cbxModel);

        this.modeloTablaProducto = (DefaultTableModel) this.TableProd.getModel();
        this.cargarProducto();
        this.TableProd.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = TableProd.getSelectedRow();

                    if (selectedRow != -1) {
                        // Obtener los valores de la fila seleccionada
                        String valor1 = TableProd.getValueAt(selectedRow, 0).toString();
                        String valor2 = TableProd.getValueAt(selectedRow, 1).toString();
                        String valor3 = TableProd.getValueAt(selectedRow, 2).toString();
                        String valor4 = TableProd.getValueAt(selectedRow, 3).toString();
                        String valor5 = TableProd.getValueAt(selectedRow, 4).toString();

                        txtCodigoProd.setText(valor1);
                        txtProductoProd.setText(valor2);
                        txtCantidadProd.setText(valor3);
                        txtPrecioProd.setText(valor4);
                        // Recorrer la lista de proveedores
                        for (int i = 0; i < cbxProveedorProd.getItemCount(); i++) {
                            String nombreProveedor = cbxProveedorProd.getItemAt(i);

                            // Comparar el nombre del proveedor con el texto recuperado del valor 5
                            if (nombreProveedor.equals(valor5)) {
                                // Seleccionar el proveedor en el JComboBox
                                cbxProveedorProd.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                }
            }
        });

        this.txtCodigoVenta.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void focusLost(FocusEvent e) {
                int codigo = Integer.parseInt(txtCodigoVenta.getText());
                Producto nuevo = productoImpl.consultaEspecifica(codigo);
                txtProductoVenta.setText(nuevo.getNombre());
                txtPrecioVenta.setText(String.valueOf(nuevo.getPrecio()));
                txtStockDisponible.setText(String.valueOf(nuevo.getExistencia()));
                txtIdVenta.setText(String.valueOf(generarNumeroAleatorio(99, 99999)));
            }

            public static int generarNumeroAleatorio(int minimo, int maximo) {
                Random random = new Random();
                return random.nextInt(maximo - minimo + 1) + minimo;
            }
        });

        this.modeloTablaVenta = (DefaultTableModel) this.TableVenta.getModel();

        this.modeloTablaVentas = (DefaultTableModel) this.TableVentas.getModel();
        
        this.cargarVentas();
    }

    public Sistema(Conexion conn) throws SQLException {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);

        this.conexion = conn;
        clienteImpl.setConnection(conn.getConnection());
        detalleVentaImpl.setConnection(conn.getConnection());
        productoImpl.setConnection(conn.getConnection());
        proveedorImpl.setConnection(conn.getConnection());
        ventaImpl.setConnection(conn.getConnection());

        this.modeloTablaProveedor = (DefaultTableModel) this.TableProv.getModel();
        this.cargarProveedores();
        this.TableProv.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = TableProv.getSelectedRow();

                    if (selectedRow != -1) {
                        // Obtener los valores de la fila seleccionada
                        String valor1 = TableProv.getValueAt(selectedRow, 0).toString();
                        String valor2 = TableProv.getValueAt(selectedRow, 1).toString();
                        String valor3 = TableProv.getValueAt(selectedRow, 2).toString();
                        String valor4 = TableProv.getValueAt(selectedRow, 3).toString();
                        String valor5 = TableProv.getValueAt(selectedRow, 4).toString();

                        txtIdProv.setText(valor1);
                        txtNombreProv.setText(valor2);
                        txtTelefonoProv.setText(valor3);
                        txtDireccionProv.setText(valor4);
                        txtCorreoProv.setText(valor5);
                    }
                }
            }
        }
        );

        this.modeloTablaCliente = (DefaultTableModel) this.TableCli.getModel();
        this.cargarCliente();
        this.TableCli.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = TableCli.getSelectedRow();

                    if (selectedRow != -1) {
                        // Obtener los valores de la fila seleccionada
                        String valor1 = TableCli.getValueAt(selectedRow, 0).toString();
                        String valor2 = TableCli.getValueAt(selectedRow, 1).toString();
                        String valor3 = TableCli.getValueAt(selectedRow, 2).toString();
                        String valor4 = TableCli.getValueAt(selectedRow, 3).toString();
                        String valor5 = TableCli.getValueAt(selectedRow, 4).toString();

                        txtIdCli.setText(valor1);
                        txtNombreCli.setText(valor2);
                        txtApellidosCli.setText(valor3);
                        txtTelefonoCli.setText(valor4);
                        txtCiudadCli.setText(valor5);
                    }
                }
            }
        });

        proveedores = this.proveedorImpl.consultar();
        DefaultComboBoxModel<String> cbxModel = (DefaultComboBoxModel<String>) this.cbxProveedorProd.getModel();
        for (Proveedor prov : proveedores) {
            cbxModel.addElement(prov.getNombre());
        }
        this.cbxProveedorProd.setModel(cbxModel);

        this.modeloTablaProducto = (DefaultTableModel) this.TableProd.getModel();
        this.cargarProducto();
        this.TableProd.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = TableProd.getSelectedRow();

                    if (selectedRow != -1) {
                        // Obtener los valores de la fila seleccionada
                        String valor1 = TableProd.getValueAt(selectedRow, 0).toString();
                        String valor2 = TableProd.getValueAt(selectedRow, 1).toString();
                        String valor3 = TableProd.getValueAt(selectedRow, 2).toString();
                        String valor4 = TableProd.getValueAt(selectedRow, 3).toString();
                        String valor5 = TableProd.getValueAt(selectedRow, 4).toString();

                        txtCodigoProd.setText(valor1);
                        txtProductoProd.setText(valor2);
                        txtCantidadProd.setText(valor3);
                        txtPrecioProd.setText(valor4);
                        // Recorrer la lista de proveedores
                        for (int i = 0; i < cbxProveedorProd.getItemCount(); i++) {
                            String nombreProveedor = cbxProveedorProd.getItemAt(i);

                            // Comparar el nombre del proveedor con el texto recuperado del valor 5
                            if (nombreProveedor.equals(valor5)) {
                                // Seleccionar el proveedor en el JComboBox
                                cbxProveedorProd.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                }
            }
        });

        this.txtCodigoVenta.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void focusLost(FocusEvent e) {
                int codigo = Integer.parseInt(txtCodigoVenta.getText());
                Producto nuevo = productoImpl.consultaEspecifica(codigo);
                txtProductoVenta.setText(nuevo.getNombre());
                txtPrecioVenta.setText(String.valueOf(nuevo.getPrecio()));
                txtStockDisponible.setText(String.valueOf(nuevo.getExistencia()));
                if (txtIdVenta.getText().equals("")) {
                    txtIdVenta.setText(String.valueOf(generarNumeroAleatorio(99, 99999)));
                }
            }

            public static int generarNumeroAleatorio(int minimo, int maximo) {
                Random random = new Random();
                return random.nextInt(maximo - minimo + 1) + minimo;
            }
        });

        this.modeloTablaVenta = (DefaultTableModel) this.TableVenta.getModel();

        this.modeloTablaVentas = (DefaultTableModel) this.TableVentas.getModel();
        this.cargarVentas();
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
        btNV = new javax.swing.JButton();
        btnCli = new javax.swing.JButton();
        btnProv = new javax.swing.JButton();
        btnProd = new javax.swing.JButton();
        btnVen = new javax.swing.JButton();
        LabelPortada = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        PANELES = new javax.swing.JTabbedPane();
        PanelProducto = new javax.swing.JPanel();
        labCogidoPro = new javax.swing.JLabel();
        labProductoPro = new javax.swing.JLabel();
        labCantidadPro = new javax.swing.JLabel();
        labPrecioPro = new javax.swing.JLabel();
        labProveedorPro = new javax.swing.JLabel();
        txtCodigoProd = new javax.swing.JTextField();
        txtProductoProd = new javax.swing.JTextField();
        txtCantidadProd = new javax.swing.JTextField();
        txtPrecioProd = new javax.swing.JTextField();
        cbxProveedorProd = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableProd = new javax.swing.JTable();
        btnGuardarProd = new javax.swing.JButton();
        btnActualizarProd = new javax.swing.JButton();
        btnEliminarProd = new javax.swing.JButton();
        PanelVentas = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableVentas = new javax.swing.JTable();
        PanelVenta = new javax.swing.JPanel();
        labCodigoVen = new javax.swing.JLabel();
        labProductoVen = new javax.swing.JLabel();
        labCantidadVen = new javax.swing.JLabel();
        labPrecioVen = new javax.swing.JLabel();
        labSDVen = new javax.swing.JLabel();
        btnEliminarVenta = new javax.swing.JButton();
        txtCodigoVenta = new javax.swing.JTextField();
        txtProductoVenta = new javax.swing.JTextField();
        txtCantidadVenta = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        txtStockDisponible = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableVenta = new javax.swing.JTable();
        labIdVen = new javax.swing.JLabel();
        txtIdVenta = new javax.swing.JTextField();
        labNombreVen = new javax.swing.JLabel();
        txtNombreClienteVenta = new javax.swing.JTextField();
        btnGenerarVenta = new javax.swing.JButton();
        labTotalVen = new javax.swing.JLabel();
        LabelTotal = new javax.swing.JLabel();
        btnAgregarVenta = new javax.swing.JButton();
        PanelCliente = new javax.swing.JPanel();
        labIdCli = new javax.swing.JLabel();
        labNombreCli = new javax.swing.JLabel();
        labTelefonoCli = new javax.swing.JLabel();
        txtIdCli = new javax.swing.JTextField();
        txtNombreCli = new javax.swing.JTextField();
        txtTelefonoCli = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableCli = new javax.swing.JTable();
        btnGuardarCli = new javax.swing.JButton();
        txtActualizarCli = new javax.swing.JButton();
        btnEliminarCli = new javax.swing.JButton();
        labApellidosCli = new javax.swing.JLabel();
        txtApellidosCli = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtCiudadCli = new javax.swing.JTextField();
        labCiudadCli = new javax.swing.JLabel();
        PanelProveedor = new javax.swing.JPanel();
        labIdProv = new javax.swing.JLabel();
        labNombreProv = new javax.swing.JLabel();
        labTelefonoProv = new javax.swing.JLabel();
        labDireccionProv = new javax.swing.JLabel();
        txtIdProv = new javax.swing.JTextField();
        txtNombreProv = new javax.swing.JTextField();
        txtTelefonoProv = new javax.swing.JTextField();
        txtDireccionProv = new javax.swing.JTextField();
        labCorreoProv = new javax.swing.JLabel();
        txtCorreoProv = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableProv = new javax.swing.JTable();
        btnGuardarProv = new javax.swing.JButton();
        btnActualizarProv = new javax.swing.JButton();
        btnEliminarProv = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        btNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Nventa.png"))); // NOI18N
        btNV.setText("Nueva Venta ");
        btNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNVActionPerformed(evt);
            }
        });

        btnCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btnCli.setText("Clientes");
        btnCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliActionPerformed(evt);
            }
        });

        btnProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/proveedor.png"))); // NOI18N
        btnProv.setText("Proveedor");
        btnProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProvActionPerformed(evt);
            }
        });

        btnProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/producto.png"))); // NOI18N
        btnProd.setText("Productos");
        btnProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdActionPerformed(evt);
            }
        });

        btnVen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/compras.png"))); // NOI18N
        btnVen.setText("Ventas");
        btnVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenActionPerformed(evt);
            }
        });

        LabelPortada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logoTAP.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(LabelPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btNV, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnProv, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(btnVen, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(LabelPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProv))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        labCogidoPro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labCogidoPro.setText("Codigo:");

        labProductoPro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labProductoPro.setText("Producto:");

        labCantidadPro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labCantidadPro.setText("Cantidad:");

        labPrecioPro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labPrecioPro.setText("Precio:");

        labProveedorPro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labProveedorPro.setText("Proveedor:");

        cbxProveedorProd.setEditable(true);
        cbxProveedorProd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un proveedor" }));
        cbxProveedorProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxProveedorProdActionPerformed(evt);
            }
        });

        TableProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "PRODUCTO", "STOCK", "PRECIO", "PROVEEDOR"
            }
        ));
        jScrollPane3.setViewportView(TableProd);
        if (TableProd.getColumnModel().getColumnCount() > 0) {
            TableProd.getColumnModel().getColumn(0).setPreferredWidth(50);
            TableProd.getColumnModel().getColumn(1).setPreferredWidth(100);
            TableProd.getColumnModel().getColumn(2).setPreferredWidth(50);
            TableProd.getColumnModel().getColumn(3).setPreferredWidth(50);
            TableProd.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        btnGuardarProd.setText("Guardar");
        btnGuardarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProdActionPerformed(evt);
            }
        });

        btnActualizarProd.setText("Actualizar");
        btnActualizarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProdActionPerformed(evt);
            }
        });

        btnEliminarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelProductoLayout = new javax.swing.GroupLayout(PanelProducto);
        PanelProducto.setLayout(PanelProductoLayout);
        PanelProductoLayout.setHorizontalGroup(
            PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labCogidoPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labPrecioPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labCantidadPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labProveedorPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labProductoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelProductoLayout.createSequentialGroup()
                        .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProductoProd)
                            .addComponent(txtPrecioProd)
                            .addComponent(txtCantidadProd)
                            .addComponent(txtCodigoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarProd))
                        .addGap(46, 46, 46)
                        .addComponent(btnActualizarProd)
                        .addGap(565, 565, 565))
                    .addGroup(PanelProductoLayout.createSequentialGroup()
                        .addComponent(cbxProveedorProd, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        PanelProductoLayout.setVerticalGroup(
            PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProductoLayout.createSequentialGroup()
                .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelProductoLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnActualizarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarProd)
                        .addGap(0, 612, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProductoLayout.createSequentialGroup()
                        .addGap(0, 360, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(PanelProductoLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCogidoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labProductoPro)
                    .addComponent(txtProductoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCantidadPro)
                    .addComponent(txtCantidadProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labPrecioPro)
                    .addComponent(txtPrecioProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labProveedorPro)
                    .addComponent(cbxProveedorProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PANELES.addTab("PRODUCTO", PanelProducto);

        TableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLIENTE", "VENDEDOR", "TOTAL"
            }
        ));
        jScrollPane4.setViewportView(TableVentas);
        if (TableVentas.getColumnModel().getColumnCount() > 0) {
            TableVentas.getColumnModel().getColumn(0).setPreferredWidth(20);
            TableVentas.getColumnModel().getColumn(1).setPreferredWidth(60);
            TableVentas.getColumnModel().getColumn(2).setPreferredWidth(60);
            TableVentas.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        javax.swing.GroupLayout PanelVentasLayout = new javax.swing.GroupLayout(PanelVentas);
        PanelVentas.setLayout(PanelVentasLayout);
        PanelVentasLayout.setHorizontalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1068, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(526, Short.MAX_VALUE))
        );
        PanelVentasLayout.setVerticalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(355, Short.MAX_VALUE))
        );

        PANELES.addTab("VENTAS", PanelVentas);

        labCodigoVen.setText("Codigo");

        labProductoVen.setText("Producto");

        labCantidadVen.setText("Cantidad");

        labPrecioVen.setText("Precio");

        labSDVen.setText("Stock Disponible");

        btnEliminarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N

        txtCodigoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoVentaActionPerformed(evt);
            }
        });

        txtProductoVenta.setEditable(false);

        txtCantidadVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadVentaActionPerformed(evt);
            }
        });

        txtPrecioVenta.setEditable(false);

        txtStockDisponible.setEditable(false);
        txtStockDisponible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockDisponibleActionPerformed(evt);
            }
        });

        TableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(TableVenta);
        if (TableVenta.getColumnModel().getColumnCount() > 0) {
            TableVenta.getColumnModel().getColumn(0).setPreferredWidth(30);
            TableVenta.getColumnModel().getColumn(1).setPreferredWidth(100);
            TableVenta.getColumnModel().getColumn(2).setPreferredWidth(30);
            TableVenta.getColumnModel().getColumn(3).setPreferredWidth(30);
            TableVenta.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        labIdVen.setText("ID");

        txtIdVenta.setEditable(false);

        labNombreVen.setText("Nombre");

        txtNombreClienteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreClienteVentaActionPerformed(evt);
            }
        });

        btnGenerarVenta.setText("IMPRIMIR");
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });

        labTotalVen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/money.png"))); // NOI18N
        labTotalVen.setText("TOTAL a Pagar");

        LabelTotal.setText("------");

        btnAgregarVenta.setText("Agregar");
        btnAgregarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelVentaLayout = new javax.swing.GroupLayout(PanelVenta);
        PanelVenta.setLayout(PanelVentaLayout);
        PanelVentaLayout.setHorizontalGroup(
            PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentaLayout.createSequentialGroup()
                        .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(PanelVentaLayout.createSequentialGroup()
                                .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelVentaLayout.createSequentialGroup()
                                        .addComponent(labCodigoVen, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(53, 53, 53)
                                        .addComponent(labProductoVen, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelVentaLayout.createSequentialGroup()
                                        .addComponent(txtCodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(66, 66, 66)
                                .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labCantidadVen, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(77, 77, 77)
                                .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labPrecioVen, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(89, 89, 89)
                                .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labSDVen, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PanelVentaLayout.createSequentialGroup()
                                        .addComponent(txtStockDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(107, 107, 107)
                                        .addComponent(btnEliminarVenta)))
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregarVenta)
                                .addGap(0, 454, Short.MAX_VALUE)))
                        .addGap(93, 93, 93))
                    .addGroup(PanelVentaLayout.createSequentialGroup()
                        .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelVentaLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(labIdVen, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(52, 52, 52)
                        .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labNombreVen, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenerarVenta)
                        .addGap(45, 45, 45)
                        .addComponent(labTotalVen, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(LabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176))))
        );
        PanelVentaLayout.setVerticalGroup(
            PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelVentaLayout.createSequentialGroup()
                        .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labCodigoVen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labPrecioVen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labSDVen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labProductoVen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labCantidadVen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtStockDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtCodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnEliminarVenta)))
                    .addComponent(btnAgregarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentaLayout.createSequentialGroup()
                        .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labNombreVen, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labIdVen, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(24, 24, 24)
                        .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGenerarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labTotalVen)
                        .addComponent(LabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(346, 346, 346))
        );

        PANELES.addTab("VENTA", PanelVenta);

        labIdCli.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labIdCli.setText("ID");

        labNombreCli.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labNombreCli.setText("Nombre ");

        labTelefonoCli.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labTelefonoCli.setText("Telefono");

        txtTelefonoCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoCliActionPerformed(evt);
            }
        });

        TableCli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDOS", "TELEFONO", "CIUDAD", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableCli.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jScrollPane2.setViewportView(TableCli);
        if (TableCli.getColumnModel().getColumnCount() > 0) {
            TableCli.getColumnModel().getColumn(0).setPreferredWidth(30);
            TableCli.getColumnModel().getColumn(1).setPreferredWidth(30);
            TableCli.getColumnModel().getColumn(2).setPreferredWidth(60);
            TableCli.getColumnModel().getColumn(3).setPreferredWidth(80);
            TableCli.getColumnModel().getColumn(4).setPreferredWidth(80);
            TableCli.getColumnModel().getColumn(5).setPreferredWidth(40);
            TableCli.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        btnGuardarCli.setText("Guardar");
        btnGuardarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCliActionPerformed(evt);
            }
        });

        txtActualizarCli.setText("Actualizar");
        txtActualizarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtActualizarCliActionPerformed(evt);
            }
        });

        btnEliminarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCliActionPerformed(evt);
            }
        });

        labApellidosCli.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labApellidosCli.setText("Apellidos");

        txtCiudadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCiudadCliActionPerformed(evt);
            }
        });

        labCiudadCli.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labCiudadCli.setText("Ciudad");

        javax.swing.GroupLayout PanelClienteLayout = new javax.swing.GroupLayout(PanelCliente);
        PanelCliente.setLayout(PanelClienteLayout);
        PanelClienteLayout.setHorizontalGroup(
            PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelClienteLayout.createSequentialGroup()
                        .addComponent(labTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelClienteLayout.createSequentialGroup()
                        .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labApellidosCli, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtIdCli, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreCli, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApellidosCli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelClienteLayout.createSequentialGroup()
                        .addComponent(labCiudadCli, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCiudadCli, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 471, Short.MAX_VALUE)
                .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarCli)
                    .addComponent(btnGuardarCli))
                .addGap(30, 30, 30)
                .addComponent(txtActualizarCli)
                .addGap(45, 45, 45))
        );
        PanelClienteLayout.setVerticalGroup(
            PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelClienteLayout.createSequentialGroup()
                .addContainerGap(190, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
            .addGroup(PanelClienteLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelClienteLayout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addComponent(btnEliminarCli))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelClienteLayout.createSequentialGroup()
                            .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtActualizarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnGuardarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(69, 69, 69)))
                    .addGroup(PanelClienteLayout.createSequentialGroup()
                        .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtApellidosCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labApellidosCli))
                        .addGap(21, 21, 21)
                        .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labCiudadCli)
                            .addComponent(txtCiudadCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                        .addComponent(jLabel23)))
                .addContainerGap(318, Short.MAX_VALUE))
        );

        PANELES.addTab("CLIENTE", PanelCliente);

        labIdProv.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labIdProv.setText("ID");

        labNombreProv.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labNombreProv.setText("Nombre ");

        labTelefonoProv.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labTelefonoProv.setText("Telefono");

        labDireccionProv.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labDireccionProv.setText("Direccion");

        txtIdProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProvActionPerformed(evt);
            }
        });

        txtNombreProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProvActionPerformed(evt);
            }
        });

        txtTelefonoProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoProvActionPerformed(evt);
            }
        });

        labCorreoProv.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labCorreoProv.setText("Correo");

        txtCorreoProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoProvActionPerformed(evt);
            }
        });

        TableProv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "TELEFONO", "DIRECCION", "CORREO", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(TableProv);
        if (TableProv.getColumnModel().getColumnCount() > 0) {
            TableProv.getColumnModel().getColumn(0).setPreferredWidth(30);
            TableProv.getColumnModel().getColumn(1).setPreferredWidth(60);
            TableProv.getColumnModel().getColumn(2).setPreferredWidth(60);
            TableProv.getColumnModel().getColumn(3).setPreferredWidth(30);
            TableProv.getColumnModel().getColumn(4).setPreferredWidth(50);
            TableProv.getColumnModel().getColumn(5).setPreferredWidth(60);
        }

        btnGuardarProv.setText("Guardar");
        btnGuardarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProvActionPerformed(evt);
            }
        });

        btnActualizarProv.setText("Actualizar");
        btnActualizarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProvActionPerformed(evt);
            }
        });

        btnEliminarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelProveedorLayout = new javax.swing.GroupLayout(PanelProveedor);
        PanelProveedor.setLayout(PanelProveedorLayout);
        PanelProveedorLayout.setHorizontalGroup(
            PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 521, Short.MAX_VALUE)
                .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProveedorLayout.createSequentialGroup()
                        .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labTelefonoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labDireccionProv, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labCorreoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProveedorLayout.createSequentialGroup()
                        .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labIdProv, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labNombreProv))
                        .addGap(27, 27, 27)))
                .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelProveedorLayout.createSequentialGroup()
                        .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefonoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccionProv, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtIdProv, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNombreProv, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelProveedorLayout.createSequentialGroup()
                                .addComponent(btnGuardarProv)
                                .addGap(26, 26, 26)
                                .addComponent(btnActualizarProv))
                            .addGroup(PanelProveedorLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(btnEliminarProv))))
                    .addComponent(txtCorreoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        PanelProveedorLayout.setVerticalGroup(
            PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProveedorLayout.createSequentialGroup()
                .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelProveedorLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelProveedorLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelProveedorLayout.createSequentialGroup()
                                .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIdProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labIdProv, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labNombreProv)
                                    .addComponent(txtNombreProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labTelefonoProv)
                                    .addComponent(txtTelefonoProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDireccionProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labDireccionProv, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labCorreoProv)
                                    .addComponent(txtCorreoProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PanelProveedorLayout.createSequentialGroup()
                                .addGroup(PanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnGuardarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnActualizarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addComponent(btnEliminarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(350, Short.MAX_VALUE))
        );

        PANELES.addTab("PROVEEDOR", PanelProveedor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1084, 1084, 1084)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PANELES, javax.swing.GroupLayout.PREFERRED_SIZE, 1202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PANELES, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenActionPerformed
        // TODO add your handling code here:
        this.PANELES.setSelectedIndex(1);
    }//GEN-LAST:event_btnVenActionPerformed

    private void btnProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvActionPerformed
        // TODO add your handling code here:
        this.PANELES.setSelectedIndex(4);
    }//GEN-LAST:event_btnProvActionPerformed

    private void txtCantidadVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadVentaActionPerformed

    private void txtCodigoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoVentaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCodigoVentaActionPerformed

    private void txtNombreClienteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreClienteVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreClienteVentaActionPerformed

    private void txtTelefonoCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoCliActionPerformed

    private void btnEliminarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCliActionPerformed
        // TODO add your handling code here:
        int nRow = this.TableCli.getSelectedRow();

        if (nRow < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Sugerencia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        this.clienteImpl.eliminar(Integer.parseInt(this.txtIdCli.getText()));
        this.txtIdCli.setText("");
        this.txtNombreCli.setText("");
        this.txtApellidosCli.setText("");
        this.txtTelefonoCli.setText("");
        this.txtCiudadCli.setText("");
        this.cargarCliente();
    }//GEN-LAST:event_btnEliminarCliActionPerformed

    private void txtActualizarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActualizarCliActionPerformed
        // TODO add your handling code here:
        int nRow = this.TableCli.getSelectedRow();
        int id = (int) this.TableCli.getModel().getValueAt(nRow, 0);
        String nombre = this.txtNombreCli.getText();
        String apellido = this.txtApellidosCli.getText();
        String telefono = this.txtTelefonoCli.getText();
        String ciudad = this.txtCiudadCli.getText();
        Cliente cliente = new Cliente(id, nombre, apellido, telefono, ciudad);
        this.clienteImpl.actualizar(cliente);
        this.cargarCliente();
    }//GEN-LAST:event_txtActualizarCliActionPerformed

    private void txtIdProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProvActionPerformed

    private void txtTelefonoProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoProvActionPerformed

    private void btnEliminarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProvActionPerformed
        // TODO add your handling code here:
        int nRow = this.TableProv.getSelectedRow();

        if (nRow < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Sugerencia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        this.proveedorImpl.eliminar(Integer.parseInt(this.txtIdProv.getText()));
        this.txtIdProv.setText("");
        this.txtNombreProv.setText("");
        this.txtTelefonoProv.setText("");
        this.txtDireccionProv.setText("");
        this.txtCorreoProv.setText("");
        this.cargarProveedores();
    }//GEN-LAST:event_btnEliminarProvActionPerformed

    private void btnGuardarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProvActionPerformed
        // TODO add your handling code here:
        this.agregarProveedor();
        this.cargarProveedores();
    }//GEN-LAST:event_btnGuardarProvActionPerformed

    private void btnActualizarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProvActionPerformed
        // TODO add your handling code here:
        int nRow = this.TableProv.getSelectedRow();
        int id = (int) this.TableProv.getModel().getValueAt(nRow, 0);
        String nombre = this.txtNombreProv.getText();
        String telefono = this.txtTelefonoProv.getText();
        String ciudad = this.txtDireccionProv.getText();
        String correo = this.txtCorreoProv.getText();
        Proveedor prov = new Proveedor(id, nombre, telefono, ciudad, correo);
        this.proveedorImpl.actualizar(prov);
        this.cargarProveedores();
    }//GEN-LAST:event_btnActualizarProvActionPerformed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
        try {
            // TODO add your handling code here:
            this.guardarVenta();
            this.cargarVentas();
        } catch (SQLException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void btnCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliActionPerformed
        // TODO add your handling code here:
        this.PANELES.setSelectedIndex(3);
    }//GEN-LAST:event_btnCliActionPerformed

    private void cbxProveedorProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxProveedorProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxProveedorProdActionPerformed

    private void txtCorreoProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoProvActionPerformed

    private void txtNombreProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProvActionPerformed

    private void txtStockDisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockDisponibleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockDisponibleActionPerformed

    private void txtCiudadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCiudadCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCiudadCliActionPerformed

    private void btNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNVActionPerformed
        // TODO add your handling code here:
        this.PANELES.setSelectedIndex(2);
    }//GEN-LAST:event_btNVActionPerformed

    private void btnProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdActionPerformed
        // TODO add your handling code here:
        this.PANELES.setSelectedIndex(0);
    }//GEN-LAST:event_btnProdActionPerformed

    private void btnGuardarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCliActionPerformed
        // TODO add your handling code here:
        this.agregarCliente();
        this.cargarCliente();
    }//GEN-LAST:event_btnGuardarCliActionPerformed

    private void btnGuardarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProdActionPerformed
        // TODO add your handling code here:
        this.agregarCliente();
        this.cargarCliente();
    }//GEN-LAST:event_btnGuardarProdActionPerformed

    private void btnEliminarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProdActionPerformed
        // TODO add your handling code here:
        int nRow = this.TableProv.getSelectedRow();

        if (nRow < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Sugerencia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        this.productoImpl.eliminar(Integer.parseInt(this.txtCodigoProd.getText()));
        this.txtCodigoProd.setText("");
        this.txtProductoProd.setText("");
        this.txtCantidadProd.setText("");
        this.txtPrecioProd.setText("");
        this.cbxProveedorProd.removeItemAt(this.cbxProveedorProd.getSelectedIndex());
    }//GEN-LAST:event_btnEliminarProdActionPerformed

    private void btnAgregarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarVentaActionPerformed
        // TODO add your handling code here:
        this.agregarVenta();
        this.actualizarTabla();
    }//GEN-LAST:event_btnAgregarVentaActionPerformed

    private void btnActualizarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProdActionPerformed
        // TODO add your handling code here:
        int nRow = this.TableProv.getSelectedRow();
        int id = (int) this.TableProd.getModel().getValueAt(nRow, 0);
        String nombreProducto = this.txtProductoProd.getText();
        int stock = Integer.parseInt(this.txtCantidadProd.getText());
        double precio = Double.parseDouble(txtPrecioProd.getText());
        String nombreProveedor = (String) this.cbxProveedorProd.getSelectedItem();

        int idProveedorEncontrado = -1;

        for (Proveedor proveedor : this.proveedores) {
            if (proveedor.getNombre().equals(nombreProveedor)) {
                idProveedorEncontrado = proveedor.getId();
                break;
            }
        }

        Producto producto = new Producto(id, nombreProducto, precio, stock, idProveedorEncontrado);
        this.productoImpl.actualizar(producto);
        this.cargarProducto();
    }//GEN-LAST:event_btnActualizarProdActionPerformed

    public void agregarProducto() {
        int id = Integer.parseInt(this.txtCodigoProd.getText());
        String nombreProducto = this.txtProductoProd.getText();
        int stock = Integer.parseInt(this.txtCantidadProd.getText());
        double precio = Double.parseDouble(txtPrecioProd.getText());
        String nombreProveedor = (String) this.cbxProveedorProd.getSelectedItem();

        int idProveedorEncontrado = -1;

        for (Proveedor proveedor : this.proveedores) {
            if (proveedor.getNombre().equals(nombreProveedor)) {
                idProveedorEncontrado = proveedor.getId();
                break;
            }
        }

        Producto producto = new Producto(id, nombreProducto, precio, stock, idProveedorEncontrado);

        try {
            if (idProveedorEncontrado == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione una opcion valida");
            } else {
                this.productoImpl.agregar(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarProducto() {
        List<Producto> productos = productoImpl.consultar();

        this.modeloTablaProducto.setRowCount(0);
        String nomProveedor = "";

        for (Producto prod : productos) {
            for (Proveedor prov : this.proveedores) {

                if (prov.getId() == prod.getIdProveedor()) {
                    nomProveedor = prov.getNombre();
                    break;
                }
            }

            Object[] fila = {
                prod.getId(),
                prod.getNombre(),
                prod.getExistencia(),
                prod.getPrecio(),
                nomProveedor
            };
            this.modeloTablaProducto.addRow(fila);
        }
    }

    public void agregarProveedor() {
        int id = Integer.parseInt(this.txtIdProv.getText());
        String nombre = this.txtNombreProv.getText();
        String telefono = this.txtTelefonoProv.getText();
        String ciudad = this.txtDireccionProv.getText();
        String correo = this.txtCorreoProv.getText();
        Proveedor prov = new Proveedor(id, nombre, telefono, ciudad, correo);

        try {
            if (!this.isValidEmail(correo)) {
                JOptionPane.showMessageDialog(null, "Error el formato de Email no es valido!");
            } else {
                this.proveedorImpl.agregar(prov);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarProveedores() {
        List<Proveedor> proveedores = proveedorImpl.consultar();

        modeloTablaProveedor.setRowCount(0);

        for (Proveedor proveed : proveedores) {
            Object[] fila = {
                proveed.getId(),
                proveed.getNombre(),
                proveed.getTelefono(),
                proveed.getCiudad(),
                proveed.getCorreo()
            };
            modeloTablaProveedor.addRow(fila);
        }
    }

    public Proveedor buscar(int id) throws SQLException {
        List<Proveedor> prov = proveedorImpl.consultar();
        Proveedor encontrado = null;

        for (Proveedor proveedor : prov) {
            if (proveedor.getId() == id) {
                encontrado = proveedor;
                break;
            }
        }

        return encontrado;
    }

    public void agregarCliente() {
        int id = Integer.parseInt(this.txtIdCli.getText());
        String nombre = this.txtNombreCli.getText();
        String apellido = this.txtApellidosCli.getText();
        String telefono = this.txtTelefonoCli.getText();
        String ciudad = this.txtCiudadCli.getText();
        Cliente cliente = new Cliente(id, nombre, apellido, telefono, ciudad);
        try {
            this.clienteImpl.agregar(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarCliente() {
        List<Cliente> clientes = clienteImpl.consultar();

        this.modeloTablaCliente.setRowCount(0);

        for (Cliente cliente : clientes) {
            Object[] fila = {
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getTelefono(),
                cliente.getCiudad()
            };
            this.modeloTablaCliente.addRow(fila);
        }
    }

    public void agregarVenta() {
        int codigo = Integer.parseInt(this.txtCodigoVenta.getText());
        int id_venta = Integer.parseInt(this.txtIdVenta.getText());
        String descripcion = this.txtProductoVenta.getText();
        int cantidad = Integer.parseInt(this.txtCantidadVenta.getText());
        Double precio = Double.valueOf(this.txtPrecioVenta.getText());
        DetalleVenta nProducto = new DetalleVenta(id_venta, codigo, descripcion, cantidad, precio);

        this.ventaImpl.agregarCarrito(nProducto);
    }

    public void actualizarTabla() {
        List<DetalleVenta> productos = ventaImpl.mostrarCarrito();
        Double total = 0.0;

        this.modeloTablaVenta.setRowCount(0);
        for (DetalleVenta prod : productos) {
            total = total + (prod.getCantidad() * prod.getPrecioUnitario());
            Object[] fila = {
                prod.getIdProducto(),
                prod.getNombreProducto(),
                prod.getCantidad(),
                prod.getPrecioUnitario(),
                (prod.getCantidad() * prod.getPrecioUnitario())
            };
            this.modeloTablaVenta.addRow(fila);
        }

        this.LabelTotal.setText(String.valueOf(total));
    }

    public void guardarVenta() throws SQLException {
        if (!this.ventaImpl.mostrarCarrito().isEmpty()) {
            int id = Integer.parseInt(this.txtIdVenta.getText());
            String cliente = this.txtNombreClienteVenta.getText();
            Double total = Double.parseDouble(this.LabelTotal.getText());
            Venta nuevaV = new Venta(id, cliente, total);
            ventaImpl.insertarVenta(nuevaV);
            this.PANELES.setSelectedIndex(1);
        } else {
            JOptionPane.showMessageDialog(this, "Agregue un producto a la venta para continuar", "Sugerencia", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void cargarVentas() {
        List<Venta> ventas = this.ventaImpl.obtenerTodasVentas();
        
        this.modeloTablaVentas.setRowCount(0);
        for (Venta venta: ventas) {
            Object[] fila = {
                venta.getIdVenta(),
                venta.getNombreCliente(),
                "Administrador",
                venta.getTotalVenta()
            };
            this.modeloTablaVentas.addRow(fila);
        }
    }

    public static boolean isValidEmail(String email) {
        String EMAIL_REGEX
                = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Sistema().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelPortada;
    private javax.swing.JLabel LabelTotal;
    private javax.swing.JTabbedPane PANELES;
    private javax.swing.JPanel PanelCliente;
    private javax.swing.JPanel PanelProducto;
    private javax.swing.JPanel PanelProveedor;
    private javax.swing.JPanel PanelVenta;
    private javax.swing.JPanel PanelVentas;
    private javax.swing.JTable TableCli;
    private javax.swing.JTable TableProd;
    private javax.swing.JTable TableProv;
    private javax.swing.JTable TableVenta;
    private javax.swing.JTable TableVentas;
    private javax.swing.JButton btNV;
    private javax.swing.JButton btnActualizarProd;
    private javax.swing.JButton btnActualizarProv;
    private javax.swing.JButton btnAgregarVenta;
    private javax.swing.JButton btnCli;
    private javax.swing.JButton btnEliminarCli;
    private javax.swing.JButton btnEliminarProd;
    private javax.swing.JButton btnEliminarProv;
    private javax.swing.JButton btnEliminarVenta;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JButton btnGuardarCli;
    private javax.swing.JButton btnGuardarProd;
    private javax.swing.JButton btnGuardarProv;
    private javax.swing.JButton btnProd;
    private javax.swing.JButton btnProv;
    private javax.swing.JButton btnVen;
    private javax.swing.JComboBox<String> cbxProveedorProd;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labApellidosCli;
    private javax.swing.JLabel labCantidadPro;
    private javax.swing.JLabel labCantidadVen;
    private javax.swing.JLabel labCiudadCli;
    private javax.swing.JLabel labCodigoVen;
    private javax.swing.JLabel labCogidoPro;
    private javax.swing.JLabel labCorreoProv;
    private javax.swing.JLabel labDireccionProv;
    private javax.swing.JLabel labIdCli;
    private javax.swing.JLabel labIdProv;
    private javax.swing.JLabel labIdVen;
    private javax.swing.JLabel labNombreCli;
    private javax.swing.JLabel labNombreProv;
    private javax.swing.JLabel labNombreVen;
    private javax.swing.JLabel labPrecioPro;
    private javax.swing.JLabel labPrecioVen;
    private javax.swing.JLabel labProductoPro;
    private javax.swing.JLabel labProductoVen;
    private javax.swing.JLabel labProveedorPro;
    private javax.swing.JLabel labSDVen;
    private javax.swing.JLabel labTelefonoCli;
    private javax.swing.JLabel labTelefonoProv;
    private javax.swing.JLabel labTotalVen;
    private javax.swing.JButton txtActualizarCli;
    private javax.swing.JTextField txtApellidosCli;
    private javax.swing.JTextField txtCantidadProd;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtCiudadCli;
    private javax.swing.JTextField txtCodigoProd;
    private javax.swing.JTextField txtCodigoVenta;
    private javax.swing.JTextField txtCorreoProv;
    private javax.swing.JTextField txtDireccionProv;
    private javax.swing.JTextField txtIdCli;
    private javax.swing.JTextField txtIdProv;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtNombreCli;
    private javax.swing.JTextField txtNombreClienteVenta;
    private javax.swing.JTextField txtNombreProv;
    private javax.swing.JTextField txtPrecioProd;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtProductoProd;
    private javax.swing.JTextField txtProductoVenta;
    private javax.swing.JTextField txtStockDisponible;
    private javax.swing.JTextField txtTelefonoCli;
    private javax.swing.JTextField txtTelefonoProv;
    // End of variables declaration//GEN-END:variables
}
