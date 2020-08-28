package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import dao.FornecedorDAO;
import dao.ItemEntradaDAO;
import dao.ProdutoDAO;
import model.Fornecedor;
import model.ItemEntrada;
import model.Produto;
import java.io.Serializable;

@Named("IEBean")
@ViewScoped
public class ItemEntradaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ItemEntrada iEntrada;

	private ArrayList<ItemEntrada> itens;
	private ArrayList<ItemEntrada> itensFilt;
	private ArrayList<Fornecedor> comboFornecedor;
	private ArrayList<Produto> comboProduto;
	
	public ArrayList<Produto> getComboProduto() {
		return comboProduto;
	}
	public void setComboProduto(ArrayList<Produto> comboProduto) {
		this.comboProduto = comboProduto;
	}
	public ArrayList<Fornecedor> getComboFornecedor() {
		return comboFornecedor;
	}
	public void setComboFornecedor(ArrayList<Fornecedor> comboFornecedor) {
		this.comboFornecedor = comboFornecedor;
	}
	public ItemEntrada getiEntrada() {
		return iEntrada;
	}
	public void setiEntrada(ItemEntrada iEntrada) {
		this.iEntrada = iEntrada;
	}
	public ArrayList<ItemEntrada> getItens() {
		return itens;
	}
	public void setItens(ArrayList<ItemEntrada> itens) {
		this.itens = itens;
	}
	public ArrayList<ItemEntrada> getItensFilt() {
		return itensFilt;
	}
	public void setItensFilt(ArrayList<ItemEntrada> itensFilt) {
		this.itensFilt = itensFilt;
	}
	
	public void reset() {
		iEntrada = new ItemEntrada();
	}
	public void prepararNovo() {
		try {
			iEntrada = new ItemEntrada();
			FornecedorDAO dao = new FornecedorDAO();
			comboFornecedor = dao.listar();
			ProdutoDAO pdao = new ProdutoDAO();
			comboProduto = pdao.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void pesquisa() {
		try {
			ItemEntradaDAO dao = new ItemEntradaDAO();
			itens = dao.listar();
		} catch (SQLException e) {
			msgErro("e.printStackTrace()");
			e.printStackTrace();
		}
	}
	
	public void cadastrar() {
		try {
			ItemEntradaDAO dao = new ItemEntradaDAO();
			dao.salvar(iEntrada);
			
			itens = dao.listar();
			
			msgOk("Entrada Salva.");
		} catch (SQLException e) {
			msgErro("Salve error SQLException");
			e.printStackTrace();
		}
	}
	
	
	public void excluir() {
		try {
			msgOk("Excluido com sucesso.");
			ItemEntradaDAO dao = new ItemEntradaDAO();
			dao.excluir(iEntrada);
			
			itens = dao.listar();
			
		} catch (SQLException e) {
			msgErro("DeleteErrorSQLException");
			e.printStackTrace();
		}
	}
	
	public void editar() {
		try {
			ItemEntradaDAO dao = new ItemEntradaDAO();
			dao.editar(iEntrada);
			
			itens= dao.listar();
			
			msgOk("Entrada editada.");
		} catch (SQLException e) {
			msgErro("EditErrorSQLException");
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
	
}
