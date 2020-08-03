package merka;

import DAO.ProductoDao;
import DAO.TipoProductoDao;
import DAO.UsuarioDao;
import VO.Producto;
import VO.TipoProducto;
import java.util.ArrayList;

public class Mediador {

    UsuarioDao usuarioDao;
    ProductoDao productoDao;
    TipoProductoDao tipoProductoDao;

    public Mediador() {
        usuarioDao = new UsuarioDao();
        productoDao = new ProductoDao();
        tipoProductoDao = new TipoProductoDao();
    }

    public boolean login(String username, String pass) {
        return usuarioDao.login(username, pass);
    }
    
    
    //PRODUCTOS
    public boolean registrarProducto(Producto producto) {
        return productoDao.registrarProductos(producto);
    }
    
    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> lista = new ArrayList<Producto>();
        lista = productoDao.obtenerProductos();
        return lista;
    }
    
    public boolean actualizarProducto(Producto producto){
        return productoDao.actualizarProducto(producto);
    }
    
    public boolean eliminarProducto(int prod_id){
        return productoDao.eliminarProducto(prod_id);
    }
    
    //TIPO PRODUCTOS
    public boolean registrarTipoProducto(TipoProducto tipoProducto) {
        return tipoProductoDao.registrarTipoProducto(tipoProducto);
    }

}
