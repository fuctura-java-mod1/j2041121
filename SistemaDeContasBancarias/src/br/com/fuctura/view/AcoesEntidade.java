package br.com.fuctura.view;

public enum AcoesEntidade {
	GERENCIAR_USUARIO("1", "Gerenciar Usuário"), GERENCIAR_BANCO("2", "Gerenciar Banco"), SAIR("0", "Sair");

	AcoesEntidade(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static AcoesEntidade getAcaoFromCodigo(String codigo) {
		for (int i = 0; i < AcoesUsuario.values().length; i++) {
			if (AcoesEntidade.values()[i].codigo.equals(codigo)) {
				return AcoesEntidade.values()[i];
			}
		}
		return null;
	}

	String codigo;
	String descricao;
}
