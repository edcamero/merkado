package DAO;

import VO.Cargo;
import VO.Cliente;
import VO.Empleado;
import VO.Persona;
import database.Conexion;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    //OBTENER LOS EMPLEADOS
    public ArrayList<Empleado> obtenerEmpleados() {
        ArrayList<Empleado> lista = new ArrayList();
        String consulta = "select *\n"
                + "from empleados as e inner join personas as p on e.pers_id = p.pers_id \n"
                + "join cargos as c on c.carg_id = e.carg_id\n"
                + "order by e.empl_id;";
        try {
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getBoolean("empl_estado") == true) {
                    Empleado e = new Empleado(rs.getInt(1), rs.getDate(2), rs.getBoolean(3), new Cargo(rs.getInt(12), rs.getString(13), rs.getString(14), rs.getInt(15), rs.getBoolean(16)), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
                    lista.add(e);
                }
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

    //ACTUALIZAR EMPLEADO
    public boolean actualizarEmpleado(Empleado empleado) {

        boolean resultado = false;
        try {
            String consulta = "UPDATE empleados SET empl_fecha_contratacion=?, carg_id=? WHERE empl_id=?";
            //con = Conexion.getDataSource().getConnection();
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            java.util.Date utilDate = empleado.getFechaContratacion();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            pst.setDate(1, sqlDate);
            pst.setInt(2, empleado.getCargo().getId());
            pst.setInt(3, empleado.getId());

            pst.executeUpdate();

            resultado = true;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDao.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        return resultado;
    }

//ELIMINAR CLIENTE
    public boolean eliminarEmpleado(boolean estado, int empl_id) {
        boolean resultado = false;
        try {
            String consulta = "UPDATE empleados SET empl_estado=? WHERE empl_id=?";
            //con = Conexion.getDataSource().getConnection();
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setBoolean(1, estado);
            pst.setInt(2, empl_id);

            pst.executeUpdate();

            resultado = true;

        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        return resultado;
    }
}
