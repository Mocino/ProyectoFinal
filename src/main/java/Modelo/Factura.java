package Modelo;

public class Factura {
    //Id_factura, Cliente, Total, Fecha_Factura
    private int Id_Fac;
    private String Cliente_Fac;
    private double Total_Fac;
    private String Fecha_Fac;
    
    public Factura(){}

    public Factura(int Id_Fac, String Cliente_Fac, double Total_Fac, String Fecha_Fac) {
        this.Id_Fac = Id_Fac;
        this.Cliente_Fac = Cliente_Fac;
        this.Total_Fac = Total_Fac;
        this.Fecha_Fac = Fecha_Fac;
    }

    public int getId_Fac() {
        return Id_Fac;
    }

    public void setId_Fac(int Id_Fac) {
        this.Id_Fac = Id_Fac;
    }

    public String getCliente_Fac() {
        return Cliente_Fac;
    }

    public void setCliente_Fac(String Cliente_Fac) {
        this.Cliente_Fac = Cliente_Fac;
    }

    public double getTotal_Fac() {
        return Total_Fac;
    }

    public void setTotal_Fac(double Total_Fac) {
        this.Total_Fac = Total_Fac;
    }

    public String getFecha_Fac() {
        return Fecha_Fac;
    }

    public void setFecha_Fac(String Fecha_Fac) {
        this.Fecha_Fac = Fecha_Fac;
    }
            
    
    
}
