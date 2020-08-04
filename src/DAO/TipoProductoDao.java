/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.Producto;
import VO.TipoProducto;
import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class TipoProductoDao {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public boolean registrarTipoProducto(TipoProducto tipoProducto) {
        boolean resultado = false;
        String consulta = "INSERT INTO public.tipo_productos(\n"
                + "	tipr_nombre, tipr_descripcion)\n"
                + "	VALUES ( ?, ?) returning tipr_id;";

        try {
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta);
            pst.setString(1, tipoProducto.getNombre());
            pst.setString(2, tipoProducto.getDescripcion());

            rs = pst.executeQuery();
            while (rs.next()) {
                tipoProducto.setId(rs.getInt("tipr_id"));
                System.out.println("registrado tipo producto");
                resultado = true;
            }
            pst.close();
            rs.close();
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

    //OBTENER LOS TIPO DE PRODUCTOS
    public ArrayList<TipoProducto> obtenerTipoProductos() {
        ArrayList<TipoProducto> lista = new ArrayList();
        String consulta = "select * FROM tipo_productos ORDER BY tipr_id;";
        try {
            //con = Conexion.getConexion();
            con = Conexion.objConexion().getConexion();
            //con = conexion.getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                TipoProducto P = new TipoProducto(rs.getInt(1), rs.getString(2), rs.getString(3));
                lista.add(P);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar obtener la informacion:\n"
                    + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                pst.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    //ACUTLIZAR EL TIPO DE PRODUCTO
    public static boolean actualizarTipoProducto(TipoProducto tipoProducto) {

        try {
//            String consulta = "update productos\n"
//                    + "set \n"
//                    + "prod_nombre=?,\n"
//                    + "prod_precio_compra=?,\n"
//                    + "prod_precio_venta=?,\n"
//                    + "prod_cantidad=?,\n"
//                    + "prod_descripcion=?\n"
//                    + "where prod_id=? returning *";

            String consulta = "UPDATE tipo_productos SET tipr_nombre =  ?, tipr_descripcion = ? WHERE tipr_id=? returning *";
            //con = Conexion.getDataSource().getConnection();
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setString(1, tipoProducto.getNombre());
            System.out.println("nom" + pst);
            pst.setString(2, tipoProducto.getDescripcion());
            System.out.println("desc" + pst);
            pst.setInt(3, tipoProducto.getId());
            System.out.println("id" + pst);
            rs = pst.executeQuery();

            pst.close();
            rs.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }
    
    //ELIMINAR TIPO DE PRODUCTO
    public static boolean eliminarTipoProducto(int tipr_id) {
        try {
            String consulta = "DELETE FROM tipo_productos "
                    + "WHERE tipr_id = ?";
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setInt(1, tipr_id);

            if (pst.executeUpdate() > 0) {
                return true;
            }
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                pst.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }
}
