package DAO;

import VO.Persona;
import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PersonaDao {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public boolean registrarPersona(Persona persona) {

        boolean resultado = false;

        String consulta = "INSERT INTO public.personas(\n"
                + "	pers_nombre, pers_apellido, pers_documento, pers_telefono, pers_email)\n"
                + "	VALUES (?, ?, ?, ?, ?) returning pers_id;";
        try {
            con = Conexion.objConexion().getConexion();

            PreparedStatement pst = con.prepareStatement(consulta);
            pst.setString(1, persona.getNombre());
            pst.setString(2, persona.getApellido());
            pst.setString(3, persona.getDocumento());
            pst.setString(4, persona.getTelefono());
            pst.setString(5, persona.getEmail());

            rs = pst.executeQuery();
            while (rs.next()) {
                persona.setId(rs.getInt("pers_id"));
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
    public ArrayList<Persona> obtenerPersonas() {
        ArrayList<Persona> personas = new ArrayList();
        String consulta = "select * FROM personas ORDER BY pers_id;";
        try {
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                Persona P = new Persona(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
                personas.add(P);
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
                Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return personas;
    }

    //MODIFICAR PRODUCTOS
    public boolean actualizarPersona(Persona persona) {
        try {
//            String consulta = "update productos\n"
//                    + "set \n"
//                    + "prod_nombre=?,\n"
//                    + "prod_precio_compra=?,\n"
//                    + "prod_precio_venta=?,\n"
//                    + "prod_cantidad=?,\n"
//                    + "prod_descripcion=?\n"
//                    + "where prod_id=? returning *";

            String consulta = "UPDATE personas SET pers_nombre =  ?, pers_apellido =  ?, pers_documento =  ?, pers_telefono =  ?, pers_email = ? WHERE pers_id=? returning *";
            //con = Conexion.getDataSource().getConnection();
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setString(1, persona.getNombre());
            pst.setString(2, persona.getApellido());
            pst.setString(3, persona.getDocumento());
            pst.setString(4, persona.getTelefono());
            pst.setString(5, persona.getEmail());
            pst.setInt(6, persona.getId());
            rs = pst.executeQuery();

            return true;

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
        return false;
    }

    //ELIMINAR PRODUCTO
    public static boolean eliminarPersona(int pers_id) {

        try {
            String consulta = "DELETE FROM personas "
                    + "WHERE pers_id = ?";
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setInt(1, pers_id);

            if (pst.executeUpdate() > 0) {
                return true;
            }
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
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
