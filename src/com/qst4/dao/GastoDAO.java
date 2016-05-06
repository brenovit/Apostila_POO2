package com.qst4.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.qst4.vo.Gasto;

public class GastoDAO implements DAO{
	private ArrayList<Gasto> listaGasto;
	File arq;
	public GastoDAO() {
		listaGasto = new ArrayList<Gasto>();
	}
	
	public void Create (Object o){
		listaGasto.add((Gasto)o);
	}
	
	public String Show (){
		String texto = "";
		for(Gasto gasto : listaGasto){
			texto += "\nID: "+gasto.getID()+
					 "\nDescrição: "+gasto.getDescricao()+
					 "\nValor: R$"+gasto.getValor()+
					 "\n----------------------";
		}
		texto += "\nValor total do Gasto: R$"+TotalGasto();		
		return texto;		
	}
	
	public int Find (Object o){
		int pos = -1;
		int posAux = 0;
		while((posAux < listaGasto.size()) && 
				(!listaGasto.get(posAux).getID().equals(((Gasto)o).getID()))){
			posAux++;
		}
		if((posAux < listaGasto.size()) && 
				(listaGasto.get(posAux).getID().equals(((Gasto)o).getID()))){
			pos = posAux;
		}
		return pos;		
	}
	
	public void Update (Object o){
		int pos = Find(o);
		if(pos != -1){
			listaGasto.get(pos).setDescricao(((Gasto) o).getDescricao());
			listaGasto.get(pos).setValor(((Gasto) o).getValor());
		}
	}

	public boolean Delete (Object o){
		int pos = Find(o);
		if(pos != -1){
			listaGasto.remove(pos);
		}
		return false;		
	}
	
	public Double TotalGasto(){
		Double totalGasto = 0.0;
		for(Gasto gasto : listaGasto){
			totalGasto +=  gasto.getValor();
		}
		return totalGasto;
	}
	
	public void SaveDataFile(String arquivo){
		arq = new File(arquivo);
		String comando = "C:\\WINDOWS\\System32\\ATTRIB.EXE +H "+arquivo;
		try {
			Runtime.getRuntime().exec(comando);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileWriter fw = new FileWriter(arq, false);
			PrintWriter pw = new PrintWriter(fw);
			Gson gson = new Gson();
			
			for(Gasto gasto : listaGasto){				
				String aux = gson.toJson(gasto);
				pw.println(aux);
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void LoadDataFile(String arquivo){
		arq = new File(arquivo);
		if(!arq.exists()){
			try {
				arq.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
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
				Gasto gasto = gson.fromJson(linha, Gasto.class);
				gasto.setGerador(gasto.getID());
				Create(gasto);
			}
			fr.close();
			br.close();			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
