package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import entity.EntityRigaAcquisto;
import exception.DAOException;

public class MagazzinoDao{
	
	GestoreDb db = null;
	
	public boolean readQuantitaDisponibile(Map<Integer, Integer>carrello) throws DAOException {
		
		String sql = "select quantitaDisponibile from magazzino where id_prodotto = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		db = new GestoreDb();
		try {
			conn = db.getConn();
			try {
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
					}catch(SQLException e) {
						throw new DAOException("Errore lettura risultato");
					}		
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura quantita");
			}finally {
				db.closeStatement(stmt);
				db.closeConn(conn);
			}				
		}catch(SQLException e) {
			throw new DAOException("Errore connessione DB");
		}
		return true;
	}
	
	public void decrementaScorte(ArrayList<EntityRigaAcquisto> righe) throws DAOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		db = new GestoreDb();
		String sql = "update magazzino set quantitadisponibile = quantitaDisponibile - ? where id_prodotto = ?";
		
		try {
			conn = db.getConn();
			try {
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
					try{
						conn.rollback();
					}catch(SQLException e1) {
					}
				}
				throw new DAOException("Errore decremento scorte");
			}finally {			
				db.closeStatement(stmt);
				if(conn!=null) {
					try {
						conn.setAutoCommit(true);
					}catch(SQLException e1) {
					}
				}
				db.closeConn(conn);
			}
		}catch(SQLException e) {
			throw new DAOException("Errore connessione DB");
		}
	}
	
}