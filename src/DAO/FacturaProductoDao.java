package DAO;

import VO.FacturaProducto;
import VO.Producto;
import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FacturaProductoDao {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public FacturaProductoDao() {
    }

    //GUARDAR EL PRODUCTO EN LA FACTURA
    public boolean registrarFacturaProducto(FacturaProducto facturaProducto) {

        boolean resultado = false;

        String consulta = "INSERT INTO public.factura_producto(\n"
                + "	fact_id, prod_id, fapr_cantidad, fapr_total)\n"
                + "	VALUES (?, ?, ?, ?) returning fapr_id;";
        try {
            con = Conexion.objConexion().getConexion();

            PreparedStatement pst = con.prepareStatement(consulta);

            pst.setInt(1, facturaProducto.getId_factura());
            pst.setInt(2, facturaProducto.getProducto().getId());
            pst.setInt(3, facturaProducto.getCantidad());
            pst.setInt(4, facturaProducto.getTotal());

            rs = pst.executeQuery();
            while (rs.next()) {
                facturaProducto.setId(rs.getInt("fapr_id"));
            }
            resultado = true;
            pst.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            if (e.getErrorCode() == 0) {
                JOptionPane.showMessageDialog(null, "¡Nro. de Codigo de Barras ya existe!", "Codigo Duplicado",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
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

    //MODIFICAR PRODUCTO DE LA FACTURA
    public boolean actualizarProductoFactura(FacturaProducto facturaProducto) {
        try {
            String consulta = "UPDATE factura_producto SET "
                    + "fapr_cantidad =  ?, "
                    + "fapr_total = ? WHERE fapr_id = ? returning *";

            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setInt(1, facturaProducto.getCantidad());
            System.out.println("cantidad deb " + facturaProducto.getCantidad());
            pst.setInt(2, facturaProducto.getTotal());
            pst.setInt(3, facturaProducto.getId());
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
}
