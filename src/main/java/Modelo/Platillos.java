
package Modelo;

public class Platillos {
//Id_Platillo, Nombre_Platillo, Descripcion_Platillo, Precio_Platillo, Stock_Platillo, Estado_Platillo
    int Id_Pla;
    String Nombre_Pla;
    String Descripcion_Pla;
    double Precio_Pla;
    int Stock_Pla;
    String Estado_Pla;
    
    public Platillos(){}

    public Platillos(int Id_Pla, String Nombre_Pla, String Descripcion_Pla, double Precio_Pla, int Stock_Pla, String Estado_Pla) {
        this.Id_Pla = Id_Pla;
        this.Nombre_Pla = Nombre_Pla;
        this.Descripcion_Pla = Descripcion_Pla;
        this.Precio_Pla = Precio_Pla;
        this.Stock_Pla = Stock_Pla;
        this.Estado_Pla = Estado_Pla;
    }

    public int getId_Pla() {
        return Id_Pla;
    }

    public void setId_Pla(int Id_Pla) {
        this.Id_Pla = Id_Pla;
    }

    public String getNombre_Pla() {
        return Nombre_Pla;
    }

    public void setNombre_Pla(String Nombre_Pla) {
        this.Nombre_Pla = Nombre_Pla;
    }

    public String getDescripcion_Pla() {
        return Descripcion_Pla;
    }

    public void setDescripcion_Pla(String Descripcion_Pla) {
        this.Descripcion_Pla = Descripcion_Pla;
    }

    public double getPrecio_Pla() {
        return Precio_Pla;
    }

    public void setPrecio_Pla(double Precio_Pla) {
        this.Precio_Pla = Precio_Pla;
    }

    public int getStock_Pla() {
        return Stock_Pla;
    }

    public void setStock_Pla(int Stock_Pla) {
        this.Stock_Pla = Stock_Pla;
    }

    public String getEstado_Pla() {
        return Estado_Pla;
    }

    public void setEstado_Pla(String Estado_Pla) {
        this.Estado_Pla = Estado_Pla;
    }
}
