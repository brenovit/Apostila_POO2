package com.qst1.vo;

import java.util.ArrayList;
import java.util.List;

import com.qst1.dao.GradeEscolar;

public class Aluno {
	private String nome;	
	private Integer matricula;
	private String cpf;
	
	private static int geradorMatricula = 0;
	
	private ArrayList<Disciplina> grade;
	
	public Aluno(){
		grade = new ArrayList<Disciplina>();
		geradorMatricula++;
	}
	
	public Aluno(String nome, String cpf){
		grade = new ArrayList<Disciplina>();
		geradorMatricula++;
		this.nome = nome;
		this.matricula = geradorMatricula;
		this.cpf = cpf;
	}
	
	public Aluno(String nome, int matricula, String cpf){
		grade = new ArrayList<Disciplina>();
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

	public Integer getMatricula() {
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
	
	public void addMateria(Disciplina materia){
		this.grade.add(materia);
	}
	
	public ArrayList<Disciplina> getMaterias(){		
		return this.grade;
	}
}
