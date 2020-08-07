package VO;

public class Cliente extends Persona {

    private int id;

    public Cliente() {
        super();
    }

    public Cliente(Persona persona) {
        super(persona.getNombre(), persona.getApellido(), persona.getDocumento(), persona.getTelefono(), persona.getEmail(), persona.getDireccion());
    }
    
    public Cliente(int id, Persona persona) {
        super(persona.getNombre(), persona.getApellido(), persona.getDocumento(), persona.getTelefono(), persona.getEmail(), persona.getDireccion());
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString() + " Cliente{" + "id=" + id + ", credito=" + '}';
    }
}
