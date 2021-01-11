package DAO;

import VO.Factura;
import VO.Producto;
import database.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FacturaDao {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public FacturaDao() {
    }

    //GUARDAR FACTURA
    public boolean registrarFactura(Factura factura) {

        boolean resultado = false;

        String consulta = "INSERT INTO public.facturas(\n"
                + "	fact_fecha, clie_id, empl_id, fact_total, fact_estado)\n"
                + "	VALUES (?, ?, ?, ?, ?) returning fact_id;";
        try {
            con = Conexion.objConexion().getConexion();

            PreparedStatement pst = con.prepareStatement(consulta);

            java.util.Date utilDate = factura.getFecha();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            pst.setDate(1, sqlDate);
            pst.setInt(2, factura.getCliente().getId());
            pst.setInt(3, 1);
            pst.setInt(4, factura.getTotalFactura());
            pst.setString(5, factura.getEstado());

            rs = pst.executeQuery();
            while (rs.next()) {
                factura.setId(rs.getInt("fact_id"));
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
}
