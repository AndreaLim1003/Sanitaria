package entity;

public class EntityMessaggioConferma{
	
	private int id_ordine;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private double prezzoTot;
	
	public EntityMessaggioConferma() {
		
	}
	
	public EntityMessaggioConferma(int i,String n,String c,String ind, String t,double p) {
		this.id_ordine = i;
		this.nome = n;
		this.cognome = c;
		this.indirizzo = ind;
		this.telefono = t;
		this.prezzoTot = p;
	}
	
	public String getNome() {
		return this.nome;
	}
	public int getId_ordine() {
		return this.id_ordine;
	}
	public double getPrezzoTot() {
		return this.prezzoTot;
	}
	public String getCognome() {
		return this.cognome;
	}
	public String getIndirizzo() {
		return this.indirizzo;
	}
	public String getTelefono() {
		return this.telefono;
	}
	public void setNome(String n) {
		nome = n;
	}
	public void setPrezzoTot(double p) {
		prezzoTot = p;
	}
	public void setId_ordine(int i) {
		id_ordine = i;
	}
	public void setCognome(String cn) {
		cognome = cn;
	}
	public void setIndirizzo(String i) {
		indirizzo = i;
	}
	public void setTelefono(String t) {
		telefono = t;
	}
}