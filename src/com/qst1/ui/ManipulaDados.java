package com.qst1.ui;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;

public class ManipulaDados{
	
	private static AlunoDAO listaAluno = new AlunoDAO();
	private static GradeEscolar grade = new GradeEscolar();
	private static Dado dados = new Dado();
	private static InternalFrameCadastroAluno iFCadAluno;
	
	protected static void CadastrarAluno(Dado dadoAluno){
		Aluno aluno = new Aluno(dadoAluno.getNome(),dadoAluno.getCpf());
		listaAluno.Create(aluno);
	}
	
	protected static void AtualizarAluno(Dado dadoAluno){
		Aluno aluno = new Aluno();
		aluno.setCPF(dadoAluno.getCpf());
		aluno.setNome(dadoAluno.getNome());
		aluno.setMatricula(dadoAluno.getMatricula());
		listaAluno.Update(aluno);
	}
	
	protected static void RemoverAluno(Dado dadoAluno){
		Aluno aluno = new Aluno();
		aluno.setMatricula(dadoAluno.getMatricula());
		listaAluno.Delete(aluno);
	}
	
	protected static boolean PesquisarAluno(Dado dadoAluno){
		Aluno aluno = new Aluno();
		aluno.setMatricula(dadoAluno.getMatricula());
		if(listaAluno.Find(aluno,true) != -1){
			dadoAluno.setNome(aluno.getNome());
			dadoAluno.setCpf(aluno.getCPF());
			dadoAluno.setMatricula(aluno.getMatricula());
			return true;
		}
		return false;
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
	
	protected static void setDados(Dado pdados){
		dados = pdados;
		iFCadAluno.MudarCampos(dados);
	}
	
	protected static void Salvar(){
		listaAluno.SaveData();
	}
	
	protected static void Carregar(){
		listaAluno.LoadData(grade);
	}
}
