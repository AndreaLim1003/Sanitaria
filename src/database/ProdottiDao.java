package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProdottiDao{
	
	GestoreDb db = null;
	
	public Map<Integer,Integer> getIdProdotto(Map<String,Integer> carrello) throws SQLException {
		//---FUNZIONE FORMATTATRICE DEL CARRELLO---
		String sql = "Select id_Prodotto from prodotti where nomeProdotto = ?";
		Map<Integer,Integer> carrelloDiOutput = new HashMap<Integer,Integer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {	
			conn = db.getConn();
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
		}finally {
			db.closeStatement(stmt);
			db.closeConn(conn);
		}
	}
	
	public double getPrezzoTotaleCarrello(Map<Integer,Integer> carrello) throws SQLException {
		
		String sql = "Select prezzoUnitario from prodotti where id_prodotto = ?";
		double prezzoTot = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConn();
			stmt = db.getPreparedStatement(conn, sql);
			for(Map.Entry<Integer, Integer> entry : carrello.entrySet()) {
				int prodotto = entry.getKey();
				int quantita = entry.getValue();
				stmt.setInt(1, prodotto);
				try(ResultSet r = db.getResultSet(stmt)){
					double prezzoUnitario = r.getInt(1);
					prezzoTot = prezzoTot + quantita*prezzoUnitario;
				}
			}
			return prezzoTot;
			
		}finally {
			db.closeStatement(stmt);
			db.closeConn(conn);
		}
		
	}
	
	
}