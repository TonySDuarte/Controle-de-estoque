package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factory.ConexaoFactory;
import model.Fornecedor;
import model.ItemEntrada;
import model.Produto;

public class ItemEntradaDAO {
	public void salvar(ItemEntrada i) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO itementrada ");
		sql.append("(quantidade, preco_at, produto_idproduto, valor_total, fornecedor_id_f) ");
		sql.append("VALUES (?, ?, ?, ?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, i.getQuantidade());
		comando.setDouble(2, i.getPreco_at());
		comando.setInt(3, i.getProduto().getId());
		comando.setDouble(4, i.getValor_total());
		comando.setInt(5, i.getFornecedor().getId_f());
		comando.executeUpdate();
	}

	public ArrayList<ItemEntrada> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT i.id, i.quantidade, i.preco_at, i.valor_total, p.id, p.nome, p.quantidade, p.preco, f.id_f, f.nome, f.cnpj ");
		sql.append("FROM itementrada i ");
		sql.append("INNER JOIN produto p ON p.id = i.produto_idproduto ");
		sql.append("INNER JOIN fornecedor f ON f.id_f = i.fornecedor_id_f");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<ItemEntrada> lista = new ArrayList<ItemEntrada>();

		while (resultado.next()) {
			Produto p = new Produto();
			p.setId(resultado.getInt("p.id"));
			p.setNome(resultado.getString("p.nome"));
			p.setQuantidade(resultado.getInt("p.quantidade"));
			p.setPreco(resultado.getDouble("p.preco"));

			Fornecedor f = new Fornecedor();
			f.setId_f(resultado.getInt("f.id_f"));
			f.setNome(resultado.getString("f.nome"));
			f.setCnpj(resultado.getString("f.cnpj"));

			ItemEntrada i = new ItemEntrada();
			i.setId(resultado.getInt("i.id"));
			i.setQuantidade(resultado.getInt("i.quantidade"));
			i.setPreco_at(resultado.getDouble("i.preco_at"));
			i.setValor_total(resultado.getDouble("i.valor_total"));
			i.setProduto(p);
			i.setFornecedor(f);

			lista.add(i);
		}

		return lista;

	}

	public void excluir(ItemEntrada i) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM itementrada ");
		sql.append("WHERE id = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, i.getId());
		comando.executeUpdate();
	}
	
	public void editar(ItemEntrada i) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE itementrada ");
		sql.append("SET quantidade = ?, preco_at = ?, produto_idproduto = ?, valor_total = ?, fornecedor_id_f = ? ");
		sql.append("WHERE id = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, i.getQuantidade());
		comando.setDouble(2, i.getPreco_at());
		comando.setInt(3, i.getProduto().getId());
		comando.setDouble(4, i.getValor_total());
		comando.setInt(5, i.getFornecedor().getId_f());
		comando.setInt(6, i.getId());
		comando.executeUpdate();
		
	}

	public static void main(String[] args) throws SQLException {

		/*
		 * Listar
		 * 
		 * ItemEntrada i = new ItemEntrada(); i.setPreco_at(3.0); i.setQuantidade(1);
		 * i.setValor_total(3.0);
		 * 
		 * Produto p = new Produto(); p.setId(58);
		 * 
		 * i.setProduto(p);
		 * 
		 * Fornecedor f = new Fornecedor(); f.setId_f(1);
		 * 
		 * i.setFornecedor(f);
		 * 
		 * ItemEntradaDAO dao = new ItemEntradaDAO(); try { System.out.println("Ok");
		 * dao.salvar(i); } catch (SQLException e) { // TODO Auto-generated catch block
		 * System.out.println("Erro"); e.printStackTrace(); }
		 * 
		 * 
		 * 
		 * ItemEntradaDAO dao = new ItemEntradaDAO(); ArrayList<ItemEntrada> lista =
		 * dao.listar();
		 * 
		 * for (ItemEntrada i : lista) { System.out.println("Id do item: " + i.getId());
		 * System.out.println("Nome do produto: " + i.getProduto().getNome());
		 * System.out.println("Quantidade: " + i.getQuantidade());
		 * System.out.println("Preço de compra: " + i.getPreco_at());
		 * System.out.println("Preço de revenda: " + i.getProduto().getPreco());
		 * System.out.println("Fornecedor: " + i.getFornecedor().getNome());
		 * System.out.println("Valor total: " + i.getValor_total());
		 * System.out.println(""); }
		 */

		
		
		
		
		
	}
}
