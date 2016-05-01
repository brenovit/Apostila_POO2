package com.qst4.vo;

public class Gasto {
	private String descricao;
	private Double valor;
	private Integer id;
	
	private static int geradorId = 0;
	
	public Gasto(){
	}
	
	public Gasto(String description, Double value){
		geradorId++;
		this.descricao = description;
		this.valor = value;
		this.id = geradorId;
		
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Integer getID() {
		return id;
	}
	public void setID(Integer id) {
		this.id = id;
	}
}
