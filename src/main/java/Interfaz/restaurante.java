
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class restaurante extends javax.swing.JFrame {
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
    
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    int item;
    double TotalPagar = 0.00;
    
    public restaurante(){
        initComponents();
    }
    
    public restaurante(Login priv){
        initComponents();
        if(priv.getTipo_Emp_U().equals("Asistente")){
            btnConfigurarPlatillos.setEnabled(false);
            btnConfigurariEmpleados.setEnabled(false);
            brnConfigurarClientes.setEnabled(false);
        }
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
            ob[7] = ListarCli.get(i).getIdTipo_cli();
            ob[8] = ListarCli.get(i).getEstado_cliente();
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

        JPanelinterfaz = new javax.swing.JTabbedPane();
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
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtIdClienteVenta = new javax.swing.JTextField();
        txtNombreVenta = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTelVenta = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDirVenta = new javax.swing.JTextField();
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
        jPanel4 = new javax.swing.JPanel();
        Table2 = new javax.swing.JScrollPane();
        TBEmpleado = new javax.swing.JTable();
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
        btnCVenta = new javax.swing.JButton();
        btnCreacionCyE = new javax.swing.JButton();
        brnConfigurarClientes = new javax.swing.JButton();
        btnConfigurariEmpleados = new javax.swing.JButton();
        btnConfigurarPlatillos = new javax.swing.JButton();
        btnVerVentas = new javax.swing.JButton();
        btnRegistrarAdmin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TBVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID del Platillo", "Platillo", "Descripccion", "Cantidad", "Precio U", "Sub Total"
            }
        ));
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
        });

        jLabel14.setText("Disponible");

        txtStockVenta.setEditable(false);
        txtStockVenta.setBackground(new java.awt.Color(204, 204, 204));

        txtPrecioVenta.setEditable(false);
        txtPrecioVenta.setBackground(new java.awt.Color(204, 204, 204));

        jLabel19.setText("Precio");

        txtDescripccionVenta.setEditable(false);
        txtDescripccionVenta.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(txtIDVentas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtplatilloVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtDescripccionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel13))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStockVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel19)
                    .addComponent(jLabel11))
                .addGap(1, 1, 1)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtStockVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIDVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtplatilloVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDescripccionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setText("Id Cliente");

        txtIdClienteVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdClienteVentaKeyPressed(evt);
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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtIdClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDirVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDirVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addGroup(JPANELinterfazLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JPANELinterfazLayout.createSequentialGroup()
                        .addGroup(JPANELinterfazLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPANELinterfazLayout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCrearPDF)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(Labeltotal))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addGroup(JPANELinterfazLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Labeltotal)
                        .addComponent(jLabel9))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrearPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        JPanelinterfaz.addTab("tab1", JPANELinterfaz);

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
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(141, 141, 141))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(32, 32, 32))
        );

        JPanelinterfaz.addTab("tab2", jPanel2);

        TBCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Cliente", "Nombres", "Apellidos", "Nit", "Correo", "Direccion", "Telefono", "Tipo Cliente", "Estado"
            }
        ));
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
            TBCliente.getColumnModel().getColumn(8).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Table1, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addComponent(Table1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JPanelinterfaz.addTab("tab3", jPanel3);

        TBEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Empleado", "Nombres", "Apellidos", "Correo", "Edad", "Genero", "Estado"
            }
        ));
        Table2.setViewportView(TBEmpleado);
        if (TBEmpleado.getColumnModel().getColumnCount() > 0) {
            TBEmpleado.getColumnModel().getColumn(0).setPreferredWidth(60);
            TBEmpleado.getColumnModel().getColumn(1).setPreferredWidth(150);
            TBEmpleado.getColumnModel().getColumn(2).setPreferredWidth(150);
            TBEmpleado.getColumnModel().getColumn(3).setPreferredWidth(70);
            TBEmpleado.getColumnModel().getColumn(4).setPreferredWidth(70);
            TBEmpleado.getColumnModel().getColumn(5).setPreferredWidth(80);
            TBEmpleado.getColumnModel().getColumn(6).setPreferredWidth(80);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Table2, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addComponent(Table2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JPanelinterfaz.addTab("tab4", jPanel4);

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

        CBEstado_Pla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija Estado del Platillo", "Activo", "Inactivo" }));

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Table3, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Table3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        JPanelinterfaz.addTab("tab5", jPanel5);

        TBFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Factura", "Cliente", "Total", "Fecha"
            }
        ));
        jScrollPane2.setViewportView(TBFactura);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );

        JPanelinterfaz.addTab("tab6", jPanel1);

        btnCVenta.setText("Hacer Venta");
        btnCVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCVentaActionPerformed(evt);
            }
        });

        btnCreacionCyE.setText("Creación de Cliente y Empleado");
        btnCreacionCyE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreacionCyEActionPerformed(evt);
            }
        });

        brnConfigurarClientes.setText("Configurar Clientes");
        brnConfigurarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnConfigurarClientesActionPerformed(evt);
            }
        });

        btnConfigurariEmpleados.setText("Configurar Empleados");
        btnConfigurariEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigurariEmpleadosActionPerformed(evt);
            }
        });

        btnConfigurarPlatillos.setText("Configurar Platillos");
        btnConfigurarPlatillos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigurarPlatillosActionPerformed(evt);
            }
        });

        btnVerVentas.setText("Ver Ventas");
        btnVerVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVentasActionPerformed(evt);
            }
        });

        btnRegistrarAdmin.setText("Registrar Admin");
        btnRegistrarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreacionCyE, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brnConfigurarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfigurariEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfigurarPlatillos, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(JPanelinterfaz)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JPanelinterfaz, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreacionCyE, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(brnConfigurarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConfigurariEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConfigurarPlatillos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrarAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        this.setVisible(false);
        Registrar_Cliente RC = new Registrar_Cliente();
        RC.setVisible(true);
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadoActionPerformed
        this.setVisible(false);
        Registrar_Empleado RC = new Registrar_Empleado();
        RC.setVisible(true);
    }//GEN-LAST:event_btnEmpleadoActionPerformed

    private void btnCVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCVentaActionPerformed
        JPanelinterfaz.setSelectedIndex(0);
    }//GEN-LAST:event_btnCVentaActionPerformed

    private void btnCreacionCyEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreacionCyEActionPerformed
        JPanelinterfaz.setSelectedIndex(1);
    }//GEN-LAST:event_btnCreacionCyEActionPerformed

    private void brnConfigurarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnConfigurarClientesActionPerformed
        LimpiarTabla();
        ListarCliente();
        JPanelinterfaz.setSelectedIndex(2);
    }//GEN-LAST:event_brnConfigurarClientesActionPerformed

    private void btnConfigurariEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigurariEmpleadosActionPerformed
        LimpiarTabla();
        ListarEmpleado();
        JPanelinterfaz.setSelectedIndex(3);
    }//GEN-LAST:event_btnConfigurariEmpleadosActionPerformed

    private void btnConfigurarPlatillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigurarPlatillosActionPerformed
        LimpiarTabla();
        ListarPlatillos();
        JPanelinterfaz.setSelectedIndex(4);
    }//GEN-LAST:event_btnConfigurarPlatillosActionPerformed

    private void btnVerVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVentasActionPerformed
        LimpiarTabla();
        ListarFacturas();
        JPanelinterfaz.setSelectedIndex(5);
    }//GEN-LAST:event_btnVerVentasActionPerformed

    private void btnGuardarPlatilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPlatilloActionPerformed
        if(!"".equals(txtNombre_pla.getText()) && !"".equals(txtDescrip_Pla.getText()) && !"".equals(txtPrecio_Pla.getText()) && !"".equals(txtStock_Pla.getText()))
        {
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
                    Limpiar();
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
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(!"".equals(txtIDVentas.getText()))
            {
                String id = txtIDVentas.getText();
                Pla = PlaC.BuscarPlatillo(id);
                if(Pla.getNombre_Pla() != null)
                {
                    txtplatilloVentas.setText(""+Pla.getNombre_Pla());
                    txtDescripccionVenta.setText(""+Pla.getDescripcion_Pla());
                    txtPrecioVenta.setText(""+Pla.getPrecio_Pla());
                    txtStockVenta.setText(""+Pla.getStock_Pla());
                    txtCantidadVenta.requestFocus();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "El platillo inexiste");
                    txtplatilloVentas.setText("");
                    txtDescripccionVenta.setText("");
                    txtPrecioVenta.setText("");
                    txtStockVenta.setText("");
                    txtIDVentas.requestFocus();
                }
            }
            else
            {
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
                        if(TBVentas.getValueAt(i, 1).equals(txtplatilloVentas.getText()))
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
                    TotalPagar();
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
                    txtNombreVenta.setText(""+cli.getNombre_cli());
                    txtTelVenta.setText(""+cli.getTel_cli());
                    txtDirVenta.setText(""+cli.getDireccion_cli());
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
            modelo.removeRow(TBVentas.getSelectedRow());
            TotalPagar();
            txtIDVentas.requestFocus();
        
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

                            Limpiar();
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

    private void btnCrearPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPDFActionPerformed
        if(TBVentas.getRowCount() > 0)
        {
            if(!"".equals(txtNombreVenta.getText()))
            {
                JOptionPane.showMessageDialog(null, "Venta Hecha¡");
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
        this.setVisible(false);
        Registro_Administrador RA = new Registro_Administrador();
        RA.setVisible(true);
    }//GEN-LAST:event_btnRegistrarAdminActionPerformed

    void Limpiar()
    {
        txtNombre_pla.setText("");
        txtDescrip_Pla.setText("");
        txtPrecio_Pla.setText("");
        txtStock_Pla.setText("");
        CBEstado_Pla.setSelectedItem("Elija Estado del Platillo");
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
    private javax.swing.JComboBox<String> CBEstado_Pla;
    private javax.swing.JPanel JPANELinterfaz;
    private javax.swing.JTabbedPane JPanelinterfaz;
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
    private javax.swing.JButton btnActualizarPlatillo;
    private javax.swing.JButton btnBuscarPlatillo;
    private javax.swing.JButton btnCVenta;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnConfigurarPlatillos;
    private javax.swing.JButton btnConfigurariEmpleados;
    private javax.swing.JButton btnCreacionCyE;
    private javax.swing.JButton btnCrearPDF;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtDescrip_Pla;
    private javax.swing.JTextField txtDescripccionVenta;
    private javax.swing.JTextField txtDirVenta;
    private javax.swing.JTextField txtIDVentas;
    private javax.swing.JTextField txtID_pla;
    private javax.swing.JTextField txtIdClienteVenta;
    private javax.swing.JTextField txtNombreVenta;
    private javax.swing.JTextField txtNombre_pla;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtPrecio_Pla;
    private javax.swing.JTextField txtStockVenta;
    private javax.swing.JTextField txtStock_Pla;
    private javax.swing.JTextField txtTelVenta;
    private javax.swing.JTextField txtbuscaridplatillo;
    private javax.swing.JTextField txtplatilloVentas;
    // End of variables declaration//GEN-END:variables
    
    private void TotalPagar()
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
            fecha.add("Fecha: "+ new SimpleDateFormat("dd/MM/yyyy").format(date)+"\n\n");
            
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[] {20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            Encabezado.addCell(img);
            
            String nom = "Restaurante";
            String Tel = "55685063";
            String dir = "Rabinal";
            
            
            Encabezado.addCell("");
            Encabezado.addCell("Nombre:"+nom+"\nTelefono:"+Tel+"\nDireccion:"+dir);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);
            
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("Datos de los clientes"+"\n\n");
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
            firma.add("Cancelacion Firma\n\n");
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

