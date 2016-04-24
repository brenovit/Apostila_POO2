package com.qst1.dao;

import java.util.ArrayList;

import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;
import com.recursos.InOut;

public class AlunoDAO implements DAO {
	
	private ArrayList<Aluno> listaAluno;
	private ArrayList<Disciplina> listaDisciplina;

	private String msg = "";
	
	public AlunoDAO(){
		listaAluno = new ArrayList<Aluno>();
	}
	
	@Override
	public void Create(Object o) {
		listaAluno.add((Aluno) o);
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
			listaDisciplina = aluno.getMaterias();
			for(Disciplina disciplina : listaDisciplina){
				msg += "\nCodigo: " + disciplina.getCodigo() +
						"\nNome: " + disciplina.getNome() +
						"\nNota: " + disciplina.getNota() +
						"\nAprovado: " + disciplina.getAprovado() +
						"\n------------------------------------";
			}
		}
		InOut.OutMessage(msg);		
	}
	
	public int FindMateria(Aluno aluno, Disciplina disc){
		int pos = Find(aluno);
		if(pos != -1){
			int posAux = 0;
			pos = -1;
			listaDisciplina = aluno.getMaterias();
			while((posAux < listaDisciplina.size()) && 
					(!listaDisciplina.get(posAux).getCodigo().equals(disc.getCodigo()))){
				posAux++;
			}
			if((posAux < listaDisciplina.size()) && 
					(listaDisciplina.get(posAux).getCodigo().equals(disc.getCodigo())) == true){
				pos = posAux;
			}
		}
		return pos;
	}
	
	public void AddNota(Aluno aluno, Disciplina disc, Double nota){
		int pos = FindMateria(aluno, disc);
		if(pos != -1){
			listaDisciplina = aluno.getMaterias();
			listaDisciplina.get(pos).setNota(nota);
		}
	}
	
	public boolean Aprovado (Aluno aluno, Disciplina disc){
		int pos = FindMateria(aluno, disc);
		boolean aprovacao = false;
		if(pos != -1){
			listaDisciplina = aluno.getMaterias();
			aprovacao = listaDisciplina.get(pos).getAprovado();
		}
		return aprovacao;		
	}
}
