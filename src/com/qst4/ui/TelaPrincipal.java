package com.qst4.ui;

import com.qst4.dao.GastoDAO;
import com.qst4.vo.Gasto;
import com.recursos.InOut;

public class TelaPrincipal {
	
	private static GastoDAO gastoC = new GastoDAO();
	private static GastoDAO gastoL = new GastoDAO();
	private static GastoDAO gastoA = new GastoDAO();
	private static GastoDAO gasto = new GastoDAO();
	
	public static void main(String[] args) {
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
							"4 - Remover Gasto\n"+
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
					 RemoverGasto(gas);
					break;
				default:
					InOut.OutMessage("Opção Invalida!", "Erro!");
					break;
			}
		}while(op != 0);
	}

	private static void ContabilizarGasto() {
		String descricao = InOut.InString("Insira uma Descrição para o Gasto:");
		Double valor = InOut.InDouble("Insira o Valor do Gasto:");
		Gasto gas = new Gasto(descricao, valor);
		int op = GerirSistema("Contabilizar");
		switch(op){
			case 1:
				gastoC.Create(gas);
				break;
			case 2:
				gastoL.Create(gas);
				break;
			case 3:
				gastoA.Create(gas);
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
		int	id = InOut.InInt("Insira o ID do Gasto que deseja Alterar:");
		gas.setID(id);
		int op = GerirSistema("Atualizar");
		switch(op){
			case 1:
				gastoC.Create(gas);
			case 2:
				gastoL.Create(gas);
			case 3:
				gastoA.Create(gas);
		}
	}

	private static void RemoverGasto(Gasto gas) {
		int	id = InOut.InInt("Insira o ID do Gasto que deseja Remover:");
		gas.setID(id);
		int op = GerirSistema("Remover");
		switch(op){
			case 1:
				gastoC.Delete(gas);
			case 2:
				gastoL.Delete(gas);
			case 3:
				gastoA.Delete(gas);
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
			switch(tipoGasto){
				case 1:
					return tipoGasto;
				case 2:
					return tipoGasto;
				case 3:
					return tipoGasto;
				default:
					InOut.OutMessage("Opção Invalida!", "Erro!");
					break;
			}
		}while(tipoGasto != 1 || tipoGasto != 2 || tipoGasto !=3);
		return tipoGasto;
	}
}
