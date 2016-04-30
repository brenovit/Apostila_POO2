package com.qst1.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;

public class Arquivo {
	private File arq = new File("DadosAluno.txt");				//objeto que vair tratar do diretorio
	
	private AlunoDAO lista;
	
	private Disciplina disc;
	private Aluno aluno;
	
	
	public void SaveDataFile(ArrayList<Aluno> listaAluno){	
		ArrayList<Disciplina> listaDisciplina;
		
		try {
			FileWriter fw = new FileWriter(arq, false);
			PrintWriter pw = new PrintWriter(fw);
			for(Aluno aluno : listaAluno){
				listaDisciplina = aluno.getMaterias();
				
				pw.print(aluno.getMatricula()+";");
				pw.print(aluno.getNome()+";");
				pw.print(aluno.getCPF()+";");
				for(Disciplina disc : listaDisciplina){ //contar -1 na leitura
					pw.print(disc.getCodigo()+";");
					pw.print(disc.getNota()+";");
				}
				pw.println("");
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void LoadDataFile(GradeEscolar grade){
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
				
				aluno = new Aluno(user[1],Integer.parseInt(user[0]),user[2]);
				if(user.length > 2){
					for(int i = 3; i < user.length - 1; i+=2){
						disc = new Disciplina();
						disc.setCodigo(Integer.parseInt(user[i]));
						if(grade.Find(disc, true) != 1){
							grade.CadastrarGrade(aluno, disc);
							if(!user[i+1].equals(null)){
								lista.AddNota(aluno, disc, Double.parseDouble(user[i+1]));
							}
						}
					}
				}
				
				//System.out.println(u.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
