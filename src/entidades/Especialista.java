package entidades;
public class Especialista extends Participante {
    private String especialidad;
    private int penalizacion;
    public Especialista(String nombre, Equipo equipo, String especialidad, int penalizacion){
        super(nombre,equipo);
        this.especialidad = especialidad;
        this.penalizacion = penalizacion;
    }

    @Override
    public void competirCon(Participante p, int r) {
        p.retado(r);
    }

    @Override
    protected void retado(int idReto) {
        System.out.println(getNombre()+" ha sido retado a resolver el reto nº "+idReto);
    }

    public int getPenalizacion() {
        return penalizacion;
    }

    public void setPenalizacion(int penalizacion) {
        this.penalizacion = penalizacion;
    }
}
