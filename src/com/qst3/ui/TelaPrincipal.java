package com.qst3.ui;

import com.qst3.dao.CadastroUsuario;
import com.qst3.vo.Usuario;
import com.recursos.InOut;

public class TelaPrincipal {
	
	private CadastroUsuario lista = new CadastroUsuario();
	
	public static void main(String[] args){
		

	}

	
	public void Menu(){
		int op;
		
		do{
			String opcoes = "Digite um dos numeros abaixo.: \n" + 
							"1 - Cadastrar Usuario\n" + 
							"2 - Procurar Usuario\n" +
							"3 - Alterar Usuario\n" +
							"4 - Listar Usuarios\n" + 
							"5 - Deletar Usuario\n" +
							"6 - Testar autenticação" +
							"0 - Sair";
			
			op = InOut.InInt(opcoes);
			switch(op){
				case 1:
					
					break;
				case 2:
									
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				case 5:
					
					break;
				case 6:
					
					break;
				case 0:
					
					break;
				default:
					InOut.OutMessage("Opção inexistente");
					break;
			}
		}while(op != 0);
	}
	
		public void CadastroUsuario(){
			Usuario user = new Usuario();
			
			String nome = InOut.InString("Digite o Nome.: ") ;
			String email = InOut.InString("Digite o E-mail.: ");
			String login = InOut.InString("Digite o Login.: ");
			String senha = InOut.InString("Digite a Senha.: ");
			
			user.setNome(nome);
			user.setEmail(email);
			user.setLogin(login);
			user.setSenha(senha);
			
			//adicionar o usuario na lista

		}
	
}
