package com.qts1.testes;

import com.qst1.dao.AlunoDAO;
import com.qst1.vo.Aluno;
import com.recursos.InOut;

public class Principal {
	public static void main(String[] args){
		AlunoDAO lista = new AlunoDAO();
		
		Aluno al1 = new Aluno("Breno", "01234567890");
		Aluno al2 = new Aluno("Mauricio", "09876543210");
		Aluno al3 = new Aluno("Mateus", "1238569753");
		
		
		lista.Create(al1);
		lista.Create(al2);
		lista.Create(al3);
		
		lista.Show();
		
		Aluno obj = new Aluno();
		obj.setMatricula(3);
		obj.setNome("Hulk");
		//obj.setCPF("147258369");
		lista.Uptade(obj);
		
		lista.Delete(al2);
		lista.Show();
		
		if(lista.Find(al2) == -1 ){
			InOut.OutMessage("Aluno, não encontrado");
		}else{
			InOut.OutMessage("Aluno, encontrado");
			InOut.OutMessage("Aluno = " + al2.getNome());
		}
		
		
		
		
				
		//agora eu vou atualizar o meu projeto
		//para isso eu vou no git clico em sync
		//quando finalizar eu dou um refresh no projeto
		//o mesmo vale para vc quando eu colcoar algo novo, vc vai no git, da um sync, dps vem no eclipse e da um refresh
		//ta pronto o sorvetinho
		//danoninho haha, ok, vou ligar no hang
		//fechando aqui
	}
}
