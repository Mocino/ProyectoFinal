package Modelo;

public class Login {
    
    int id_u;
    String correo_U;
    String pass_U;
    int Id_Empleado;
    String Tipo_Emp_U;
    String Nombre_U;
    
    public Login(){}

    public Login(int id_u, String correo_U, String pass_U, int Id_Empleado, String Tipo_Emp_U, String Nombre_U) {
        this.id_u = id_u;
        this.correo_U = correo_U;
        this.pass_U = pass_U;
        this.Id_Empleado = Id_Empleado;
        this.Tipo_Emp_U = Tipo_Emp_U;
        this.Nombre_U = Nombre_U;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public String getCorreo_U() {
        return correo_U;
    }

    public void setCorreo_U(String correo_U) {
        this.correo_U = correo_U;
    }

    public String getPass_U() {
        return pass_U;
    }

    public void setPass_U(String pass_U) {
        this.pass_U = pass_U;
    }

    public int getId_Empleado() {
        return Id_Empleado;
    }

    public void setId_Empleado(int Id_Empleado) {
        this.Id_Empleado = Id_Empleado;
    }

    public String getTipo_Emp_U() {
        return Tipo_Emp_U;
    }

    public void setTipo_Emp_U(String Tipo_Emp_U) {
        this.Tipo_Emp_U = Tipo_Emp_U;
    }

    public String getNombre_U() {
        return Nombre_U;
    }

    public void setNombre_U(String Nombre_U) {
        this.Nombre_U = Nombre_U;
    }

   
    
    
}
