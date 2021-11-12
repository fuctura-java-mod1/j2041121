package br.com.fuctura.model;

public class Usuario {
	private String nome;
	private String email;
	private int idade;
	private String cpf;

	
	
	
	// ctrl + shift + f - organizar
	
	public Usuario(String nome, String email, int idade, String cpf) {
		super();
		this.nome = nome;
		this.email = email;
		this.idade = idade;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", email=" + email + ", idade=" + idade + ", cpf=" + cpf + "]";
	}

}
