package entidades;

import java.util.ArrayList;

public class Equipo implements Comparable<Equipo>{
    private String nombre;
    private int puntos;
    private ArrayList<Participante> participantes= new ArrayList<>();
    public Equipo (String nombre){
        this.nombre = nombre;
        puntos = 0;
    }
    public void asignarMiembro(Participante p){
        participantes.add(p);
        p.setEquipo(this);
    }
    public void desasignarMiembro(Participante p){
        participantes.remove(p);
    }
    public String toString(){
        String cadena= "";
        for(Participante p: participantes){
            cadena += p.toString()+"\n";
        }
        return cadena;
    }
    public boolean equals(Equipo e){
        return getNombre().equals(e.getNombre());
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    @Override
    public int compareTo(Equipo e) {
        return getPuntos() - e.getPuntos();
    }
}
