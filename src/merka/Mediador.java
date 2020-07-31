package merka;

import DAO.ProductoDao;
import DAO.UsuarioDao;

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
    
    public boolean registrarProducto(String nombre, int precio, int cantidad) {
        return productoDao.registrarProducto(nombre, precio, cantidad);
    }

}
