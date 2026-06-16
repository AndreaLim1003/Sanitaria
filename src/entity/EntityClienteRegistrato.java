package entity;

public class EntityClienteRegistrato{
	
	private int id_cliente;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String email;
	private String password;
	private String telefono;
	
	public EntityClienteRegistrato(int id, String n,String cn,String i, String e, String pw, String t) {
		this.id_cliente = id;
		this.nome = n;
		this.cognome = cn;
		this.indirizzo = i;
		this.email = e;
		this.password = pw;
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
	public String getPassword() {
		return this.password;
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
	public void setPassword(String pw) {
		password = pw;
	}
	public void setTelefono(String t) {
		telefono = t;
	}
	public void setEmail(String e) {
		email = e;
	}
}