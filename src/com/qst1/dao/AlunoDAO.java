package com.qst1.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.qst1.persistencia.Arquivo;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;

public class AlunoDAO implements DAO {
	
	private ArrayList<Aluno> listaAluno;
	private ArrayList<Disciplina> listaDisciplina;
	private String msg = "";
	
	private Arquivo arquivo = new Arquivo();
	
	public AlunoDAO(){
		listaAluno = new ArrayList<Aluno>();
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
	
	public int Find(Aluno aluno, boolean alterar) {
		//Aluno aluno = (Aluno) o;
        int posicao = -1;        
        int posAux = 0;
        
        while((posAux < listaAluno.size()) &&
                (!listaAluno.get(posAux).getMatricula().equals(aluno.getMatricula()))){
            posAux++;
        }
        if((posAux < listaAluno.size()) && 
        		(listaAluno.get(posAux).getMatricula().equals(aluno.getMatricula())) == true){
        	if(alterar){
	        	aluno.setCPF(listaAluno.get(posAux).getCPF());
	        	aluno.setNome(listaAluno.get(posAux).getNome());
	        	aluno.setMaterias(listaAluno.get(posAux).getMaterias());
        	}
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
		arquivo.SaveDataFile(listaAluno);
	}
	
	public void LoadDataFile(GradeEscolar grade){
		File arq = new File("DadosAluno.txt");
		try {
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = "";
			
			ArrayList <String> result = new ArrayList<String>();
			
			while((linha = br.readLine())!= null){
				System.out.println(linha);
				if(linha != null && !linha.isEmpty()){
					result.add(linha);
				}
			}
			fr.close();
			br.close();
			
			for(String s: result){
				String[] user = s.split(";");
				System.out.println("Estou no For de Strings\n");
				Aluno aluno = new Aluno(user[1],Integer.parseInt(user[0]),user[2]);
				Create(aluno);
				
				if(user.length > 2){
					for(int i = 3; i < user.length - 1; i+=2){
						Disciplina disc = new Disciplina();
						disc.setCodigo(Integer.parseInt(user[i]));
						if(grade.Find(disc, true) != 1){
							grade.CadastrarGrade(aluno, disc);
							if(!user[i].equals(null)){
								AddNota(aluno, disc, Double.parseDouble(user[i+1]));
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
