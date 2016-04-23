package com.qts1.testes;

import com.qst1.dao.AlunoDAO;
import com.qst1.vo.Aluno;

public class Principal {
	public static void main(String[] args){
		AlunoDAO lista = new AlunoDAO();
		
		Aluno al1 = new Aluno("Breno", "01234567890");
		Aluno al2 = new Aluno("Mauricio", "09876543210");
		
		lista.Create(al1);
		lista.Create(al2);
		
		lista.Show();
		
	}
}
