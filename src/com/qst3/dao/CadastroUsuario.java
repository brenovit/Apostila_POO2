package com.qst3.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



import com.google.gson.Gson;
import com.qst3.vo.Usuario;
import com.recursos.InOut;

public class CadastroUsuario {

	private ArrayList<Usuario> listaUsuario;   //Criando a lista
	private File arq = new File("DadosUsuario.json");   //Criando txt

	
	public CadastroUsuario(){				//Contrutor
		listaUsuario = new ArrayList<Usuario>();    //Instanciando a lista no constrututor, para não ficar criando uma nova lista o tempo todo no teste
	}	
	  
	public void Inserir(Usuario user){
		listaUsuario.add(user);
	}
	
	public String Show(){    //Mostrar dados do usuario contidos na lista de usuarios 
		String texto = "";  
			for(Usuario user: listaUsuario){  //Para cada usuario (variavel user) contido em listaUsuario (variavel) faça alguma coisa.
				texto += "\n Nome.: "+user.getNome() + 
						 "\n Email.: "+user.getEmail() +
						 "\n Login.: "+user.getLogin() + 
						 "\n Senha.: "+user.getSenha() +
						 "\n------------------------------";
			}
		return texto;
	}
	
	public String Show(Usuario user){    //Mostrar dados do usuario contidos na lista de usuarios 
		String texto = "";  
			for(Usuario usuario: listaUsuario){  //Para cada usuario (variavel user) contido em listaUsuario (variavel) faça alguma coisa.
				texto += "\n Nome.: "+user.getNome() + 
						 "\n Email.: "+user.getEmail() +
						 "\n Login.: "+user.getLogin() + 
						 "\n Senha.: "+user.getSenha() +
						 "\n------------------------------";
			}
		return texto;
	}
	
	
	public void Update(Usuario user){  //Atualizar os dados do cliente
		int posicao = Find(user, false);
		if(posicao != -1){
			listaUsuario.get(posicao).setNome(user.getNome());
			listaUsuario.get(posicao).setEmail(user.getEmail());
			listaUsuario.get(posicao).setLogin(user.getLogin());
			listaUsuario.get(posicao).setSenha(user.getSenha());
		}
	}

	public int Find(Usuario user, boolean alterar) {
		//Aluno aluno = (Aluno) o;
        int posicao = -1;        
        int posAux = 0;
        
        while((posAux < listaUsuario.size()) &&
                (!listaUsuario.get(posAux).getLogin().equals(user.getLogin()))){
            posAux++;
        }
        if((posAux < listaUsuario.size()) && 
        		(listaUsuario.get(posAux).getLogin().equals(user.getLogin())) == true){
        	if(alterar){
	        	user.setNome(listaUsuario.get(posAux).getNome());
	        	user.setEmail(listaUsuario.get(posAux).getEmail());
	        	user.setLogin(listaUsuario.get(posAux).getLogin());
	        	user.setSenha(listaUsuario.get(posAux).getSenha());
        	}
            posicao = posAux;
        }
        return posicao;
	}
	
	public void UsuarioNaoEncontrado(){
		InOut.OutMessage("Não foi encontrado nenhum usuario com este login.\n"
				+ "Por favor verifique se digitou corretamente os dados e tente novamente.", 
				"Usuario Não Encontrado");
	}
	
	public boolean Delete(Usuario user) {
	    int posicao = Find(user, false);
	    if(posicao != -1){
	    	listaUsuario.remove(posicao);	
	    	return true;
	    }
	    return false;
	}
	
	public void SaveDataFile(){		
		try {
			FileWriter fw = new FileWriter(arq, false);    //Abir arquivo
			PrintWriter pw = new PrintWriter(fw);		   //Escrever no arquivo
			Gson gson = new Gson();
			
			for(Usuario user : listaUsuario){
				String objeto = gson.toJson(user);
				pw.println(objeto);
			}
			
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void LoadDataFile(){
		try {
			FileReader fr = new FileReader(arq);         //Abrir o arquivo na memoria para  leitura
			BufferedReader br = new BufferedReader(fr);	 //Objeto responsavel por ler o arquivo	
			String linha = "";
			Gson gson = new Gson();
			
			ArrayList <String> result = new ArrayList<String>();
			
			while((linha = br.readLine())!= null){
				Usuario user = gson.fromJson(linha, Usuario.class);
				Inserir(user);
			}
			fr.close();
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
