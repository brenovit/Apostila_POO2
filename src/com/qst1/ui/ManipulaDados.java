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
	
	protected static boolean ValidaPesquisa(String dadoPesquisa){
		if(dadoPesquisa.equals("")){
			InOut.OutMessage("Por Favor digite a matricula do aluno que deseja pesquisar no campo ao lado.", "Atenção", 1);
			return true;
		}
		return false;
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
	
	protected static boolean AdicionarMateria(Dado dado){
		Aluno aluno = new Aluno();
		Disciplina disc = new Disciplina();
		
		aluno.setMatricula(dado.getMatricula());
		disc.setCodigo(dado.getCodigo());
		
		if(listaAluno.FindMateria(aluno, disc) == -1){
			grade.CadastrarGrade(aluno, disc);	
			return true;
		}
		return false;		
	}
	
	protected static void RemoverMateria(Dado dado){
		Aluno aluno = new Aluno();
		Disciplina disc = new Disciplina();
		
		aluno.setMatricula(dado.getMatricula());
		disc.setCodigo(dado.getCodigo());
		
		listaAluno.RemoverGrade(aluno, disc);
	}
	
	protected static List<Disciplina> DisciplinasCadastradas(Dado dado){		
		Aluno aluno = new Aluno();
		aluno.setMatricula(dado.getMatricula());		
		List<Disciplina> listaDisciplina = null;
		
		if(listaAluno.Find(aluno, true) != -1){
			listaDisciplina = aluno.getMaterias();
		}	
		
		return listaDisciplina;
	}
		
	protected static boolean EditarNota(Dado dado){
		Aluno aluno = new Aluno();
		Disciplina disc = new Disciplina();
		
		aluno.setMatricula(dado.getMatricula());
		disc.setCodigo(dado.getCodigo());
		
		Double nota = dado.getNota();
		
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
	
	protected static void MudaCampos(Dado pdados){
		InternalFrameCadastroAluno.MudarCampos(pdados);
		InternalFrameCadastrarGradeAluno.MudarCampos(pdados);
		InternalFrameCadastrarGradeAluno.AttLista(pdados);
		InternalFrameInserirNota.MudarCampos(pdados);	
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
