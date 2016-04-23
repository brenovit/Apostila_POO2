package com.qst1.testes;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;
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
		
		/*Aluno obj = new Aluno();
		obj.setMatricula(3);
		obj.setNome("Hulk");
		//obj.setCPF("147258369");
		lista.Uptade(obj);
		
		lista.Delete(al2);
		lista.Show();
		
		if(lista.Find(al2) == -1 ){
			InOut.OutMessage("Aluno, n�o encontrado");
		}else{
			InOut.OutMessage("Aluno, encontrado");
			InOut.OutMessage("Aluno = " + al2.getNome());
		}*/
		
		GradeEscolar grade = new GradeEscolar();
		Disciplina disc1 = new Disciplina("POO");
		Disciplina disc2 = new Disciplina("ED");
		Disciplina disc3 = new Disciplina("ASIC");
		
		grade.AdicionarDiciplina(disc1);
		grade.AdicionarDiciplina(disc2);
		grade.AdicionarDiciplina(disc3);
		
		grade.Show();
		
		grade.CadastrarGrade(al1, disc1);
		grade.CadastrarGrade(al1, disc2);
		
		lista.ShowDisciplinas(al1);
		
	}
}
