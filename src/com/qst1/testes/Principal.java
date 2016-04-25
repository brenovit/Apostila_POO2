package com.qst1.testes;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;
import com.recursos.InOut;

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
		lista.Show();
		
		//atualizar dados do aluno pela matricula
		Aluno obj = new Aluno();
		obj.setMatricula(3);	//<- obj.setMatricula(al3.getMatricula());
		obj.setNome("Hulk");
		obj.setCPF("147258369");
		lista.Uptade(obj);
		
		//deletar aluno
		lista.Delete(al2);
		
		//mostrar todos os alunos cadastrados
		lista.Show();		
		
		//procurar aluno na lista
		lista.Find(al3);
		
		//checar existencia do aluno na lista
		if(lista.Find(al1) == -1 ){
			InOut.OutMessage("Aluno não cadastrado");
		}else{
			InOut.OutMessage("Aluno Cadastrado\nNome: " + al1.getNome());
		}
		
		//criar grade escolar
		GradeEscolar grade = new GradeEscolar();
		
		//criar as materias
		Disciplina disc1 = new Disciplina("POO");
		Disciplina disc2 = new Disciplina("ED");
		Disciplina disc3 = new Disciplina("ASIC");
		Disciplina disc4 = new Disciplina("PI");
		
		//cadastrar as materias na grade escolar
		grade.CadastrarDisciplina(disc1);
		grade.CadastrarDisciplina(disc2);
		grade.CadastrarDisciplina(disc3);
		grade.CadastrarDisciplina(disc4);
		
		//mostrar todas as disciplinas cadastradas
		grade.Show();

		//checar existencia do aluno na lista
		if(grade.Find(disc3) == -1){
			InOut.OutMessage("Disciplina não cadastrada");
		}else{
			InOut.OutMessage("Disciplina Cadastrada\nNome: " + disc3.getNome());
		}
		
		//adicionar materias ao aluno
		grade.CadastrarGrade(al1, disc1);
		grade.CadastrarGrade(al1, disc2);
		
		//Checar se o aluno esta cadastrado em uma materia
		if(lista.FindMateria(al1, disc2) == -1){
			InOut.OutMessage("Disciplina não cadastrada");
		}else{
			InOut.OutMessage("Disciplina Cadastrada\nDisciplina: " + disc1.getNome() + "\nAluno: "+al1.getNome());
		}
		
		//adicionar nota na disciplina cadastrada de um aluno
		lista.AddNota(al1,disc1,8.0);
		lista.AddNota(al1,disc2,5.0);
		
		//verificar se o aluno esta aprovado na disciplina1
		if(lista.Aprovado(al1, disc1)){
			InOut.OutMessage("Aluno Aprovado");
		}else{
			InOut.OutMessage("Aluno Reprovado");
		}
		
		//verificar se o aluno esta aprovado na disciplina2
		if(lista.Aprovado(al1, disc2)){
			InOut.OutMessage("Aluno Aprovado");
		}else{
			InOut.OutMessage("Aluno Reprovado");
		}
		
		//mostrar as disciplinas cadastrados do aluno, sua nota, nome da disciplina, estado da aprovação
		lista.ShowDisciplinasMatriculadas(al1);
	}
}
