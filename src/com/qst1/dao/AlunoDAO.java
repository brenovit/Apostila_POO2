package com.qst1.dao;

import java.util.ArrayList;

import com.qst1.vo.Aluno;
import com.recursos.InOut;

public class AlunoDAO implements DAO {
	private ArrayList<Aluno> listaAluno;
	private String msg = "";
	private int pos = 0;
	
	public AlunoDAO(){
		listaAluno = new ArrayList<Aluno>();
	}
	
	@Override
	public <T> void Create(T o) {
		listaAluno.add((Aluno) o);
		//o aluno foi adicionado na lista
	}

	@Override
	public void Show() {
		msg = "";
		for(Aluno aluno : listaAluno){
			msg += "\nMatricula: " + aluno.getMatricula()+
					"\nNome: " + aluno.getNome()+
					"\nCPF: " + aluno.getCPF()+
					"\n------------------------------------";
		}
		InOut.OutMessage(msg);
	}
	
	@Override
	public <T> void Find(T o) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public <T> void Uptade(T o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void Delete(T o) {
		// TODO Auto-generated method stub
		
	}	
}
