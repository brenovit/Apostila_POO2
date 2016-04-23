package com.qst1.vo;

public class Disciplina {
	private String nome;
	private boolean aprovado;
	private Float nota;
	private Integer codigo;
	
	private static Integer geradorCodigo = 0;
	
	public Disciplina(){
		geradorCodigo++;
	}
	
	public Disciplina(String nome){
		geradorCodigo++;
		this.nome = nome;
		this.codigo = geradorCodigo;
	}
	
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
	
	public Integer getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(Integer codigo){
		this.codigo = codigo;
	}
}
