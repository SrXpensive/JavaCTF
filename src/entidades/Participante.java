package entidades;

public abstract class Participante {
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
    public void equals(Participante p){
        if(this.nombre.equals(p.nombre)){
            System.out.println("Son el mismo participante");
        }
    }
    public abstract void competirCon(Participante p);
    protected abstract void retado(int idReto);
}
