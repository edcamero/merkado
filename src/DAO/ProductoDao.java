package DAO;

import VO.Producto;
import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProductoDao {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public ProductoDao() {
    }

    //GUARDAR PRODUCTO
    public boolean registrarProductos(Producto producto) {

        boolean resultado = false;

        String consulta = "INSERT INTO public.productos(\n"
                + "	prod_nombre, prod_precio_compra, prod_precio_venta, prod_cantidad, prod_descripcion, tipr_id)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?) returning prod_id;";
        try {
            con = Conexion.objConexion().getConexion();

            PreparedStatement pst = con.prepareStatement(consulta);

            pst.setString(1, producto.getNombre());
            pst.setInt(2, producto.getPrecioCompra());
            pst.setInt(3, producto.getPrecioVenta());
            pst.setInt(4, producto.getCantidad());
            pst.setString(5, producto.getDescripcion());
            pst.setInt(6, producto.getTipoProducto());

            rs = pst.executeQuery();
            while (rs.next()) {
                producto.setId(rs.getInt("prod_id"));
            }
            resultado = true;
            pst.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar almacenar la información:\n"
                    + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
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
        return resultado;
    }

    //OBTENER LOS PRODUCTOS
    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> lista = new ArrayList();
        String consulta = "select * FROM productos ORDER BY prod_id;";
        try {
            //con = Conexion.getConexion();
            con = Conexion.objConexion().getConexion();
            //con = conexion.getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                Producto P = new Producto(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7));
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

    //MODIFICAR PRODUCTOS
    public boolean actualizarProducto(Producto producto) {
        try {
            String consulta = "UPDATE productos SET "
                    + "prod_nombre =  ?, "
                    + "prod_precio_compra =  ?,"
                    + "prod_precio_venta =  ?, "
                    + "prod_cantidad =  ?, "
                    + "prod_descripcion = ? WHERE prod_id=? returning *";
            
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setString(1, producto.getNombre());
            pst.setInt(2, producto.getPrecioCompra());
            pst.setInt(3, producto.getPrecioVenta());
            pst.setInt(4, producto.getCantidad());
            pst.setString(5, producto.getDescripcion());
            pst.setInt(6, producto.getId());
            rs = pst.executeQuery();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class
                    .getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Base de Datos:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
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

    //ELIMINAR PRODUCTO
    public boolean eliminarProducto(int prod_id) {

        try {
            String consulta = "DELETE FROM productos "
                    + "WHERE prod_id = ?";
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setInt(1, prod_id);

            if (pst.executeUpdate() > 0) {
                return true;
            }
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Base de Datos:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
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
