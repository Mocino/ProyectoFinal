package Modelo;

public class Cliente {

    int id_cli;
    String nombre_cli;
    String apellido_cli;
    int nit_cli;
    String correo_cli;
    String direccion_cli;
    int tel_cli;
    int IdTipo_cli;
    String Estado_cliente;
    
    public Cliente(){}

    public Cliente(int id_cli, String nombre_cli, String apellido_cli, int nit_cli, String correo_cli, String direccion_cli, int tel_cli, int IdTipo_cli, String Estado_cliente) {
        this.id_cli = id_cli;
        this.nombre_cli = nombre_cli;
        this.apellido_cli = apellido_cli;
        this.nit_cli = nit_cli;
        this.correo_cli = correo_cli;
        this.direccion_cli = direccion_cli;
        this.tel_cli = tel_cli;
        this.IdTipo_cli = IdTipo_cli;
        this.Estado_cliente = Estado_cliente;
    }

    

    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }

    public String getNombre_cli() {
        return nombre_cli;
    }

    public void setNombre_cli(String nombre_cli) {
        this.nombre_cli = nombre_cli;
    }

    public String getApellido_cli() {
        return apellido_cli;
    }

    public void setApellido_cli(String apellido_cli) {
        this.apellido_cli = apellido_cli;
    }

    public int getNit_cli() {
        return nit_cli;
    }

    public void setNit_cli(int nit_cli) {
        this.nit_cli = nit_cli;
    }

    public String getCorreo_cli() {
        return correo_cli;
    }

    public void setCorreo_cli(String correo_cli) {
        this.correo_cli = correo_cli;
    }

    public String getDireccion_cli() {
        return direccion_cli;
    }

    public void setDireccion_cli(String direccion_cli) {
        this.direccion_cli = direccion_cli;
    }

    public int getTel_cli() {
        return tel_cli;
    }

    public void setTel_cli(int tel_cli) {
        this.tel_cli = tel_cli;
    }

    public int getIdTipo_cli() {
        return IdTipo_cli;
    }

    public void setIdTipo_cli(int IdTipo_cli) {
        this.IdTipo_cli = IdTipo_cli;
    }

    public String getEstado_cliente() {
        return Estado_cliente;
    }

    public void setEstado_cliente(String Estado_cliente) {
        this.Estado_cliente = Estado_cliente;
    }
    
    
    
}
