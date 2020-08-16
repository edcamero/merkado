package VO;

import java.util.Date;

public class Empleado extends Persona {

    private int id;
    private Date fechaContratacion;
    private boolean estado;
    private Cargo cargo;

    

    public Empleado(Date fechaContratacion, boolean estado, Cargo cargo, String nombre, String apellido, String documento, String telefono, String direccion) {
        super(nombre, apellido, documento, telefono, direccion);
        this.fechaContratacion = fechaContratacion;
        this.estado = estado;
        this.cargo = cargo;
    }

    public Empleado(int id, Date fechaContratacion, boolean estado, Cargo cargo, int pers_id, String nombre, String apellido, String documento, String telefono, String direccion) {
        super(pers_id, nombre, apellido, documento, telefono, direccion);
        this.id = id;
        this.fechaContratacion = fechaContratacion;
        this.estado = estado;
        this.cargo = cargo;
    }

//    public Empleado(int aInt, java.sql.Date date, boolean aBoolean, int aInt0, String nombre, String nombre0, String nombre1, String nombre2, String nombre3) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

}
