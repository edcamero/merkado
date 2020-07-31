package merka;

import DAO.ProductoDao;
import DAO.UsuarioDao;
import VO.Producto;
import java.util.ArrayList;

public class Mediador {

    UsuarioDao usuarioDao;
    ProductoDao productoDao;

    public Mediador() {
        usuarioDao = new UsuarioDao();
        productoDao = new ProductoDao();
    }

    public boolean login(String username, String pass) {
        return usuarioDao.login(username, pass);
    }
    
    public boolean registrarProducto(Producto producto) {
        return productoDao.Guardar(producto);
    }
    
    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> lista = new ArrayList<Producto>();
        lista = productoDao.obtenerProductos();
        return lista;
    }

}
