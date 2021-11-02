package br.com.fuctura.view;

public enum AcoesUsuario {
	SALVAR("1", "Cadastrar Usuário"), EXCLUIR("2", "Excluir Usuário"), ATUALIZAR("3", "Atualizar Usuário"),
	BUSCAR_TODOS("4", "Buscar Todos");

	AcoesUsuario(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static AcoesUsuario getAcaoFromCodigo(String codigo) {
		for (int i = 0; i < AcoesUsuario.values().length; i++) {
			if (AcoesUsuario.values()[i].codigo.equals(codigo)) {
				return AcoesUsuario.values()[i];
			}
		}
		return null;
	}

	String codigo;
	String descricao;
}
