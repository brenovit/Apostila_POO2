package com.qst1.ui;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;
import com.recursos.InOut;

public class TelaGrade {
	private static GradeEscolar grade = TelaPrincipal.RetornaGradeEscolar();
	private static AlunoDAO listaAluno = TelaPrincipal.RetornaListaAluno();
	private static Disciplina disc;
	private static Aluno aluno;
	
	protected static void MenuGrade(){
		if(TelaPrincipal.programaJaRodou == false){
			CriarDisciplinas();
			System.out.println("Criei as Disciplinas");
		}
		int op;
		do{
			String opcoes = "Digite um dos Numeros abaixo:\n"+
							"1 - Cadastrar Grade do Aluno\n"+
							"2 - Listar Disciplinas Disponiveis\n"+
							"3 - Listar Materias Cadastradas em um Aluno\n"+
							"4 - Remover Grade de Aluno\n"+
							"0 - Voltar";
			op = InOut.InInt(opcoes);
			switch(op){
				case 0:
					break;
				case 1:
					CadastrarGradeAluno();
					break;
				case 2:
					InOut.OutMessage(ListarDisciplinas());
					break;
				case 3:
					MostrarMateriasAluno();
					break;
				case 4:
					RemoverGradeAluno();
					break;
				default:
					InOut.OutMessage("Op��o Invalida!", "Erro!");
					break;		
			}
		}while(op != 0);
		TelaPrincipal.Menu();
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
		TelaPrincipal.programaJaRodou = true;
	}
	
	private static void CadastrarGradeAluno(){
		aluno = new Aluno();
		disc = new Disciplina();
		if(ProcurarAluno(aluno, "Cadastrar uma Mat�ria")){		//procura o aluno
			if(ProcurarDisciplina(disc)){						//procura a materia
				if(listaAluno.FindMateria(aluno, disc) == -1){	//verifica se o aluno ja esta cadastrado na materia
					grade.CadastrarGrade(aluno, disc);
				}else{
					InOut.OutMessage("Disciplina j� cadastrada neste Aluno", "ERRO");
				}
			}
		}
	}
	
	
	private static boolean ProcurarDisciplina(Disciplina disc){
		String disciplinas = ListarDisciplinas();
		Integer codigo = InOut.InInt(disciplinas +
									"\nDigite o Codigo da Disciplina que voc� deseja Cadastrar no Aluno:");
		disc.setCodigo(codigo);
		if(grade.Find(disc,true) != -1){
			InOut.OutMessage("Diciplina: "+disc.getNome()+
							 "\nCodigo: "+disc.getCodigo());
			return true;
		}
		InOut.OutMessage("Disciplina n�o cadastrada");
		return false;
	}
	
	
	private static String ListarDisciplinas(){
		String msg = "Disciplinas Cadastradas no Sistema"+
				     "\n------------------------------------"+grade.Show();
		return msg;
	}

	private static void RemoverGradeAluno(){
		
	}
	
	private static boolean ProcurarAluno(Aluno aluno, String msg){
		TelaAluno.ProcurarDefinindoMatricula(aluno, msg);
		if(listaAluno.Find(aluno,true) != -1){
			InOut.OutMessage(TelaAluno.DadosAlunoEncontrado(aluno));
			return true;
		}else{
			TelaAluno.AlunoNaoEncontrado();
		}
		return false;
	}
	
	public static void MostrarMateriasAluno(){
		aluno = new Aluno();
		if(ProcurarAluno(aluno, "Visualizar as Materias")){
			String msg = TelaAluno.DadosAlunoEncontrado(aluno) +
					"\nMaterias do Aluno:\n------------------------------------"+
					listaAluno.ShowDisciplinasMatriculadas(aluno);						 
			InOut.OutMessage(msg);
		}else{
			TelaAluno.AlunoNaoEncontrado();
		}
	}
}
