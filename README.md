# j2041121

## Aula 2

* Abrimos uma conexão com o SGBD da Oracle

* Utilizando o DriverManager e a classe Connection para abrir uma conexão:

<pre><code>
    String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		String usuario = "fuctura";
		String senha = "123";
		
		Connection conexao = DriverManager.getConnection(URL, usuario, senha);
</code></pre>

* Inserimos os primeiros registros

- Utilizamos a classe PreparedStatement para inserir 1 registro fixo:

<pre><code>
    public void inserir(Connection conexao) throws SQLException {
		  try {
			//URL, USUARIO e SENHA
			Connection minhaConexao = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "fuctura", "123");
																		                               //1, 2, 3	
			String sql = "insert into jogador(nome, idade, camisa) values (?, ?, ?)";
			
			PreparedStatement ps = minhaConexao.prepareStatement(sql);
		
			//usar o scanner pra capturar os dados digitados pelo o usuário
		
			ps.setString(1, "Messi");
			ps.setInt(2, 33);
			ps.setInt(3, 10);
			
			ps.execute();
			
			System.out.println("Resgistro Inserido Com Sucesso!");
		
		} catch (SQLException e) {
			System.out.println("Ocorreu um problema ao acessar o Banco de Dados");
			e.printStackTrace();
		}
	}
</code></pre>
