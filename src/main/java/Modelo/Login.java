package Modelo;

public class Login {
    
    int id_u;
    String correo_U;
    String pass_U;
    int Id_Tipo_EU;
    int Estado_EU;
    
    public Login(){}

    public Login(int id_u, String correo_U, String pass_U, int Id_Tipo_EU, int Estado_EU) {
        this.id_u = id_u;
        this.correo_U = correo_U;
        this.pass_U = pass_U;
        this.Id_Tipo_EU = Id_Tipo_EU;
        this.Estado_EU = Estado_EU;
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

    public int getId_Tipo_EU() {
        return Id_Tipo_EU;
    }

    public void setId_Tipo_EU(int Id_Tipo_EU) {
        this.Id_Tipo_EU = Id_Tipo_EU;
    }

    public int getEstado_EU() {
        return Estado_EU;
    }

    public void setEstado_EU(int Estado_EU) {
        this.Estado_EU = Estado_EU;
    }
}
