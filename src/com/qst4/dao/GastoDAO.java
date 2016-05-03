package com.qst4.dao;

import java.util.ArrayList;

import com.qst4.vo.Gasto;

public class GastoDAO implements DAO{
	private ArrayList<Gasto> listaGasto;
	
	public GastoDAO() {
		listaGasto = new ArrayList<Gasto>();
	}
	
	public void Create (Object o){
		listaGasto.add((Gasto)o);
	}
	
	public String Show (){
		String texto = "";
		//para  cada objeto do tipo gasto	contido    na minha lista de gastos -> faça
		for    (        Gasto gasto           :       listaGasto){
			texto += "\nID: "+gasto.getID()+
					 "\nDescrição: "+gasto.getDescricao()+
					 "\nValor: R$"+gasto.getValor()+
					 "\n----------------------";
		}
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
	
	public void Uptade (Object o){
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
	
}
