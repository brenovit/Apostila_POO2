package com.qst1.ui;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;
import com.recursos.InOut;

public class TelaGrade {
	private static GradeEscolar grade = new GradeEscolar();
	private static Disciplina disc;
	private static AlunoDAO listaAluno;
	private static Aluno aluno;
	
	public static void MenuGrade(){
		listaAluno = TelaAluno.RetornaLista();		
		if(TelaPrincipal.programaJaRodou == false){
			CriarDisciplinas();
			System.out.println("Criei as Disciplinas");
		}
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
					CadastrarGradeAluno();
					break;
				case 2:
					InOut.OutMessage(ListarDisciplinas());
					break;
				case 3:
					RemoverGradeAluno();
					break;
				default:
					InOut.OutMessage("Opção Invalida!", "Erro!");
					break;		
			}
		}while(op != 0);
		TelaPrincipal.Menu();
	}
	
	private static void CriarDisciplinas(){
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
		TelaPrincipal.programaJaRodou = true;
	}
	
	private static void CadastrarGradeAluno(){
		aluno = new Aluno();
		disc = new Disciplina();
		if(ProcurarAluno(aluno)){
			grade.CadastrarGrade(al1, disc2);		
		}
	}

	private static String ListarDisciplinas(){
		String msg = "Disciplinas Cadastradas no Sistema"+
				     "\n------------------------------------"+grade.Show();
		return msg;
	}

	private static void RemoverGradeAluno(){
		
	}
	
	Procurar
	public static boolean ProcurarAluno(Aluno aluno){
		DadosAlunoEncontrado (aluno, "Cadastrar a Materia");
		if(listaAluno.Find(aluno,true) != -1){
			InOut.OutMessage(DadosAlunoEncontrado(aluno));
			return true;
		}else{
			AlunoNaoEncontrado();
		}
		return false;
	}
	
	public static String DadosAlunoEncontrado (Aluno paluno, String complemento) {
		Integer matricula = InOut.InInt("Insira a matricula do Aluno que deseja "+complemento+":");
		paluno.setMatricula(matricula);
		return DadosAlunoEncontrado (paluno);		
	}
	
	public static String DadosAlunoEncontrado (Aluno aluno){
		String msg = "Aluno Encontrado\n------------------------------------" +
				 "\nMatricula: " + aluno.getMatricula() +
			     "\nNome: "+aluno.getNome()+
			     "\nCPF: "+aluno.getCPF()+
			     "\n------------------------------------\n";
		return msg;		
	}
	
	public static void AlunoNaoEncontrado(){
		InOut.OutMessage("No registro não consta nenhum aluno com esta matricula.\n"
				+ "Por favor verifique se digitou corretamente\ne tente novamente.", 
				"Aluno Não Encontrado");
	}
	
	public static GradeEscolar RetornaGrade(){
		return grade;
	}
	public static AlunoDAO RetornaListaAluno(){
		return listaAluno;
	}
}
