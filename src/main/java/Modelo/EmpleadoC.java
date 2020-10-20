
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EmpleadoC {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarEmpleado(Empleado emp){
        String sql = "INSERT INTO empleado (Nombre_Empleado, Apellido_Empleado, Correo_Empleado, Edad_Empleado, Genero_Empleado, Estado_Empleado) VALUES (?,?,?,?,?,?)";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, emp.getNombre_E());
            ps.setString(2, emp.getApellido_E());
            ps.setString(3, emp.getCorreo_E());
            ps.setInt(4, emp.getEdad_E());
            ps.setString(5, emp.getGenero_E());
            ps.setString(6, emp.getEstado_E());           
            ps.executeQuery();
            
            return true;
        } catch (SQLException e) {
            System.out.println("Error en RegistroEmpleado:"+e);
            return false;
        }
        finally{
            try 
            {
                con.close();
            } 
            catch (SQLException e) {
            System.out.println("Error CB"+e.toString());
            }
        }
    }
    
   public List ListarEmpleado(){
       List<Empleado> ListaEMP = new ArrayList();
       String sql = "SELECT * FROM empleado";
       
       try {
           con = cn.getConection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Empleado emp = new Empleado();
               emp.setId_E(rs.getInt("Id_Empleado"));
               emp.setNombre_E(rs.getString("Nombre_Empleado"));
               emp.setApellido_E(rs.getString("Apellido_Empleado"));
               emp.setCorreo_E(rs.getString("Correo_Empleado"));
               emp.setEdad_E(rs.getInt("Edad_Empleado"));
               emp.setGenero_E(rs.getString("Genero_Empleado"));
               emp.setEstado_E(rs.getString("Estado_Empleado"));
               
               ListaEMP.add(emp);
           }
       } catch (SQLException e) {
           System.out.println("Error listar emp: "+e.toString());
       }
       return ListaEMP;
   }
    
}