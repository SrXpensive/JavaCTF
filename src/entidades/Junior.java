package entidades;
public class Junior extends Participante {
    private int bonificacion;
    public Junior(String nombre, Equipo equipo, int bonificacion){
        super(nombre,equipo);
        this.bonificacion = bonificacion;
    }

    @Override
    public void competirCon(Participante p) {

    }

    @Override
    protected void retado(int idReto) {

    }
}
