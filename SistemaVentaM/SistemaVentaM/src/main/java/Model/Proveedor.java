/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author A315-58
 */
public class Proveedor extends Generic {
    private String correo;

    public Proveedor(int id, String nombre, String ciudad, String telefono, String correo) {
        super(id, nombre, telefono, ciudad);
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
