package cl.mess.perros.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Perro implements Serializable {
    private String raza, subraza;
    private ArrayList<String> imagenes;

    public Perro(){}

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSubraza() {
        return subraza;
    }

    public void setSubraza(String subraza) {
        this.subraza = subraza;
    }

    public ArrayList<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<String> imagenes) {
        this.imagenes = imagenes;
    }
}
