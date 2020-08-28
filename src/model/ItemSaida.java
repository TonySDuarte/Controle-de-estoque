package model;

public class ItemSaida {
	
	private int idSaida;
	private int quantidade;
	private Produto produto = new Produto();
	
	public int getIdSaida() {
		return idSaida;
	}
	public void setIdSaida(int idSaida) {
		this.idSaida = idSaida;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
