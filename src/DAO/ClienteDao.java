package DAO;

import VO.Cliente;
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

public class ClienteDao {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    //GUARDAR PRODUCTO
    public boolean registrarCliente(Cliente cliente) {

        boolean resultado = false;

        String consulta = "INSERT INTO public.clientes(\n"
                + "	fk_pers_id, estado)\n"
                + "	VALUES (?,?) returning clie_id;";
        try {
            con = Conexion.objConexion().getConexion();

            PreparedStatement pst = con.prepareStatement(consulta);
            pst.setInt(1, cliente.getPers_Id());
            pst.setBoolean(2, cliente.isEstado());

            rs = pst.executeQuery();
            while (rs.next()) {
                cliente.setId(rs.getInt("clie_id"));
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
    public ArrayList<Cliente> obtenerClientes(ArrayList<Persona> personas) {
        ArrayList<Cliente> lista = new ArrayList();
        String consulta = "select * FROM clientes ORDER BY clie_id;";
        try {
            //con = Conexion.getConexion();
            con = Conexion.objConexion().getConexion();
            //con = conexion.getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                if (rs.getBoolean("estado") == true) {
                    Cliente P = new Cliente(rs.getInt(1), rs.getBoolean(2), personas.get(i));
                    lista.add(P);
                }
                i++;
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

    //OBTENER EL ID DE LA PERSONA
    public int personaId(int clie_id) {
        int pers_id = 0;
        String consulta = "select * FROM clientes where clie_id=?;";
        try {
            //con = Conexion.getConexion();
            con = Conexion.objConexion().getConexion();
            //con = conexion.getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setInt(1, clie_id);
            rs = pst.executeQuery();
            if (rs.next()) {
                pers_id = rs.getInt(2);
            }

            //pers_id = rs.getInt(1);
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
        return pers_id;
    }

    //MODIFICAR PRODUCTOS
    public boolean eliminarCliente(boolean estado, int clie_id) {
        boolean resultado = false;
        try {
            String consulta = "UPDATE clientes SET estado=? WHERE clie_id=?";
            //con = Conexion.getDataSource().getConnection();
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setBoolean(1, estado);
            pst.setInt(2, clie_id);

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
