package com.qst1.dao;

import java.util.ArrayList;

import com.qst1.vo.Disciplina;

public class GradeEscolar {
	ArrayList<Disciplina> listDisc = new ArrayList<Disciplina>();
	
	public void AdicionarDiciplina(Disciplina disciplina){
		listDisc.add(disciplina);
	}
	
}
