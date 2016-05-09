package com.qst1.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.qst1.vo.Aluno;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JFrame;

public class FrameCadastroAluno extends JInternalFrame {
	private JTextField txtMatricula;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCadastroAluno frame = new FrameCadastroAluno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameCadastroAluno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 395, 479);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Matricula:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 108, 58, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(10, 132, 46, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("CPF:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(10, 157, 46, 14);
		getContentPane().add(label_2);
		
		txtMatricula = new JTextField();
		txtMatricula.setText("1");
		txtMatricula.setEditable(false);
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(78, 107, 86, 20);
		getContentPane().add(txtMatricula);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setEnabled(false);
		txtNome.setColumns(10);
		txtNome.setBounds(78, 130, 200, 20);
		getContentPane().add(txtNome);
		
		txtCPF = new JTextField();
		txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCPF.setEnabled(false);
		txtCPF.setColumns(10);
		txtCPF.setBounds(78, 155, 200, 20);
		getContentPane().add(txtCPF);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 384, 50);
		getContentPane().add(panel);
		
		JButton button = new JButton("");
		button.setToolTipText("Cadastrar");
		button.setForeground(Color.BLACK);
		button.setBackground(Color.BLACK);
		button.setBounds(10, 5, 40, 40);
		panel.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setToolTipText("Atualizar");
		button_1.setBackground(Color.BLACK);
		button_1.setBounds(60, 5, 40, 40);
		panel.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setToolTipText("Excluir");
		button_2.setBackground(Color.BLACK);
		button_2.setBounds(110, 5, 40, 40);
		panel.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setToolTipText("Confirmar");
		button_3.setEnabled(false);
		button_3.setBackground(Color.BLACK);
		button_3.setBounds(280, 5, 40, 40);
		panel.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.setToolTipText("Cancelar");
		button_4.setEnabled(false);
		button_4.setBackground(Color.BLACK);
		button_4.setBounds(330, 5, 40, 40);
		panel.add(button_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(153, 255, 153));
		panel_1.setBounds(0, 50, 384, 50);
		getContentPane().add(panel_1);
		
		JLabel label_3 = new JLabel("Pesquisar:");
		label_3.setFont(new Font("Cambria", Font.BOLD, 14));
		label_3.setBounds(10, 13, 70, 20);
		panel_1.add(label_3);
		
		txtPesquisa = new JTextField();
		txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPesquisa.setColumns(10);
		txtPesquisa.setBounds(90, 13, 181, 20);
		panel_1.add(txtPesquisa);
		
		JButton button_5 = new JButton("");
		button_5.setToolTipText("Pesquisar");
		button_5.setBackground(Color.BLACK);
		button_5.setBounds(280, 5, 40, 40);
		panel_1.add(button_5);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_2.setBackground(SystemColor.controlHighlight);
		panel_2.setBounds(0, 430, 384, 20);
		getContentPane().add(panel_2);
		
		JLabel label_4 = new JLabel("Status");
		panel_2.add(label_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 183, 364, 236);
		getContentPane().add(scrollPane);

	}
	
	protected void AtivarCampos(boolean estado){
		txtNome.setEnabled(estado);
		txtCPF.setEnabled(estado);		
	}
	
	protected void AtivarBotoes(boolean estado){
		btnConfirmar.setEnabled(estado);
		btnCancelar.setEnabled(estado);	
		
		btnPesquisar.setEnabled(!estado);
		btnCadastrar.setEnabled(!estado);
		btnAtualizar.setEnabled(!estado);
		btnExcluir.setEnabled(!estado);
	}
	
	protected void LimparCampos(){
		txtNome.setText("");
		txtCPF.setText("");
	}
	
	protected void Salvar(){
		LimparCampos();
		listaAluno.SaveData();
	}
	
	protected void PreencherTabela(){
		try{
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			if(modelo.getRowCount() > 0){
				modelo.setRowCount(0);
			}
		
			for(Aluno aluno : listaAluno.getLista()){
				Object [] obj = {
					aluno.getMatricula(),
					aluno.getNome(),
					aluno.getCPF()
				};
				modelo.addRow(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
