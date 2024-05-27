public abstract class Participante {
    private String nombre;
    private int puntosGanados;
    private int[] flags = new int [3];
    private int nIntentos;
    private Equipo equipo;

    public Participante(String nombre, int puntosGanados, int[] flags, int nIntentos, Equipo equipo) {
        this.nombre = nombre;
        this.puntosGanados = puntosGanados;
        this.flags = flags;
        this.nIntentos = nIntentos;
        this.equipo = equipo;
    }

    ;
}
