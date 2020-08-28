package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factory.ConexaoFactory;
import model.Produto;

public class ProdutoDAO {
	public void salvar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(id, nome, quantidade, preco) ");
		sql.append("VALUES (?, ?, ?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, p.getId());
		comando.setString(2, p.getNome());
		comando.setInt(3, p.getQuantidade());
		comando.setDouble(4, p.getPreco());
		comando.executeUpdate();
	}

	public void excluir(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE nome = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getNome());
		comando.executeUpdate();
	}

	public void editar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produto ");
		sql.append("SET nome = ?, quantidade = ?, preco = ? ");
		sql.append("WHERE id = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getNome());
		comando.setInt(2, p.getQuantidade());
		comando.setDouble(3, p.getPreco());
		comando.setInt(4, p.getId());
		// comando.setInt(3, p.getQuantidade());
		comando.executeUpdate();
	}

	public ArrayList<Produto> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome, quantidade, preco ");
		sql.append("FROM produto ");
		sql.append("ORDER BY nome ASC");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Produto> lista = new ArrayList<Produto>();

		while (resultado.next()) {
			Produto p = new Produto();
			p.setId(resultado.getInt("id"));
			p.setNome(resultado.getString("nome"));
			p.setQuantidade(resultado.getInt("quantidade"));
			p.setPreco(resultado.getDouble("preco"));

			lista.add(p);
		}

		return lista;

	}
	
	public boolean validar(String nome) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nome ");
		sql.append("FROM produto ");
		sql.append("WHERE nome = ?");

		boolean check = false;
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, nome);
		
		ResultSet resultado = comando.executeQuery();

		if (resultado.next()) {
			check = true;
		}

		return check;
	}

}
