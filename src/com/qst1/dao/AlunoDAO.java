package com.qst1.dao;

import java.util.ArrayList;

import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;
import com.recursos.InOut;

public class AlunoDAO implements DAO {
	
	private ArrayList<Aluno> listaAluno;
	private String msg = "";
	
	public AlunoDAO(){
		listaAluno = new ArrayList<Aluno>();
	}
	
	@Override
	public void Create(Object o) {
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
	public int Find(Object o) {
		Aluno aluno = (Aluno) o;
        int posicao = -1;        
        int posAux = 0;
        
        while((posAux < listaAluno.size()) &&
                (!listaAluno.get(posAux).getMatricula().equals(aluno.getMatricula()))){
            posAux++;
        }
        if((posAux < listaAluno.size()) && 
        		(listaAluno.get(posAux).getMatricula().equals(aluno.getMatricula())) == true){
            posicao = posAux;
        }
        return posicao; 
	}
	
	@Override
	public void Uptade(Object o) {
		Aluno aluno = (Aluno) o;
        int posicao = Find(aluno);
        if(posicao != -1){
        	listaAluno.get(posicao).setNome(aluno.getNome());
        	listaAluno.get(posicao).setCPF(aluno.getCPF());
       }
	}

	@Override
	public void Delete(Object o) {
		Aluno aluno = (Aluno) o;
	    int posicao = Find(aluno);
	    if(posicao != -1){
	    	listaAluno.remove(posicao);		
	    }
	}
	
	public void ShowDisciplinasMatriculadas(Aluno aluno) {
		int pos = Find(aluno);
		if(pos != -1){
			msg = "";
			ArrayList<Disciplina> listaDisciplina = aluno.getMaterias();
			for(Disciplina disciplina : listaDisciplina){
				msg += "\nCodigo: " + disciplina.getCodigo() +
						"\nNome: " + disciplina.getNome() +
						"\n------------------------------------";
			}
		}
		InOut.OutMessage(msg);
		
	}
	
	public void AddNota(Aluno aluno, Disciplina disciplina, Double nota){
		
	}
	
}
