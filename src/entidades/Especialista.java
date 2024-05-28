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
    public void competirCon(Participante p) {

    }

    @Override
    protected void retado(int idReto) {

    }
}
