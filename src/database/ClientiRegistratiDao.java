package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntityClienteRegistrato;
import exception.DAOException;

public class ClientiRegistratiDao{
	
	GestoreDb db = new GestoreDb();
	
	public EntityClienteRegistrato login(String email,String password) throws DAOException{
		String sql = "Select id_cliente,nome,cognome,indirizzo,telefono from clientiregistrati where email = ? and password = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet r = null;
		try {
			conn = db.getConn();
			try {
				stmt = db.getPreparedStatement(conn, sql);
				stmt.setString(1, email);
				stmt.setString(2, password);
				r = db.getResultSet(stmt);
				if(r.next()) {
					int id = r.getInt("id_cliente");
					String nome = r.getString("nome");
					String cognome = r.getString("cognome");
					String indirizzo = r.getString("indirizzo");
					String telefono = r.getString("telefono");
					return new EntityClienteRegistrato(id,nome,cognome,indirizzo,email,password,telefono);
				}else {
					return null;
				}			
			}catch(SQLException e){
				throw new DAOException("errore lettura cliente registrato");
			}finally{
				db.closeResultSet(r);
				db.closeStatement(stmt);
				db.closeConn(conn);
			}			
		}catch(SQLException e) {
			throw new DAOException("Errore connessione DB");
		}
	}
	
	public boolean registrati(String nome,String cognome, String email, String password, String indirizzo,String telefono) throws DAOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet r = null;
		String sqlricerca = "Select id_cliente from clientiregistrati where email = ?";
		String sqlInserimento = "Insert into clientiRegistrati(nome,cognome,indirizzo,telefono,email,password) values (?,?,?,?,?,?)";
		try {
			conn = db.getConn();
			try {
				stmt = db.getPreparedStatement(conn, sqlricerca);
				stmt.setString(1, email);
				r = db.getResultSet(stmt);
				if(r.next()) {
					//Utente gia registrato
					return false;
				}
				stmt2 = db.getPreparedStatement(conn, sqlInserimento);
				stmt2.setString(1, nome);
				stmt2.setString(2, cognome);
				stmt2.setString(3, indirizzo);
				stmt2.setString(4, telefono);
				stmt2.setString(5, email);
				stmt2.setString(6, password);
				stmt2.executeUpdate();
				return true;
				
			}catch(SQLException e){
				throw new DAOException("Errore Ricerca/Inserimento cliente");
			}finally {
				db.closeResultSet(r);
				db.closeStatement(stmt2);
				db.closeStatement(stmt);
				db.closeConn(conn);
			}	
		}catch(SQLException e){
			throw new DAOException("Errore Connessione DB");
		}
		
	}
	
}