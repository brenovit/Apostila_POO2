package com.qst1.ui;

import com.recursos.InOut;

public class TelaPrincipal {
	protected static boolean programaJaRodou = false;
	//eu sou a tela principal
	
	public static void main(String[] args){//esse é o main
		Menu();
		System.out.println("Programa Finzalizado");
	}
	
	public static void Menu(){
		int op;
		do{
			String opcoes = "Digite um dos Numeros abaixo:\n"+
							"1 - Aluno\n"+
							"2 - Gerir Grade Escolar\n"+
							"3 - Gerir Notas do Aluno\n"+
							"0 - Voltar";
			op = InOut.InInt(opcoes);
			switch(op){
				case 0:
					InOut.OutMessage("O programa será Finalizado", "Atenção");
					break;
				case 1:
					TelaAluno.MenuAluno();
					break;
				case 2:
					TelaGrade.MenuGrade();
					break;
				case 3:
					TelaNota.MenuNota();
					break;
				default:
					InOut.OutMessage("Opção Invalida!", "Erro!");
					break;		
			}
		}while(op != 0);
	}	
}
