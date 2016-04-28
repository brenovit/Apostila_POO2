package com.qst1.ui;

import com.qst1.dao.AlunoDAO;
import com.qst1.vo.Aluno;
import com.recursos.InOut;

public class TelaAluno {
	private static AlunoDAO listaAluno = TelaPrincipal.RetornaListaAluno();
	private static Aluno aluno;
	protected static void MenuAluno(){
		int op;
		do{
		String opcoes = "Digite um dos Numeros abaixo:\n"+
						"1 - Cadastrar Aluno\n"+
						"2 - Listar Alunos\n"+
						"3 - Alterar Dados do Aluno\n"+
						"4 - Procurar por Aluno\n"+
						"5 - Deletar Aluno\n"+						
						"6 - Limpar Lista\n"+
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
				ApagarAluno();
				break;
			default:
				InOut.OutMessage("Opção Invalida!", "Erro!");
				break;		
		}
		}while(op != 0);
		TelaPrincipal.Menu();
	}
	
	private static void CadastrarAluno() {
		//String nome = InOut.InString("Insira o Nome do Aluno:");
		//String cpf = InOut.InString("Digite o CPF do Aluno:");
		Aluno aluno = new Aluno("Breno","07049603546");
		listaAluno.Create(aluno);
	}
	
	private static void ListarAluno() {
		String msg = "Alunos Cadastrados no Sistema\n------------------------------------"+listaAluno.Show();
		InOut.OutMessage(msg);
	}
	
	private static void AlterarAluno(){
		aluno = new Aluno();
		ProcurarDefinindoMatricula (aluno, "Alterar");
		if(listaAluno.Find(aluno,false) != -1){					 
			String nome = InOut.InString(DadosAlunoEncontrado(aluno) + "Digite o novo Nome:");
			String cpf = InOut.InString(DadosAlunoEncontrado(aluno) + "Digite o novo CPF do Aluno:");
			aluno.setNome(nome);
			aluno.setCPF(cpf);
			listaAluno.Uptade(aluno);
		}else{
			AlunoNaoEncontrado();
		}
	}
	
	private static void ProcurarAluno(){
		aluno = new Aluno();
		ProcurarDefinindoMatricula (aluno, "Procurar");
		if(listaAluno.Find(aluno,true) != -1){
			InOut.OutMessage(DadosAlunoEncontrado(aluno));
		}else{
			AlunoNaoEncontrado();
		}	
	}
	
	private static void DeletarAluno(){
		aluno = new Aluno();
		ProcurarDefinindoMatricula (aluno, "Deletar");
		if(listaAluno.Delete(aluno)){						 
			InOut.OutMessage(DadosAlunoEncontrado(aluno) + "Clique Confirmar para Remover o aluno do registro.");			
		}else{
			AlunoNaoEncontrado();
		}		
	}
	
	private static void ApagarAluno() {
		InOut.OutMessage("Esta Função ainda não foi Feita!");
	}

	public static String ProcurarDefinindoMatricula (Aluno paluno, String complemento) {
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
}
