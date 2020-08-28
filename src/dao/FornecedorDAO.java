package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factory.ConexaoFactory;
import model.Fornecedor;

public class FornecedorDAO {
	public void salvar(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedor ");
		sql.append("(id_f, nome, cnpj) ");
		sql.append("VALUES (?, ?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, f.getId_f());
		comando.setString(2, f.getNome());
		comando.setString(3, f.getCnpj());
		comando.executeUpdate();
	}

	public void excluir(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedor ");
		sql.append("WHERE nome = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getNome());
		comando.executeUpdate();
	}

	public void editar(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedor ");
		sql.append("SET nome = ?, cnpj = ? ");
		sql.append("WHERE id_f = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getNome());
		comando.setString(2, f.getCnpj());
		comando.setInt(3, f.getId_f());
		comando.executeUpdate();
	}

	public Fornecedor buscaPorNome(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nome, cnpj");
		sql.append("FROM fornecedor ");
		sql.append("WHERE nome = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getNome());

		ResultSet resultado = comando.executeQuery();
		Fornecedor retorno = null;

		if (resultado.next()) {
			retorno = new Fornecedor();
			retorno.setNome(resultado.getString("nome"));
			retorno.setCnpj(resultado.getString("cnpj"));
		}

		return retorno;
	}

	public ArrayList<Fornecedor> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_f, nome, cnpj ");
		sql.append("FROM fornecedor ");
		sql.append("ORDER BY nome ASC");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fornecedor> lista = new ArrayList<Fornecedor>();

		while (resultado.next()) {
			Fornecedor f = new Fornecedor();
			f.setId_f(resultado.getInt("id_f"));
			f.setNome(resultado.getString("nome"));
			f.setCnpj(resultado.getString("cnpj"));
			
			lista.add(f);
		}

		return lista;

	}
}
