package VO;

public class Cargo {

    private int id;
    private String nombre;
    private String descripcion;
    private int salario;
    private boolean estado;

    public Cargo() {
    }

    public Cargo(String nombre, String descripcion, int salario, boolean estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.salario = salario;
        this.estado = estado;
    }

    public Cargo(int id, String nombre, String descripcion, int salario, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.salario = salario;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
