package VO;

public class Cliente extends Persona {

    private int id;
    private boolean estado;

    public Cliente() {
        super();
    }

    public Cliente(boolean estado, Persona persona) {
        super(persona.getPers_Id(), persona.getNombre(), persona.getApellido(), persona.getDocumento(), persona.getTelefono(), persona.getDireccion());
        this.estado = estado;
    }

    public Cliente(int id, boolean estado, Persona persona) {
        super(persona.getPers_Id(), persona.getNombre(), persona.getApellido(), persona.getDocumento(), persona.getTelefono(), persona.getDireccion());
        this.id = id;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return super.toString() + " Cliente{" + "id=" + id + ", estado=" + estado + '}';
    }
}
