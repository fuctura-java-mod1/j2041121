package br.com.fuctura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import br.com.fuctura.model.ContaCorrente;
import br.com.fuctura.model.Usuario;

public class Aplicacao {

	public static void main(String[] args) {
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
			
			//Digitar o comando:
			String inserir1Usuario = " insert into usuario (nome, email, cpf, idade ) "
					+ "values ('" + nome + "', '" + email  +"', '" + cpf +"',"  + idade + ") ";
			
			System.out.println("Comando: " + inserir1Usuario);
			
			//enviar para o sgbd
			Statement stmt = conexao.createStatement();
			
			//envia para o SGBD
			stmt.execute(inserir1Usuario);
			
			Usuario novo = new Usuario();
			
			novo.setNome(nome);
			novo.setEmail(email);
			novo.setIdade(Integer.valueOf(idade));
			novo.setCpf(cpf);
			
			ContaCorrente c = new ContaCorrente();
			
			inserir(novo, conexao);
			
			System.out.println("Conectado com sucesso!");
		
		} catch (SQLException e) {
			System.out.println("Erro de conexão:");
			e.printStackTrace();
			
		}

	}

	public static void inserir(Usuario u, Connection conexao) throws SQLException {
		//Digitar o comando:
		String inserir1Usuario = " insert into usuario (nome, email, cpf, idade ) "
				+ "values ('" + u.getNome() + "', '" + u.getEmail()  +"', '" + u.getCpf() +"',"  + u.getIdade() + ") ";
		
		System.out.println("Comando: " + inserir1Usuario);
		
		//enviar para o sgbd
		Statement stmt = conexao.createStatement();
		
		//envia para o SGBD
		stmt.execute(inserir1Usuario);
	}
}
