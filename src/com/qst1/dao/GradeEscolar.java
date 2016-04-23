package com.qst1.dao;

import java.util.ArrayList;

import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;
import com.recursos.InOut;

public class GradeEscolar {
	private ArrayList<Disciplina> listaDisc;
	private String msg = ""; 

	public GradeEscolar(){
		listaDisc = new ArrayList<Disciplina>();	
	}
	
	public void AdicionarDiciplina(Disciplina disciplina){
		listaDisc.add(disciplina);
	}

	public void Show() {
		msg = "Codigo\t-\tNome\n";
		for(Disciplina disciplina : listaDisc){
			msg += disciplina.getCodigo()+"\t-\t"+disciplina.getNome() + "\n";
		}
		InOut.OutMessage(msg);
	}

	public int Find(Disciplina disc) {
		int pos = -1;
		int posAux = 0;
		while((posAux < listaDisc.size()) && (!listaDisc.get(posAux).getCodigo().equals(disc.getNome()))){
			posAux++;
		}
		if((posAux < listaDisc.size()) && (!listaDisc.get(posAux).getCodigo().equals(disc.getNome())) == true){
			pos = posAux;
		}
		return pos;
	}

	public <T> void Delete(T o) {
		/*int pos = Procurar(o);
		if(pos != -1){
			listaDisc.remove(pos);
		}else{
			InOut.OutMessage("Cliente não Cadastrado");
		}	*/
	}	
	
	public void CadastrarGrade(Aluno aluno, Disciplina disc){
				
	}
}
