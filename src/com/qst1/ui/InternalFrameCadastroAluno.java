package com.qst1.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;

import com.qst1.dao.AlunoDAO;
import com.qst1.vo.Aluno;
import com.recursos.InOut;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InternalFrameCadastroAluno extends JInternalFrame {
	private 	AlunoDAO	listaAluno = ManipulaDados.getLista();
	private		Dado		dadoAluno;
	
	protected	static	JTextField 	txtPesquisa;
	protected	static	JTextField 	txtMatricula;
	protected	static	JTextField 	txtNome;
	protected	static	JTextField 	txtCPF;	
	
	protected 	JButton 	btnConfirmar;
	protected 	JButton 	btnCancelar;
	protected 	JButton 	btnAbrir;
	protected 	JButton 	btnCadastrar;
	protected 	JButton 	btnExcluir;
	protected 	JButton 	btnAtualizar;
	protected 	JButton 	btnPesquisar;
	
	protected 	JTable 		table;
	
	protected 	JPanel 		contentPane;
	protected 	JPanel 		panelAzul;	
	protected 	JPanel 		panelPesquisa;
	protected 	JPanel 		statusBar;
	protected	JPanel		panelCampos;
	
	private		JLabel 		lblStatus;
	private 	JLabel		lblPesquisar;
	
	private static int mode; 

	private Integer matricula;
	private		String	pesquisa;
	private String nome;
	private String CPF;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalFrameCadastroAluno frame = new InternalFrameCadastroAluno();
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
	public InternalFrameCadastroAluno() {
		setFrameIcon(new ImageIcon(InternalFrameCadastroAluno.class.getResource("/com/qst1/images/student.png")));
		setTitle("Gerir Aluno");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 400, 260);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);
		
		panelAzul = new JPanel();
		panelAzul.setBounds(0, 0, 384, 50);
		panelCenter.add(panelAzul);
		panelAzul.setLayout(null);
		panelAzul.setBackground(new Color(153, 204, 255));
		
		btnCadastrar = new JButton("");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Cadastro aluno > Botão Cadastrar
				mode = 0;
				AtivarCampos(true);
				AtivarBotoes(true);
				LimparCampos();
				txtNome.requestFocus();
				txtMatricula.setText(""+(Aluno.getGerador()+1));
				lblStatus.setText("Cadastrando Aluno");
			}
		});
		btnCadastrar.setIcon(new ImageIcon(InternalFrameCadastroAluno.class.getResource("/com/qst1/images/new.png")));
		btnCadastrar.setToolTipText("Cadastrar");
		btnCadastrar.setForeground(Color.BLACK);
		btnCadastrar.setBackground(Color.BLACK);
		btnCadastrar.setBounds(10, 5, 40, 40);
		panelAzul.add(btnCadastrar);
		
		btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Cadastro aluno > Botão Atualizar
				mode = 1;
				if(CamposVazios("Alterar"))
					return;
				AtivarCampos(true);
				AtivarBotoes(true);
			}
		});
		btnAtualizar.setIcon(new ImageIcon(InternalFrameCadastroAluno.class.getResource("/com/qst1/images/update.png")));
		btnAtualizar.setToolTipText("Atualizar");
		btnAtualizar.setBackground(Color.BLACK);
		btnAtualizar.setBounds(60, 5, 40, 40);
		panelAzul.add(btnAtualizar);
		
		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Cadastro aluno > Botão Excluir
				mode = 2;
				if(CamposVazios("Excluir"))
					return;
				matricula = Integer.parseInt(txtMatricula.getText());
				AtivarBotoes(true);
				FrmPrincipal.setTableEnable(false);				
				InOut.OutMessage("Para excluir o Aluno: " + txtNome.getText() + "\n,clique no Botão Confirmar");
				lblStatus.setText("Excluindo Aluno");	
			}
		});
		btnExcluir.setIcon(new ImageIcon(InternalFrameCadastroAluno.class.getResource("/com/qst1/images/delete.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBackground(Color.BLACK);
		btnExcluir.setBounds(110, 5, 40, 40);
		panelAzul.add(btnExcluir);
		
		btnConfirmar = new JButton("");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Cadastro aluno > Botão Confirmar
				switch(mode){
				case 0:					///modo de criação
					dadoAluno = new Dado(txtNome.getText(),txtCPF.getText());
					ManipulaDados.CadastrarAluno(dadoAluno);					
					break;
				case 1:					///modo de alteração
					dadoAluno = new Dado(Integer.parseInt(txtMatricula.getText()),txtNome.getText(),txtCPF.getText());
					ManipulaDados.AtualizarAluno(dadoAluno);
					break;
				case 2:					///modo de exclusão
					dadoAluno = new Dado();
					dadoAluno.setMatricula(matricula);
					ManipulaDados.RemoverAluno(dadoAluno);
					break;
			}
				
			txtMatricula.setText((Aluno.getGerador()+1)+"");
			FrmPrincipal.setTableEnable(true);
			FrmPrincipal.AttLista();
			AtivarBotoes(false);
			AtivarCampos(false);
			LimparCampos();
			lblStatus.setText("Pronto");			
			}
		});

		btnConfirmar.setIcon(new ImageIcon(InternalFrameCadastroAluno.class.getResource("/com/qst1/images/confirm.png")));
		btnConfirmar.setToolTipText("Confirmar");
		btnConfirmar.setEnabled(false);
		btnConfirmar.setBackground(Color.BLACK);
		btnConfirmar.setBounds(280, 5, 40, 40);
		panelAzul.add(btnConfirmar);
		
		btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Cadastro aluno > Botão Cancelar
				AtivarBotoes(false);
				AtivarCampos(false);
				LimparCampos();
				FrmPrincipal.setTableEnable(true);
				lblStatus.setText("Pronto");
			}
		});
		btnCancelar.setIcon(new ImageIcon(InternalFrameCadastroAluno.class.getResource("/com/qst1/images/cancel.png")));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setBackground(Color.BLACK);
		btnCancelar.setBounds(330, 5, 40, 40);
		panelAzul.add(btnCancelar);
		
		panelPesquisa = new JPanel();
		panelPesquisa.setBounds(0, 50, 384, 50);
		panelCenter.add(panelPesquisa);
		panelPesquisa.setLayout(null);
		panelPesquisa.setBackground(new Color(153, 255, 153));
		
		lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setFont(new Font("Cambria", Font.BOLD, 14));
		lblPesquisar.setBounds(10, 13, 70, 20);
		panelPesquisa.add(lblPesquisar);
		
		txtPesquisa = new JTextField();
		txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPesquisa.setColumns(10);
		txtPesquisa.setBounds(90, 13, 181, 20);
		panelPesquisa.add(txtPesquisa);
		
		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Pesquisar > Pesquisar
				pesquisa = txtPesquisa.getText();
				dadoAluno = new Dado();
				dadoAluno.setMatricula(Integer.parseInt(pesquisa));
				if(ManipulaDados.PesquisarAluno(dadoAluno)){
					InOut.OutMessage("Aluno Encontrado");
					MudarCampos(dadoAluno);
				}else{
					InOut.OutMessage("Aluno Não Encontrado");
				}
			}
		});
		btnPesquisar.setIcon(new ImageIcon(InternalFrameCadastroAluno.class.getResource("/com/qst1/images/search.png")));
		btnPesquisar.setToolTipText("Pesquisar");
		btnPesquisar.setBackground(Color.BLACK);
		btnPesquisar.setBounds(280, 5, 40, 40);
		panelPesquisa.add(btnPesquisar);
		
		panelCampos = new JPanel();
		panelCampos.setBounds(0, 98, 384, 106);
		panelCenter.add(panelCampos);
		panelCampos.setLayout(null);
		
		JLabel label = new JLabel("Matricula:");
		label.setBounds(10, 12, 58, 14);
		panelCampos.add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setBounds(10, 36, 46, 14);
		panelCampos.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel label_2 = new JLabel("CPF:");
		label_2.setBounds(10, 61, 46, 14);
		panelCampos.add(label_2);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(78, 11, 86, 20);
		panelCampos.add(txtMatricula);
		txtMatricula.setEditable(false);
		txtMatricula.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBounds(78, 34, 200, 20);
		panelCampos.add(txtNome);
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setEnabled(false);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracter = "0987654321";
				int limiteChar = 11;
				if(!caracter.contains(e.getKeyChar()+"")){
					e.consume();
				}
				if(!(txtCPF.getText().length() < limiteChar)){
					e.consume();
				}
			}
		});
		txtCPF.setBounds(78, 59, 200, 20);
		panelCampos.add(txtCPF);
		txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCPF.setEnabled(false);
		txtCPF.setColumns(11);
		
		statusBar = new JPanel();
		FlowLayout fl_statusBar = (FlowLayout) statusBar.getLayout();
		fl_statusBar.setAlignment(FlowLayout.LEFT);
		statusBar.setBackground(SystemColor.controlHighlight);
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		
		lblStatus = new JLabel("Pronto");
		statusBar.add(lblStatus);
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
	
	protected static void MudarCampos(Dado dados){
		txtMatricula.setText(dados.getMatricula().toString());
		txtCPF.setText(dados.getCpf());
		txtNome.setText(dados.getNome());
	}
	
	private boolean CamposVazios(String msg){
		if(txtMatricula.getText().equals("") || txtCPF.getText().equals("")){
			InOut.OutMessage("Para "+msg+" um Aluno, primeiro selecione-o na Tabela de Alunos\n");
			return true;
		}
		return false;
	}
}
