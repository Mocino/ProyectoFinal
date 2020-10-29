package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatillosC {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistroPlatillos(Platillos pla)
    {
        String sql = "INSERT INTO platillo (Nombre_Platillo, Descripcion_Platillo, Precio_Platillo, Stock_Platillo, Estado_Platillo) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            
            
            ps.setString(1, pla.getNombre_Pla());
            ps.setString(2, pla.getDescripcion_Pla());
            ps.setDouble(3, pla.getPrecio_Pla());
            ps.setInt(4, pla.getStock_Pla());
            ps.setString(5, pla.getEstado_Pla());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en RegistroPlatillos:"+e);
            return false;
        }
        finally
        {
            try
            {
                con.close();
            }
            catch(SQLException e){
                System.out.println("Error en PC"+e.toString());
            }
        }
    }
    
    public Platillos BuscarPlatillo(String IDP)
    {
        Platillos plat = new Platillos();
        String sql = "SELECT * FROM platillo WHERE Id_Platillo = ?";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, IDP);
            rs = ps.executeQuery();
            if (rs.next())
            {
                //Traera la informacion
                plat.setNombre_Pla(rs.getString("Nombre_Platillo"));
                plat.setDescripcion_Pla(rs.getString("Descripcion_Platillo"));
                plat.setPrecio_Pla(rs.getDouble("Precio_Platillo"));
                plat.setStock_Pla(rs.getInt("Stock_Platillo"));
                plat.setEstado_Pla(rs.getString("Estado_Platillo"));
                
            }
        } catch (Exception e) {
            System.out.println("Error en BuscarPlatillo: "+ e);
        }
        return plat;
    }
    
    public boolean ActualizarStock(int cant, String cod){
        String sql = "UPDATE platillo SET Stock_Platillo = ? WHERE Id_Platillo = ?";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setString(2, cod);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public List ListarPlatillo(){
        List<Platillos> ListPla = new ArrayList();
        String sql = "SELECT * FROM platillo";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Platillos pla = new Platillos();
                pla.setId_Pla(rs.getInt("Id_Platillo"));
                pla.setNombre_Pla(rs.getString("Nombre_Platillo"));
                pla.setDescripcion_Pla(rs.getString("Descripcion_Platillo"));
                pla.setPrecio_Pla(rs.getDouble("Precio_Platillo"));
                pla.setStock_Pla(rs.getInt("Stock_Platillo"));
                pla.setEstado_Pla(rs.getString("Estado_Platillo"));
                ListPla.add(pla);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error en ListarPlatillo: "+e.toString());
        }
        return ListPla;
    }
}
