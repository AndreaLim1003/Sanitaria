package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestoreDb{
	
	private String nomeDatabase = "./databaseSanitaria";
	private String url = "jdbc:h2:";
	private String admin = "sa";
	private String pw = "";
	
	public Connection getConn() throws SQLException {
		return DriverManager.getConnection(url+nomeDatabase,admin,pw);
	}
	public ResultSet getResultSet(PreparedStatement stmt) throws SQLException {
		return stmt.executeQuery();
	}
	public PreparedStatement getPreparedStatement(Connection c,String sql) throws SQLException {
		return c.prepareStatement(sql);
	}
	
	public PreparedStatement getStatementConRitorno(Connection c, String sql) throws SQLException {
		return c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	}
	
	public void closeConn(Connection c) throws SQLException {
		if(c!= null) {
			c.close();
		}
	}
	public void closeStatement(PreparedStatement stmt) throws SQLException {
		if(stmt!= null) {
			stmt.close();
		}
	}
	public void closeResultSet(ResultSet r) throws SQLException {
		if(r!= null) {
			r.close();
		}
	}
}