
package Interfaz;

import Modelo.Cliente;
import Modelo.ClienteC;
import Modelo.Conexion;
import Modelo.Empleado;
import Modelo.EmpleadoC;
import Modelo.Factura;
import Modelo.FacturaC;
import Modelo.Login;
import Modelo.Platillos;
import Modelo.PlatillosC;
import Modelo.restricciones;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class restaurante extends javax.swing.JFrame {
    
    public static String ema;
    
    Date fechaVenta = new Date();
    String fechaAct = new SimpleDateFormat("yyyy-MM-dd").format(fechaVenta);

    Cliente cli = new Cliente();
    ClienteC cliC = new ClienteC();
    Empleado Emp = new Empleado();
    EmpleadoC EmpL = new EmpleadoC();
    Platillos Pla = new Platillos();
    PlatillosC PlaC = new PlatillosC();
    Factura Fac = new Factura();
    FacturaC F = new FacturaC();
    
    restricciones restrin = new restricciones();
    
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    int item;
    double TotalPagar = 0.00;
    
    public restaurante(){
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false); 
        JPestañas.setEnabledAt(0,false);
        JPestañas.setEnabledAt(1,false);
        JPestañas.setEnabledAt(2,false);
        JPestañas.setEnabledAt(3,false);
        JPestañas.setEnabledAt(4,false);
        JPestañas.setEnabledAt(5,false);
    }
    
    public restaurante(Login priv){
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false); 
        if(priv.getTipo_Emp_U().equals("Asistente")){
            btnConfigurariEmpleados.setEnabled(false);
            brnConfigurarClientes.setEnabled(false);
            btnRegistrarAdmin.setEnabled(false);
        }
        txtIDCli.setEnabled(false);
        txtIDEmpleado.setEnabled(false);
        txtID_pla.setEnabled(false);
        txtIDVentas1.setEnabled(false);
        
        
    }

    
    
    public void ListarCliente(){
        List<Cliente> ListarCli = cliC.ListarCliente();
        modelo = (DefaultTableModel) TBCliente.getModel(); 
        
        Object[] ob = new Object[9];
        for(int i = 0 ; i<ListarCli.size(); i++)
        {
            ob[0] = ListarCli.get(i).getId_cli();
            ob[1] = ListarCli.get(i).getNombre_cli();
            ob[2] = ListarCli.get(i).getApellido_cli();
            ob[3] = ListarCli.get(i).getNit_cli();
            ob[4] = ListarCli.get(i).getCorreo_cli();
            ob[5] = ListarCli.get(i).getDireccion_cli();
            ob[6] = ListarCli.get(i).getTel_cli();
            ob[7] = ListarCli.get(i).getEstado_cliente();
            modelo.addRow(ob);
        }
        TBCliente.setModel(modelo);
    }
    
    public void ListarEmpleado(){
        
        List<Empleado> ListarEmp = EmpL.ListarEmpleado();
        modelo = (DefaultTableModel) TBEmpleado.getModel(); 
        
        Object[] ob = new Object[8];
        for(int i = 0 ; i<ListarEmp.size(); i++)
        {
            ob[0] = ListarEmp.get(i).getId_E();
            ob[1] = ListarEmp.get(i).getNombre_E();
            ob[2] = ListarEmp.get(i).getApellido_E();
            ob[3] = ListarEmp.get(i).getCorreo_E();
            ob[4] = ListarEmp.get(i).getEdad_E();
            ob[5] = ListarEmp.get(i).getGenero_E();
            ob[6] = ListarEmp.get(i).getEstado_E();
            modelo.addRow(ob);
        }
        TBEmpleado.setModel(modelo);
    }
    
    public void ListarPlatillos()
    {
        List<Platillos> ListarPla = PlaC.ListarPlatillo();
        modelo = (DefaultTableModel) TBPlatillos.getModel();
        Object[] ob = new Object[6];
        for(int i = 0 ; i<ListarPla.size(); i++)
        {
            ob[0] = ListarPla.get(i).getId_Pla();
            ob[1] = ListarPla.get(i).getNombre_Pla();
            ob[2] = ListarPla.get(i).getDescripcion_Pla();
            ob[3] = ListarPla.get(i).getPrecio_Pla();
            ob[4] = ListarPla.get(i).getStock_Pla();
            ob[5] = ListarPla.get(i).getEstado_Pla();
            modelo.addRow(ob);
        }
        TBPlatillos.setModel(modelo);
    }
    
    public void ListarFacturas()
    {
        List<Factura> ListarFAC = F.ListarFacturas();
        modelo = (DefaultTableModel) TBFactura.getModel();
        Object[] ob = new Object[4];
        for(int i = 0 ; i<ListarFAC.size(); i++)
        {
            ob[0] = ListarFAC.get(i).getId_Fac();
            ob[1] = ListarFAC.get(i).getCliente_Fac();
            ob[2] = ListarFAC.get(i).getTotal_Fac();
            ob[3] = ListarFAC.get(i).getFecha_Fac();
            modelo.addRow(ob);
        }
        TBFactura.setModel(modelo);
    }
    
    public void LimpiarTabla(){
        for(int i=0; i<modelo.getRowCount(); i++)
        {
            modelo.removeRow(i);
            i=i-1;
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

        jPanel11 = new javax.swing.JPanel();
        JPestañas = new javax.swing.JTabbedPane();
        JPANELinterfaz = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBVentas = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtIDVentas = new javax.swing.JTextField();
        txtplatilloVentas = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCantidadVenta = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtStockVenta = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtDescripccionVenta = new javax.swing.JTextField();
        txtIDVentas1 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtIdClienteVenta = new javax.swing.JTextField();
        txtNombreVenta = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTelVenta = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDirVenta = new javax.swing.JTextField();
        txtCorreoVenta = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Labeltotal = new javax.swing.JLabel();
        btnEliminarVenta = new javax.swing.JButton();
        btnCrearPDF = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnCliente = new javax.swing.JButton();
        btnEmpleado = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Table1 = new javax.swing.JScrollPane();
        TBCliente = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtNombreCli = new javax.swing.JTextField();
        txtApellidoCli = new javax.swing.JTextField();
        txtCorreoCli = new javax.swing.JTextField();
        txtNitCli = new javax.swing.JTextField();
        CBEstadoCli = new javax.swing.JComboBox<>();
        txtIDCli = new javax.swing.JTextField();
        txtDireccionCli = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtTelefonoCli = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtbuscaridCli = new javax.swing.JTextField();
        btnBuscaCli = new javax.swing.JButton();
        btnActualizarCli = new javax.swing.JButton();
        btnEliminarCli = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Table2 = new javax.swing.JScrollPane();
        TBEmpleado = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtNombreEmpleado = new javax.swing.JTextField();
        txtApellidoEmpleado = new javax.swing.JTextField();
        txtCorreoEmpleado = new javax.swing.JTextField();
        txtEdadEmpleado = new javax.swing.JTextField();
        CBEstadoEmpleado = new javax.swing.JComboBox<>();
        txtIDEmpleado = new javax.swing.JTextField();
        CBGeneroEmpleado = new javax.swing.JComboBox<>();
        txtbuscaridEmpleado = new javax.swing.JTextField();
        btnBuscarEmpleado = new javax.swing.JButton();
        btnActualizEmpleado = new javax.swing.JButton();
        btnEliminarEmpleado = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        Table3 = new javax.swing.JScrollPane();
        TBPlatillos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre_pla = new javax.swing.JTextField();
        txtDescrip_Pla = new javax.swing.JTextField();
        txtPrecio_Pla = new javax.swing.JTextField();
        txtStock_Pla = new javax.swing.JTextField();
        CBEstado_Pla = new javax.swing.JComboBox<>();
        txtID_pla = new javax.swing.JTextField();
        btnGuardarPlatillo = new javax.swing.JButton();
        btnActualizarPlatillo = new javax.swing.JButton();
        btnEliminarPlatillo = new javax.swing.JButton();
        btnBuscarPlatillo = new javax.swing.JButton();
        txtbuscaridplatillo = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TBFactura = new javax.swing.JTable();
        txtIdFac = new javax.swing.JTextField();
        btnAbrirFac = new javax.swing.JButton();
        btnCVenta = new javax.swing.JButton();
        btnCreacionCyE = new javax.swing.JButton();
        brnConfigurarClientes = new javax.swing.JButton();
        btnConfigurariEmpleados = new javax.swing.JButton();
        btnConfigurarPlatillos = new javax.swing.JButton();
        btnVerVentas = new javax.swing.JButton();
        btnRegistrarAdmin = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Restaurante");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(0, 51, 102));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 730, 30));

        JPestañas.setBackground(new java.awt.Color(255, 255, 255, 225));

        JPANELinterfaz.setBackground(new java.awt.Color(204, 204, 204, 225));

        TBVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID del Platillo", "Platillo", "Descripccion", "Cantidad", "Precio U", "Sub Total"
            }
        ));
        TBVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBVentasMouseClicked(evt);
            }
        });
        TBVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBVentasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(TBVentas);
        if (TBVentas.getColumnModel().getColumnCount() > 0) {
            TBVentas.getColumnModel().getColumn(0).setPreferredWidth(75);
            TBVentas.getColumnModel().getColumn(1).setPreferredWidth(125);
            TBVentas.getColumnModel().getColumn(2).setPreferredWidth(125);
            TBVentas.getColumnModel().getColumn(3).setPreferredWidth(75);
            TBVentas.getColumnModel().getColumn(4).setPreferredWidth(75);
            TBVentas.getColumnModel().getColumn(5).setPreferredWidth(75);
        }

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setText("Id del Platillo");

        txtIDVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDVentasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDVentasKeyTyped(evt);
            }
        });

        txtplatilloVentas.setEditable(false);
        txtplatilloVentas.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setText("Platillo");

        jLabel11.setText("Descripcion");

        jLabel13.setText("Cantidad");

        txtCantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyTyped(evt);
            }
        });

        jLabel14.setText("Disponible");

        txtStockVenta.setEditable(false);
        txtStockVenta.setBackground(new java.awt.Color(204, 204, 204));

        txtPrecioVenta.setEditable(false);
        txtPrecioVenta.setBackground(new java.awt.Color(204, 204, 204));

        jLabel19.setText("Precio");

        txtDescripccionVenta.setEditable(false);
        txtDescripccionVenta.setBackground(new java.awt.Color(204, 204, 204));

        txtIDVentas1.setBackground(new java.awt.Color(204, 204, 204));
        txtIDVentas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDVentas1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(txtIDVentas))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtplatilloVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtDescripccionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(21, 21, 21))
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(txtStockVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(txtIDVentas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(1, 1, 1)
                        .addComponent(txtDescripccionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(1, 1, 1)
                        .addComponent(txtplatilloVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14)
                                .addComponent(jLabel19))
                            .addGap(1, 1, 1)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtStockVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(1, 1, 1)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtIDVentas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIDVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setText("Id Cliente");

        txtIdClienteVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdClienteVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdClienteVentaKeyTyped(evt);
            }
        });

        txtNombreVenta.setEditable(false);
        txtNombreVenta.setBackground(new java.awt.Color(204, 204, 204));

        jLabel16.setText("Nombre");

        txtTelVenta.setEditable(false);
        txtTelVenta.setBackground(new java.awt.Color(204, 204, 204));

        jLabel17.setText("Telefono");

        jLabel18.setText("Direccion");

        txtDirVenta.setEditable(false);
        txtDirVenta.setBackground(new java.awt.Color(204, 204, 204));

        txtCorreoVenta.setEditable(false);
        txtCorreoVenta.setBackground(new java.awt.Color(204, 204, 204));

        jLabel20.setText("Correo");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtIdClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtTelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDirVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDirVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jLabel9.setText("TOTAL:");

        Labeltotal.setText("__________");

        btnEliminarVenta.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        btnEliminarVenta.setText("X");
        btnEliminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVentaActionPerformed(evt);
            }
        });

        btnCrearPDF.setIcon(new javax.swing.ImageIcon("E:\\Documentos\\NetBeansProjects\\Restaurante1\\src\\main\\java\\IMG\\impre.png")); // NOI18N
        btnCrearPDF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCrearPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPANELinterfazLayout = new javax.swing.GroupLayout(JPANELinterfaz);
        JPANELinterfaz.setLayout(JPANELinterfazLayout);
        JPANELinterfazLayout.setHorizontalGroup(
            JPANELinterfazLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPANELinterfazLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPANELinterfazLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(JPANELinterfazLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(btnEliminarVenta))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPANELinterfazLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPANELinterfazLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JPANELinterfazLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(Labeltotal))
                            .addComponent(btnCrearPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        JPANELinterfazLayout.setVerticalGroup(
            JPANELinterfazLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPANELinterfazLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPANELinterfazLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(JPANELinterfazLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPANELinterfazLayout.createSequentialGroup()
                        .addGroup(JPANELinterfazLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Labeltotal)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addComponent(btnCrearPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        JPestañas.addTab("tab1", JPANELinterfaz);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204, 225));

        btnCliente.setIcon(new javax.swing.ImageIcon("E:\\Documentos\\NetBeansProjects\\Restaurante1\\src\\main\\java\\IMG\\cliente.png")); // NOI18N
        btnCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        btnEmpleado.setIcon(new javax.swing.ImageIcon("E:\\Documentos\\NetBeansProjects\\Restaurante1\\src\\main\\java\\IMG\\empleado.png")); // NOI18N
        btnEmpleado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadoActionPerformed(evt);
            }
        });

        jLabel2.setText("CLIENTE");

        jLabel3.setText("EMPLEADO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(144, 144, 144))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(53, 53, 53))
        );

        JPestañas.addTab("tab2", jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204, 225));

        TBCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Cliente", "Nombres", "Apellidos", "Nit", "Correo", "Direccion", "Telefono", "Estado"
            }
        ));
        TBCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBClienteMouseClicked(evt);
            }
        });
        Table1.setViewportView(TBCliente);
        if (TBCliente.getColumnModel().getColumnCount() > 0) {
            TBCliente.getColumnModel().getColumn(0).setPreferredWidth(75);
            TBCliente.getColumnModel().getColumn(1).setPreferredWidth(125);
            TBCliente.getColumnModel().getColumn(2).setPreferredWidth(125);
            TBCliente.getColumnModel().getColumn(3).setPreferredWidth(60);
            TBCliente.getColumnModel().getColumn(4).setPreferredWidth(125);
            TBCliente.getColumnModel().getColumn(5).setPreferredWidth(100);
            TBCliente.getColumnModel().getColumn(6).setPreferredWidth(80);
            TBCliente.getColumnModel().getColumn(7).setPreferredWidth(70);
        }

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setText("Nombre ");

        jLabel24.setText("Apellido");

        jLabel25.setText("Correo");

        jLabel26.setText("Nit");

        txtApellidoCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoCliActionPerformed(evt);
            }
        });

        txtNitCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNitCliKeyTyped(evt);
            }
        });

        CBEstadoCli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija Estado del Cliente", "Activo", "Inactivo" }));

        txtIDCli.setEditable(false);
        txtIDCli.setBackground(new java.awt.Color(204, 204, 204));

        jLabel27.setText("Direccion");

        txtTelefonoCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoCliKeyTyped(evt);
            }
        });

        jLabel28.setText("No.Telefono");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreCli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(txtCorreoCli, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellidoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(txtDireccionCli, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNitCli, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(txtTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(CBEstadoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(txtIDCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(jLabel24)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNitCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDireccionCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBEstadoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))))
        );

        txtbuscaridCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaridCliKeyTyped(evt);
            }
        });

        btnBuscaCli.setText("Buscar");
        btnBuscaCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaCliActionPerformed(evt);
            }
        });

        btnActualizarCli.setText("Actualizar");
        btnActualizarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCliActionPerformed(evt);
            }
        });

        btnEliminarCli.setText("Eliminar");
        btnEliminarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Table1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtbuscaridCli, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(btnBuscaCli, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnActualizarCli)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarCli)
                    .addComponent(btnActualizarCli)
                    .addComponent(btnBuscaCli)
                    .addComponent(txtbuscaridCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Table1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        JPestañas.addTab("tab3", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204, 225));

        TBEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Empleado", "Nombres", "Apellidos", "Correo", "Edad", "Genero", "Estado"
            }
        ));
        TBEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBEmpleadoMouseClicked(evt);
            }
        });
        Table2.setViewportView(TBEmpleado);
        if (TBEmpleado.getColumnModel().getColumnCount() > 0) {
            TBEmpleado.getColumnModel().getColumn(0).setPreferredWidth(30);
            TBEmpleado.getColumnModel().getColumn(1).setPreferredWidth(130);
            TBEmpleado.getColumnModel().getColumn(2).setPreferredWidth(130);
            TBEmpleado.getColumnModel().getColumn(3).setPreferredWidth(150);
            TBEmpleado.getColumnModel().getColumn(4).setPreferredWidth(50);
            TBEmpleado.getColumnModel().getColumn(5).setPreferredWidth(80);
            TBEmpleado.getColumnModel().getColumn(6).setPreferredWidth(80);
        }

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setText("Nombre ");

        jLabel21.setText("Apellido");

        jLabel22.setText("Correo");

        jLabel29.setText("Edad");

        txtEdadEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadEmpleadoKeyTyped(evt);
            }
        });

        CBEstadoEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija Estado del Empleado", "Activo", "Inactivo" }));

        txtIDEmpleado.setEditable(false);
        txtIDEmpleado.setBackground(new java.awt.Color(204, 204, 204));

        CBGeneroEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija Genero", "Masculino", "Femenino", " " }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(txtCorreoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addComponent(CBGeneroEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CBEstadoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(txtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(txtApellidoEmpleado))))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel29))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtEdadEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel21)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEdadEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBEstadoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBGeneroEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtbuscaridEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaridEmpleadoKeyTyped(evt);
            }
        });

        btnBuscarEmpleado.setText("Buscar");
        btnBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEmpleadoActionPerformed(evt);
            }
        });

        btnActualizEmpleado.setText("Actualizar");
        btnActualizEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizEmpleadoActionPerformed(evt);
            }
        });

        btnEliminarEmpleado.setText("Eliminar");
        btnEliminarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Table2, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtbuscaridEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnActualizEmpleado)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarEmpleado)
                    .addComponent(btnActualizEmpleado)
                    .addComponent(btnBuscarEmpleado)
                    .addComponent(txtbuscaridEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Table2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        JPestañas.addTab("tab4", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204, 225));

        TBPlatillos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Platillo", "Platillo", "Descripcion", "Precio", "Stock", "Estado"
            }
        ));
        TBPlatillos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBPlatillosMouseClicked(evt);
            }
        });
        Table3.setViewportView(TBPlatillos);
        if (TBPlatillos.getColumnModel().getColumnCount() > 0) {
            TBPlatillos.getColumnModel().getColumn(0).setPreferredWidth(60);
            TBPlatillos.getColumnModel().getColumn(1).setPreferredWidth(150);
            TBPlatillos.getColumnModel().getColumn(2).setPreferredWidth(150);
            TBPlatillos.getColumnModel().getColumn(3).setPreferredWidth(70);
            TBPlatillos.getColumnModel().getColumn(4).setPreferredWidth(70);
            TBPlatillos.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Nombre del Platillo");

        jLabel5.setText("Descripción del Platillo ");

        jLabel6.setText("Precio");

        jLabel7.setText("Stock");

        txtPrecio_Pla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecio_PlaKeyTyped(evt);
            }
        });

        txtStock_Pla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStock_PlaKeyTyped(evt);
            }
        });

        CBEstado_Pla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija Estado del Platillo", "Activo", "Inactivo" }));

        txtID_pla.setEditable(false);
        txtID_pla.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(94, 94, 94)
                        .addComponent(jLabel5))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre_pla, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtPrecio_Pla, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtStock_Pla, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDescrip_Pla, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(CBEstado_Pla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtID_pla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre_pla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescrip_Pla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecio_Pla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStock_Pla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBEstado_Pla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID_pla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGuardarPlatillo.setText("Guardar");
        btnGuardarPlatillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPlatilloActionPerformed(evt);
            }
        });

        btnActualizarPlatillo.setText("Actualizar");
        btnActualizarPlatillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPlatilloActionPerformed(evt);
            }
        });

        btnEliminarPlatillo.setText("Eliminar");
        btnEliminarPlatillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPlatilloActionPerformed(evt);
            }
        });

        btnBuscarPlatillo.setText("Buscar");
        btnBuscarPlatillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPlatilloActionPerformed(evt);
            }
        });

        txtbuscaridplatillo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaridplatilloKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Table3, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtbuscaridplatillo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarPlatillo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActualizarPlatillo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminarPlatillo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGuardarPlatillo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarPlatillo)
                    .addComponent(btnEliminarPlatillo)
                    .addComponent(btnActualizarPlatillo)
                    .addComponent(btnBuscarPlatillo)
                    .addComponent(txtbuscaridplatillo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Table3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        JPestañas.addTab("tab5", jPanel5);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204, 225));

        TBFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Factura", "Cliente", "Total", "Fecha"
            }
        ));
        TBFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBFacturaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TBFactura);

        txtIdFac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdFacKeyTyped(evt);
            }
        });

        btnAbrirFac.setText("Fac");
        btnAbrirFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirFacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAbrirFac)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdFac, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrirFac)
                    .addComponent(txtIdFac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        JPestañas.addTab("tab6", jPanel1);

        getContentPane().add(JPestañas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 730, 370));

        btnCVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCVenta.setText("Hacer Venta");
        btnCVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btnCVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 93, 184, 38));

        btnCreacionCyE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCreacionCyE.setText("Creación de Cliente y Empleado");
        btnCreacionCyE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreacionCyEActionPerformed(evt);
            }
        });
        getContentPane().add(btnCreacionCyE, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 149, 184, 38));

        brnConfigurarClientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        brnConfigurarClientes.setText("Configurar Clientes");
        brnConfigurarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnConfigurarClientesActionPerformed(evt);
            }
        });
        getContentPane().add(brnConfigurarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 205, 184, 38));

        btnConfigurariEmpleados.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConfigurariEmpleados.setText("Configurar Empleados");
        btnConfigurariEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigurariEmpleadosActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfigurariEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 261, 184, 38));

        btnConfigurarPlatillos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConfigurarPlatillos.setText("Configurar Platillos");
        btnConfigurarPlatillos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigurarPlatillosActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfigurarPlatillos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 317, 184, 38));

        btnVerVentas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVerVentas.setText("Ver Ventas");
        btnVerVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVentasActionPerformed(evt);
            }
        });
        getContentPane().add(btnVerVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 373, 184, 38));

        btnRegistrarAdmin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRegistrarAdmin.setText("Registrar Admin");
        btnRegistrarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarAdminActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrarAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 429, 184, 38));

        jLabel30.setIcon(new javax.swing.ImageIcon("E:\\Documentos\\NetBeansProjects\\Restaurante1\\src\\main\\java\\IMG\\cp1.jpg")); // NOI18N
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
//src/main/java/IMG/cafe.png
    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        
        Registrar_Cliente RC = new Registrar_Cliente();
        RC.setVisible(true);
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadoActionPerformed
        
        Registrar_Empleado RC = new Registrar_Empleado();
        RC.setVisible(true);
    }//GEN-LAST:event_btnEmpleadoActionPerformed

    private void btnCVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCVentaActionPerformed
        JPestañas.setSelectedIndex(0);
        
        JPestañas.setEnabledAt(0, false);
        JPestañas.setEnabledAt(1, false);
        JPestañas.setEnabledAt(2, false);
        JPestañas.setEnabledAt(3, false);
        JPestañas.setEnabledAt(4, false);
        JPestañas.setEnabledAt(5, false);
    }//GEN-LAST:event_btnCVentaActionPerformed

    private void btnCreacionCyEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreacionCyEActionPerformed
        JPestañas.setSelectedIndex(1);
    }//GEN-LAST:event_btnCreacionCyEActionPerformed

    private void brnConfigurarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnConfigurarClientesActionPerformed
        LimpiarTabla();
        ListarCliente();
        JPestañas.setSelectedIndex(2);
    }//GEN-LAST:event_brnConfigurarClientesActionPerformed

    private void btnConfigurariEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigurariEmpleadosActionPerformed
        LimpiarTabla();
        ListarEmpleado();
        JPestañas.setSelectedIndex(3);
    }//GEN-LAST:event_btnConfigurariEmpleadosActionPerformed

    private void btnConfigurarPlatillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigurarPlatillosActionPerformed
        LimpiarTabla();
        ListarPlatillos();
        JPestañas.setSelectedIndex(4);
    }//GEN-LAST:event_btnConfigurarPlatillosActionPerformed

    private void btnVerVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVentasActionPerformed
        LimpiarTabla();
        ListarFacturas();
        JPestañas.setSelectedIndex(5);
    }//GEN-LAST:event_btnVerVentasActionPerformed

    private void btnGuardarPlatilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPlatilloActionPerformed
        if(!"".equals(txtNombre_pla.getText()) && !"".equals(txtDescrip_Pla.getText()) && !"".equals(txtPrecio_Pla.getText()) && !"".equals(txtStock_Pla.getText()))
        {
            for (int i = 0; i<TBPlatillos.getRowCount(); i++)
            {
                if(TBPlatillos.getValueAt(i, 2).equals(txtDescrip_Pla.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Producto ya Registrado");
                    return;
                }
            }
            
            Pla.setNombre_Pla(txtNombre_pla.getText());
            Pla.setDescripcion_Pla(txtDescrip_Pla.getText());
            Pla.setPrecio_Pla(Double.parseDouble(txtPrecio_Pla.getText()));
            Pla.setStock_Pla(Integer.parseInt(txtStock_Pla.getText()));
            
            String op = CBEstado_Pla.getSelectedItem().toString();
            
            if(!"Elija Estado del Platillo".equals(op))
            {
                if(JOptionPane.showConfirmDialog(null, "Guardar Datos?", "Confirmar", 1)==0)
                {
                    Pla.setEstado_Pla(op);
                    PlaC.RegistroPlatillos(Pla);
                    JOptionPane.showMessageDialog(null, "Platillo Registrado");
                    LimpiarPla();
                    LimpiarTabla();
                    ListarPlatillos();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Platillo no Registrado");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Elija el Estado del Platillo, Porfavor");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Rellene los Campos, Porfavor");
        }
    }//GEN-LAST:event_btnGuardarPlatilloActionPerformed

    private void txtIDVentasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDVentasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtIDVentas.getText())) {
                String id = txtIDVentas.getText();
                Pla = PlaC.BuscarPlatillo(id);
                if (Pla.getNombre_Pla() != null) {

                    if (!"Inactivo".equals(Pla.getEstado_Pla())) {
                        txtplatilloVentas.setText("" + Pla.getNombre_Pla());
                        txtDescripccionVenta.setText("" + Pla.getDescripcion_Pla());
                        txtPrecioVenta.setText("" + Pla.getPrecio_Pla());
                        txtStockVenta.setText("" + Pla.getStock_Pla());
                        txtCantidadVenta.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(null, "Platillo Inactivo por el momento");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El platillo inexiste");
                    txtplatilloVentas.setText("");
                    txtDescripccionVenta.setText("");
                    txtPrecioVenta.setText("");
                    txtStockVenta.setText("");
                    txtIDVentas.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese Codigo");
                txtIDVentas.requestFocus();
            }
        }
    }//GEN-LAST:event_txtIDVentasKeyPressed

    private void txtCantidadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(!"".equals(txtCantidadVenta.getText()))
            {
                String id = txtIDVentas.getText();
                String platio = txtplatilloVentas.getText();
                String descrip = txtDescripccionVenta.getText();
                int cantidad = Integer.parseInt(txtCantidadVenta.getText());
                double precio = Double.parseDouble(txtPrecioVenta.getText());
                double total = cantidad * precio;
                
                int stock = Integer.parseInt(txtStockVenta.getText());
                if(stock >= cantidad)
                {
                    item = item+1;
                    tmp =(DefaultTableModel) TBVentas.getModel();
                    for (int i = 0; i<TBVentas.getRowCount(); i++)
                    {
                        if(TBVentas.getValueAt(i, 0).equals(txtIDVentas.getText()))
                        {
                            JOptionPane.showMessageDialog(null, "Producto ya Registrado");
                            return;
                        }
                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(id);
                    lista.add(platio);
                    lista.add(descrip);
                    lista.add(cantidad);
                    lista.add(precio);
                    lista.add(total);
                    
                    Object[] o = new Object[6];
                    o[0] = lista.get(1);
                    o[1] = lista.get(2);
                    o[2] = lista.get(3);
                    o[3] = lista.get(4);
                    o[4] = lista.get(5);
                    o[5] = lista.get(6);
                    tmp.addRow(o);
                    TotalaPagar();
                    LimpiarVenta();
                    txtIDVentas.requestFocus();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Stock no disponible");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Ingrese Cantidad");
            }
        }
    }//GEN-LAST:event_txtCantidadVentaKeyPressed

    private void txtIdClienteVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdClienteVentaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(!"".equals(txtIdClienteVenta.getText()))
            {
                int id = Integer.parseInt(txtIdClienteVenta.getText());
                cli = cliC.BuscarCliente(id);
                if(cli.getNombre_cli() != null)
                {
                    if (!"Inactivo".equals(cli.getEstado_cliente())) {
                        txtNombreVenta.setText(""+cli.getNombre_cli());
                        txtTelVenta.setText(""+cli.getTel_cli());
                        txtDirVenta.setText(""+cli.getDireccion_cli());
                        txtCorreoVenta.setText(""+cli.getCorreo_cli());      
                    }
                    else {
                        LimpiarVenta();
                        JOptionPane.showMessageDialog(null, "Cliente Inactivo por el momento");
                    }
                }
                else
                {
                    txtIdClienteVenta.setText("");
                    txtNombreVenta.setText("");
                    txtTelVenta.setText("");
                    txtDirVenta.setText("");
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                }
            }
        }
    }//GEN-LAST:event_txtIdClienteVentaKeyPressed

    private void btnEliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVentaActionPerformed

            modelo =(DefaultTableModel) TBVentas.getModel();
            if(!"".equals(txtIDVentas1.getText())){
                
                modelo.removeRow(TBVentas.getSelectedRow());
                TotalaPagar();
                txtIDVentas1.setText("");
                txtIDVentas.requestFocus();   
            }
           else
            {
                JOptionPane.showMessageDialog(null, "Error, Seleccine dato");
            }        
    }//GEN-LAST:event_btnEliminarVentaActionPerformed

    private void btnEliminarPlatilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPlatilloActionPerformed

            try {
                if(!"".equals(txtID_pla.getText()))
                {
                        Conexion ObjC = new Conexion();
                        Connection cone = ObjC.getConection();
                        PreparedStatement ps;

                        int fila = TBPlatillos.getSelectedRow();
                        String cod = TBPlatillos.getValueAt(fila, 0).toString();

                        if(JOptionPane.showConfirmDialog(null, "Eliminar Datos?", "Confirmar", 1)==0)
                        {
                            ps = cone.prepareStatement("DELETE FROM platillo WHERE Id_Platillo = ?");
                            ps.setString(1, cod);
                            ps.execute();
                            modelo.removeRow(fila);
                            txtID_pla.setText("");
                            JOptionPane.showMessageDialog(null, "Dato Eliminado");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Dato no Eliminado");
                        }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Seleccione un dato para Eliminar");
                }    

            } catch (SQLException e) {
                System.out.println("Error en borrar:"+e);
            }
        
    }//GEN-LAST:event_btnEliminarPlatilloActionPerformed

    private void TBPlatillosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBPlatillosMouseClicked
        int fila = TBPlatillos.rowAtPoint(evt.getPoint());
        txtID_pla.setText(TBPlatillos.getValueAt(fila, 0).toString());
        txtNombre_pla.setText(TBPlatillos.getValueAt(fila, 1).toString());
        txtDescrip_Pla.setText(TBPlatillos.getValueAt(fila, 2).toString());
        txtPrecio_Pla.setText(TBPlatillos.getValueAt(fila, 3).toString());
        txtStock_Pla.setText(TBPlatillos.getValueAt(fila, 4).toString());
        CBEstado_Pla.setSelectedItem(TBPlatillos.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_TBPlatillosMouseClicked

    
    private void btnActualizarPlatilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPlatilloActionPerformed
        try {
           if(!"".equals(txtID_pla.getText()))
           {
                if(!"".equals(txtNombre_pla.getText()) && !"".equals(txtDescrip_Pla.getText()) && !"".equals(txtPrecio_Pla.getText()) && !"".equals(txtStock_Pla.getText()))
                {
                    Conexion ObjC = new Conexion();
                    Connection cone = ObjC.getConection();
                    PreparedStatement ps;
                    ResultSet rs;

                    if(JOptionPane.showConfirmDialog(null, "Modificar Datos?", "Confirmar", 1)==0)
                    {
                        ps = cone.prepareStatement("UPDATE platillo SET  Nombre_Platillo=?, Descripcion_Platillo=?, Precio_Platillo=?, Stock_Platillo=?, Estado_Platillo=? WHERE Id_Platillo=?");

                        ps.setString(1, txtNombre_pla.getText());
                        ps.setString(2, txtDescrip_Pla.getText());
                        ps.setDouble(3, Double.parseDouble(txtPrecio_Pla.getText()));
                        ps.setInt(4, Integer.parseInt(txtStock_Pla.getText()));
                        ps.setString(5, CBEstado_Pla.getSelectedItem().toString());
                        ps.setString(6, txtID_pla.getText());

                        ps.executeUpdate();

                            LimpiarPla();
                            LimpiarTabla();
                            ListarPlatillos();
                            JOptionPane.showMessageDialog(null, "Datos Modificados");
                        txtID_pla.setText("");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Datos no Modificados");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Rellene los datos");
                }
           }
           else
           {
               JOptionPane.showMessageDialog(null, "Seleccione un dato para Actualizar");
           }
        } 
        catch (Exception e) 
        {
            System.out.println("Error en Actualizar Platillo: "+e);
        }
    }//GEN-LAST:event_btnActualizarPlatilloActionPerformed

    private void btnBuscarPlatilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPlatilloActionPerformed
        try {
            if(!"".equals(txtbuscaridplatillo.getText()))
            {
                Conexion ObjC = new Conexion();
                Connection cone = ObjC.getConection();
                PreparedStatement ps;
                ResultSet rs;

                ps=cone.prepareStatement("SELECT * FROM platillo WHERE Id_platillo=?");
                ps.setInt(1, Integer.parseInt(txtbuscaridplatillo.getText()));
                rs = ps.executeQuery();
                if(rs.next())
                { 
                    txtNombre_pla.setText(rs.getString("Nombre_Platillo"));
                    txtDescrip_Pla.setText(rs.getString("Descripcion_Platillo"));
                    txtPrecio_Pla.setText(rs.getString("Precio_Platillo"));
                    txtStock_Pla.setText(rs.getString("Stock_Platillo"));
                    CBEstado_Pla.setSelectedItem(rs.getString("Estado_Platillo"));
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No existe ese id");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Ingrese ID");
            }
        } catch (Exception e) {
            System.out.println("Error en buscar platillo: "+e);
        }
    }//GEN-LAST:event_btnBuscarPlatilloActionPerformed

    void correos(){
       ema = txtCorreoVenta.getText();
       Correo cor = new Correo();
       cor.setVisible(true);
       
    }
    
    private void btnCrearPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPDFActionPerformed
        if(TBVentas.getRowCount() > 0)
        {
            if(!"".equals(txtNombreVenta.getText()))
            {
                correos();   
                RegistrarFactura();
                ActualizarStock();
                pdf();
                LimpiarVenta();
                LimpiarTBVenta();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Ingrese un Id de un cliente que exista");
            }
           
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Ingrese productos");
        }
    }//GEN-LAST:event_btnCrearPDFActionPerformed

    private void btnRegistrarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarAdminActionPerformed
        Registro_Administrador RA = new Registro_Administrador();
        RA.setVisible(true);
    }//GEN-LAST:event_btnRegistrarAdminActionPerformed

    private void TBFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBFacturaMouseClicked
        int fila = TBFactura.rowAtPoint(evt.getPoint());
        txtIdFac.setText(TBFactura.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_TBFacturaMouseClicked

    private void btnAbrirFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirFacActionPerformed
        try {
            int id = Integer.parseInt(txtIdFac.getText());
            File file = new File("src/main/java/pdf/factura"+id+".pdf");
            Desktop.getDesktop().open(file);
            
        } catch (IOException ex) {
            Logger.getLogger(restaurante.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error");
        }
    }//GEN-LAST:event_btnAbrirFacActionPerformed

    private void txtIDVentas1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDVentas1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDVentas1KeyPressed

    private void TBVentasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBVentasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBVentasKeyPressed

    private void TBVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBVentasMouseClicked
        int fila = TBVentas.rowAtPoint(evt.getPoint());
        txtIDVentas1.setText(TBVentas.getValueAt(fila, 0).toString());
        
    }//GEN-LAST:event_TBVentasMouseClicked

    private void txtApellidoCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoCliActionPerformed

    private void btnBuscaCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaCliActionPerformed
        try {
            if(!"".equals(txtbuscaridCli.getText()))
            {
                Conexion ObjC = new Conexion();
                Connection cone = ObjC.getConection();
                PreparedStatement ps;
                ResultSet rs;

                ps=cone.prepareStatement("SELECT * FROM Cliente WHERE Id_cliente=?");
                ps.setInt(1, Integer.parseInt(txtbuscaridCli.getText()));
                rs = ps.executeQuery();
                if(rs.next())
                {
                    txtNombreCli.setText(rs.getString("Nombre_Cliente"));
                    txtApellidoCli.setText(rs.getString("Apellido_Cliente"));
                    txtNitCli.setText(rs.getString("Nit_Cliente"));
                    txtTelefonoCli.setText(rs.getString("Telefono_Cliente"));
                    txtCorreoCli.setText(rs.getString("Correo_Cliente"));
                    txtDireccionCli.setText(rs.getString("Direccion_Cliente"));
                    CBEstadoCli.setSelectedItem(rs.getString("Estado_Cliente"));
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No existe ese id");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Ingrese ID");
            }
        } catch (Exception e) {
            System.out.println("Error en buscar Cliente: "+e);
        }
    }//GEN-LAST:event_btnBuscaCliActionPerformed

    private void btnActualizarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarCliActionPerformed
        try {
            if(!"".equals(txtIDCli.getText()))
            {
                if(!"".equals(txtNombreCli.getText()) && !"".equals(txtApellidoCli.getText()) && !"".equals(txtNitCli.getText()) && !"".equals(txtTelefonoCli.getText()) && !"".equals(txtCorreoCli.getText()) && !"".equals(txtDireccionCli.getText()))
                {
                    Conexion ObjC = new Conexion();
                    Connection cone = ObjC.getConection();
                    PreparedStatement ps;
                    ResultSet rs;

                    ps = cone.prepareStatement("UPDATE Cliente SET  Nombre_Cliente=?, Apellido_Cliente=?, Nit_Cliente=?, Telefono_Cliente=?, Correo_Cliente=?, Direccion_Cliente=?, Estado_Cliente=? WHERE Id_Cliente=?");

                    ps.setString(1, txtNombreCli.getText());
                    ps.setString(2, txtApellidoCli.getText());
                    ps.setInt(3, Integer.parseInt(txtNitCli.getText()));
                    ps.setInt(4, Integer.parseInt(txtTelefonoCli.getText()));
                    ps.setString(5, txtCorreoCli.getText());
                    ps.setString(6, txtDireccionCli.getText());
                    ps.setString(7, CBEstadoCli.getSelectedItem().toString());
                    ps.setString(8, txtIDCli.getText());

                    ps.executeUpdate();

                    LimpiarC();
                    LimpiarTabla();
                    ListarCliente();
                    JOptionPane.showMessageDialog(null, "Modificado");
                    txtIDCli.setText("");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Rellene los datos");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Seleccione un dato para Actualizar");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error en Actualizar Cliente: "+e);
        }
    }//GEN-LAST:event_btnActualizarCliActionPerformed

    private void btnEliminarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCliActionPerformed
        try {
            if(!"".equals(txtIDCli.getText()))
            {
                Conexion ObjC = new Conexion();
                Connection cone = ObjC.getConection();
                PreparedStatement ps;

                int fila = TBCliente.getSelectedRow();
                String cod = TBCliente.getValueAt(fila, 0).toString();

                ps = cone.prepareStatement("DELETE FROM Cliente WHERE Id_Cliente = ?");
                ps.setString(1, cod);
                ps.execute();

                modelo.removeRow(fila);

                txtID_pla.setText("");
                JOptionPane.showMessageDialog(null, "Dato Eliminado");
                LimpiarC();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Seleccione un dato para Eliminar");
            }

        } catch (SQLException e) {
            System.out.println("Error en borrar Cliente:"+e);
        }
    }//GEN-LAST:event_btnEliminarCliActionPerformed

    private void btnBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEmpleadoActionPerformed
        try {
            if(!"".equals(txtbuscaridEmpleado.getText()))
            {
                Conexion ObjC = new Conexion();
                Connection cone = ObjC.getConection();
                PreparedStatement ps;
                ResultSet rs;

                ps=cone.prepareStatement("SELECT * FROM Empleado WHERE Id_empleado=?");
                ps.setInt(1, Integer.parseInt(txtbuscaridEmpleado.getText()));
                rs = ps.executeQuery();
                if(rs.next())
                {
                    txtNombreEmpleado.setText(rs.getString("Nombre_Empleado"));
                    txtApellidoEmpleado.setText(rs.getString("Apellido_Empleado"));
                    txtEdadEmpleado.setText(rs.getString("Edad_Empleado"));
                    txtCorreoEmpleado.setText(rs.getString("Correo_Empleado"));
                    CBGeneroEmpleado.setSelectedItem(rs.getString("Genero_Empleado"));
                    CBEstadoEmpleado.setSelectedItem(rs.getString("Estado_Empleado"));
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No existe ese id");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Ingrese ID");
            }
        } catch (Exception e) {
            System.out.println("Error en buscar Empleado: "+e);
        }
    }//GEN-LAST:event_btnBuscarEmpleadoActionPerformed

    private void btnActualizEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizEmpleadoActionPerformed
        try {
            if(!"".equals(txtIDEmpleado.getText()))
            {
                if(!"".equals(txtNombreEmpleado.getText()) && !"".equals(txtApellidoEmpleado.getText()) && !"".equals(txtEdadEmpleado.getText()) && !"".equals(txtCorreoEmpleado.getText()))
                {
                    Conexion ObjC = new Conexion();
                    Connection cone = ObjC.getConection();
                    PreparedStatement ps;
                    ResultSet rs;

                    ps = cone.prepareStatement("UPDATE Empleado SET  Nombre_Empleado=?, Apellido_Empleado=?, Edad_Empleado=?, Correo_Empleado=?, Genero_Empleado=?, Estado_Empleado=? WHERE Id_Empleado=?");

                    ps.setString(1, txtNombreEmpleado.getText());
                    ps.setString(2, txtApellidoEmpleado.getText());
                    ps.setInt(3, Integer.parseInt(txtEdadEmpleado.getText()));
                    ps.setString(4, txtCorreoEmpleado.getText());
                    ps.setString(5, CBGeneroEmpleado.getSelectedItem().toString());
                    ps.setString(6, CBEstadoEmpleado.getSelectedItem().toString());
                    ps.setString(7, txtIDEmpleado.getText());

                    ps.executeUpdate();

                    LimpiarE();
                    LimpiarTabla();
                    ListarEmpleado();
                    JOptionPane.showMessageDialog(null, "Modificado");
                    txtIDEmpleado.setText("");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Rellene los datos");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Seleccione un dato para Actualizar");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error en Actualizar Platillo: "+e);
        }
    }//GEN-LAST:event_btnActualizEmpleadoActionPerformed

    private void btnEliminarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpleadoActionPerformed
        try {
            if(!"".equals(txtIDEmpleado.getText()))
            {
                Conexion ObjC = new Conexion();
                Connection cone = ObjC.getConection();
                PreparedStatement ps;

                int fila = TBEmpleado.getSelectedRow();
                String cod = TBEmpleado.getValueAt(fila, 0).toString();

                ps = cone.prepareStatement("DELETE FROM Empleado WHERE Id_Empleado = ?");
                ps.setString(1, cod);
                ps.execute();

                modelo.removeRow(fila);

                txtID_pla.setText("");
                JOptionPane.showMessageDialog(null, "Dato Eliminado");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Seleccione un dato para Eliminar");
            }

        } catch (SQLException e) {
            System.out.println("Error en borrar:"+e);
        }
    }//GEN-LAST:event_btnEliminarEmpleadoActionPerformed

    private void TBClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBClienteMouseClicked
        int fila = TBCliente.rowAtPoint(evt.getPoint());
        txtIDCli.setText(TBCliente.getValueAt(fila, 0).toString());
        txtNombreCli.setText(TBCliente.getValueAt(fila, 1).toString());
        txtApellidoCli.setText(TBCliente.getValueAt(fila, 2).toString());
        txtNitCli.setText(TBCliente.getValueAt(fila, 3).toString());
        txtCorreoCli.setText(TBCliente.getValueAt(fila, 4).toString());
        txtDireccionCli.setText(TBCliente.getValueAt(fila, 5).toString());
        txtTelefonoCli.setText(TBCliente.getValueAt(fila, 6).toString());
        CBEstadoCli.setSelectedItem(TBCliente.getValueAt(fila, 7).toString());
    }//GEN-LAST:event_TBClienteMouseClicked

    private void TBEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBEmpleadoMouseClicked
        int fila = TBEmpleado.rowAtPoint(evt.getPoint());
        txtIDEmpleado.setText(TBEmpleado.getValueAt(fila, 0).toString());
        txtNombreEmpleado.setText(TBEmpleado.getValueAt(fila, 1).toString());
        txtApellidoEmpleado.setText(TBEmpleado.getValueAt(fila, 2).toString());
        txtCorreoEmpleado.setText(TBEmpleado.getValueAt(fila, 3).toString());
        txtEdadEmpleado.setText(TBEmpleado.getValueAt(fila, 4).toString());
        
        CBGeneroEmpleado.setSelectedItem(TBEmpleado.getValueAt(fila, 5).toString());
        CBEstadoEmpleado.setSelectedItem(TBEmpleado.getValueAt(fila, 6).toString());
    }//GEN-LAST:event_TBEmpleadoMouseClicked

    private void txtIDVentasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDVentasKeyTyped
        restrin.numberKeyPress(evt);
    }//GEN-LAST:event_txtIDVentasKeyTyped

    private void txtIdClienteVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdClienteVentaKeyTyped
        restrin.numberKeyPress(evt);
    }//GEN-LAST:event_txtIdClienteVentaKeyTyped

    private void txtCantidadVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyTyped
        restrin.numberKeyPress(evt);
    }//GEN-LAST:event_txtCantidadVentaKeyTyped

    private void txtNitCliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitCliKeyTyped
        restrin.numberKeyPress(evt);
    }//GEN-LAST:event_txtNitCliKeyTyped

    private void txtTelefonoCliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoCliKeyTyped
        restrin.numberKeyPress(evt);
    }//GEN-LAST:event_txtTelefonoCliKeyTyped

    private void txtbuscaridCliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaridCliKeyTyped
        restrin.numberKeyPress(evt);
    }//GEN-LAST:event_txtbuscaridCliKeyTyped

    private void txtEdadEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadEmpleadoKeyTyped
        restrin.numberKeyPress(evt);
    }//GEN-LAST:event_txtEdadEmpleadoKeyTyped

    private void txtbuscaridEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaridEmpleadoKeyTyped
        restrin.numberKeyPress(evt);
    }//GEN-LAST:event_txtbuscaridEmpleadoKeyTyped

    private void txtStock_PlaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStock_PlaKeyTyped
        restrin.numberKeyPress(evt);
    }//GEN-LAST:event_txtStock_PlaKeyTyped

    private void txtbuscaridplatilloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaridplatilloKeyTyped
        restrin.numberKeyPress(evt);
    }//GEN-LAST:event_txtbuscaridplatilloKeyTyped

    private void txtIdFacKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdFacKeyTyped
        restrin.numberKeyPress(evt);
    }//GEN-LAST:event_txtIdFacKeyTyped

    private void txtPrecio_PlaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecio_PlaKeyTyped
        restrin.numberDecimalKeyPress(evt, txtPrecio_Pla);
    }//GEN-LAST:event_txtPrecio_PlaKeyTyped

    void LimpiarPla()
    {
        txtNombre_pla.setText("");
        txtDescrip_Pla.setText("");
        txtPrecio_Pla.setText("");
        txtStock_Pla.setText("");
        CBEstado_Pla.setSelectedItem("Elija Estado del Platillo");
    }
    
    void LimpiarC()
    {
        txtNombreCli.setText("");
        txtApellidoCli.setText("");
        txtNitCli.setText("");
        txtTelefonoCli.setText("");
        txtCorreoCli.setText("");
        txtDireccionCli.setText("");
        CBEstadoCli.setSelectedItem("Elija Estado del Cliente");
    }
    
    void LimpiarE()
    {
        txtNombreEmpleado.setText("");
        txtApellidoEmpleado.setText("");
        txtEdadEmpleado.setText("");
        txtCorreoEmpleado.setText("");
        CBGeneroEmpleado.setSelectedItem("Elija Genero");
        CBEstadoEmpleado.setSelectedItem("Elija Estado del Empleado");
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
            java.util.logging.Logger.getLogger(restaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(restaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(restaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(restaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new restaurante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBEstadoCli;
    private javax.swing.JComboBox<String> CBEstadoEmpleado;
    private javax.swing.JComboBox<String> CBEstado_Pla;
    private javax.swing.JComboBox<String> CBGeneroEmpleado;
    private javax.swing.JPanel JPANELinterfaz;
    private javax.swing.JTabbedPane JPestañas;
    private javax.swing.JLabel Labeltotal;
    private javax.swing.JTable TBCliente;
    private javax.swing.JTable TBEmpleado;
    private javax.swing.JTable TBFactura;
    private javax.swing.JTable TBPlatillos;
    private javax.swing.JTable TBVentas;
    private javax.swing.JScrollPane Table1;
    private javax.swing.JScrollPane Table2;
    private javax.swing.JScrollPane Table3;
    private javax.swing.JButton brnConfigurarClientes;
    private javax.swing.JButton btnAbrirFac;
    private javax.swing.JButton btnActualizEmpleado;
    private javax.swing.JButton btnActualizarCli;
    private javax.swing.JButton btnActualizarPlatillo;
    private javax.swing.JButton btnBuscaCli;
    private javax.swing.JButton btnBuscarEmpleado;
    private javax.swing.JButton btnBuscarPlatillo;
    private javax.swing.JButton btnCVenta;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnConfigurarPlatillos;
    private javax.swing.JButton btnConfigurariEmpleados;
    private javax.swing.JButton btnCreacionCyE;
    private javax.swing.JButton btnCrearPDF;
    private javax.swing.JButton btnEliminarCli;
    private javax.swing.JButton btnEliminarEmpleado;
    private javax.swing.JButton btnEliminarPlatillo;
    private javax.swing.JButton btnEliminarVenta;
    private javax.swing.JButton btnEmpleado;
    private javax.swing.JButton btnGuardarPlatillo;
    private javax.swing.JButton btnRegistrarAdmin;
    private javax.swing.JButton btnVerVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtApellidoCli;
    private javax.swing.JTextField txtApellidoEmpleado;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtCorreoCli;
    private javax.swing.JTextField txtCorreoEmpleado;
    private javax.swing.JTextField txtCorreoVenta;
    private javax.swing.JTextField txtDescrip_Pla;
    private javax.swing.JTextField txtDescripccionVenta;
    private javax.swing.JTextField txtDirVenta;
    private javax.swing.JTextField txtDireccionCli;
    private javax.swing.JTextField txtEdadEmpleado;
    private javax.swing.JTextField txtIDCli;
    private javax.swing.JTextField txtIDEmpleado;
    private javax.swing.JTextField txtIDVentas;
    private javax.swing.JTextField txtIDVentas1;
    private javax.swing.JTextField txtID_pla;
    private javax.swing.JTextField txtIdClienteVenta;
    private javax.swing.JTextField txtIdFac;
    private javax.swing.JTextField txtNitCli;
    private javax.swing.JTextField txtNombreCli;
    private javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextField txtNombreVenta;
    private javax.swing.JTextField txtNombre_pla;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtPrecio_Pla;
    private javax.swing.JTextField txtStockVenta;
    private javax.swing.JTextField txtStock_Pla;
    private javax.swing.JTextField txtTelVenta;
    private javax.swing.JTextField txtTelefonoCli;
    private javax.swing.JTextField txtbuscaridCli;
    private javax.swing.JTextField txtbuscaridEmpleado;
    private javax.swing.JTextField txtbuscaridplatillo;
    private javax.swing.JTextField txtplatilloVentas;
    // End of variables declaration//GEN-END:variables
    
    private void TotalaPagar()
    {
        TotalPagar = 0.00;
        int numFila = TBVentas.getRowCount();
        for (int i = 0; i<numFila; i++)
        {
            double can = Double.parseDouble(String.valueOf(TBVentas.getModel().getValueAt(i, 5)));
            TotalPagar = TotalPagar+can;
        }
        Labeltotal.setText(String.format("%.2f", TotalPagar)); 
    }

    private void LimpiarVenta()
    {
        txtIDVentas.setText("");
        txtplatilloVentas.setText("");
        txtDescripccionVenta.setText("");
        txtPrecioVenta.setText("");
        txtPrecioVenta.setText("");
        txtCantidadVenta.setText("");
        txtStockVenta.setText("");
        txtIdClienteVenta.setText("");
        txtNombreVenta.setText("");
        txtTelVenta.setText("");
        txtDirVenta.setText("");
        txtCorreoVenta.setText("");
    }
    
    private void LimpiarTBVenta() {
        tmp = (DefaultTableModel) TBVentas.getModel();
        int fila = TBVentas.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }
    
    private void RegistrarFactura()
    { 
        double monto = TotalPagar;
        Fac.setCliente_Fac(txtNombreVenta.getText());
        Fac.setTotal_Fac(monto);
        Fac.setFecha_Fac(fechaAct);
        F.RegistrarFactura(Fac);
    }
    
    private void ActualizarStock()
    {
         for (int i = 0; i < TBVentas.getRowCount(); i++) {
            String cod = TBVentas.getValueAt(i, 0).toString();
            int cant = Integer.parseInt(TBVentas.getValueAt(i, 3).toString());
            Pla = PlaC.BuscarPlatillo(cod);
            int StockActual = Pla.getStock_Pla() - cant;
            PlaC.ActualizarStock(StockActual, cod);
        }
    }
    private void pdf()
    {
        try {
            int id = F.IdFactura();
            FileOutputStream archivo; 
            File file = new File("src/main/java/pdf/factura"+id+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document(); 
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("src/main/java/IMG/cafe.png");
            
            Paragraph fecha = new Paragraph();
            
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("Factura: "+id+"\nFecha: "+ new SimpleDateFormat("dd/MM/yyyy").format(date)+"\n\n");
            
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[] {20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            Encabezado.addCell(img);
            
            String nom = "Restaurante Hyrule";
            String Tel = "55685063";
            String dir = "Rabinal";
            
            
            Encabezado.addCell("");
            Encabezado.addCell("Nombre:"+nom+"\nTelefono:"+Tel+"\nDireccion:"+dir);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);
            
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("Dato del cliente"+"\n\n");
            doc.add(cli);
            
            PdfPTable TBcli = new PdfPTable(4);
            TBcli.setWidthPercentage(100);
            TBcli.getDefaultCell().setBorder(0);
            float[] columnacli = new float[]{20f, 50f, 70f, 40f};
            TBcli.setWidths(columnacli);
            TBcli.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cl1 = new PdfPCell(new Phrase("Id"));
            PdfPCell cl2 = new PdfPCell(new Phrase("Nombre"));
            PdfPCell cl3 = new PdfPCell(new Phrase("Telefono"));
            PdfPCell cl4 = new PdfPCell(new Phrase("Direccion"));
            
            cl1.setBorder(0);
            cl2.setBorder(0);
            cl3.setBorder(0);
            cl4.setBorder(0);
            
            cl1.setBackgroundColor(BaseColor.RED);
            cl2.setBackgroundColor(BaseColor.RED);
            cl3.setBackgroundColor(BaseColor.RED);
            cl4.setBackgroundColor(BaseColor.RED);
            
            TBcli.addCell(cl1);
            TBcli.addCell(cl2);
            TBcli.addCell(cl3);
            TBcli.addCell(cl4);
            
            TBcli.addCell(txtIdClienteVenta.getText());
            TBcli.addCell(txtNombreVenta.getText());
            TBcli.addCell(txtTelVenta.getText());
            TBcli.addCell(txtDirVenta.getText());
            
            doc.add(TBcli);
            
            PdfPTable TBpla = new PdfPTable(5);
            TBpla.setWidthPercentage(100);
            TBpla.getDefaultCell().setBorder(0);
            float[] columnapro = new float[]{40f, 40f, 30f, 30f, 30f};
            TBpla.setWidths(columnapro);
            TBpla.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            PdfPCell pro1 = new PdfPCell(new Phrase("Platillo"));
            PdfPCell pro2 = new PdfPCell(new Phrase("Descripcion"));
            PdfPCell pro3 = new PdfPCell(new Phrase("Cantidad"));
            PdfPCell pro4 = new PdfPCell(new Phrase("Precio U"));
            PdfPCell pro5 = new PdfPCell(new Phrase("Precio T"));
            
            pro1.setBorder(0);
            pro2.setBorder(0);
            pro3.setBorder(0);
            pro4.setBorder(0);
            pro5.setBorder(0);
            
            pro1.setBackgroundColor(BaseColor.GRAY);
            pro2.setBackgroundColor(BaseColor.GRAY);
            pro3.setBackgroundColor(BaseColor.GRAY);
            pro4.setBackgroundColor(BaseColor.GRAY);
            pro5.setBackgroundColor(BaseColor.GRAY);
            
            TBpla.addCell(pro1);
            TBpla.addCell(pro2);
            TBpla.addCell(pro3);
            TBpla.addCell(pro4);
            TBpla.addCell(pro5);
            
            for(int i = 0; i< TBVentas.getRowCount(); i++)
            {
                String platillo = TBVentas.getValueAt(i, 1).toString();
                String descrip = TBVentas.getValueAt(i, 2).toString();
                String cant = TBVentas.getValueAt(i, 3).toString();
                String preU = TBVentas.getValueAt(i, 4).toString();
                String subT = TBVentas.getValueAt(i, 5).toString();
                
                TBpla.addCell(platillo);
                TBpla.addCell(descrip);
                TBpla.addCell(cant);
                TBpla.addCell(preU);
                TBpla.addCell(subT);
            }
            doc.add(TBpla);
            
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Cuenta: "+TotalPagar);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Firma\n\n");
            firma.add("---------------------");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);
            
            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("Gracias por su compra");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);
            
            doc.close();
            archivo.close();
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    }
}

