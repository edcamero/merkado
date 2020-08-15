package merka;

import DAO.*;
import VO.*;
import java.util.ArrayList;

public class Mediador {

    TipoUsuarioDao tipoUsuarioDao;
    UsuarioDao usuarioDao;
    ProductoDao productoDao;
    TipoProductoDao tipoProductoDao;
    PersonaDao personaDao;
    ClienteDao clienteDao;
    CargoDao cargoDao;
    EmpleadoDao empleadoDao;

    public Mediador() {
        usuarioDao = new UsuarioDao();
        productoDao = new ProductoDao();
        tipoProductoDao = new TipoProductoDao();
        tipoUsuarioDao = new TipoUsuarioDao();
        personaDao = new PersonaDao();
        clienteDao = new ClienteDao();
        cargoDao = new CargoDao();
        empleadoDao = new EmpleadoDao();
    }

    public boolean login(String username, String pass) {
        return usuarioDao.login(username, pass);
    }

    //*********************PRODUCTOS
    public boolean registrarProducto(Producto producto) {
        return productoDao.registrarProductos(producto);
    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> lista = new ArrayList<Producto>();
        lista = productoDao.obtenerProductos();
        return lista;
    }

    public boolean actualizarProducto(Producto producto) {
        return productoDao.actualizarProducto(producto);
    }

    public boolean eliminarProducto(int prod_id) {
        return productoDao.eliminarProducto(prod_id);
    }

    //*******************TIPO PRODUCTOS
    public boolean registrarTipoProducto(TipoProducto tipoProducto) {
        return tipoProductoDao.registrarTipoProducto(tipoProducto);
    }

    public ArrayList<TipoProducto> obtenerTipoProductos() {
        return tipoProductoDao.obtenerTipoProductos();
    }

    public TipoProducto obtenerTipoProducto(String tipr_nombre) {
        return tipoProductoDao.obtenerTipoProducto(tipr_nombre);
    }

    public boolean actualizarTipoProducto(TipoProducto tipoProducto) {
        return tipoProductoDao.actualizarTipoProducto(tipoProducto);
    }

    public boolean eliminarTipoProducto(int tipr_id) {
        return tipoProductoDao.eliminarTipoProducto(tipr_id);
    }

    //********************metodos de Tipo Usuario
    public ArrayList<TipoUsuario> obtenerTipoUsuarios() {
        return tipoUsuarioDao.obtenerTipoUsuario();
    }

    //***********************metodos de Usuario
    public boolean registrarUsuario(Usuario usuario) {
        return usuarioDao.registrar(usuario);
    }

    public ArrayList<Usuario> obtenerUsuarios() {
        return usuarioDao.obtenerUsuarios();

    }

    public boolean actualizarTipoUsuario(Usuario usuario) {
        return usuarioDao.actualizarTipo(usuario);
    }

    public boolean actualizarPassword(Usuario usuario) {
        return usuarioDao.cambiarPassword(usuario);
    }

    public boolean eliminarUsuario(int id) {
        return usuarioDao.eliminarUsuario(id);
    }

    //******************PERSONA******************************
    public int registrarPersona(Persona persona) {
        return personaDao.registrarPersona(persona);
    }

    public ArrayList<Persona> obtenerPersonas() {
        return personaDao.obtenerPersonas();

    }

    public boolean actualizarPersona(Persona persona) {
        return personaDao.actualizarPersona(persona);
    }

    public boolean eliminarPersona(int pers_id) {
        return personaDao.eliminarPersona(pers_id);
    }

    //*******************EMPLEADO***************************
    public boolean registrarEmpleado(Empleado empleado) {
        empleado.setPers_Id(registrarPersona(empleado));
        return empleadoDao.registrarEmpleado(empleado);
    }

    //******************CLIENTE**************************
    public boolean registrarCliente(Cliente cliente) {
        return clienteDao.registrarCliente(cliente);
    }

    public ArrayList<Cliente> obtenerClientes(ArrayList<Persona> personas) {
        return clienteDao.obtenerClientes(personas);
    }

    public int personaId(int clie_id) {
        return clienteDao.personaId(clie_id);
    }

    public boolean eliminarCliente(boolean estado, int clie_id) {
        return clienteDao.eliminarCliente(estado, clie_id);
    }

    //************************* CARGOS *****************************
    public boolean registrarCargo(Cargo cargo) {
        return cargoDao.registrarCargo(cargo);
    }

    public ArrayList<Cargo> obtenerCargos() {
        return cargoDao.obtenerCargos();
    }

    public boolean actualizarCargo(Cargo cargo) {
        return cargoDao.actualizarCargo(cargo);
    }

    public boolean eliminarCargo(boolean estado, int carg_id) {
        return cargoDao.eliminarCargo(estado, carg_id);
    }

}
