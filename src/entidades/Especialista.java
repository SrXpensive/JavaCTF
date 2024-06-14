/**
 * @author Eduardo Caro Lorente
 */
package entidades;

/**
 * Clase especialista que hereda de participante
 */
public class Especialista extends Participante {
    private String especialidad;
    private int penalizacion;
    public Especialista(String nombre, Equipo equipo, String especialidad, int penalizacion){
        super(nombre,equipo);
        this.especialidad = especialidad;
        this.penalizacion = penalizacion;
    }

    /**
     * Método que indica el participante al que se reta y el reto al que se enfrenta
     * @param p Participante retado
     * @param r id del reto
     */
    @Override
    public void competirCon(Participante p, int r) {
        p.retado(r);
    }

    /**
     * Metodo que indica con texto a quién se reta y con qué reto
     * @param idReto id del reto seleccionado
     */
    @Override
    protected void retado(int idReto) {
        System.out.println(this.getNombre()+" ha sido retado a resolver el reto nº "+idReto);
    }

    public int getPenalizacion() {
        return penalizacion;
    }

    public void setPenalizacion(int penalizacion) {
        this.penalizacion = penalizacion;
    }
}
