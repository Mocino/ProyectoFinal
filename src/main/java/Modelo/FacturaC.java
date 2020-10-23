package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaC {
    //Id_factura, Cliente, Total, Fecha_Factura
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public int RegistrarFactura(Factura F)
    {
        
        String sql = "INSERT INTO factura (Cliente_Factura, Total_Factura, Fecha_Factura) VALUES (?,?,?)";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, F.getCliente_Fac());
            ps.setDouble(2, F.getTotal_Fac());
            ps.setString(3, F.getFecha_Fac());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error en Registar Factura: "+e.toString());
            
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error VDAO:"+e.toString());
            }
        }
        return r;
    }
    
    public List ListarFacturas()
    {
        List<Factura> ListFac = new ArrayList();
        String sql = "SELECT * FROM factura";
        try
        {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Factura Fac = new Factura();
                Fac.setId_Fac(rs.getInt("Id_Factura"));
                Fac.setCliente_Fac(rs.getString("Cliente_Factura"));
                Fac.setTotal_Fac(rs.getInt("Total_Factura"));
                Fac.setFecha_Fac(rs.getString("Fecha_Factura"));
                ListFac.add(Fac);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error en Listar Factura");
        }        
        return ListFac;
    }
}
