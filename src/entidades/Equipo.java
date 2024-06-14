/**
 * @author Eduardo Caro Lorente
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase Equipo
 */
public class Equipo implements Comparable<Equipo>, Serializable {
    private String nombre;
    private int puntos;
    private ArrayList<Participante> participantes= new ArrayList<>();
    public Equipo (String nombre){
        this.nombre = nombre;
        puntos = 0;
    }

    /**
     * Método para asignar miembros a un equipo
     * @param p Participante que se añadirá a un equipo
     */
    public void asignarMiembro(Participante p){
        participantes.add(p);
        p.setEquipo(this);
    }

    /**
     * Método para desasignar un miembro de un equipo
     * @param p Participante que se eliminará del equipo
     */
    public void desasignarMiembro(Participante p){
        participantes.remove(p);
    }

    public String toString(){
        String cadena= "";
        for(Participante p: participantes){
            cadena += "Nombre:"+p.toString()+" Equipo:"+this.getNombre()+" Puntos:"+getPuntos()+"\n";
        }
        return cadena;
    }

    /**
     * Método para comparar si un equipo ya existe
     * @param e Equipo con el que se compara
     * @return
     */
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

    /**
     * Método al que se accede al hacer una llamada al método sort (para ordenar arraylist de equipos). Se ordenará por puntos
     * @param e Equipo que será comparado
     * @return un entero al realizar la comparación de puntos
     */
    @Override
    public int compareTo(Equipo e) {
        return e.getPuntos() - getPuntos();
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
