package com.qst1.ui;

public class Dado {
	private Integer matricula;
	private String nome;
	private String cpf;
	
	public Dado(){		
	}
	
	public Dado(String nome, String cpf){
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public Dado(Integer matricula, String nome, String cpf){
		this.matricula = matricula;
		this.nome = nome;
		this.cpf = cpf;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
