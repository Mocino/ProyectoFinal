package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

    
public class LoginB {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public Login log (String correo, String pass){
        Login l = new Login();
        String sql = "SELECT * FROM usuario WHERE Correo_Usuario=? AND Pass_Usuario=?";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            //Id_Usuario, Correo_Usuario, Pass_Usuario, Id_Empleado, Tipo_Empleado, Nombre
            if(rs.next())
            {
                l.setId_u(rs.getInt("Id_Usuario"));
                l.setCorreo_U(rs.getString("Correo_Usuario"));
                l.setPass_U(rs.getString("Pass_Usuario"));
                l.setId_Empleado(rs.getInt("Id_Empleado_Usuario"));
                l.setTipo_Emp_U(rs.getString("Tipo_Empleado_Usuario"));
                l.setNombre_U(rs.getString("Nombre_Usuario"));
            }
        } catch (SQLException e) {
            System.out.println("Error en LogB/Log log: "+e);
        }
        return l;
    }
    
    public boolean RegistrarUsuario(Login log)
    {
        String sql = "INSERT INTO usuario (Correo_Usuario, Pass_Usuario, Id_Empleado_Usuario, Tipo_Empleado_Usuario, Nombre_Usuario) VALUES (?,?,?,?,?)";
        try
        {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, log.getCorreo_U());
            ps.setString(2, log.getPass_U());
            ps.setInt(3, log.getId_Empleado());
            ps.setString(4, log.getTipo_Emp_U());
            ps.setString(5, log.getNombre_U());
            ps.execute();
            return true;
            
        }
        catch(Exception e)
        {
            System.out.println("Error en Agregar Usuario"+e);
            return false;
        }
        finally
        {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error en RU: "+e);
            }
        }    
    }
}
