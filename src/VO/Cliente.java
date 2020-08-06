
package VO;

public class Cliente extends Persona{
   private int id;
   private int credito;

    public Cliente() {
        super();
    }

    public Cliente(int credito, String nombre, String apellido, String documento, String telefono, String email) {
        super(nombre, apellido, documento, telefono, email);
        this.credito = credito;
    }

    public Cliente(int id, int credito, String nombre, String apellido, String documento, String telefono, String email) {
        super(nombre, apellido, documento, telefono, email);
        this.id = id;
        this.credito = credito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    @Override
    public String toString() {
        return super.toString() + "Cliente{" + "id=" + id + ", credito=" + credito + '}';
    }
    
    
}
