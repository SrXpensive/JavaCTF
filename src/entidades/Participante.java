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
        this.nIntentos = 20;
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
    public boolean equals(Participante p1,Participante p2){
        return p1 == p2;
    }
    public abstract void competirCon(Participante p, int r);
    protected abstract void retado(int idReto);

    public void setnIntentos(int nIntentos) {
        this.nIntentos = nIntentos;
    }
    public void asignarEquipo(Equipo e){
        setEquipo(e);
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
