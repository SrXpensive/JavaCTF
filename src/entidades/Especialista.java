package entidades;
public class Especialista extends Participante {
    private String especialidad;
    private int penalizacion;
    public Especialista(String nombre, Equipo equipo, String especialidad, int penalizacion, int nIntentos){
        super(nombre,equipo,nIntentos);
        super.setnIntentos(20);
        this.especialidad = especialidad;
        this.penalizacion = penalizacion;
    }

    @Override
    public void competirCon(Participante p, int r) {

    }

    @Override
    protected void retado(int idReto) {

    }
}
