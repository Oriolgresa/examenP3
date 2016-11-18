package edu.upc.eetac.dsa;

/**
 * Created by OriolGresa on 18/11/16.
 */
public class User extends UserDAO {
    public int dni;
    public String nombre, direccion;


    public User(int dni, String nombre, String direccion) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("dni: ").append(this.getDni());
        sb.append("\nnombre: ").append(this.getNombre());
        sb.append("\ndireccion: ").append(this.getDireccion());
        return sb.toString();
    }
}
