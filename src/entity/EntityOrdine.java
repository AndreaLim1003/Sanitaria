package entity;

import java.time.LocalDate;

public class EntityOrdine{
	
	private int id_ordine;
	private int id_cliente;
	private LocalDate dataCreazione;
	private String indirizzo;
	private String email;
	private double prezzoFinale;
	private StatoOrdine statoOrdine;
	
	public EntityOrdine() {
		
	}
	
	public EntityOrdine(int idc, LocalDate dc, String i,String e,double pf,StatoOrdine so) {
		
		this.id_cliente = idc;
		this.dataCreazione = dc;
		this.indirizzo = i;
		this.email = e;
		this.prezzoFinale = pf;
		this.statoOrdine = so;
		
	}
	
	public int getIdOrdine() {
		return this.id_ordine;
	}
	public int getIdCliente() {
		return this.id_cliente;
	}
	public LocalDate getDataCreazione() {
		return this.dataCreazione;
	}
	public String getIndirizzo() {
		return this.indirizzo;
	}
	public String getEmail() {
		return this.email;
	}
	public double getPrezzoFinale() {
		return this.prezzoFinale;
	}
	public StatoOrdine getStatoOrdine() {
		return this.statoOrdine;
	}
	
	public void setIdCliente(int idC) {
		id_cliente = idC;
	}
	public void setDataCreazione(LocalDate dc) {
		dataCreazione = dc;
	}
	public void setIndirizzo(String i) {
		indirizzo = i;
	}
	public void setEmail(String e) {
		email = e;
	}
	public void setPrezzoFinale(double pf) {
		prezzoFinale = pf;
	}
	public void setStatoOrdine(StatoOrdine s) {
		statoOrdine = s;
	}
	
}