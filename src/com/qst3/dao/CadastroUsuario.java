package com.qst3.dao;

import java.util.ArrayList;
import com.qst3.vo.Usuario;

public class CadastroUsuario {

	private ArrayList<Usuario> listaUsuario;   //Criando a lista
	
	
	public CadastroUsuario(){				//Contrutor
		listaUsuario = new ArrayList<Usuario>();    //Instanciando a lista no constrututor, para não ficar criando uma nova lista o tempo todo no teste
	}	
	  
	public void Inserir(Usuario user){
		listaUsuario.add(user);
	}
	
	public String Show(){    //Mostrar dados do usuario contidos na lista de usuarios 
		String texto = "";  
			for(Usuario user: listaUsuario){  //Para cada usuario (variavel user) contido em listaUsuario (variavel) faça alguma coisa.
				texto += "\n Nome.: "+user.getNome() + 
						 "\n Email.: "+user.getEmail() +
						 "\n Login.: "+user.getLogin() + 
						 "\n Senha.: "+user.getSenha() +
						 "\n------------------------------";
			}
		return texto;
	}
	
	public int Find(Usuario user){
		int posicaoAtual = -1;
		int posicaoAux = 0;
		
		while((posicaoAux < listaUsuario.size()) &&
				(!listaUsuario.get(posicaoAux).getLogin().equals(user.getLogin()))){  //Se o objeto contido na lista na posição Aux tiver o login diferente(por causa da !) ao login do obejto passado como parametro
			posicaoAux++;   //PosicaoAux aumenta																			
		}
		
		if((posicaoAux < listaUsuario.size()) &&
				(listaUsuario.get(posicaoAux).getLogin().equals(user.getLogin()))){  //Se o objeto contido na lista na posição Aux tiver o login diferente(por causa da !) ao login do obejto passado como parametro
			posicaoAtual = posicaoAux;
		}
		return posicaoAtual;
	}
	
	public void Update(Usuario user){  //Atualizar os dados do cliente
		int posicao = Find(user);
		if(posicao != -1){
			listaUsuario.get(posicao).setNome(user.getNome());
			listaUsuario.get(posicao).setEmail(user.getEmail());
			listaUsuario.get(posicao).setLogin(user.getLogin());
			listaUsuario.get(posicao).setSenha(user.getSenha());
		}
	}
	
	
	
}
