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
	
	public String Show(){    //MOstrar dados do usuario contidos na lista de usuarios 
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
	
	public int Update(){
		
	}
	
}
