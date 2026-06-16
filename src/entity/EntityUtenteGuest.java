package entity;

public class EntityUtenteGuest{
	
	private int id_cliente = 0;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String email;
	private String telefono;
	
	public EntityUtenteGuest(String n,String cn,String i, String e, String t) {
		
		this.nome = n;
		this.cognome = cn;
		this.indirizzo = i;
		this.email = e;
		this.telefono = t;
		
	}
	
	public int getIdCliente() {
		return this.id_cliente;
	}
	public String getNome() {
		return this.nome;
	}
	public String getCognome() {
		return this.cognome;
	}
	public String getIndirizzo() {
		return this.indirizzo;
	}
	public String getEmail() {
		return this.email;
	}
	public String getTelefono() {
		return this.telefono;
	}
	public void setNome(String n) {
		nome = n;
	}
	public void setIdCliente(int idc) {
		id_cliente = idc;
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
	public void setEmail(String e) {
		email = e;
	}
}