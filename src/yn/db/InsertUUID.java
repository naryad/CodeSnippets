package yn.db;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.naming.NamingException;

public class InsertUUID {
	/**
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	static Connection getConnection() throws SQLException {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/test";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "Hack2Forbes2014";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO uuids (uuid) values ('" + UUID.randomUUID() + "')");
			
		} finally {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			
		}
	}
	
	private static byte [] getIdAsBytes() {
		UUID u = UUID.randomUUID();
		ByteBuffer bb = ByteBuffer.allocate(16);

		bb.putLong(u.getMostSignificantBits()).putLong(u.getLeastSignificantBits());
		return bb.array();	
	}
}
