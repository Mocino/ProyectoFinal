/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Usuario
 */
public class Empleado {
    int id_E;
    String nombre_E;
    String apellido_E;
    String Correo_E;
    int edad_E;
    String genero_E;
    String estado_E;
        
    public Empleado(){}

    public Empleado(int id_E, String nombre_E, String apellido_E, String Correo_E, int edad_E, String genero_E, String estado_E) {
        this.id_E = id_E;
        this.nombre_E = nombre_E;
        this.apellido_E = apellido_E;
        this.Correo_E = Correo_E;
        this.edad_E = edad_E;
        this.genero_E = genero_E;
        this.estado_E = estado_E;
    }


    public int getId_E() {
        return id_E;
    }

    public void setId_E(int id_E) {
        this.id_E = id_E;
    }

    public String getNombre_E() {
        return nombre_E;
    }

    public void setNombre_E(String nombre_E) {
        this.nombre_E = nombre_E;
    }

    public String getApellido_E() {
        return apellido_E;
    }

    public void setApellido_E(String apellido_E) {
        this.apellido_E = apellido_E;
    }

    public String getCorreo_E() {
        return Correo_E;
    }

    public void setCorreo_E(String Correo_E) {
        this.Correo_E = Correo_E;
    }

    public int getEdad_E() {
        return edad_E;
    }

    public void setEdad_E(int edad_E) {
        this.edad_E = edad_E;
    }

    public String getGenero_E() {
        return genero_E;
    }

    public void setGenero_E(String genero_E) {
        this.genero_E = genero_E;
    }
    
    public String getEstado_E() {
        return estado_E;
    }

    public void setEstado_E(String estado_E) {
        this.estado_E = estado_E;
    }

}
