package com.qst1.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;

public class Arquivo {
	private File arq = new File("DadosAluno.txt");				//objeto que vair tratar do diretorio
	private Gson gson = new Gson();
	public void SaveFile(List<Aluno> listaAluno){		
		try {
			FileWriter fw = new FileWriter(arq, false);
			PrintWriter pw = new PrintWriter(fw);
			
			
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
	
	public void LoadFile(GradeEscolar grade){
		Gson gson = new Gson();
		
		try {
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = "";
			
			ArrayList <String> result = new ArrayList<String>();
			
			while((linha = br.readLine())!= null){
				if(linha != null && !linha.isEmpty()){
					result.add(linha);
				}
			}
			fr.close();
			br.close();
			
			for(String s: result){
				Aluno aluno = gson.fromJson(s, Aluno.class);
				aluno.setGerador(aluno.getMatricula());
				//Create(aluno);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
