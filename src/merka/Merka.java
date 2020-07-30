/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merka;

import database.Pool;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public class Merka {
    private static Connection connection = null;
    public static void main( String[] args) throws SQLException{
        System.out.println("hola bb");
        connection=getConnection();
        
    }
    
    private static Connection getConnection() throws SQLException {
		return Pool.getConexion();
	}
    
}
