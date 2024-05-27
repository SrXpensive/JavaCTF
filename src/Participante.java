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
}
