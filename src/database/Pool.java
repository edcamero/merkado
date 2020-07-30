package database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;


import org.apache.commons.dbcp2.BasicDataSource;

public class Pool {
	private static BasicDataSource ds = null;

	public static DataSource getDataSource() {
		if (ds == null) {
			ds = new BasicDataSource();
			ds.setDriverClassName("org.postgresql.Driver");
			ds.setUsername("postgres");
			ds.setPassword("1234");
			ds.setUrl("jdbc:postgresql://localhost:5432/merka");
			// Definimos el tamano del pool de conexiones
			ds.setInitialSize(2);// 2 Conexiones iniciales
			ds.setMaxIdle(10);
			ds.setMaxTotal(2);
			ds.setMaxWaitMillis(5000);

		}
		return ds;
	}

	public static Connection getConexion() throws SQLException {
		return getDataSource().getConnection();
	}
}
