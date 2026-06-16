package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import exception.DAOException;

public class ProdottiDao{
	
	GestoreDb db = null;
	
	public Map<Integer,Integer> getIdProdotto(Map<String,Integer> carrello) throws DAOException {
		//---FUNZIONE FORMATTATRICE DEL CARRELLO---
		db = new GestoreDb();
		String sql = "Select id_Prodotto from prodotti where nomeProdotto = ?";
		Map<Integer,Integer> carrelloDiOutput = new HashMap<Integer,Integer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {	
			conn = db.getConn();
			try {
				stmt = db.getPreparedStatement(conn, sql);
				for(Map.Entry<String,Integer> entry : carrello.entrySet()) {
					String prodotto = entry.getKey();
					Integer quantita = entry.getValue();
					
					stmt.setString(1, prodotto);
					
					try(ResultSet r = db.getResultSet(stmt)){
						if(r.next()) {
							int id_prod = r.getInt(1);
							carrelloDiOutput.put(id_prod, quantita);
						}else {
							throw new SQLException("Prodotto non trovato a catalogo: " + prodotto);
						}
					}
				}	
				return carrelloDiOutput;	
			}catch(SQLException e) {
				throw new DAOException("Errore formattazione carrello");
			}finally {
				db.closeStatement(stmt);
				db.closeConn(conn);
			}
		}catch(SQLException e){
			throw new DAOException("Errore connessione DB");
		}
	}
	
	public double getPrezzoTotaleCarrello(Map<Integer,Integer> carrello) throws DAOException {
		db = new GestoreDb();
		String sql = "Select prezzoUnitario from prodotti where id_prodotto = ?";
		double prezzoTot = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConn();
			try {
				stmt = db.getPreparedStatement(conn, sql);
				for(Map.Entry<Integer, Integer> entry : carrello.entrySet()) {
					int prodotto = entry.getKey();
					int quantita = entry.getValue();
					stmt.setInt(1, prodotto);
					try(ResultSet r = db.getResultSet(stmt)){
						if(r.next()) {
							double prezzoUnitario = r.getDouble(1);
							prezzoTot = prezzoTot + quantita*prezzoUnitario;							
						}
					}
				}
				return prezzoTot;
				
			}catch(SQLException e) {
				throw new DAOException("Errore ottenimento prezzo carrello");
			}finally {
				db.closeStatement(stmt);
				db.closeConn(conn);
			}
		}catch(SQLException e) {
			throw new DAOException("Errore connessione DB");
		}
		
	}
	
	public int getIdProdotto(String nome) throws  DAOException{
		db = new GestoreDb();
		String sql = "Select id_Prodotto from prodotti where nomeProdotto = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {	
			conn = db.getConn();
			try {
				stmt = db.getPreparedStatement(conn, sql);
				stmt.setString(1, nome);
				try(ResultSet r = db.getResultSet(stmt)){
					if(r.next()) {
						return r.getInt(1);
					}else return -1;	
				}catch(SQLException e) {
					throw new DAOException("Errore lettura Risultati");
				}
			}catch(SQLException e){
				throw new DAOException("Errore lettura DB");
			}finally {
				db.closeStatement(stmt);
				db.closeConn(conn);
			}
		}catch(SQLException e){
				throw new DAOException("Errore connessione DB");
		}
	}
	
	
}