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

    public int registrarPersona(Persona persona) {
        int resultado = 0;

        String consulta = "INSERT INTO public.personas(\n"
                + "	pers_nombre, pers_apellido, pers_documento, pers_telefono, pers_direccion)\n"
                + "	VALUES (?, ?, ?, ?, ?) returning pers_id;";
        try {
            con = Conexion.objConexion().getConexion();

            PreparedStatement pst = con.prepareStatement(consulta);

            pst.setString(1, persona.getNombre());
            pst.setString(2, persona.getApellido());
            pst.setString(3, persona.getDocumento());
            pst.setString(4, persona.getTelefono());
            pst.setString(5, persona.getDireccion());

            rs = pst.executeQuery();
            while (rs.next()) {
                persona.setPers_Id(rs.getInt("pers_id"));
            }
            resultado = persona.getPers_Id();
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
        boolean resultado = false;
        try {
            String consulta = "UPDATE personas SET pers_nombre =  ?, pers_apellido =  ?, pers_documento =  ?, pers_telefono =  ?, pers_direccion = ? WHERE pers_id=? returning *";
            //con = Conexion.getDataSource().getConnection();
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setString(1, persona.getNombre());
            pst.setString(2, persona.getApellido());
            pst.setString(3, persona.getDocumento());
            pst.setString(4, persona.getTelefono());
            pst.setString(5, persona.getDireccion());
            pst.setInt(6, persona.getPers_Id());
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("pers_id"));
            }

            resultado = true;

        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class
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
        return resultado;
    }
//    MODIFICAR PRODUCTOS
//    public boolean buscarPersona(String documento) {
//        
//        boolean resultado = false;
//        try {
//            String consulta = "UPDATE personas SET pers_nombre =  ?, pers_apellido =  ?, pers_documento =  ?, pers_telefono =  ?, pers_direccion = ? WHERE pers_id=? returning *";
//            con = Conexion.getDataSource().getConnection();
//            con = Conexion.objConexion().getConexion();
//            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//
//            pst.setString(1, persona.getNombre());
//            pst.setString(2, persona.getApellido());
//            pst.setString(3, persona.getDocumento());
//            pst.setString(4, persona.getTelefono());
//            pst.setString(5, persona.getDireccion());
//            pst.setInt(6, persona.getPers_Id());
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                System.out.println(rs.getInt("pers_id"));
//            }
//
//            resultado = true;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(PersonaDao.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//                con.close();
//
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
//                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//        return resultado;
//    }

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
