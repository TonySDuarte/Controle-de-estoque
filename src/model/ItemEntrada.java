package model;

import javax.persistence.Table;

@Table(name = "itemEntrada")
public class ItemEntrada {
	private int id;
	private int quantidade;
	private Double preco_at;
	private Double valor_total;
	private Produto produto = new Produto();
	private Fornecedor fornecedor = new Fornecedor();
	
	public Double getValor_total() {
		return valor_total;
	}
	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPreco_at() {
		return preco_at;
	}
	public void setPreco_at(Double preco_at) {
		this.preco_at = preco_at;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@Override
	public String toString() {
		String saida = id +" - " + preco_at + " - " + quantidade + " - " + valor_total;
		return saida;
	}
}
