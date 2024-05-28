package entidades;

import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private int puntos;
    private ArrayList<Participante> participantes= new ArrayList<>();
    public Equipo (String nombre){
        this.nombre = nombre;
        puntos = 0;
    }
}
