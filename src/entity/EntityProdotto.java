package entity;

public class EntityProdotto{
	
	private int id_prodotto;
	private Categoria categoria;
	private String nomeProdotto;
	private String descrizione;
	private double prezzoUnitario;
	
	public EntityProdotto(Categoria c, String np, String d, double p) {
		this.categoria = c;
		this.nomeProdotto = np;
		this.descrizione = d;
		this.prezzoUnitario = p;
	}
	
	public int getIdPrdotto() {
		return this.id_prodotto;
	}
	public Categoria getCategoria() {
		return this.categoria;
	}
	public String getNomeProdotto() {
		return this.nomeProdotto;
	}
	public String getDescrizione() {
		return this.descrizione;
	}
	public double getPrezzoUnitario() {
		return this.prezzoUnitario;
	}
	
	public void setIdProdotto(int id) {
		id_prodotto = id;
	}
	public void setCategoria(Categoria c) {
		categoria = c;
	}
	public void setNomeProdotto(String np) {
		nomeProdotto = np;
	}
	public void setDescrizione(String d) {
		descrizione = d;
	}
	public void setPrezzoUnitario(double p) {
		prezzoUnitario = p;
	}
}