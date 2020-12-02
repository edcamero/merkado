/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.TipoUsuario;
import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class TipoUsuarioDao {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public ArrayList<TipoUsuario> obtenerTipoUsuario() {
        ArrayList<TipoUsuario> lista = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM public.tipo_usuarios ORDER BY tius_id";
            //retorna la conexion del pool de conexiones
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                TipoUsuario tipoUsuario = new TipoUsuario(rs.getInt("tius_id"),
                        rs.getString("tius_nombre"), rs.getString("tius_descripcion"));
                lista.add(tipoUsuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TipoUsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Base de Datos:\n"
                    + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                pst.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        }
        return lista;
    }

}
