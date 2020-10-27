package Modelo;

import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    
    Connection conn= null;
    String hostname=null;
    String port =null;
    String database =null;
    String username = null;
    String password = null;
    String jndi=null;
    String driver= null;
    static Properties props = new Properties();
    
    public Conexion()
    {
        InputStream in = null;
        try{
        in = Files.newInputStream(FileSystems.getDefault().getPath("C:\\Conexion\\Restaurante\\db_props.properties"));
        props.load(in);
        in.close();
    }
    catch (IOException ex)
    {
        ex.printStackTrace();
    }
    
    
    cargarPropiedades();
    }
    
    public void cargarPropiedades(){
        this.hostname= props.getProperty("hostname");
        this.port= props.getProperty("port");
        this.database=props.getProperty("database");
        this.username=props.getProperty("username");
        this.password=props.getProperty("password");
        this.driver=props.getProperty("driver");
        jndi= props.getProperty("jndi");
    }
    
    public Connection getConection() throws SQLException{
                try{
                    Class.forName(driver);  
                    String jdbcUrl = "jdbc:mysql://"+hostname+":"+port+"/"+database+"?useTimezone=true&serverTimezone=UTC";
                    conn = DriverManager.getConnection(jdbcUrl,username,password);
                    
                    System.out.println("Conexion Establecida");
                }catch(ClassNotFoundException | SQLException e){
                   System.out.println("Error en conexion a BD"+e);
                }
        return conn;
        
    }
   
     
    public void CerrarConection(){
    try
        {
            conn.close();
            System.out.println("La sesion se cerro exitosamente");
        }
    catch(SQLException e)
        {
            System.out.println("Error al cerrar la conexion");
        }
    }
}