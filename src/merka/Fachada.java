package merka;

import VO.Producto;
import VO.TipoProducto;
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

    //producto
    public boolean registrarProducto(Producto producto) {
        return mediador.registrarProducto(producto);
    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> lista = new ArrayList<Producto>();
        lista = mediador.obtenerProductos();
        return lista;
    }
    
    public boolean actualizarProducto(Producto producto){
        return mediador.actualizarProducto(producto);
    }
    
    public boolean eliminarProducto(int prod_id){
        return mediador.eliminarProducto(prod_id);
    }
    
    //TIPO PRODUCTO
    public boolean registrarTipoProducto(TipoProducto tipoProducto) {
        return mediador.registrarTipoProducto(tipoProducto);
    }
    
    public ArrayList<TipoProducto> obtenerTipoProductos() {
        ArrayList<TipoProducto> lista = new ArrayList<TipoProducto>();
        lista = mediador.obtenerTipoProductos();
        return lista;
    }
    
    public boolean actualizarTipoProducto(TipoProducto tipoProducto) {
        return mediador.actualizarTipoProducto(tipoProducto);
    }
    
    public boolean eliminarTipoProducto(int tipr_id){
        return mediador.eliminarTipoProducto(tipr_id);
    }
}
