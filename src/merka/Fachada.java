package merka;

import VO.Producto;
import VO.TipoProducto;
import VO.TipoUsuario;
import VO.Usuario;
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
    
    public boolean actualizarProducto(Producto producto){
        return mediador.actualizarProducto(producto);
    }
    
    public boolean eliminarProducto(int prod_id){
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
    
    public boolean eliminarTipoProducto(int tipr_id){
        return mediador.eliminarTipoProducto(tipr_id);
    }
    
    
    //TIPO USUARIO
    
    
    public ArrayList<TipoUsuario> obtenerTipoUsuario(){
        return mediador.obtenerTipoUsuarios();
    }
    
    
    //USUARIO
    
    public boolean registrarUsuario(Usuario usuario){
        return mediador.registrarUsuario(usuario);
    }
    
    public ArrayList<Usuario> obtenerUsuarios(){
        return mediador.obtenerUsuarios();
    }
}
