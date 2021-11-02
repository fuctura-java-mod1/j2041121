package br.com.fuctura.view;

public class ItemMenu {
	
	private AcoesUsuario codigoDaAcao;
	private String descricao;
	
	public String getCodigo() {
		return codigoDaAcao.getCodigo();
	}

	public String getDescricao() {
		return descricao;
	}

	public ItemMenu(AcoesUsuario codigoDaAcao, String descricao) {
		this.codigoDaAcao = codigoDaAcao;
		this.descricao = descricao;
	}
}
