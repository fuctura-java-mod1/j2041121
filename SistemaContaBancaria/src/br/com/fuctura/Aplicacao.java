package br.com.fuctura;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.fuctura.model.Usuario;

public class Aplicacao {

	public static void main(String[] args) throws IOException {
		try {
			String URL = "jdbc:oracle:thin:@localhost:1521:xe";
			String usuario = "fuctura";
			String senha = "123";
			Connection conexao = DriverManager.getConnection(URL, usuario, senha);

			Scanner entradaDoUsuario = new Scanner(System.in);

			String nome;
			System.out.println("Digite seu nome: ");
			nome = entradaDoUsuario.nextLine();

			String email;
			System.out.println("Digite seu email: ");
			email = entradaDoUsuario.nextLine();

			String cpf;
			System.out.println("Digite seu cpf: ");
			cpf = entradaDoUsuario.nextLine();

			System.out.println("Digite sua idade: ");
			String idade = entradaDoUsuario.nextLine();

			Usuario novo = new Usuario(nome, email, Integer.valueOf(idade), cpf);

			inserir(novo, conexao);

			ArrayList<Usuario> resultadoDaConsulta = consultarUsuario(conexao);

			imprimirResultado(resultadoDaConsulta);
			
			
			salvarEmHTML(resultadoDaConsulta);

			System.out.println("Conectado com sucesso!");

		} catch (SQLException e) {
			System.out.println("Erro de conexão:");
			e.printStackTrace();

		}

	}

	public static void inserir(Usuario u, Connection conexao) throws SQLException {
		// Digitar o comando:
		String inserir1Usuario = " insert into usuario (nome, email, cpf, idade ) " + "values ('" + u.getNome() + "', '"
				+ u.getEmail() + "', '" + u.getCpf() + "'," + u.getIdade() + ") ";

		System.out.println("Comando: " + inserir1Usuario);

		// enviar para o sgbd
		Statement stmt = conexao.createStatement();

		// envia para o SGBD
		stmt.execute(inserir1Usuario);

	}

	private static void imprimirResultado(ArrayList<Usuario> resultadoDaConsulta) {
		// TODO Auto-generated method stub
		System.out.println("---Usuários Cadastrados---");
		
		for(Usuario usuario : resultadoDaConsulta) {
			System.out.println(usuario);
		}
	}

	public static void inserirComPreparated(Usuario u, Connection conexao) throws SQLException {

		// 1, 2, 3, 4
		String inserir1Usuario = " insert into usuario (nome, email, cpf, idade ) " + "values (?, ?, ?, ?) ";

		PreparedStatement pstmt = conexao.prepareStatement(inserir1Usuario);

		pstmt.setString(1, u.getNome());
		pstmt.setInt(4, u.getIdade());
		pstmt.setString(2, u.getEmail());
		pstmt.setString(3, u.getCpf());

		pstmt.execute();
	}

	public static ArrayList<Usuario> consultarUsuario(Connection conexao) throws SQLException {
		// 1 2 3 4
		String comando = " select nome, idade, email, cpf from usuario ";

		Statement stmt = conexao.createStatement();

		ResultSet resultado = stmt.executeQuery(comando);

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		while (resultado.next()) {
			String nome = resultado.getString("nome");
			int idade = resultado.getInt("idade");
			String email = resultado.getString("email");
			String cpf = resultado.getString("cpf");

			Usuario u = new Usuario(nome, email, idade, cpf);

			usuarios.add(u);
		}
		return usuarios;
	}
	
	private static void salvarEmHTML(ArrayList<Usuario> resultadoDaConsulta) throws IOException {
		File arquivo = new File("saida.html");
		FileWriter fr = new FileWriter(arquivo);
		BufferedWriter brf = new BufferedWriter(fr);
		
		
		StringBuilder sbTabelas = new StringBuilder();
		sbTabelas.append("<table style=\"width:100%\">\n");
		
		sbTabelas.append("<tr>\n"
				+ "    <th>Nome</th>\n"
				+ "    <th>Email</th>\n"
				+ "    <th>CPF</th>\n"
				+ "    <th>Idade</th>\n"
				+ "  </tr>");
				
		for (Usuario usuario : resultadoDaConsulta) {
			sbTabelas.append("<tr>\n"
			+ "<td>" + usuario.getNome() +" </td>\n"
			+ "<td>" + usuario.getEmail() +" </td>\n"
			+ "<td>" + usuario.getCpf() +" </td>\n"
			+ "<td>" + usuario.getIdade() +" </td>\n"
			+ "  </tr>");
		}		
				
		sbTabelas.append("</table>\n");
		
		StringBuilder sb = new StringBuilder();
		sb.append(" <!DOCTYPE html>\n"
				+ "<html>\n" +
				"<style>\n"
				+ "table, th, td {\n"
				+ "  border:1px solid black;\n"
				+ "}\n"
				+ "</style>"
				+ "<title>HTML Tutorial</title>\n"
				+ "<body>\n"
				+ "\n"
				+ "<h1>Sou uma Página HTML</h1><br>" + 
				"<form action=\"\">\n"
				+ "  <label for=\"fname\">Nome:</label><br>\n"
				+ "  <input type=\"text\" id=\"fname\" name=\"fname\"><br>\n"
				+ "  <label for=\"lname\">Email:</label><br>\n"
				+ "  <input type=\"text\" id=\"lname\" name=\"lname\"><br><br>\n"
				+ "  <label for=\"lname\">CPF:</label><br>\n"
				+ "  <input type=\"text\" id=\"lname\" name=\"lname\"><br><br>\n"	
				+ "  <label for=\"lname\">Idade:</label><br>\n"
				+ "  <input type=\"text\" id=\"lname\" name=\"lname\"><br><br>\n"
				+ "  <input type=\"submit\" value=\"Cadastrar\">\n"
				+ "</form>"
				+ "<h2>Tabela de Usuários</h2>\n"
				+ sbTabelas.toString() 
				+ "\n"
				+ "</body>\n"
				+ "</html>");
		
		
		brf.write(sb.toString());
		brf.flush();
		brf.close();
		
	}
}
