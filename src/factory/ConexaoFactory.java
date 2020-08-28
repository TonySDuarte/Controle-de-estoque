package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static final String USUARIO = "root";
	private static final String SENHA = "166824 Tsd0";
	private static final String URL = "jdbc:mysql://localhost/estoquedb?serverTimezone=UTC";
	
	public static Connection conectar() throws SQLException {
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	
	public static void main(String[] args) {
		try {
			Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conectado com sucesso.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Conexão falhou.");
			e.printStackTrace();
		}
	}

}
