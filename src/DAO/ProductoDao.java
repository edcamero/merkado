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

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductoDao() {
    }

    //GUARDAR PRODUCTO
    public boolean Guardar(Producto producto) {

        boolean resultado = false;

        String SSQL = "INSERT INTO public.productos(\n"
                + "prod_nombre, prod_precio_compra, prod_precio_venta, prod_cantidad, prod_descripcion)\n"
                + "VALUES (?, ?, ?, ?, ?) returning prod_id;";

        try {

            con = Conexion.getConexion();

            PreparedStatement psql = con.prepareStatement(SSQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            //PreparedStatement psql = con.prepareStatement(SSQL);
            psql.setString(1, producto.getNombre());
            psql.setInt(2, producto.getPrecioCompra());
            psql.setInt(3, producto.getPrecioVenta());
            psql.setInt(4, producto.getCantidad());
            psql.setString(5, producto.getDescripcion());

            rs = psql.executeQuery();
            while (rs.next()) {
                producto.setId(rs.getInt("prod_id"));
                resultado = true;
            }
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

    //OBTENER LOS PRODUCTOS
    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> lista = new ArrayList();
        String consulta = "select * FROM productos ORDER BY prod_id;";
        try {
            con = Conexion.getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();

            while (rs.next()) {
                Producto P = new Producto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
                lista.add(P);
            }
        } catch (SQLException ex) {
        }
        return lista;
    }
}
