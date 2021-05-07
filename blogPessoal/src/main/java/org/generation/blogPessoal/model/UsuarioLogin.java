package org.generation.blogPessoal.model;

public class UsuarioLogin {

	//Essa classe apenas entrega uma resposta quando o usuário realizar login, não tem interação com o DB
	
	private String nome;
	
	private String usuario;
	
	private String senha;
	
	private String token;

	//Encapsulamento
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
