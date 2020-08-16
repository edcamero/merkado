package DAO;

import VO.Empleado;
import database.Conexion;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class EmpleadoDao {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    //GUARDAR EMPLEADO
    public boolean registrarEmpleado(Empleado empleado) {

        boolean resultado = false;

        String consulta = "INSERT INTO public.empleados(\n"
                + "	empl_fecha_contratacion, empl_estado, pers_id, carg_id)\n"
                + "	VALUES (?,?,?,?) returning empl_id;";
        try {
            con = Conexion.objConexion().getConexion();

            PreparedStatement pst = con.prepareStatement(consulta);

            java.util.Date utilDate = empleado.getFechaContratacion();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            pst.setDate(1, sqlDate);
            pst.setBoolean(2, empleado.isEstado());
            pst.setInt(3, empleado.getPers_Id());
            pst.setInt(4, empleado.getCargo().getId());

            rs = pst.executeQuery();
            while (rs.next()) {
                empleado.setId(rs.getInt("empl_id"));
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
}
