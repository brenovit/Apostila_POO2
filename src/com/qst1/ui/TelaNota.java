package com.qst1.ui;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;
import com.recursos.InOut;

public class TelaNota {
	public static AlunoDAO listaAluno = TelaPrincipal.RetornaListaAluno();
	private static GradeEscolar grade = TelaPrincipal.RetornaGradeEscolar();
	private static Disciplina disc;
	private static Aluno aluno;
	
	public static void MenuNota() {
		int op;
		do{
			String opcoes = "Digite um dos Numeros abaixo:\n"+
							"1 - Inserir/Alterar Nota do Aluno\n"+
							"2 - Checar Aprova��o dos Alunos\n"+
							"0 - Voltar";
			/*"Digite um dos Numeros abaixo:\n"+
			"1 - inserir nota do aluno:\n"+
			"2 - checar aprova��o
			1 = escolha o aluno:
			"1- breno
			"2- mauricio
			digite a matricula - 1
			materias do aluno breno:
			"1 - poo;
			"2 - ed;
			escolha a materia: 1
			digite a nota do aluno: 10;
			mostrar na tela - que o aluno foi reprovado.
			*/
			op = InOut.InInt(opcoes);
			switch(op){
				case 0:
					break;
				case 1:
					InserirNotaAluno();
					break;
				case 2:					
					AprovacaoAluno();
					break;
				default:
					InOut.OutMessage("Op��o Invalida!", "Erro!");
					break;		
			}
		}while(op != 0);
		TelaPrincipal.Menu();
	}
	
	public static void InserirNotaAluno(){
		//procurar aluno
		//procurar materia
		//inserir nota
	}
	
	public static void AprovacaoAluno(){
		//procurar aluno
		//procurar materia
		//retornar aprova��o
	}	
}
