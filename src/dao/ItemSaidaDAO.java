package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factory.ConexaoFactory;
import model.ItemSaida;
import model.Produto;

public class ItemSaidaDAO {
	public void salvar(ItemSaida i) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO itemsaida ");
		sql.append("(quantidade, produto_idproduto) ");
		sql.append("VALUES (?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, i.getQuantidade());
		comando.setInt(2, i.getProduto().getId());
		comando.executeUpdate();
	}

	public ArrayList<ItemSaida> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT i.id, i.quantidade, p.id, p.nome, p.quantidade, p.preco ");
		sql.append("FROM itemsaida i ");
		sql.append("INNER JOIN produto p ON p.id = i.produto_idproduto ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<ItemSaida> lista = new ArrayList<ItemSaida>();

		while (resultado.next()) {
			Produto p = new Produto();
			p.setId(resultado.getInt("p.id"));
			p.setNome(resultado.getString("p.nome"));
			p.setQuantidade(resultado.getInt("p.quantidade"));
			p.setPreco(resultado.getDouble("p.preco"));

			ItemSaida i = new ItemSaida();
			i.setIdSaida(resultado.getInt("i.id"));
			i.setQuantidade(resultado.getInt("i.quantidade"));
			i.setProduto(p);

			lista.add(i);
		}

		return lista;

	}

	public void excluir(ItemSaida i) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM itemSaida ");
		sql.append("WHERE id = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, i.getIdSaida());
		comando.executeUpdate();
	}

	public void editar(ItemSaida i) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE itemSaida ");
		sql.append("SET quantidade = ?, preco_at = ?, produto_idproduto = ? ");
		sql.append("WHERE id = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, i.getQuantidade());
		comando.setInt(2, i.getProduto().getId());
		comando.setInt(3, i.getIdSaida());
		comando.executeUpdate();

	}

}
