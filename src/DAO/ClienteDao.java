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
                + "	fk_pers_id)\n"
                + "	VALUES (?) returning clie_id;";
        try {
            con = Conexion.objConexion().getConexion();

            PreparedStatement pst = con.prepareStatement(consulta);
            pst.setInt(1, cliente.getPers_Id());

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
                if (rs.getInt(2) == personas.get(i).getPers_Id()) {
                    Cliente P = new Cliente(rs.getInt(1), personas.get(i));
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
}
