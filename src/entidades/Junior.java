package entidades;
public class Junior extends Participante {
    private int bonificacion;
    public Junior(String nombre, Equipo equipo, int bonificacion){
        super(nombre,equipo);
        this.bonificacion = bonificacion;
        super.setnIntentos(30);
    }

    @Override
    public void competirCon(Participante p, int r) {
        p.retado(r);
    }

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
