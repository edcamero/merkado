/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merka;

/**
 *
 * @author USUARIO
 */
public class Fachada {

    static private Fachada fachada;
    private Mediador mediador;

    private Fachada() {
        mediador = new Mediador();
    }
    
    static public Fachada getInstancia(){
        if(fachada==null)
        {
            fachada =new Fachada();
        }
        return fachada;
    }
    
    
    public boolean login(String username,String pass){
        return mediador.login(username, pass);
    }

}
