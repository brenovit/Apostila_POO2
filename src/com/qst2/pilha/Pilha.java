package com.qst2.pilha;

import java.util.Stack;

public class Pilha {
	//criação da pilha
	private Stack<Integer> pilhaNumPar = new Stack<Integer>();
	
	//metodo de inserir numeros
	public void Empilhar(int numero){
		if(numero % 2 == 0){
			this.pilhaNumPar.push(numero);
			//System.out.println("\nNumero: "+numero);
		}
	}
	
	//metodo de remover numeros
	public Integer Desempilhar (){
		if(!estaVazia())
			return this.pilhaNumPar.pop();
		return -1;
	}
	
	//metodo de verificar se seta vazia
	public boolean estaVazia(){
		return this.pilhaNumPar.empty();
	}
		
}
