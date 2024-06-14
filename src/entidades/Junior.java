/**
 * @author Eduardo Caro Lorente
 */
package entidades;

/**
 * Clase Junior que hereda de Participante
 */
public class Junior extends Participante {
    private int bonificacion;
    public Junior(String nombre, Equipo equipo, int bonificacion){
        super(nombre,equipo);
        this.bonificacion = bonificacion;
        super.setnIntentos(30);
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
        System.out.println(getNombre()+" ha sido retado a resolver el reto nº "+idReto);
    }

    public int getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(int bonificacion) {
        this.bonificacion = bonificacion;
    }
}
