package com.qst1.dao;

import java.util.ArrayList;

import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;
import com.recursos.InOut;

public class GradeEscolar{
	
	private ArrayList<Disciplina> listaDisc;
	private String msg = ""; 

	public GradeEscolar(){
		listaDisc = new ArrayList<Disciplina>();	
	}
	
	public void CadastrarDisciplina(Disciplina disc) {
		listaDisc.add(disc);
		
	}

	public String Show() {
		for(Disciplina disciplina : listaDisc){
			msg += "\nCodigo: " + disciplina.getCodigo()+
					"\nNome: "+disciplina.getNome() + 
					"\n------------------------------------";
		}
		return msg;
	}
	
	public int Find(Disciplina disc){
        int posicao = -1;        
        int posAux = 0;
        
        while((posAux < listaDisc.size()) &&
                (!listaDisc.get(posAux).getCodigo().equals(disc.getCodigo()))){
            posAux++;
        }
        if((posAux < listaDisc.size()) && 
        		(listaDisc.get(posAux).getCodigo().equals(disc.getCodigo())) == true){
            posicao = posAux;
        }
        return posicao; 
	}
	
	public void Delete(Disciplina disciplina) {
		int pos = Find(disciplina);
		if(pos != -1){
			listaDisc.remove(pos);
		}else{
			InOut.OutMessage("Disciplina não Cadastrado");
		}
	}
	
	public void CadastrarGrade(Aluno aluno, Disciplina disc){
		int pos = Find(disc);
		if(pos != -1){
			aluno.addMateria(disc);
		}
	}	
}
