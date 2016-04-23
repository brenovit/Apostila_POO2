package com.qst1.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.qst1.vo.Aluno;
import com.recursos.InOut;

public class TelaPrincipal {
	
	private static ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
	
	public static void main(String[] args) throws IOException{
		Menu();
		System.out.println("Programa Finzalizado");
	}
	
	public static void Menu() throws IOException{
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
					break;
				case 1:
					MenuAluno();
					break;
				case 2:
					MenuGrade();
					break;
				case 3:
					AlterarAluno();
					break;
				default:
					InOut.OutMessage("Opção Invalida!", "Erro!");
					break;		
			}
			}while(op != 0);
	}
	
	
	
	public static void MenuAluno() throws IOException{
		int op;
		do{
		String opcoes = "Digite um dos Numeros abaixo:\n"+
						"1 - Cadastrar Aluno\n"+
						"2 - Listar Alunos\n"+
						"3 - Alterar Aluno\n"+
						"4 - Procurar por Aluno\n"+
						"5 - Deletar Aluno\n"+
						"6 - Apagar Aluno\n"+
						"0 - Voltar";
		/*"Digite um dos Numeros abaixo:\n"+
		"1 - Cadastrar Aluno\n"+
		nome:breno
		cpf: 123
		"2 - Lista Aluno\n"+
		"3 - Alterar Aluno\n"+
		"4 - Procurar por Aluno\n"+
		"5 - Deletar Aluno\n"+
		"6 - Apagar Aluno\n"+
		"0 - Sair";*/
		op = InOut.InInt(opcoes);
		switch(op){
			case 0:
				//InOut.OutMessage("O programa será Finalizado", "Atenção");
				break;
			case 1:
				CadastrarAluno();
				break;
			case 2:
				ListaAluno();
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
		Menu();
	}
	
	public static void MenuGrade(){
		int op;
		do{
			String opcoes = "Digite um dos Numeros abaixo:\n"+
							"1 - Cadastrar Grade do Aluno\n"+
							"2 - Listar Alunos\n"+
							"3 - Alterar Aluno\n"+
							"4 - Procurar por Aluno\n"+
							"5 - Deletar Aluno\n"+
							"6 - Apagar Aluno\n"+
							"0 - Voltar";
			/*"cadsatrar grade do aluno:
			1 escolha o aluno:
			"1 - Breno\n"+
			"2 - mauricio\n"+
			digitar matricula = 1
			escolha as materias
			1 - poo 
			2 - ed
			3 - ads
			digite o codigo da materia = 1
			a materia poo vai ser adicionada em breno
			cadastreu denovo na opçãpo 2
			
			"3 - Alterar Aluno\n"+
			"4 - Procurar por Aluno\n"+
			"5 - Deletar Aluno\n"+
			"6 - Apagar Aluno\n"+
			"0 - Sair";*/
			op = InOut.InInt(opcoes);
			switch(op){
				case 0:
					InOut.OutMessage("O programa será Finalizado", "Atenção");
					break;
				case 1:
					//CadastrarMateria();
					break;
				case 2:
					//ListaAluno();
					break;		
				default:
					InOut.OutMessage("Opção Invalida!", "Erro!");
					break;		
			}
			}while(op != 0);
	}
	
	public static void MenuNota(){
		/*"Digite um dos Numeros abaixo:\n"+
		"1 - inserir nota do aluno:\n"+
		"2 - checar aprovação
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
	}
	public static void CadastrarAluno() {
		String nome = InOut.InString("Insira o Nome do Aluno:");
		String cpf = InOut.InString("Digite o CPF do Aluno:");
		Aluno aluno = new Aluno(nome, cpf);
		listaAluno.add(aluno);
	}
	
	public static void ListaAluno() throws IOException{
		if(listaAluno.isEmpty()){
			InOut.OutMessage("Nenhum Aluno Cadastrado");
			return;
		}
		FileWriter arq = new FileWriter("listaAluno.txt");
		PrintWriter gravaArq = new PrintWriter(arq);
		String relatorio = "";
		gravaArq.printf("---------Lista de Aluno---------\r\n");
		for(int i = 0; i < listaAluno.size(); i++){
			Aluno prod = listaAluno.get(i);
			gravaArq.printf(" - |MATRICULA| %d |NOME| %s |CPF| %s\r\n", 
							prod.getMatricula(), prod.getNome(), prod.getCPF());
			
			relatorio += "\nMatricula: " + prod.getMatricula() + 
						 "\nNome: " + prod.getNome() +
						 "\nCPF: " + prod.getCPF()+
						 "\n----------------------------------------------------------\r";
		}
		gravaArq.close();
		InOut.OutMessage(relatorio);
	}
	
	public static void AlterarAluno(){
		if(listaAluno.size() == 0){
			InOut.OutMessage("Lista Vazia");
			return;
		}
		String nomeAlunoPesquisar = InOut.InString("Digite o Nome do Aluno que deseja procurar:");
		for(int i=0; i < listaAluno.size(); i++){
			Aluno Aluno = listaAluno.get(i);
			
			if(nomeAlunoPesquisar.equalsIgnoreCase(Aluno.getNome())){
				String nomeNovo = InOut.InString("Digite o novo Nome do Aluno:");
				Aluno.setNome(nomeNovo);
				InOut.OutMessage("Nome alterado com sucesso");
				break;
			}
		}
		InOut.OutMessage("Aluno não encontrado");
	}
	
	public static void ProcurarAluno(){
		String exibir = "";
		String nomeArq = "listaAluno.txt";
		String linha = "";
		File arq = new File(nomeArq);
		
		if(arq.exists()){
			exibir = "RELATORIO";
			try{
				 FileReader abrindo = new FileReader(nomeArq);
				 BufferedReader leitor = new BufferedReader(abrindo);
				 while(true){
					 linha = leitor.readLine();
					 if(linha == null)
						 break;
					 exibir += linha + "\n";
				 }
				 leitor.close();
			}catch(Exception e){
				InOut.OutMessage("Erro: \n"+e.getMessage(), "ERRO");
			}
			InOut.OutMessage(exibir, "LISTA DE AlunoS");
		}else{
			InOut.OutMessage("Arquivo inexistente", "ERRO");
		}
	}
	
	public static void DeletarAluno(){
		if(listaAluno.size() == 0){
			InOut.OutMessage("Lista Vazia");
			return;
		}
		String nomeAlunoPesquisar = InOut.InString("Digite o Nome do Aluno que deseja Deletar:");
		for(int i=0; i < listaAluno.size(); i++){
			Aluno Aluno = listaAluno.get(i);
			
			if(nomeAlunoPesquisar.equalsIgnoreCase(Aluno.getNome())){
				listaAluno.remove(i);
				InOut.OutMessage("Aluno deletado com Sucesso!");
				break;
			}
		}
		InOut.OutMessage("Nome alterado com sucesso");
	}
	
	public static void ApagarAluno() {
		if(listaAluno.isEmpty()){
			InOut.OutMessage("Nenhum Aluno Cadastrado");
			return;
		}
		listaAluno.clear();
		InOut.OutMessage("Todos os Aluno foram Apagados!");
	}
}
