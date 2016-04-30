package com.qst1.ui;

import javax.security.auth.login.CredentialException;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Disciplina;
import com.recursos.InOut;

public class TelaPrincipal {
	private static AlunoDAO listaAluno = new AlunoDAO();
	private static GradeEscolar grade = new GradeEscolar();
	private static Disciplina disc;
	
	public static void main(String[] args){
		Menu();
	}
	
	public static void Menu(){
		if(!SystemManager.ProgramaJaRodou){
			CriarDisciplinas();
		}
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
					System.exit(0);
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
	
	private static void CriarDisciplinas(){
		disc = new Disciplina("Programa��o Orientada a Objetos");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Estrutura de Dados");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("An�lise de Sistemas de Informa��es Comerciais");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Projeto Integrador");
		grade.CadastrarDisciplina(disc);/*
		Disciplina disc = new Disciplina("L�gica de Programa��o");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Estat�stica");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Banco de Dados");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Sistemas Operacionais");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Sistemas de Informa��es");
		grade.CadastrarDisciplina(disc);*/
		SystemManager.ProgramaJaRodou = true;
	}
	
	public static AlunoDAO getListaAluno(){
		return listaAluno;
	}
	public static GradeEscolar getGradeEscolar(){
		return grade;
	}
}
