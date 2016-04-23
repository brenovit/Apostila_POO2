package com.qst1.dao;

import java.util.ArrayList;

import com.qst1.vo.Aluno;

public class AlunoDAO implements DAO {
	private static ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();

	@Override
	public <T> void Create(T o) {
		listaAluno.add((Aluno) o);
	}

	@Override
	public void Show() {
		// TODO Auto-generated method stub
		
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
