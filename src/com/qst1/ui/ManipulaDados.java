package com.qst1.ui;

import java.util.List;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;
import com.recursos.InOut;

public class ManipulaDados{
	
	private static AlunoDAO listaAluno = new AlunoDAO();
	private static GradeEscolar grade = new GradeEscolar();
	
	protected static void CadastrarAluno(Aluno aluno){
		listaAluno.Create(aluno);
	}
	
	protected static void AtualizarAluno(Aluno aluno){
		aluno.setCPF(aluno.getCPF());
		aluno.setNome(aluno.getNome());
		aluno.setMatricula(aluno.getMatricula());
		listaAluno.Update(aluno);
	}
	
	protected static void RemoverAluno(Aluno aluno){
		aluno.setMatricula(aluno.getMatricula());
		listaAluno.Delete(aluno);
	}
	
	protected static boolean ValidaPesquisa(String dadoPesquisa){
		if(dadoPesquisa.equals("")){
			InOut.OutMessage("Por Favor digite a matricula do aluno que deseja pesquisar no campo ao lado.", "Atenção", 1);
			return true;
		}
		return false;
	}
	
	protected static boolean PesquisarAluno(Aluno aluno){
		aluno.setMatricula(aluno.getMatricula());
		if(listaAluno.Find(aluno,true) != -1){
			aluno.setNome(aluno.getNome());
			aluno.setCPF(aluno.getCPF());
			aluno.setMatricula(aluno.getMatricula());
			return true;
		}
		return false;
	}
	
	protected static boolean AdicionarMateria(Aluno aluno, Disciplina disc){		
		if(listaAluno.FindMateria(aluno, disc) == -1){
			grade.CadastrarGrade(aluno, disc);	
			return true;
		}
		return false;		
	}
	
	protected static void RemoverMateria(Aluno aluno, Disciplina disc){		
		listaAluno.RemoverGrade(aluno, disc);
	}
	
	protected static List<Disciplina> DisciplinasCadastradas(Aluno aluno){			
		List<Disciplina> listaDisciplina = null;
		
		if(listaAluno.Find(aluno, true) != -1){
			listaDisciplina = aluno.getMaterias();
		}	
		
		return listaDisciplina;
	}
		
	protected static boolean EditarNota(Aluno aluno, Disciplina disc, Double nota){		
		if(listaAluno.AddNota(aluno, disc, nota)){
			return true;
		}
		return false;
	}
	
	public static void CadastrarDisciplinas(){
		Disciplina disc;
		disc = new Disciplina("Programação Orientada a Objetos");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Estrutura de Dados");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Análise de Sistemas de Informações Comerciais");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Projeto Integrador");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Lógica de Programação");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Estatística");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Banco de Dados");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Sistemas Operacionais");
		grade.CadastrarDisciplina(disc);
		disc = new Disciplina("Sistemas de Informações");
		grade.CadastrarDisciplina(disc);
	}
	
	protected static AlunoDAO getListaAluno(){
		return listaAluno;
	}
	
	protected static GradeEscolar getGradeEscolar(){
		return grade;
	}
	
	protected static void MudaCampos(Aluno aluno){
		InternalFrameCadastroAluno.MudarCampos(aluno);
		InternalFrameCadastrarGradeAluno.MudarCampos(aluno);
		InternalFrameCadastrarGradeAluno.AttLista(aluno);
		InternalFrameInserirNota.MudarCampos(aluno);
	}
	
	protected static void Salvar(String arquivo){
		listaAluno.SaveData(arquivo);
	}
	
	protected static void Carregar(String arquivo){
		if(!listaAluno.LoadData(grade,arquivo)){
			InOut.OutMessage("Não foi possivel Importar os dados,\n"
					+ "Por favor verifique se o arquivo se encontra no sistema", "ERRO", 2);
		}
	}
	
	protected static void LimparLista(){
		listaAluno.LimparLista();
	}
}
