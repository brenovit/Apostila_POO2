package com.qst2.pilha;

public class Main {

	public static void main(String[] args) {
		Pilha pilha = new Pilha();
		
		for(int i = 0; i < 101; i++){
			pilha.Empilhar(i);
		}
		
		Pilha pilha2 = pilha;
		while(!pilha2.estaVazia()){
			System.out.println("\nNumero: " + pilha.Desempilhar());
		}
	}

}
