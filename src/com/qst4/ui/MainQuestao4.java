package com.qst4.ui;

import com.qst4.dao.GastoDAO;
import com.qst4.vo.Gasto;
import com.recursos.InOut;

public class MainQuestao4 {
	
	private static GastoDAO gastoC = new GastoDAO();
	private static GastoDAO gastoL = new GastoDAO();
	private static GastoDAO gastoA = new GastoDAO();
	private static boolean programaJaRodou = false;
	
	public static void main(String[] args) {
		if(!programaJaRodou){
			gastoC.LoadDataFile("GastoC.json");
			gastoA.LoadDataFile("GastoA.json");
			gastoL.LoadDataFile("GastoL.json");
		}
		MenuPrincipal();
	}
	
	public static void MenuPrincipal(){
		Gasto gas = new Gasto();
		int op;
		do{
			String opcoes = "Digite uma das Opções abaixo:\n"+
							"1 - Contabilizar Gasto\n"+
							"2 - Listar os Gastos\n"+
							"3 - Atualizar Gasto\n"+
							"4 - Total de Todos os Gastos\n"+
							"5 - Remover Gasto\n"+
							"0 - Sair";
			op = InOut.InInt(opcoes);
			switch(op){
				case 0:					
					break;
				case 1:
					ContabilizarGasto();
					break;
				case 2:
					ListarGastos();
					break;
				case 3:
					AtualizarGasto(gas) ;
					break;
				case 4:
					TotalDeGastos();
					break;
				case 5:
					 RemoverGasto(gas);
					break;
				default:
					InOut.OutMessage("Opção Invalida!", "Erro!");
					break;
			}
			gastoC.SaveDataFile("GastoC.json");
			gastoA.SaveDataFile("GastoA.json");
			gastoL.SaveDataFile("GastoL.json");			
		}while(op != 0);
	}

	private static void ContabilizarGasto() {
		String descricao = InOut.InString("Insira uma Descrição para o Gasto:");
		Double valor = InOut.InDouble("Insira o Valor do Gasto:");
		Gasto gasc, gasa, gasl;
		int op = GerirSistema("Contabilizar");
		switch(op){
			case 1:
				gasc = new Gasto(descricao, valor);
				gastoC.Create(gasc);
				break;
			case 2:
				gasl = new Gasto(descricao, valor);
				gastoL.Create(gasl);
				break;
			case 3:
				gasa = new Gasto(descricao, valor);
				gastoA.Create(gasa);
				break;
		}
				
	}

	private static void ListarGastos() {
		int op = GerirSistema("Contabilizar");
		switch(op){
			case 1:
				InOut.OutMessage("Gastos com a Casa:\n"+gastoC.Show());
				break;
			case 2:
				InOut.OutMessage("Gastos com o Lazer:\n"+gastoL.Show());
				break;
			case 3:
				InOut.OutMessage("Gastos com a Alimentação:\n"+gastoA.Show());
				break;
		}

	}

	private static void AtualizarGasto(Gasto gas) {
		int op = GerirSistema("Atualizar");
		int	id = InOut.InInt("Insira o ID do Gasto que deseja Alterar:");
		gas.setID(id);
		
		switch(op){
			case 1:
				if(gastoC.Find(gas) != -1){
					ProcurarGasto(gas);
					gastoC.Update(gas);
				}
				break;
			case 2:
				if(gastoL.Find(gas) != -1){
					ProcurarGasto(gas);
					gastoL.Update(gas);
				}
				break;
			case 3:
				if(gastoA.Find(gas) != -1){
					ProcurarGasto(gas);
					gastoA.Update(gas);
				}
				break;
		}
	}
	
	private static void ProcurarGasto(Gasto gasto){
		String descricao = InOut.InString("Insira uma Descrição para o Gasto:");
		Double valor = InOut.InDouble("Insira o Valor do Gasto:");
		gasto.setDescricao(descricao);
		gasto.setValor(valor);
	}
	
	private static void RemoverGasto(Gasto gas) {
		int	id = InOut.InInt("Insira o ID do Gasto que deseja Remover:");
		gas.setID(id);
		int op = GerirSistema("Remover");
		switch(op){
			case 1:
				gastoC.Delete(gas);
				break;
			case 2:
				gastoL.Delete(gas);
				break;
			case 3:
				gastoA.Delete(gas);
				break;
		}
	}
	
	private static int GerirSistema(String msg){
		String texto = "Qual o Tipo de Gasto você deseja "+msg+" gasto:\n"+
					   "1 - Casa\n"+
					   "2 - Lazer\n"+
					   "3 - Alimentação\n";
		int tipoGasto = 0;
		do{
			tipoGasto = InOut.InInt(texto);
			System.out.println(tipoGasto + "");
		}while(tipoGasto != 1 && tipoGasto != 2 && tipoGasto !=3);
		return tipoGasto;
	}
	
	public static void TotalDeGastos(){
		double totalGasto = gastoC.TotalGasto() + gastoL.TotalGasto() + gastoA.TotalGasto();
		String texto = 	"\nTotal Gasto Casa...........: R$"+gastoC.TotalGasto()+
						"\nTotal Gasto Lazer..........: R$"+gastoL.TotalGasto()+
						"\nTotal Gasto Alimentos..: R$"+gastoA.TotalGasto()+
						"\nTotal de Gastos..............: R$"+totalGasto;
		InOut.OutMessage(texto);
		//cncertar
				
	}
}
