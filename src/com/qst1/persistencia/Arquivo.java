package com.qst1.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;

public class Arquivo {
	//private File arq = new File("DadosAluno.txt");				//objeto que vair tratar do diretorio
	private FileReader fr;
	
	/*private static GradeEscolar grade;
	private static AlunoDAO listaAluno;*/
	private Disciplina disc;
	private Aluno aluno;
	
	
	public void SaveDataFile(ArrayList<Aluno> listaAluno){		
		aluno = new Aluno();
		disc = new Disciplina();
		Charset utf8 = StandardCharsets.UTF_8;
		ArrayList<Disciplina> grade;
		File arq = new File("DadosAluno.txt");
		try {
			FileWriter fw = new FileWriter(arq,false);
			arq.createNewFile();
			PrintWriter pw = new PrintWriter(fw);
			for(Aluno aluno: listaAluno){
				grade = aluno.getMaterias();
				pw.print(aluno.getMatricula()+";");
				pw.print(aluno.getNome()+";");
				pw.print(aluno.getCPF()+";");
				for(Disciplina disc: grade){
					pw.print(disc.getCodigo()+";");//contar -1 na leitura
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
	
	/*private void LoadDataFile(){
		try {
			fr = new FileReader(arq);
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
				
				User u = new User();
				u.setId(Integer.valueOf(user[0]));
				u.setNome(user[1]);
			
				System.out.println(u.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
}
