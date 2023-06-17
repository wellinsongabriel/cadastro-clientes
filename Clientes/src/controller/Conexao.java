package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static Conexao instancia;
	private static String DRIVER = "org.sqlite.JDBC";
	private static String BD = "jdbc:sqlite:resources/bdclientes.db";
	private static Connection conexao;
	
	private Conexao() {
		
	}
	
	public static Conexao getInstancia() {
		if(instancia == null) {
			instancia = new Conexao();
		}
		return instancia;
	}
	
	public Connection abrirConexao() {
		
		try {
			Class.forName(DRIVER);
		conexao = DriverManager.getConnection(BD);
			conexao.setAutoCommit(false);
		} catch (SQLException | ClassNotFoundException e) {			
			System.out.println("Erro ao conectar com o banco de dados: "+e.getMessage());
		}
		return conexao;
		
	}
	
	public void fecharConexao() {
		try {
			if(conexao!=null && !conexao.isClosed()) {
				conexao.close();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao fechar a conexao: "+e.getMessage());
		} finally {
			conexao = null;
		}
	}
	

}
