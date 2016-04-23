package com.qst1.vo;

public class Disciplina {
	private String nome;
	private boolean aprovado;
	private Float nota;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getNota(){
		return this.nota;
	}
	public void setNota(float valor){
		this.nota = valor;
		if(this.nota < 6){
			this.aprovado = false;
		}else{
			this.aprovado = true;
		}
	}
	
	public boolean getAprovado(){
		return this.aprovado;
	}
}
