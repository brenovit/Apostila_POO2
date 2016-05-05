package com.recursos;
import javax.swing.JOptionPane;

public class InOut {
	
	public static int InInt(String titulo){
		int in;
		try {
			in = Integer.parseInt(JOptionPane.showInputDialog(titulo));
			return in;
		}catch(Exception e){
			OutMessage("Erro de Execução:\n"+e,"ERRO");		
		}finally {
			in = 0;
		}
		return in;
	}
	
	public static float InFloat(String titulo){
		float in = Float.parseFloat(JOptionPane.showInputDialog(titulo));		
		return in;
	}
	
	public static double InDouble(String titulo){
		double in;
		try {
			in = Double.parseDouble(JOptionPane.showInputDialog(titulo));	
			return in;
		}catch(Exception e){
			OutMessage("Erro de Execução:\n"+e,"ERRO");		
		}finally {
			in = 0;
		}
		return in;
	}
	
	public static String InString(String titulo){
		String in;
		try {
			in = JOptionPane.showInputDialog(titulo);
			return in;
		}catch(Exception e){
			OutMessage("Erro de Execução:\n"+e,"ERRO");		
		}finally {
			in = "";
		}
		return in;
	}
	
	public static void  OutMessage(String msg, String titulo) {
		JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void  OutMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}	
	
	public static boolean ConfirmDialog(String msg, String titulo){
		if(JOptionPane.showConfirmDialog(null, msg, titulo, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
}