package control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import database.MagazzinoDao;
import database.OrdiniDao;
import database.ProdottiDao;
import entity.EntityClienteRegistrato;
import entity.EntityMessaggioConferma;
import entity.EntityOrdine;
import entity.EntityRigaAcquisto;
import entity.EntityUtenteGuest;
import entity.StatoOrdine;
import exception.DAOException;
import services.ServizioDiPosta;

public class GestoreSanitaria{
	// ---SCHEDAUTENTE---
	int id_cliente;
	String email;
	String indirizzo;
	String nome;
	String cognome;
	String telefono;
	// ---OGGETTI DATABASE---
	MagazzinoDao scorte = null;
	ProdottiDao catalogo = null;
	OrdiniDao dbOrdini = null;
	// ---ENTITA'---
	EntityOrdine ordine = null;
	EntityMessaggioConferma msgConferma = null;
	ServizioDiPosta sdp = null;
	Map<String,Integer>carrello = null;
	
	public Map<Boolean,EntityMessaggioConferma> acquistaProdotti(Map<String, Integer>carrello,EntityClienteRegistrato ec,EntityUtenteGuest ug) throws InterruptedException, DAOException {
		if(carrello.isEmpty()) {
			return null;
		}
		
		if(ec != null) {
			popolaSchedaUtenteReg(ec);
		}else {
			popolaSchedaUtenteGuest(ug);
		}
		
		ordine = new EntityOrdine();
		LocalDate dc = LocalDate.now();
		// ---CREAZIONE ENTITY ORDINE / FORMATTAZIONE CARRELLO TRAMITE ID---
		ordine.setEmail(email);
		ordine.setDataCreazione(dc);
		ordine.setIdCliente(id_cliente);
		ordine.setIndirizzo(indirizzo);
		ordine.setStatoOrdine(StatoOrdine.IN_PREPARAZIONE);

		catalogo = new ProdottiDao();
		Map<Integer,Integer>CarrelloFormattato = catalogo.getIdProdotto(carrello);
		double prezzoFinale = catalogo.getPrezzoTotaleCarrello(CarrelloFormattato);
		ordine.setPrezzoFinale(prezzoFinale);
		// ---VERIFICA QUANTITA IN MAGAZZINO---
		scorte = new MagazzinoDao();
		boolean esito = scorte.readQuantitaDisponibile(CarrelloFormattato);
		if(!esito) {
			ordine = null;
			throw new RuntimeException("Quantita prodotto ricercata non disponibile");
		}
		// ---CREAZIONE NUOVO ORDINE / INSERIMENTO DETTAGLI ORDINI / DECREMENTO SCORTE---
		dbOrdini = new OrdiniDao();
		int idNuovoOrdine = dbOrdini.inserisciOrdine(ordine);
		ArrayList<EntityRigaAcquisto> righeOrdini = dbOrdini.generaRigheOrdine(CarrelloFormattato);
		dbOrdini.inserisciDettagliOrdine(idNuovoOrdine, righeOrdini);
		scorte.decrementaScorte(righeOrdini);
		// ---CREAZIONE MESSAGGIO CONFERMA / TENTATIVO DI INVIO E-MAIL---
		msgConferma = new EntityMessaggioConferma(idNuovoOrdine,nome,cognome,indirizzo,telefono,prezzoFinale);
		sdp = new ServizioDiPosta();
		boolean esitoInvio = false;
		int tentativi = 0;
		while(esitoInvio == false && tentativi<20) {
			esitoInvio = sdp.inviaMsgConferma(email, msgConferma);	
			tentativi++;
		}
		// il valore di ritorno comprende un flag che farà capire al boundary cosa mostrare a video
		Map<Boolean,EntityMessaggioConferma> esitoAcquisto = new HashMap<Boolean,EntityMessaggioConferma>();
		
		if(!esitoInvio) {
			esitoAcquisto.put(false, msgConferma);
			
			Thread threadEmail = new Thread(()->{
				try {
					Thread.sleep(15*60*1000);
					sdp.inviaMsgConferma(email, msgConferma); //ultimo tentativo dopo 15 min tramite un thread figlio			
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			});
			
			threadEmail.setDaemon(true);
			threadEmail.start();
			
			
			return esitoAcquisto;
		}else {
			esitoAcquisto.put(true, msgConferma);
			return esitoAcquisto;
		}
	
		
	}
	
	private void popolaSchedaUtenteReg(EntityClienteRegistrato ec) {
		id_cliente = ec.getIdCliente();
		email = ec.getEmail();
		indirizzo = ec.getIndirizzo();
		nome = ec.getNome();
		cognome = ec.getCognome();
		telefono = ec.getTelefono();
	}
	private void popolaSchedaUtenteGuest(EntityUtenteGuest ug) {
		id_cliente = ug.getIdCliente();
		email = ug.getEmail();
		indirizzo = ug.getIndirizzo();
		nome = ug.getNome();
		cognome = ug.getCognome();
		telefono = ug.getTelefono();
	}
	
	public Map<String,Integer> selezionaProdotto(String nomeP,int quantita) throws DAOException {
		catalogo = new ProdottiDao();
		carrello = new HashMap<String,Integer>();
		int id = catalogo.getIdProdotto(nomeP);
		if(id == -1) {
			return carrello;
		}
		carrello.put(nomeP, quantita);
		return carrello;
	}
	
}