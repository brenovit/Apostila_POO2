package com.qst3.testes;

import com.qst3.dao.CadastroUsuario;
import com.qst3.vo.Usuario;
import com.recursos.InOut;


public class TesteQst3 {

	public static void main(String[] args) {
		
		CadastroUsuario lista = new CadastroUsuario();
		
 		Usuario user1 = new Usuario("Mauricio","mauricio@gmail.com","luymau","123456");
		Usuario user2 = new Usuario("Breno","breno@gmail.com","brenovit","987654");
		
		lista.Inserir(user1);
		lista.Inserir(user2);
		
		InOut.OutMessage(lista.Show());
	}

}
