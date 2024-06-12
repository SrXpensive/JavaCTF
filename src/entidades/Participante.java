package entidades;

import java.io.Serializable;

public abstract class Participante implements Comparable<Participante>, Serializable {
    private String nombre;
    private int puntosGanados;
    private int[] flags = new int [3];
    private int nIntentos;
    private Equipo equipo;

    public Participante(String nombre, Equipo equipo) {
        this.nombre = nombre;
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosGanados() {
        return puntosGanados;
    }

    public int[] getFlags() {
        return flags;
    }

    public int getnIntentos() {
        return nIntentos;
    }

    public Equipo getEquipo() {
        return equipo;
    }
    public int totalFlags(){
        int a = 0;
        for(int d:flags){
            a += d;
        }
        return a;
    }
    public String toString(){
        return getNombre()+" PG:"+getPuntosGanados()+"/ NF:"+totalFlags()+" / NI:"+getnIntentos();
    }
    public boolean equals(Participante p){
        return getNombre().equals(p.getNombre());
    }
    public int compareTo(Participante p){
        if(getNombre().equals(p.getNombre())){
            return getEquipo().getNombre().compareTo(p.getEquipo().getNombre());
        }
        return getNombre().compareTo(p.getNombre());
    }

    public abstract void competirCon(Participante p, int r);
    protected abstract void retado(int idReto);

    public void setnIntentos(int nIntentos) {
        this.nIntentos = nIntentos;
    }
    public void asignarEquipo(Equipo e){
        if(!e.getParticipantes().contains(this)){
            e.asignarMiembro(this);
        }else{
            setEquipo(e);
        }
    }
    public void desasignarEquipo(Equipo a){
        setEquipo(null);
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void setPuntosGanados(int puntosGanados) {
        this.puntosGanados = puntosGanados;
    }
}
