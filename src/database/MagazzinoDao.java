package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import entity.EntityRigaAcquisto;

public class MagazzinoDao{
	
	GestoreDb db = null;
	
	public boolean readQuantitaDisponibile(Map<Integer, Integer>carrello) throws SQLException {
		
		String sql = "select quantitaDisponibile from magazzino where id_prodotto = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		db = new GestoreDb();
		try {
			conn = db.getConn();
			stmt = db.getPreparedStatement(conn, sql);
			
			for(Map.Entry<Integer, Integer> entry: carrello.entrySet()) {
				int id_prod = entry.getKey();
				int quantita = entry.getValue();
				
				stmt.setInt(1, id_prod);
				try(ResultSet r = db.getResultSet(stmt)){
					if(r.next()) {
						if(quantita>r.getInt(1)) {
							return false;
						}
					}
				}
				
			}			
		}finally {
			db.closeStatement(stmt);
			db.closeConn(conn);
		}
		
			
		
		return true;
	}
	
	public void decrementaScorte(ArrayList<EntityRigaAcquisto> righe) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		db = new GestoreDb();
		String sql = "update magazzino set quantitadisponibile = quantitaDisponibile - ? where id_prodotto = ?";
		
		try {
			conn = db.getConn();
			conn.setAutoCommit(false);
			stmt = db.getPreparedStatement(conn, sql);
			for(int i=0;i<righe.size();i++) {
				EntityRigaAcquisto e = righe.get(i);
				stmt.setInt(1, e.getQuantitaAcquistata());
				stmt.setInt(2, e.getIdProdotto());
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
		}catch(SQLException e) {
			if(conn!=null) {
				conn.rollback();
			}
			throw e;
		}finally {			
			if(stmt != null) db.closeStatement(stmt);
			if(conn != null) db.closeConn(conn);
		}
	}
	
}