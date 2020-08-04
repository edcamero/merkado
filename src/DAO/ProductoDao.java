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

    //private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static Conexion conexion = new Conexion();

    public ProductoDao() {
    }

    //GUARDAR PRODUCTO
    public boolean Guardar(Producto producto) {

        boolean resultado = false;

        String consulta = "INSERT INTO public.productos(\n"
                + "	prod_nombre, prod_precio_compra, prod_precio_venta, prod_cantidad, prod_descripcion)\n"
                + "	VALUES (?, ?, ?, ?, ?) returning prod_id;";

        try {


            con = conexion.objConexion().getConexion();
            PreparedStatement psql = con.prepareStatement(consulta);
            psql.setString(1, producto.getNombre());
            psql.setInt(2, producto.getPrecioCompra());
            psql.setInt(3, producto.getPrecioVenta());
            psql.setInt(4, producto.getCantidad());
            psql.setString(5, producto.getDescripcion());


            rs = pst.executeQuery();
            System.out.println("ejecuto consulta");
            System.out.println(con.getCatalog());
            while (rs.next()) {
                producto.setId(rs.getInt("prod_id"));

            }


            resultado = true;


            psql.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar almacenar la información:\n"
                    + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);

        } finally {
           

                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
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

            

                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
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
            conexion = conexion.objConexion();
            Connection connection = conexion.getConexion();
            //con = conexion.getConexion();
            pst = connection.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();

            while (rs.next()) {
                Producto P = new Producto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
                lista.add(P);
            }
            connection.close();
            conexion.getClose();
        } catch (SQLException ex) {
        }
        return lista;
    }

    //MODIFICAR PRODUCTOS
    public static boolean actualizarProducto(Producto producto) {
        try {
//            String consulta = "update productos\n"
//                    + "set \n"
//                    + "prod_nombre=?,\n"
//                    + "prod_precio_compra=?,\n"
//                    + "prod_precio_venta=?,\n"
//                    + "prod_cantidad=?,\n"
//                    + "prod_descripcion=?\n"
//                    + "where prod_id=? returning *";

            String consulta = "UPDATE productos SET prod_nombre =  ?, prod_precio_compra =  ?, prod_precio_venta =  ?, prod_cantidad =  ?, prod_descripcion = ? WHERE prod_id=? returning *";

            //con = Conexion.getDataSource().getConnection();
            conexion = conexion.objConexion();
            //con = conexion.getConexion();
            Connection connection = conexion.getConexion();
            pst = connection.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setString(1, producto.getNombre());
            System.out.println("nom" + pst);
            pst.setInt(2, producto.getPrecioCompra());
            System.out.println("compr" + pst);
            pst.setInt(3, producto.getPrecioVenta());
            System.out.println("venta" + pst);
            pst.setInt(4, producto.getCantidad());
            System.out.println("cant" + pst);
            pst.setString(5, producto.getDescripcion());
            System.out.println("desc" + pst);
            pst.setInt(6, producto.getId());
            System.out.println("id" + pst);
            rs = pst.executeQuery();

            pst.close();
            rs.close();
            connection.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                rs.close();
                conexion.getClose();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    //ELIMINAR PRODUCTO
    public static boolean eliminarProducto(int prod_id) {

        try {
            String consulta = "DELETE FROM productos "
                    + "WHERE prod_id = ?";
            conexion = conexion.objConexion();
            Connection connection = conexion.getConexion();
            pst = connection.prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setInt(1, prod_id);
            
            
            if (pst.executeUpdate()>0) {
                return true;
            }
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                pst.close();
                rs.close();
                conexion.getClose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;

//            String consulta = "update productos\n"
//                    + "set \n"
//                    + "prod_nombre=?,\n"
//                    + "prod_precio_compra=?,\n"
//                    + "prod_precio_venta=?,\n"
//                    + "prod_cantidad=?,\n"
//                    + "prod_descripcion=?\n"
//                    + "where prod_id=? returning *";
//            String consulta = "UPDATE productos SET prod_nombre =  ?, prod_precio_compra =  ?, prod_precio_venta =  ?, prod_cantidad =  ?, prod_descripcion = ? WHERE prod_id=? returning *";
//
//            //con = Conexion.getDataSource().getConnection();
//            conexion = conexion.objConexion();
//            //con = conexion.getConexion();
//            Connection connection = conexion.getConexion();
//            pst = connection.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//
//            rs = pst.executeQuery();
//
//            pst.close();
//            rs.close();
//            connection.close();
//
//            return true;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductoDao.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//                conexion.getClose();
//
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
//                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//        return false;
    }
}
