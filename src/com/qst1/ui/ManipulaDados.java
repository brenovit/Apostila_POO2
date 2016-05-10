package com.qst1.ui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;

public class ManipulaDados{
	
	private static AlunoDAO listaAluno = new AlunoDAO();
	private static GradeEscolar grade = new GradeEscolar();
	
	protected static void CadastrarAluno(String nome, String cpf){
		Aluno aluno = new Aluno(nome,cpf);
		listaAluno.Create(aluno);
	}
	
	public static void CadastrarDisciplinas(GradeEscolar grade){
		Disciplina disc;
		disc = new Disciplina("Programa��o Orientada a Objetos");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Estrutura de Dados");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("An�lise de Sistemas de Informa��es Comerciais");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Projeto Integrador");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("L�gica de Programa��o");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Estat�stica");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Banco de Dados");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Sistemas Operacionais");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Sistemas de Informa��es");
		grade.CadastrarDisciplina(disc);
	}
	
	protected static AlunoDAO getLista(){
		return listaAluno;
	}
}
