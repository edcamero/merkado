package DAO;

import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProductoDao {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductoDao() {
    }

    public int Guardar(String nombre, int precio, int cantidad) {

        int resultado = 0;

        Connection con = null;

        String SSQL = "INSERT INTO contacto (nombre, precio, cantidad) "
                + "VALUES (?, ?, ?)";

        try {

            con = Conexion.getConexion();

            PreparedStatement psql = con.prepareStatement(SSQL);
            psql.setString(1, nombre);
            psql.setInt(2, precio);
            psql.setInt(3, cantidad);

            resultado = psql.executeUpdate();

            psql.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al intentar almacenar la información:\n"
                    + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);

        } finally {

            try {

                if (con != null) {

                    con.close();

                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);

            }

        }

        return resultado;

    }

    public boolean registrarProducto(String nombre, int precio, int cantidad) {

        String consulta = "INSERT INTO productos (nombre, precio, cantidad) "
                + "VALUES (?, ?, ?)";
        int id = 0;
        try {
            con = Conexion.getConexion();
            PreparedStatement pst = con.prepareStatement(consulta);
            pst.setString(1, nombre);
            pst.setInt(2, precio);
            pst.setInt(3, cantidad);

            id = pst.executeUpdate();
            if (id != 0) {
                System.out.println("Producto Registrado");
                return true;
            }

            System.out.println("Producto No Registrado");
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;

//        int resultado = 0;
//
//        Connection con = null;
//
//        String SSQL = "INSERT INTO productos (nombre, precio, cantidad) "
//                + "VALUES (?, ?, ?)";
//
//        try {
//
//            con = Conexion.getConexion();
//
//            PreparedStatement psql = con.prepareStatement(SSQL);
//            psql.setString(1, nombre);
//            psql.setInt(2, precio);
//            psql.setInt(3, cantidad);
//
//            resultado = psql.executeUpdate();
//            if(resultado != 0){
//                return true;
//            }
//
//            psql.close();
//
//        } catch (SQLException e) {
//
//            JOptionPane.showMessageDialog(null, "Error al intentar almacenar la información:\n"
//                    + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
//
//        } finally {
//
//            try {
//
//                if (con != null) {
//
//                    con.close();
//
//                }
//
//            } catch (SQLException ex) {
//
//                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
//                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
//
//            }
//
//        }
//
//        return false;
    }

}
