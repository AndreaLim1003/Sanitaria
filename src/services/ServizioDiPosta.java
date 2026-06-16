package services;

import entity.EntityMessaggioConferma;

public class ServizioDiPosta{
	
	
	public boolean inviaMsgConferma(String email,EntityMessaggioConferma msg) {
		
		/*
		 * LOGICA PER LA CONNESSIONE ALLA MAILBOX
		 */
		
		int id_ordine = msg.getId_ordine();
		String nome = msg.getNome();
		String cognome = msg.getCognome();
		String indirizzo = msg.getIndirizzo();
		String telefono = msg.getTelefono();
		double prezzoTot = msg.getPrezzoTot();
		
		
		String corpoMail = "Gentile " + nome + " " + cognome + ", Le comunichiamo che l'ordine numero: "+ id_ordine +", dall'importo totale di: "+ prezzoTot +" è stato effttuato con successo."
				+ "La spedizione presso l'indirizzo "+ indirizzo +"partirà nel minor tempo possibile."+"\nIl corriere La contatterà via telefonica al numero: "+telefono;
		
		/*
		 * GENERAZIONE MAIL
		 */
		
		return true; //simulazione riuscita
	}
	
}