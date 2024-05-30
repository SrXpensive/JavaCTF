package entidades;
public class Junior extends Participante {
    private int bonificacion;
    public Junior(String nombre, Equipo equipo, int bonificacion){
        super(nombre,equipo,30);
        this.bonificacion = bonificacion;
    }

    @Override
    public void competirCon(Participante p, int r) {
        p.retado(r);
    }

    @Override
    protected void retado(int idReto) {

    }
}
