package com.qst4.testes;

import com.qst4.dao.GastoDAO;
import com.qst4.vo.Gasto;
import com.recursos.InOut;

public class TesteQuestao4 {
	public static void main(String[] args) {	
	GastoDAO gastoC = new GastoDAO();
	GastoDAO gastoL = new GastoDAO();
	GastoDAO gastoA = new GastoDAO();
	
	Gasto gas11 = new Gasto("Roupas Proprias", 150.0);
	Gasto gas12 = new Gasto("Roupas Crianças", 250.0);
	Gasto gas13 = new Gasto("Roupas Esposa", 350.0);
	
	Gasto gas21 = new Gasto("Lanche Trabalho", 200.0);
	Gasto gas22 = new Gasto("Lanche Crianças", 200.0);
	Gasto gas23 = new Gasto("Lanche Esposa", 150.0);
	
	Gasto gas31 = new Gasto("Jogos Steam", 200.0);
	Gasto gas32 = new Gasto("Jogos Origin", 300.0);
	Gasto gas33 = new Gasto("Jogos uPlay", 400.0);
	
	gastoC.Create(gas11);
	gastoC.Create(gas12);
	gastoC.Create(gas13);
	
	gastoA.Create(gas21);
	gastoA.Create(gas22);
	gastoA.Create(gas23);
	
	gastoL.Create(gas31);
	gastoL.Create(gas32);
	gastoL.Create(gas33);
	
	InOut.OutMessage("Gastos com a Casa:\n"+gastoC.Show());
	InOut.OutMessage("Gastos com o Lazer:\n"+gastoL.Show());
	InOut.OutMessage("Gastos com a Aliemtnação:\n"+gastoA.Show());
	
	InOut.OutMessage("Total Gasto"+
					 "\n----------"+
					 "\nCasa: R$"+gastoC.TotalGasto()+
					 "\n----------"+
					 "\nLazer: R$"+gastoL.TotalGasto()+
					 "\n----------"+
					 "\nAlimentação: R$"+gastoA.TotalGasto());
	
	}
}
