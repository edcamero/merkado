package merka;

import VO.Producto;

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

}
