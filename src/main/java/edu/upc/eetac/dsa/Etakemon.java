package edu.upc.eetac.dsa;

/**
 * Created by OriolGresa on 18/11/16.
 */
public class Etakemon {
    int id;
    String nombre, descripcion;

    public Etakemon(int id, String name, String description) {
        this.id = id;
        this.nombre = name;
        this.descripcion = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public String getDescription() {
        return descripcion;
    }

    public void setDescription(String description) {
        this.descripcion = description;
    }
}
