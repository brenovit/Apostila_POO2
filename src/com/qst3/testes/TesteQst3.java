package com.qst3.testes;

import com.qst3.dao.CadastroUsuario;
import com.qst3.vo.Usuario;
import com.recursos.InOut;


public class TesteQst3 {

	public static void main(String[] args) {
		
		CadastroUsuario lista = new CadastroUsuario();  //Criando lista de usuario
		
 		Usuario user1 = new Usuario("Mauricio","mauricio@gmail.com","luymau","123456");  //criando o usuario
		Usuario user2 = new Usuario("Breno","breno@gmail.com","brenovit","987654");
		Usuario user3 = new Usuario();
		
		user3.setNome("Mateus");
		user3.setEmail("mateus@gamil.com");
		user3.setLogin(user2.getLogin());
		user3.setSenha("741852");
		
		lista.Inserir(user1);   //inserindo usuario
		lista.Inserir(user2);
		
		lista.Update(user3);
		
		InOut.OutMessage(lista.Show());  //mostrando a lista de usuario
		
		
	}

}
