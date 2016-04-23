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
	public <T> int Find(T o) {
		Aluno aluno = (Aluno) o;
        int posicao = -1;
        
        int posAux = 0;
        
        while((posAux < listaAluno.size() ) &&
                (!listaAluno.get(posAux).getMatricula().equals(aluno.getMatricula()))){
             
            posAux++;
        }
        if((posAux<listaAluno.size()) && (listaAluno.get(posAux).getMatricula().equals(aluno.getMatricula())) == true  ){
            posicao = posAux;
        }
        return posicao; 
	}
	
	@Override
	public <T> void Uptade(T o) {
		Aluno aluno = (Aluno) o;
        int posicao = Find(aluno);
       if(posicao != -1){
    	   listaAluno.get(posicao).setNome(aluno.getNome());
    	   listaAluno.get(posicao).setCPF(aluno.getCPF());
       }
	}

	@Override
	public <T> void Delete(T o) {
		 Aluno aluno = (Aluno) o;
	        int posicao = Find(aluno);
	        if(posicao != -1){
	        	listaAluno.remove(posicao);
		
	     }	
	}
}
