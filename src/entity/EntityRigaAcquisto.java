package entity;

public class EntityRigaAcquisto{
	
	private int id_prodotto;
	private int quantitaAcquistata;
	private double prezzoPerProdotto;
	
	public EntityRigaAcquisto(int id,int q, double p){
		this.id_prodotto = id;
		this.quantitaAcquistata = q;
		this.prezzoPerProdotto = p;
	}
	public void setIdProdotto(int id) {
		id_prodotto = id;
	}
	public void setQuantitaAcquistata(int q) {
		quantitaAcquistata = q;
	}
	public void setPrezzoPerProdotto(double p) {
		prezzoPerProdotto = p;
	}
	public int getIdProdotto() {
		return this.id_prodotto;
	}
	public int getQuantitaAcquistata() {
		return this.quantitaAcquistata;
	}
	public double getPrezzoPerProdotto() {
		return this.prezzoPerProdotto;
	}
	
}