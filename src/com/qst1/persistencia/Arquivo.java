package com.qst1.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Arquivo {
	File dir = new File("DadosAluno.txt");				//objeto que vair tratar do diretorio	
	/*private void parte1(){
		File dir = new File("C:\\TutoArquivo");				//objeto que vair tratar do diretorio
		File arq = new File(dir,"arq_01.txt");				//objeto que vair tratar do arquivo
	
		boolean statusDir = dir.mkdirs();					//retornar true ou false se a criação do diretorio foi bem sucedida
		System.out.print(statusDir+"\n");					//mostra o valor do retorno
		
		try {
			boolean statusArq = arq.createNewFile();		//retorna true ou false se a criação do arquivo foi bem sucedida
			System.out.print(statusArq+"\n");				//mostra o valor do retorno
		} catch (IOException e) {
			e.printStackTrace();
		}
		
				
		if(dir.exists()){									//checa se o diretorio existe
			System.out.println("Diretorio Existe!");
			if(arq.exists()){								//checa se o arquivo existe
				System.out.println("Arquivo Existe!");
			}
		}
	}*/
	
	/*private static void escrever(User user){
		File dir = new File("C:\\UserTest");
		File arq  = new File(dir, "User_arq.txt");
		try{
			dir.mkdirs();
			arq.createNewFile();
			FileWriter fw = new FileWriter(arq, true);			//false = sobrescreve - true = não sobrescreve
			PrintWriter pw = new PrintWriter(fw);				//objeto que vai escrever no arquivo fisicamente
			pw.println(user.getId());
			pw.println(user.getNome());
			
			pw.flush();											//libera a escrita do arquivo
			pw.close();											//fecha o arquivo para poder ser utilizado em outro momento ou aplicativo
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	private static void ler(){
		File dir = new File("C:\\UserTest");
		File arq  = new File(dir, "User_arq.txt");
		try{
			FileReader fr = new FileReader(arq);				//indica o arquivo que sera lido
			BufferedReader br = new BufferedReader(fr);			//este objeto vai permitir ler o arquivo
			
			String linha = "";									//vai receber cada linha do arquivo
			
			while((linha = br.readLine()) != null){				//este loop vai ler cada linha do arquivo até nao ter mais linhas para ler
				System.out.println(linha);
				if(linha.equals("1") || 
						linha.equals("2") || 
							linha.equals("3")){
					System.out.println("Encontri o ID");
				}
			}
			fr.close();
			br.close();			
		}catch(IOException e){
			e.printStackTrace();
		}
	}*/
	
	/*private static void layout (ArrayList<user> users){
		
		File dir = new File("C:\\UserTest");
		File arq = new File(dir, "User_arq2.txt");
		//GRAVAÇÃO
		try{
			dir.mkdir();
			arq.createNewFile();
			FileWriter fw = new FileWriter(arq, false);
			PrintWriter pw = new PrintWriter(fw);
			
			for(User user: users){
				pw.print(user.getId() + ";");
				pw.println(user.getNome());
			}
			pw.flush();
			pw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//LEITURA
		try{
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
				
				User u = new User();
				u.setId(Integer.valueOf(user[0]));
				u.setNome(user[1]);
				
				System.out.println(u.toString());
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}*/
}
