package com.qst3.ui;

import com.qst3.dao.CadastroUsuario;
import com.qst3.vo.Usuario;
import com.recursos.InOut;

public class MainQuestao3 {
	
	private static CadastroUsuario lista = new CadastroUsuario();
	private static Usuario usuario;
	
	
	public static void main(String[] args){

		lista.LoadDataFile();
		Menu();
	}

	
	public static void Menu(){
		usuario = new Usuario();;
		int op;
		
		do{
			String opcoes = "Digite um dos numeros abaixo.: \n" + 
							"1 - Cadastrar Usuario\n" + 
							"2 - Procurar Usuario\n" +
							"3 - Alterar Usuario\n" +
							"4 - Listar Usuarios\n" + 
							"5 - Deletar Usuario\n" +
							"6 - Testar autenticação\n" +
							"0 - Sair";
			
			op = InOut.InInt(opcoes);
			switch(op){
				case 1:
					CadastroUsuario();
					break;
				case 2:
					ProcuarUsuario();				
					break;
				case 3:
					Alterar(usuario, "alterar");
					break;
				case 4:
					Listar();
					break;
				case 5:
					Deletar(usuario);
					break;
				case 6:
					testarAutenticacao();
					break;
				case 0:
					break;
				default:
					InOut.OutMessage("Opção inexistente");
					break;
			}
			lista.SaveDataFile();
		}while(op != 0);
		
	}
	
		public static void CadastroUsuario(){
			Usuario user = new Usuario();
			
			String nome = InOut.InString("Digite o Nome.: ") ;
			String email = InOut.InString("Digite o E-mail.: ");
			String login = InOut.InString("Digite o Login.: ");
			String senha = InOut.InString("Digite a Senha.: ");
			
			user.setNome(nome);
			user.setEmail(email);
			user.setLogin(login);
			user.setSenha(senha);
			lista.Inserir(user);
			//adicionar o usuario na lista
		}
		
		public static void ProcuarUsuario(){
			String login = InOut.InString("Informe o Login do usuario.: ");
			Usuario user = new Usuario();
			user.setLogin(login);	
				if(lista.Find(user, false) != -1){
					InOut.OutMessage("Este usuario esta cadastrado");
				}else{
					InOut.OutMessage("Usuario não encontrado");
				}
		}
		
		public static void Alterar(Usuario usuario, String msg){
			String texto = "";
			String Login = InOut.InString("Informe o login do usuario que vc deseja alterar.: ");
			
			usuario.setLogin(Login);
			System.out.println(Login);
			
			if(lista.Find(usuario,true) != -1){
				texto = "\nNome.: "+usuario.getNome()+
						"\nEmail.: "+usuario.getEmail()+
						"\nLogin.: "+usuario.getLogin()+
						"\nSenha.: "+usuario.getSenha()+
						"\n-------------------------------------\n";
				String nome = InOut.InString(texto  + "\nDigite o novo Nome.: \n");
				String email = InOut.InString(texto + "\nDigite o novo Email do Usuario.: \n");
				String senha = InOut.InString(texto + "\nDigite a nova senha.: \n");
				
				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setSenha(senha);
				lista.Update(usuario);
			}else{
				lista.UsuarioNaoEncontrado();
			}
		}
		
		public static void Deletar(Usuario user){
			String Login = InOut.InString("Insira o Login do Usuario que deseja ");
			user.setLogin(Login);
			lista.Find(user, false);
			if(lista.Delete(user)){						 
				InOut.OutMessage(lista.Show(user) + "Clique Confirmar para Remover o aluno do registro.");			
			}else{
				lista.UsuarioNaoEncontrado();
			}		
		}
		
		public static void Listar(){
			InOut.OutMessage(lista.Show());
		}
		
		public static void testarAutenticacao(){
			String login = InOut.InString("Informe o Login do usuario.: ");			
			String senha = InOut.InString("Informe a senha do usuario.: ");
			String testeLogin, testeSenha;
			Usuario user = new Usuario();
			user.setLogin(login);
			
			if(lista.TestarAutenticacao(user, senha)){				
				InOut.OutMessage("Usuario Autenticado!");
			}else{
				InOut.OutMessage("Usuaio ou Senha Incorreta!");
			}
		}
	
}
