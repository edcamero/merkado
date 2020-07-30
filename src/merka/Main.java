/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Merka;


import database.Pool2;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class Main {

    
    private static Connection connection = null;
    public static void main( String[] args) {
        System.out.println("hola bb");
        try {
            connection=getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static Connection getConnection() throws SQLException {
		return Pool2.getConexion();
	}
    
}
