package merka;

import DAO.ProductoDao;
import DAO.TipoProductoDao;
import DAO.TipoUsuarioDao;
import DAO.UsuarioDao;
import VO.Producto;
import VO.TipoProducto;
import VO.TipoUsuario;
import VO.Usuario;
import java.util.ArrayList;

public class Mediador {
    TipoUsuarioDao tipoUsuarioDao;
    UsuarioDao usuarioDao;
    ProductoDao productoDao;
    TipoProductoDao tipoProductoDao;

    public Mediador() {
        usuarioDao = new UsuarioDao();
        productoDao = new ProductoDao();
        tipoProductoDao = new TipoProductoDao();
        tipoUsuarioDao=new TipoUsuarioDao();
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
    
    public ArrayList<TipoProducto> obtenerTipoProductos() {
        ArrayList<TipoProducto> lista =tipoProductoDao.obtenerTipoProductos();
        
        return lista;
    }
    
    public boolean actualizarTipoProducto(TipoProducto tipoProducto) {
        return tipoProductoDao.actualizarTipoProducto(tipoProducto);
    }
    
    public boolean eliminarTipoProducto(int tipr_id){
        return tipoProductoDao.eliminarTipoProducto(tipr_id);
    }
    
    //metodos de Tipo Usuario
    
    public ArrayList<TipoUsuario> obtenerTipoUsuarios(){
        return tipoUsuarioDao.obtenerTipoUsuario();
    }
    
    
    //metodos de Usuario
    public boolean registrarUsuario(Usuario usuario){
        return usuarioDao.registrar(usuario);
    }
    
    public ArrayList<Usuario> obtenerUsuarios(){
        return usuarioDao.obtenerUsuarios();
    }

}
