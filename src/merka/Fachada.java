package merka;

import VO.*;
import java.util.ArrayList;

public class Fachada {

    static private Fachada fachada;
    private Mediador mediador;

    private Fachada() {
        mediador = new Mediador();
    }

    static public Fachada getInstancia() {
        if (fachada == null) {
            fachada = new Fachada();
        }
        return fachada;
    }

    //usuario
    public boolean login(String username, String pass) {
        return mediador.login(username, pass);
    }

    //*******************************producto***************
    public boolean registrarProducto(Producto producto) {
        return mediador.registrarProducto(producto);
    }

    public ArrayList<Producto> obtenerProductos() {
        return mediador.obtenerProductos();
    }

    public boolean actualizarProducto(Producto producto) {
        return mediador.actualizarProducto(producto);
    }

    public boolean eliminarProducto(int prod_id) {
        return mediador.eliminarProducto(prod_id);
    }

    //*******************************TIPO PRODUCTO*****************
    public boolean registrarTipoProducto(TipoProducto tipoProducto) {
        return mediador.registrarTipoProducto(tipoProducto);
    }

    public ArrayList<TipoProducto> obtenerTipoProductos() {
        return mediador.obtenerTipoProductos();
    }

    public TipoProducto obtenerTipoProducto(String tipr_nombre) {
        return mediador.obtenerTipoProducto(tipr_nombre);
    }

    public boolean actualizarTipoProducto(TipoProducto tipoProducto) {
        return mediador.actualizarTipoProducto(tipoProducto);
    }

    public boolean eliminarTipoProducto(int tipr_id) {
        return mediador.eliminarTipoProducto(tipr_id);
    }

    //******************************TIPO USUARIO
    public ArrayList<TipoUsuario> obtenerTipoUsuario() {
        return mediador.obtenerTipoUsuarios();
    }

    //***********************USUARIO
    public boolean registrarUsuario(Usuario usuario) {
        return mediador.registrarUsuario(usuario);
    }

    public ArrayList<Usuario> obtenerUsuarios() {
        return mediador.obtenerUsuarios();
    }

    public boolean actualizarTipoUsuario(Usuario usuario) {
        return mediador.actualizarTipoUsuario(usuario);
    }

    public boolean actualizarPassword(Usuario usuario) {
        return mediador.actualizarPassword(usuario);
    }

    public boolean eliminarUsuario(int id) {
        return mediador.eliminarUsuario(id);
    }

    //*********************PERSONA*******************************************************
    public int registrarPersona(Persona persona) {
        return mediador.registrarPersona(persona);
    }

    public ArrayList<Persona> obtenerPersona() {
        return mediador.obtenerPersonas();

    }

    public boolean actualizarPersona(Persona persona) {
        return mediador.actualizarPersona(persona);
    }

    public boolean eliminarPersona(int pers_id) {
        return mediador.eliminarPersona(pers_id);
    }

    //*******************EMPLEADO**********************
    public boolean registrarEmpleado(Empleado empleado) {
        return mediador.registrarEmpleado(empleado);
    }

    public ArrayList<Empleado> obtenerEmpleados() {
        return mediador.obtenerEmpleados();
    }

    //********************CLIENTE********************************
    public boolean registrarCliente(Cliente cliente) {
        return mediador.registrarCliente(cliente);
    }

    public ArrayList<Cliente> obtenerClientes(ArrayList<Persona> personas) {
        return mediador.obtenerClientes(personas);
    }

    public int personaId(int clie_id) {
        return mediador.personaId(clie_id);
    }

    public boolean eliminarCliente(boolean estado, int clie_id) {
        return mediador.eliminarCliente(estado, clie_id);
    }

    //************************* CARGOS *****************************
    public boolean registrarCargo(Cargo cargo) {
        return mediador.registrarCargo(cargo);
    }

    public ArrayList<Cargo> obtenerCargos() {
        return mediador.obtenerCargos();
    }

    public boolean actualizarCargo(Cargo cargo) {
        return mediador.actualizarCargo(cargo);
    }

    public boolean eliminarCargo(boolean estado, int carg_id) {
        return mediador.eliminarCargo(estado, carg_id);
    }


    public boolean registrarEmpleado(GUI.Empleados emplead) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
    
//************************* PROVEEDOR *****************************//
    public boolean registrarProveedor(Proveedor proveedor){
        return mediador.registrarProveedor(proveedor);
    }
    
  
    
    public ArrayList<Proveedor> obtenerProveedores() {
        return mediador.obtenerProveedores();
    }
    
    public boolean actualizarProveedor(Proveedor proveedor){
        return mediador.actualizarProveedor(proveedor);
    }
    
    public boolean eliminarProveedor(int id){
        return mediador.eliminarProveedor(id);

    }
}
