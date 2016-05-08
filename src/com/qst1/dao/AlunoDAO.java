package com.qst1.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;

public class AlunoDAO implements DAO {
	private File arq = new File("DadosAluno.json");
	private List<Aluno> listaAluno;
	private List<Disciplina> listaDisciplina;
	private String msg = "";
	
	public AlunoDAO(){
		listaAluno = new ArrayList<Aluno>();
	}
	
	public int size(){
		return listaAluno.size();
	}
	
	public List<Aluno> getLista(){
		return listaAluno;
	}
	@Override
	public void Create(Object o) {
		listaAluno.add((Aluno) o);
	}

	@Override
	public String Show() {
		msg = "";
		for(Aluno aluno : listaAluno){
			msg += "\nMatricula: " + aluno.getMatricula()+
					"\nNome: " + aluno.getNome()+
					"\nCPF: " + aluno.getCPF()+
					"\n------------------------------------";
		}
		return msg;
	}
	
	@Override
	public int Find(Object o) {
		return Find(o, false);
	}
	
	@Override
	public int Find(Object o, boolean alterar) {
		//Aluno aluno = (Aluno) o;
        int posicao = -1;        
        int posAux = 0;
        
        while((posAux < listaAluno.size()) &&
                (!listaAluno.get(posAux).getMatricula().equals(((Aluno)o).getMatricula()))){
            posAux++;
        }
        if((posAux < listaAluno.size()) && 
        		(listaAluno.get(posAux).getMatricula().equals(((Aluno)o).getMatricula())) == true){
        	if(alterar){
        		((Aluno)o).setCPF(listaAluno.get(posAux).getCPF());
        		((Aluno)o).setNome(listaAluno.get(posAux).getNome());
        		((Aluno)o).setMaterias(listaAluno.get(posAux).getMaterias());
        		((Aluno)o).setMatricula(listaAluno.get(posAux).getMatricula());
        	}
            posicao = posAux;
        }
        return posicao;
	}
	
	@Override
	public void Update(Object o) {
		Aluno aluno = (Aluno) o;
        int posicao = Find(aluno);
        if(posicao != -1){
        	listaAluno.get(posicao).setNome(aluno.getNome());
        	listaAluno.get(posicao).setCPF(aluno.getCPF());
        }
	}

	@Override
	public boolean Delete(Object o) {
		Aluno aluno = (Aluno) o;
	    int posicao = Find(aluno);
	    if(posicao != -1){
	    	listaAluno.remove(posicao);	
	    	return true;
	    }
	    return false;
	}
	
	public String ShowDisciplinasMatriculadas(Aluno aluno) {
		msg = "";
		int pos = Find(aluno,true);
		if(pos != -1){
			listaDisciplina = aluno.getMaterias();
			for(Disciplina disciplina : listaDisciplina){
				msg += "\nCodigo: " + disciplina.getCodigo() +
						"\nNome: " + disciplina.getNome() +
						"\nNota: " + disciplina.getNota() +
						"\nAprovado: " + disciplina.getAprovado() +
						"\n------------------------------------";
			}
		}
		return msg;
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
	
	public void RemoverGrade(Aluno aluno, Disciplina disc){
		int pos = FindMateria(aluno, disc);
		if(pos != -1){
			aluno.removeDisciplina(pos);
		}
	}
	
	public boolean AddNota(Aluno aluno, Disciplina disc, Double nota){
		int pos = FindMateria(aluno, disc);
		if(pos != -1){
			listaDisciplina = aluno.getMaterias();
			listaDisciplina.get(pos).setNota(nota);
			return true;
		}
		return false;
	}
	
	public void LimparLista(){
		Aluno.zerarGerador();
		listaAluno.clear();
	}
	
	public void SaveData(){
		try {
			FileWriter fw = new FileWriter(arq, false);
			PrintWriter pw = new PrintWriter(fw);
			Gson gson = new Gson();
			
			for(Aluno aluno : listaAluno){				
				String aux = gson.toJson(aluno);
				pw.println(aux);
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void LoadData(GradeEscolar grade){
		Gson gson = new Gson();
		try {
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = "";
			
			
			while((linha = br.readLine())!= null){
				Aluno aluno = gson.fromJson(linha, Aluno.class);
				aluno.setGerador(aluno.getMatricula());
				Create(aluno);
			}
			fr.close();
			br.close();			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
