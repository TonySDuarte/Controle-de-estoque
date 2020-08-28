package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.FornecedorDAO;
import model.Fornecedor;

@ManagedBean(name = "FBean")
@ViewScoped
public class FornecedorBean {
	
	private Fornecedor fornecedor;
	
	private ArrayList<Fornecedor> itens;
	private ArrayList<Fornecedor> itensFilt;
	
	
	public void reset() {
		fornecedor = new Fornecedor();
	}

//	public void catchRow() {
//		fornecedor = itens.getRowData();
//	}
	
	public void cadastrar() {
		try {
			FornecedorDAO dao = new FornecedorDAO();
			dao.salvar(fornecedor);
			
			itens = dao.listar();
			msgOk(fornecedor.getNome() + " foi salvo na lista de fornecedores.");
			
		} catch (SQLException e) {
			msgErro("Salve error SQLException");
			e.printStackTrace();
		}
	}
	
	
	public void excluir() {
		try {
			msgOk(fornecedor.getNome() + " foi excluido da lista de fornecedores.");
			FornecedorDAO dao = new FornecedorDAO();
			dao.excluir(fornecedor);
			
			itens = dao.listar();
			
		} catch (SQLException e) {
			msgErro("DeleteErrorSQLException");
			e.printStackTrace();
		}
	}
	
	public void editar() {
		try {
			FornecedorDAO dao = new FornecedorDAO();
			dao.editar(fornecedor);
			
			itens= dao.listar();
			
			msgOk(fornecedor.getNome() + " foi editado.");
		} catch (SQLException e) {
			msgErro("EditErrorSQLException");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void pesquisa() {
		try {
			FornecedorDAO dao = new FornecedorDAO();
			itens = dao.listar();
		} catch (SQLException e) {
			msgErro("e.printStackTrace()");
			e.printStackTrace();
		}
	}
	
	public static void msgOk(String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}
	
	public static void msgErro(String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public ArrayList<Fornecedor> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedor> itens) {
		this.itens = itens;
	}

	public ArrayList<Fornecedor> getItensFilt() {
		return itensFilt;
	}

	public void setItensFilt(ArrayList<Fornecedor> itensFilt) {
		this.itensFilt = itensFilt;
	}

	
}
