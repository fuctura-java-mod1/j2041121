package br.com.fuctura;

import java.util.Arrays;
import java.util.Scanner;

import br.com.fuctura.controller.ControladorUsuario;
import br.com.fuctura.model.Usuario;
import br.com.fuctura.view.AcoesEntidade;
import br.com.fuctura.view.AcoesUsuario;

public class Aplicacao {

	public static void main(String[] args) {

		Scanner leitor = new Scanner(System.in);
		String opcaoSelecionada;
		AcoesEntidade acaoEntidade;

		do {

			System.out.println("Escolha uma das opções abaixo: ");

			exibirMenuEntidade();

			System.out.println("Digite o código da ação: ");

			opcaoSelecionada = leitor.nextLine();

			acaoEntidade = AcoesEntidade.getAcaoFromCodigo(opcaoSelecionada.toUpperCase());

			switch (acaoEntidade) {
			case GERENCIAR_USUARIO:

				ControladorUsuario controladorUsuario = new ControladorUsuario();

				exibirMenuUsuario();

				System.out.println("Digite o código da ação: ");
				opcaoSelecionada = leitor.nextLine();

				AcoesUsuario codigoConvertidoParaAcao = AcoesUsuario.getAcaoFromCodigo(opcaoSelecionada);

				switch (codigoConvertidoParaAcao) {
					case SALVAR:
						System.out.println("Digite os dados do usuario: ");
						String nome = digite("o nome do Usuário", leitor);
						String idade = digite("a idade do Usuário", leitor);
						String email = digite("o email do Usuário", leitor);
	
						Usuario u = new Usuario(nome, email, Integer.valueOf(idade));
	
						controladorUsuario.salvar(u);
	
						break;
	
					case BUSCAR_TODOS:
	
						break;
					default:
						break;
				}

				break;

			default:
				break;
			}

		} while (acaoEntidade != AcoesEntidade.SAIR);

		leitor.close();

	}

	private static void exibirMenuEntidade() {
		for (AcoesEntidade acao : Arrays.asList(AcoesEntidade.values())) {
			System.out.println(acao.getCodigo() + " - " + acao.getDescricao());
		}
	}

	private static void exibirMenuUsuario() {
		for (AcoesUsuario acao : Arrays.asList(AcoesUsuario.values())) {
			System.out.println(acao.getCodigo() + " - " + acao.getDescricao());
		}
	}

	private static String digite(String acao, Scanner leitor) {
		System.out.println("Digite " + acao);
		return leitor.nextLine();
	}
}
