package DAO;

import VO.Cargo;
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

public class CargoDao {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    //GUARDAR CARGO
    public boolean registrarCargo(Cargo cargo) {

        boolean resultado = false;

        String consulta = "INSERT INTO public.cargos(\n"
                + "	carg_nombre, carg_descripcion, carg_salario_mensual, carg_estado)\n"
                + "	VALUES (?, ?, ?, ?) returning carg_id;";
        try {
            con = Conexion.objConexion().getConexion();

            PreparedStatement pst = con.prepareStatement(consulta);

            pst.setString(1, cargo.getNombre());
            pst.setString(2, cargo.getDescripcion());
            pst.setInt(3, cargo.getSalario());
            pst.setBoolean(4, cargo.isEstado());

            rs = pst.executeQuery();
            while (rs.next()) {
                cargo.setId(rs.getInt("carg_id"));
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

    //OBTENER LOS CARGOS
    public ArrayList<Cargo> obtenerCargos() {
        ArrayList<Cargo> lista = new ArrayList();
        String consulta = "select * FROM cargos ORDER BY carg_id;";
        try {
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getBoolean(5) == true) {
                    Cargo c = new Cargo(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getInt(4), rs.getBoolean(5));
                    lista.add(c);
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

    //MODIFICAR CARGOS
    public boolean actualizarCargo(Cargo cargo) {
        try {
            String consulta = "UPDATE cargos SET carg_nombre =  ?, carg_descripcion =  ?, carg_salario_mensual =  ? WHERE carg_id=? returning *";
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setString(1, cargo.getNombre());
            pst.setString(2, cargo.getDescripcion());
            pst.setInt(3, cargo.getSalario());
            pst.setInt(4, cargo.getId());
            rs = pst.executeQuery();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class
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
        return false;
    }

    //ELIMINAR CARGO
    public boolean eliminarCargo(boolean estado, int carg_id) {
        boolean resultado = false;
        try {
            String consulta = "UPDATE cargos SET carg_estado=? WHERE carg_id=?";
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setBoolean(1, estado);
            pst.setInt(2, carg_id);
            pst.executeUpdate();
            resultado = true;
        } catch (SQLException ex) {
            Logger.getLogger(CargoDao.class
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
