package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.ItemSaidaDAO;
import dao.ProdutoDAO;
import model.ItemSaida;
import model.Produto;

@ManagedBean(name = "ISBean")
@ViewScoped
public class ItemSaidaBean {
	private ItemSaida iSaida;

	private ArrayList<ItemSaida> itens;
	private ArrayList<ItemSaida> itensFilt;
	private ArrayList<Produto> comboProduto;
	
	
	public void reset() {
		iSaida = new ItemSaida();
	}
	public void prepararNovo() {
		try {
			iSaida = new ItemSaida();
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
			ItemSaidaDAO dao = new ItemSaidaDAO();
			itens = dao.listar();
		} catch (SQLException e) {
			msgErro("e.printStackTrace()");
			e.printStackTrace();
		}
	}
	
	public void cadastrar() {
		try {
			ItemSaidaDAO dao = new ItemSaidaDAO();
			dao.salvar(iSaida);
			
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
			ItemSaidaDAO dao = new ItemSaidaDAO();
			dao.excluir(iSaida);
			
			itens = dao.listar();
			
		} catch (SQLException e) {
			msgErro("DeleteErrorSQLException");
			e.printStackTrace();
		}
	}
	
	public void editar() {
		try {
			ItemSaidaDAO dao = new ItemSaidaDAO();
			dao.editar(iSaida);
			
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
	
	
	public ItemSaida getiSaida() {
		return iSaida;
	}
	public void setiSaida(ItemSaida iSaida) {
		this.iSaida = iSaida;
	}
	public ArrayList<ItemSaida> getItens() {
		return itens;
	}
	public void setItens(ArrayList<ItemSaida> itens) {
		this.itens = itens;
	}
	public ArrayList<ItemSaida> getItensFilt() {
		return itensFilt;
	}
	public void setItensFilt(ArrayList<ItemSaida> itensFilt) {
		this.itensFilt = itensFilt;
	}
	public ArrayList<Produto> getComboProduto() {
		return comboProduto;
	}
	public void setComboProduto(ArrayList<Produto> comboProduto) {
		this.comboProduto = comboProduto;
	}
	
}
