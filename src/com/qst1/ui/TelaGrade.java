package com.qst1.ui;

import com.recursos.InOut;

public class TelaGrade {
	public static void MenuGrade(){
		int op;
		do{
			String opcoes = "Digite um dos Numeros abaixo:\n"+
							"1 - Cadastrar Grade do Aluno\n"+
							"2 - Listar Disciplinas Disponiveis\n"+
							"3 - Remover Grade de Aluno\n"+
							"0 - Voltar";
			/*"1 - Cadsatrar grade do aluno:
			mostra lista com matricula e nome dos alunos cadastrados:
			"1 - Breno\n"+
			"2 - mauricio\n"+
			digitar matricula = 1
			mostra uma lista das materias disponiveis no sistema:
			escolha as materias
			1 - poo 
			2 - ed
			3 - ads
			digite o codigo da materia = 1
			a materia escolhida é adicionada ao aluno
			se o alunos ja esetiver cadastrado mostra mensagem de erro.*/
			op = InOut.InInt(opcoes);
			switch(op){
				case 0:
					break;
				case 1:
					//metdodo para cadastrar grade dos alunos
					break;
				case 2:
					//metodos para listar todas as grades
					break;
				case 3:
					//Metodo para remover alguma grade do aluno
					break;
				default:
					InOut.OutMessage("Opção Invalida!", "Erro!");
					break;		
			}
		}while(op != 0);
		TelaPrincipal.Menu();
	}
}
