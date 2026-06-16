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

public class OrdiniDao{
	
	GestoreDb db = null;
	
	public ArrayList<Integer> readIdOrdineFromEmail(String email) throws SQLException {
		db = new GestoreDb();
		ArrayList<Integer> ordini = new ArrayList<Integer>();
		String sql = "select id_ordine from ordini where email = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet r = null;
		
		try {
			conn = db.getConn();
			stmt = db.getPreparedStatement(conn, sql);
			stmt.setString(1, email);
			r = db.getResultSet(stmt);
			
			while(r.next()) {
				ordini.add(r.getInt(1));
			}
			return ordini;
			
		}finally {
			db.closeResultSet(r);
			db.closeStatement(stmt);
			db.closeConn(conn);
			
		}
		
	}
	
	public int inserisciOrdine(EntityOrdine o) throws SQLException {
		
		db = new GestoreDb();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into Ordini(id_cliente,dataCreazione,indirizzo,email,stato,prezzoFinale) values (?,?,?,?,?,?)";
		try {
			conn = db.getConn();
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
				conn.rollback();
			}
			throw e;
		}finally {			
			db.closeStatement(stmt);
			db.closeConn(conn);
			conn.setAutoCommit(true);
		}
		
		
	}
	
	public void inserisciDettagliOrdine(int idOrdine, ArrayList<EntityRigaAcquisto> righe) throws SQLException {
		String sql = "INSERT INTO DettaglioOrdini (id_ordine,id_prodotto,quantitaSelezionata,prezzoAcquisto) values (?,?,?,?)";
		db = new GestoreDb();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn=db.getConn();
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
				conn.rollback();
			}
			throw e;
		}finally {			
			db.closeStatement(stmt);
			db.closeConn(conn);
			conn.setAutoCommit(true);
		}
		
		
	}
	
	public ArrayList<EntityRigaAcquisto> generaRigheOrdine(Map<Integer,Integer> CarrelloFormattato) throws SQLException{
		ArrayList<EntityRigaAcquisto> righe = new ArrayList<EntityRigaAcquisto>();
		EntityRigaAcquisto riga = null;
		
		db = new GestoreDb();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "select prezzoU from prodotti where id_prodotto = ?";
	
		try {
			conn = db.getConn();
			stmt = db.getPreparedStatement(conn, sql);		
			for(Map.Entry<Integer, Integer> entry:CarrelloFormattato.entrySet()) {
				
				int id = entry.getKey();
				int quantita = entry.getValue();
				stmt.setInt(1,id);
				try(ResultSet r = db.getResultSet(stmt)){;
				if(!r.next()) {
					return null;
				}else {
					double prezzo = r.getDouble(1);
					riga = new EntityRigaAcquisto(id,quantita,prezzo);
					righe.add(riga);
				}
				}
			}
			return righe;
		}finally {
			db.closeStatement(stmt);
			db.closeConn(conn);		
			conn.setAutoCommit(true);
		}
	}
	
	
	
}