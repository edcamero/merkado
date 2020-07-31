/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class UsuarioDao {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public UsuarioDao() {
    }

    public boolean login(String username, String password) {
        String consulta = "select * from usuarios where username=? and password=?";
        int id = 0;
        try {
            con = Conexion.getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            while (rs.next()) {

                id = rs.getInt(1);

            }

            if (id > 0) {
                System.out.println("ingreso");
                return true;
            }
            System.out.println("usuario o contraseña erronea");
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }
}
