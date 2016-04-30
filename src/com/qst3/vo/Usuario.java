package com.qst3.vo;

public class Usuario {

	private String nome;
	private String email;
	private String login;
	private String senha;
	
	public Usuario(){
	}
	
	public Usuario(String nome, String email, String login, String senha){
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
