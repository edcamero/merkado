/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.TipoProducto;
import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class TipoProductoDao {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public boolean Guardar(TipoProducto tipoProducto) {
        boolean resultado = false;
        String consulta = "INSERT INTO public.tipo_productos(\n"
                + "	tipr_nombre, tipr_descripcion)\n"
                + "	VALUES ( ?, ?) returning tipr_id;";
        
        try {
            con = Conexion.getConexion();
            PreparedStatement psql = con.prepareStatement(consulta);
            psql.setString(1, tipoProducto.getNombre());
            psql.setString(2, tipoProducto.getDescripcion());
            
            rs = pst.executeQuery();
            while (rs.next()) {
                tipoProducto.setId(rs.getInt("tipr_id"));
                resultado=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {

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

}