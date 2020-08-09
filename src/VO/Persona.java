package VO;

public class Persona {

    private int pers_id;
    private String nombre;
    private String apellido;
    private String documento;
    private String telefono;
    private String direccion;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String documento, String telefono, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Persona(int pers_id, String nombre, String apellido, String documento, String telefono, String direccion) {
        this.pers_id = pers_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }

    public int getPers_Id() {
        return pers_id;
    }

    public void setPers_Id(int pers_id) {
        this.pers_id = pers_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
