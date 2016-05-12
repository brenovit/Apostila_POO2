package com.qst1.ui;

public class Dado {
	private Integer 	matricula;
	private String 		nome;
	private String 		cpf;
	private	Integer		codigo;
	private	String		materia;
	
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

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}
}
