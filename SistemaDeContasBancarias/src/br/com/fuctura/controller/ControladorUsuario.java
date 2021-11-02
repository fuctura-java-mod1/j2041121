package br.com.fuctura.controller;

import java.util.List;

import br.com.fuctura.model.Usuario;

public class ControladorUsuario {
	
	private static List<Usuario> usuarios;
	
	public void salvar(Usuario u) {
		usuarios.add(u);
	}
	
	public List<Usuario> encontrarTodos() {
		return usuarios;
	}
}
