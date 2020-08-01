package database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    private static BasicDataSource ds = null;

    public static DataSource getDataSource() {
        if (ds == null) {
            ds = new BasicDataSource();

            ds.setDriverClassName("org.postgresql.Driver");

            ds.setUsername("postgres");
            ds.setPassword("fuentes");
            ds.setUrl("jdbc:postgresql://localhost:5432/merka");
            // Definimos el tamano del pool de conexiones
            ds.setInitialSize(1);// 2 Conexiones iniciales
            ds.setMaxIdle(2);
            ds.setMaxTotal(2);
            ds.setMaxWaitMillis(0);
            System.out.println("conecto");
        }
        return ds;
    }

    public static Connection getConexion() throws SQLException {
        return getDataSource().getConnection();
    }
}
