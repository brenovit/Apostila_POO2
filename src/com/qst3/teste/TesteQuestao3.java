package com.qst3.teste;

import com.qst3.dao.CadastrarTeste;
import com.qst3.vo.Teste;

public class TesteQuestao3 {

	public static void main(String[] args) {
		
		CadastrarTeste lista = new CadastrarTeste();
		
		CadastrarTeste listaTestes = lista;
		
		Teste user1 = new Teste();
		user1.setLogin("breno");
		user1.setSenha("123");
					// Metodo Construtor
		
		Teste user2 = new Teste("mauricio","456");
		
		System.out.println("\nUser1 Login: "+user1.getLogin()+" Senha: "+user1.getSenha());
		System.out.println("\nUser2 Login: "+user2.getLogin()+" Senha: "+user2.getSenha());
	}

}
