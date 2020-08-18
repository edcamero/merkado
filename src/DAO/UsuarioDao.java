/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.TipoUsuario;
import VO.Usuario;
import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import merka.Fachada;

/**
 *
 * @author USUARIO
 */
public class UsuarioDao {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public UsuarioDao() {
    }

    public boolean login(String username, String password) {
        String consulta = "select * from usuarios where username=? and password=?";
        int id = 0;
        try {
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            while (rs.next()) {

                id = rs.getInt(1);

            }

            if (id > 0) {
                //System.out.println("ingreso");
                return true;
            }
            //System.out.println("usuario o contraseña erronea");
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Base de Datos:\n"
                    + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                pst.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    public boolean registrar(Usuario usuario) {

        boolean resultado = false;
        String consulta = "INSERT INTO public.usuarios( username, password, tius_id)\n"
                + "	VALUES (?, ?, ?) returning user_id;";

        try {
            con = Conexion.objConexion().getConexion();
            PreparedStatement pst = con.prepareStatement(consulta);
            pst.setString(1, usuario.getUsuario());
            pst.setString(2, usuario.getPassword());
            pst.setInt(3, usuario.getIdTipoUsuario());
            rs = pst.executeQuery();
            while (rs.next()) {
                usuario.setId(rs.getInt("user_id"));
            }

            resultado = true;
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar almacenar la información:\n"
                    + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
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

    public ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        ArrayList<TipoUsuario> listaTipo = Fachada.getInstancia().obtenerTipoUsuario();
        try {

            String consulta = "SELECT user_id, username,tius_id\n"
                    + "	FROM public.usuarios where  user_activo=true;";
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();

            while (rs.next()) {
                TipoUsuario tipo = null;
                for (TipoUsuario t : listaTipo) {
                    if (t.getId() == rs.getInt("tius_id")) {
                        tipo = t;
                        break;
                    }
                }
                Usuario usuario = new Usuario(rs.getInt("user_id"), rs.getString("username"), tipo);
                lista.add(usuario);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Base de Datos:\n"
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

    public boolean actualizarTipo(Usuario usuario) {
        boolean respuesta = false;
        try {

            String consulta = "UPDATE public.usuarios\n"
                    + "	SET   tius_id=?\n"
                    + "	WHERE user_id=?;";

            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setInt(1, usuario.getIdTipoUsuario());
            pst.setInt(2, usuario.getId());
            boolean execute = pst.execute();
            respuesta = true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return respuesta;
    }

    public boolean cambiarPassword(Usuario usuario) {

        boolean respuesta = false;
        String consulta = "UPDATE public.usuarios\n"
                + "	SET   password=?\n"
                + "	WHERE user_id=?;";
        try {
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, usuario.getPassword());
            pst.setInt(2, usuario.getId());
            boolean execute = pst.execute();

            respuesta = true;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return respuesta;
    }

    public boolean eliminarUsuario(int id) {
        boolean respuesta = false;
        String consulta = "UPDATE public.usuarios\n"
                + "	SET  user_activo=false\n"
                + "	WHERE user_id=?;";
        try {
            con = Conexion.objConexion().getConexion();
            pst = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            boolean execute = pst.execute();

            respuesta = true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return respuesta;
    }
}
