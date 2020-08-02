package database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    private static BasicDataSource ds;
    private static Conexion conexion;
    private static Connection connection;

    public Conexion() {
    }

    public Conexion objConexion() {
        if (conexion == null) {
            conexion = new Conexion();
            return conexion;
        } else {
            return conexion;
        }
    }

    public DataSource getDataSource() {
        if (conexion.ds == null) {
            conexion.ds = new BasicDataSource();
            conexion.ds.setDriverClassName("org.postgresql.Driver");
            conexion.ds.setUsername("postgres");
            conexion.ds.setPassword("fuentes");
            conexion.ds.setUrl("jdbc:postgresql://localhost:5432/merka");
            // Definimos el tamano del pool de conexiones
            conexion.ds.setInitialSize(1);// 2 Conexiones iniciales
            conexion.ds.setMaxIdle(2);
            conexion.ds.setMaxTotal(2);
            conexion.ds.setMaxWaitMillis(1000);
            return conexion.ds;
        }
        return conexion.ds;
    }

    public Connection getConexion() throws SQLException {
        connection = conexion.getDataSource().getConnection();
        return connection;
    }

    public void getClose() {
        try {
            connection.close();
        } catch (SQLException ex) {

        }
    }
}
