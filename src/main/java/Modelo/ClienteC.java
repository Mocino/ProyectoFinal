package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteC {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarClientes(Cliente cl){
        String sql = "INSERT INTO cliente (Nombre_Cliente, Apellido_Cliente, Nit_Cliente, Correo_Cliente, Direccion_Cliente, Telefono_Cliente, Id_TipoCliente, Estado_Cliente) VALUES (?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getNombre_cli());
            ps.setString(2, cl.getApellido_cli());
            ps.setInt(3, cl.getNit_cli());
            ps.setString(4, cl.getCorreo_cli());
            ps.setString(5, cl.getDireccion_cli());
            ps.setInt(6, cl.getTel_cli());
            ps.setInt(7, cl.getIdTipo_cli());
            ps.setString(8, cl.getEstado_cliente());
            ps.execute();
            
            return true;
        } 
        catch (Exception e) 
        {
            System.out.println("Error en Agregar Cliente"+e);
            return false;
        }
        finally
        {
            try 
            {
                con.close();
            } 
            catch (Exception e) 
            {
                System.out.println("Error CC"+e.toString());
            }
        }
    }
    
    public Cliente BuscarCliente(int idCli)
    {
        Cliente cli = new Cliente();
        String sql = "SELECT * FROM cliente WHERE Id_Cliente=?";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCli);
            rs = ps.executeQuery();
            if(rs.next())
            {
                cli.setNombre_cli(rs.getString("Nombre_Cliente"));
                cli.setTel_cli(rs.getInt("Telefono_Cliente"));
                cli.setDireccion_cli(rs.getString("Direccion_Cliente"));
            }
        } catch (Exception e) 
        {
            System.out.println("Error en BuscarCliente"+e);
        }
        return cli;
    }
    
    public List ListarCliente(){
        List<Cliente> Listacl = new ArrayList();
        String sql = "SELECT * FROM  cliente";
        try 
        {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
            Cliente c = new Cliente();            
            c.setId_cli(rs.getInt("Id_Cliente"));
            c.setNombre_cli(rs.getString("Nombre_Cliente"));
            c.setApellido_cli(rs.getString("Apellido_Cliente"));
            c.setNit_cli(rs.getInt("Nit_Cliente"));
            c.setCorreo_cli(rs.getString("Correo_Cliente"));
            c.setDireccion_cli(rs.getString("Direccion_Cliente"));
            c.setTel_cli(rs.getInt("Telefono_Cliente"));
            c.setIdTipo_cli(rs.getInt("Id_TipoCliente"));
            c.setEstado_cliente(rs.getString("Estado_Cliente"));
            Listacl.add(c);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error en Listar Cliente"+e.toString());
        }
        return Listacl;
    }
    
}
