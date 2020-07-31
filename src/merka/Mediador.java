/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merka;

import DAO.UsuarioDao;

/**
 *
 * @author USUARIO
 */
public class Mediador {
    UsuarioDao usuarioDao;

    public Mediador() {
        usuarioDao = new UsuarioDao();
    }
    
    public boolean login(String username,String pass){
        return usuarioDao.login(username, pass);
    }
    
}
