package com.qst1.vo;

import java.util.List;

import com.qst1.dao.GradeEscolar;

public class Aluno {
	private String nome;	
	private int matricula;
	private String cpf;
	
	private static int geradorMatricula = 0;
	
	GradeEscolar grade;
	
	public Aluno(){
		geradorMatricula++;
	}
	
	public Aluno(String nome, String cpf){
		geradorMatricula++;
		this.nome = nome;
		this.matricula = geradorMatricula;
		this.cpf = cpf;
	}
	
	public Aluno(String nome, int matricula, String cpf){
		this.nome = nome;
		this.matricula = matricula;
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
}
