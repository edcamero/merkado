/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.Producto;
import VO.Proveedor;
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
 * @author enyerson
 */
public class ProveedorDao {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public boolean registrarProveedor(Proveedor proveedor) {

        boolean respuesta = false;
        String consulta = "INSERT INTO public.proveedor(\n"
                + "	 prov_nombre, prov_nit, prov_direccion, prov_telefono)\n"
                + "	VALUES ( ?, ?, ?, ?) returning prov_id;;";
        try {
            con = Conexion.objConexion().getConexion();
            PreparedStatement pst = con.prepareStatement(consulta);
            pst.setString(1, proveedor.getNombre());
            pst.setString(2, proveedor.getNit());
            pst.setString(3, proveedor.getDireccion());
            pst.setString(4, proveedor.getTelefono());

            rs = pst.executeQuery();
            while (rs.next()) {
                proveedor.setId(rs.getInt("prov_id"));
            }
            respuesta = true;
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("estamos cerrando la conexion por su bien ");
            }
        }
        return respuesta;

    }

    public ArrayList<Proveedor> obtenerProveedores() {

        ArrayList<Proveedor> lista = new ArrayList();
        String consulta = "select * FROM proveedor ORDER BY prod_id;";
        try {
            con = Conexion.objConexion().getConexion();
            //con = conexion.getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(rs.getInt("prov_id"),
                        rs.getString("prov_nombre"), rs.getString("prov_nit "),
                        rs.getString("prov_direccion "), rs.getString("prov_telefono ")
                );

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return lista;

    }

    public boolean actualizarProveedor(Proveedor proveedor) {

        boolean respuesta = false;
        String consulta = "UPDATE public.proveedor\n"
                + "	SET  "
                + "prov_nombre=?, "
                + "prov_nit=?, "
                + "prov_direccion=?, "
                + "prov_telefono=?\n"
                + "WHERE prov_id=?;";
        try {
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setString(1, proveedor.getNombre());
            pst.setString(2, proveedor.getNit());
            pst.setString(3, proveedor.getDireccion());
            pst.setString(4, proveedor.getTelefono());
            pst.setInt(5, proveedor.getId());
            rs = pst.executeQuery();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
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

        return respuesta;
    }

    public boolean eliminarProveedor(int id) {
        boolean respuesta = false;
            String consulta = "DELETE FROM public.proveedor\n"
                    + "	WHERE prov_id=?;";
        try {
            
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setInt(1,id);
            if (pst.executeUpdate() > 0) {
                respuesta=true;
            }
            con.close();
            
            //return respuesta;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally {
            try {
                pst.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        }
        return respuesta;
    }

}
