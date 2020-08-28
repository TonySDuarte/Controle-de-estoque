package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {
	@Id
	private int id_f;
	private String nome;
	private String cnpj;
	
	
	public int getId_f() {
		return id_f;
	}
	public void setId_f(int id_f) {
		this.id_f = id_f;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	@Override
	public String toString() {
		String saida = id_f +" - " + nome + " - " + cnpj;
		return saida;
	}
}
