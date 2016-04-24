package com.qst1.testes;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;

public class Principal {
	public static void main(String[] args){
		//lista de alunos cadastrados
		AlunoDAO lista = new AlunoDAO();
		
		//criar os aluno
		Aluno al1 = new Aluno("Breno", "01234567890");
		Aluno al2 = new Aluno("Mauricio", "09876543210");
		Aluno al3 = new Aluno("Mateus", "1238569753");
		
		//cadastrar alunos
		lista.Create(al1);
		lista.Create(al2);
		lista.Create(al3);
		
		//mostrar alunos cadastrados
		//lista.Show();
		
		//atualizar dados do aluno pela matricula
		/*Aluno obj = new Aluno();
		obj.setMatricula(3);
		obj.setNome("Hulk");
		//obj.setCPF("147258369");
		lista.Uptade(obj);*/
		
		//deletar aluno
		/*lista.Delete(al2);
		lista.Show();*/
		//lista.Find(al3);
		//checar existencia do aluno na lsita
		/*if(lista.Find(al1) == -1 ){
			InOut.OutMessage("Aluno não cadastrado");
		}else{
			InOut.OutMessage("Aluno Cadastrado\nNome: " + al1.getNome());
		}*/
		
		//criação da grade escolar
		GradeEscolar grade = new GradeEscolar();
		
		//criando as materias
		Disciplina disc1 = new Disciplina("POO");
		Disciplina disc2 = new Disciplina("ED");
		Disciplina disc3 = new Disciplina("ASIC");
		Disciplina disc4 = new Disciplina("PI");
		
		//cadastrando as materias na grade escolar
		grade.CadastrarDisciplina(disc1);
		grade.CadastrarDisciplina(disc2);
		//grade.CadastrarDisciplina(disc3);
		
		//mostrar todas as disciplinas possiveis
		//grade.Show();
		
		//System.out.println("disc1 e disc2 tem codigos diferentes");

		//checar existencia do aluno na lista
		/*if(grade.Find(disc3) == -1){
			InOut.OutMessage("Disciplina não cadastrada");
		}else{
			InOut.OutMessage("Disciplina Cadastrada\nNome: " + disc3.getNome());
		}*/
		
		//adicionando materias ao aluno
		grade.CadastrarGrade(al1, disc1);
		grade.CadastrarGrade(al1, disc2);
		
		//mostrando as disciplinas cadastrados no aluno
		lista.ShowDisciplinasMatriculadas(al1);
		
		//adicionando nota na disciplina cadastrada de um aluno
		lista.AddNota(al1,disc1,8.0);
		
	}
}
