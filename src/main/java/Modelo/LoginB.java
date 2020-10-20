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
            //Id_Usuario, Correo_Usuario, Pass_Usuario, Id_TipoEmpleado, Estado_Empleado
            if(rs.next())
            {
                l.setId_u(rs.getInt("Id_Usuario"));
                l.setCorreo_U(rs.getString("Correo_Usuario"));
                l.setPass_U(rs.getString("Pass_Usuario"));
                l.setId_Tipo_EU(rs.getInt("Id_TipoEmpleado"));
                l.setEstado_EU(rs.getInt("Estado_Empleado"));
            }
        } catch (SQLException e) {
            System.out.println("Error en LogB/Log log: "+e);
        }
        return l;
    }
}
