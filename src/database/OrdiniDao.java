package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import entity.EntityOrdine;
import entity.EntityRigaAcquisto;
import entity.StatoOrdine;
import exception.DAOException;

public class OrdiniDao{
	
	GestoreDb db = null;
	
	public ArrayList<Integer> readIdOrdineFromEmail(String email) throws DAOException {
		db = new GestoreDb();
		ArrayList<Integer> ordini = new ArrayList<Integer>();
		String sql = "select id_ordine from ordini where email = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet r = null;
		
		try {
			conn = db.getConn();
			try {
				stmt = db.getPreparedStatement(conn, sql);
				stmt.setString(1, email);
				r = db.getResultSet(stmt);
				
				while(r.next()) {
					ordini.add(r.getInt(1));
				}
				return ordini;
				
			}catch(SQLException e) {
				throw new DAOException("Errore lettura id ordine");
			}finally {
				db.closeResultSet(r);
				db.closeStatement(stmt);
				db.closeConn(conn);
			}	
		}catch(SQLException e) {
			throw new DAOException("Errore connessione DB");
		}
		
	}
	
	public int inserisciOrdine(EntityOrdine o) throws DAOException {
		
		db = new GestoreDb();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into Ordini(id_cliente,dataCreazione,indirizzo,email,stato,prezzoFinale) values (?,?,?,?,?,?)";
		try {
			conn = db.getConn();
			try {
				conn.setAutoCommit(false);
				stmt = db.getStatementConRitorno(conn, sql);
				stmt.setInt(1, o.getIdCliente());
				stmt.setObject(2, o.getDataCreazione());
				stmt.setString(3, o.getIndirizzo());
				stmt.setString(4, o.getEmail());
				StatoOrdine stato = o.getStatoOrdine();
				String statostring = stato.toString();
				stmt.setString(5, statostring);
				stmt.setDouble(6, o.getPrezzoFinale());
				
				stmt.executeUpdate();
				int idGen = -1;
				try(ResultSet genkey = stmt.getGeneratedKeys()){
					if(genkey.next()) {
						idGen = genkey.getInt(1);
					}
				}
				conn.commit();
				return idGen;
			}catch(SQLException e) {
				if(conn!=null) {
					try{
						conn.rollback();
					}catch(SQLException e1) {
					}
				}
				throw new DAOException("Errore creazione ordine");
			}finally {			
				db.closeStatement(stmt);
				if(conn!=null) {
					try{
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
	
	public void inserisciDettagliOrdine(int idOrdine, ArrayList<EntityRigaAcquisto> righe) throws DAOException {
		String sql = "INSERT INTO DettaglioOrdini (id_ordine,id_prodotto,quantitaSelezionata,prezzoAcquisto) values (?,?,?,?)";
		db = new GestoreDb();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn=db.getConn();
			try {
				conn.setAutoCommit(false);
				stmt = db.getPreparedStatement(conn, sql);
				
				for(EntityRigaAcquisto riga : righe) {
					stmt.setInt(1, idOrdine);
					stmt.setInt(2, riga.getIdProdotto());
					stmt.setInt(3, riga.getQuantitaAcquistata());
					stmt.setDouble(4, riga.getPrezzoPerProdotto());
					
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
				throw new DAOException("Errore inserimento riga ordine");
			}finally {			
				db.closeStatement(stmt);
				if(conn!=null) {
					try{
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
	
	public ArrayList<EntityRigaAcquisto> generaRigheOrdine(Map<Integer,Integer> CarrelloFormattato) throws DAOException{
		ArrayList<EntityRigaAcquisto> righe = new ArrayList<EntityRigaAcquisto>();
		EntityRigaAcquisto riga = null;
		
		db = new GestoreDb();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "select prezzoUnitario from prodotti where id_prodotto = ?";
	
		try {
			conn = db.getConn();
			try {
				
				stmt = db.getPreparedStatement(conn, sql);		
				for(Map.Entry<Integer, Integer> entry:CarrelloFormattato.entrySet()) {
					
					int id = entry.getKey();
					int quantita = entry.getValue();
					stmt.setInt(1,id);
					try(ResultSet r = db.getResultSet(stmt)){
						if(!r.next()) {
							return null;
						}else {
							double prezzo = r.getDouble(1);
							riga = new EntityRigaAcquisto(id,quantita,prezzo);
							righe.add(riga);
						}
					}catch(SQLException e) {
						throw new DAOException("Errore lettura risultato");
					}
				}
				return righe;
			}catch(SQLException e) {
				System.out.println("ERRORE SQL REALE: " + e.getMessage());
				e.printStackTrace();
				throw new DAOException("Errore crezione riga ordine");
			}finally {
			db.closeStatement(stmt);
			db.closeConn(conn);		
			}
		}catch(SQLException e) {
			throw new DAOException("Errore connessione DB");
		}
			
	}
	
	
	
}