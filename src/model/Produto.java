package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
	private int id;
	private String nome;
	private int quantidade;
	private Double preco;
	
	
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		String saida = id +" - " + nome + " - " + quantidade;
		return saida;
	}
	
}
