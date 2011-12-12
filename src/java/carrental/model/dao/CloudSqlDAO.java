package carrental.model.dao;

import com.google.appengine.api.rdbms.AppEngineDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Abstract cloud sql DAO
 */
abstract public class CloudSqlDAO implements DAO {

	private static final String CONNECTION_URL = "jdbc:google:rdbms://mdwcarrental:mdw-car-rental/carrenal";

	protected Connection c = null;

	protected Connection getConnection() {
		Connection conn = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());
			conn = DriverManager.getConnection(CONNECTION_URL);
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			closeConnection(conn);
		}

		return conn;
	}


	protected void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ignore) {
			}
		}
	}

	protected void closeConnection(Connection c) {
		if (c != null) {
			try {
				c.close();
			} catch (SQLException ignore) {
			}
		}
	}
}
