package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.ProdutoDAO;
import model.Produto;

@ManagedBean(name = "PBean")
@ViewScoped
public class ProdutoBean {

	private Produto produto;

	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFilt;

	public void reset() {
		produto = new Produto();
	}

	// public void catchRow() {
	// produto = itens.getRowData();
	// }

	public void cadastrar() {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			if (dao.validar(this.produto.getNome()) == false) {

				try {
					dao.salvar(produto);

					itens = dao.listar();
					msgOk(produto.getNome() + " foi salvo no estoque.");

				} catch (SQLException e) {
					msgErro("Erro ao cadastrar");
					e.printStackTrace();
				}
			}else {
				msgErro("Já existe um produto com esse nome.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void excluir() {
		try {
			msgOk(produto.getNome() + " foi excluido do estoque.");
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(produto);

			itens = dao.listar();

		} catch (SQLException e) {
			msgErro("Error");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.editar(produto);

			itens = dao.listar();

			msgOk(produto.getNome() + " foi editado.");
		} catch (SQLException e) {
			msgErro("Erro na edição");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void pesquisa() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			itens = dao.listar();
		} catch (SQLException e) {
			msgErro("e.printStackTrace()");
			e.printStackTrace();
		}
	}

	public static void msgOk(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}

	public static void msgErro(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public ArrayList<Produto> getItensFilt() {
		return itensFilt;
	}

	public void setItensFilt(ArrayList<Produto> itensFilt) {
		this.itensFilt = itensFilt;
	}

}
