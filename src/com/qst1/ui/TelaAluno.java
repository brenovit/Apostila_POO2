package com.qst1.ui;

import com.qst1.dao.AlunoDAO;
import com.qst1.vo.Aluno;
import com.recursos.InOut;

public class TelaAluno {
	private static AlunoDAO listaAluno = new AlunoDAO();
	private static Aluno aluno;
	public static void MenuAluno(){
		int op;
		do{
		String opcoes = "Digite um dos Numeros abaixo:\n"+
						"1 - Cadastrar Aluno\n"+
						"2 - Listar Alunos\n"+
						"3 - Alterar Dados do Aluno\n"+
						"4 - Procurar por Aluno\n"+
						"5 - Deletar Aluno\n"+
						"6 - Mostrar Materias Cadastradas\n"+
						"7 - Limpar Lista\n"+
						"0 - Voltar";
		/*"Digite um dos Numeros abaixo:\n"+
		"1 - Cadastrar Aluno\n"+
		nome:breno
		cpf: 123
		"2 - Lista Aluno\n"+
		mostra uma lista com todas as informações dos alunos: 
		matricula:123
		nome: breno
		cpf:123456789
		"3 - Alterar Aluno\n"+
		mostra lista com matricula e nome de todos os alunos:
		matricula:123
		nome:breno
		digita a matricula do aluno que deseja.
		altera todos os dados deste aluno.
		"4 - Procurar por Aluno\n"+
		digita a matricula do aluno:123
		se exisistir, mostra as informações deste aluno;
		"5 - Deletar Aluno\n"+
		digita a matricula do aluno
		deleta ele
		"6 - Mostrar Materias Cadastradas\n"+
		digita a matricula do aluno: 1
		checa se aluno existe
		se existir
		mostrar uma lista com todas as materias que o aluno tiver.
		se não, volta
		deleta todos os alunos
		"0 - Sair";*/
		op = InOut.InInt(opcoes);
		switch(op){
			case 0:
				break;
			case 1:
				CadastrarAluno();
				break;
			case 2:
				ListarAluno();
				break;
			case 3:
				AlterarAluno();
				break;
			case 4:
				ProcurarAluno();
				break;
			case 5:
				DeletarAluno();
				break;
			case 6:
				MostrarMateriasAluno();
				break;
			case 7:
				ApagarAluno();
				break;
			default:
				InOut.OutMessage("Opção Invalida!", "Erro!");
				break;		
		}
		}while(op != 0);
		TelaPrincipal.Menu();
	}
	
	public static void CadastrarAluno() {
		String nome = InOut.InString("Insira o Nome do Aluno:");
		String cpf = InOut.InString("Digite o CPF do Aluno:");
		Aluno aluno = new Aluno(nome, cpf);
		listaAluno.Create(aluno);
	}
	
	public static void ListarAluno() {
		String msg = "Alunos Cadastrados no Sistema\n------------------------------------"+listaAluno.Show();
		InOut.OutMessage(msg);
	}
	
	public static void AlterarAluno(){
		aluno = new Aluno();
		Integer matricula = InOut.InInt("Insira a matricula do Aluno que deseja Procurar:");
		aluno.setMatricula(matricula);
		String msg = "Aluno Encontrado\n------------------------------------\n";
		if(listaAluno.Find(aluno) != -1){					 
			String nome = InOut.InString(msg + "Digite o novo Nome:");
			String cpf = InOut.InString(msg + "Digite o novo CPF do Aluno:");
			aluno.setNome(nome);
			aluno.setCPF(cpf);
			listaAluno.Uptade(aluno);
		}else{
			AlunoNaoEncontrado();
		}		
	}
	
	public static void ProcurarAluno(){
		aluno = new Aluno();
		DadosAlunoEncontrado (aluno, "Procurar");
		if(listaAluno.Find(aluno,true) != -1){
			InOut.OutMessage(DadosAlunoEncontrado(aluno));
		}else{
			AlunoNaoEncontrado();
		}	
	}
	
	public static void DeletarAluno(){		
		aluno = new Aluno();
		DadosAlunoEncontrado (aluno, "Deletar");
		if(listaAluno.Delete(aluno)){						 
			InOut.OutMessage(DadosAlunoEncontrado(aluno) + "Clique Confirmar para Remover o aluno do registro.");			
		}else{
			AlunoNaoEncontrado();
		}		
	}
	
	public static void ApagarAluno() {
		InOut.OutMessage("Esta Função ainda não foi Feita!");
	}
	
	public static void MostrarMateriasAluno(){
		aluno = new Aluno();
		DadosAlunoEncontrado (aluno, "Visualizar as Materias");
		if(listaAluno.Find(aluno,true) != -1){
			String msg = DadosAlunoEncontrado(aluno) +
					"\nMaterias do Aluno:\n------------------------------------"+
					listaAluno.ShowDisciplinasMatriculadas(aluno);
						 
			InOut.OutMessage(msg);			
			listaAluno.Delete(aluno);
		}else{
			AlunoNaoEncontrado();
		}
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
	
	public static AlunoDAO RetornaLista(){
		return listaAluno;
	}
}
