package com.qst1.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;
import com.recursos.InOut;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListModel;

public class FrmPrincipal extends JFrame {

	private JPanel mainPane;
	
	private Interacao interacao = new Interacao();
	
	private static AlunoDAO listaAluno = new AlunoDAO();
	private static GradeEscolar grade = new GradeEscolar();
	private static Disciplina disc;
	private static Aluno aluno;
	private static boolean ProgramaJaRodou = false;
		
	private 	JDesktopPane desktopPane;
	private 	JInternalFrame frameCadastroAluno;
	
	protected 	JTextField 	txtPesquisa;
	protected 	JTextField 	txtMatricula;
	protected 	JTextField 	txtNome;
	protected 	JTextField 	txtCPF;	
	
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
	protected 	JPanel 		panelStatus;
	
	protected 	JScrollPane	scrollPane;
	private		JLabel 		lblStatus;
	private 	JLabel		lblPesquisar;
	
	private static int mode; 

	private Integer matricula;
	private String nome;
	private String CPF;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {			//Windows, Nimbus, Metal, CDE
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
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
	public FrmPrincipal() {
		setTitle("Gerenciador de Faculdade");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 767, 563);		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		mntmNovo.setEnabled(false);
		mnArquivo.add(mntmNovo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnArquivo.add(mntmAbrir);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mnArquivo.add(mntmSalvar);
		
		JSeparator separator = new JSeparator();
		mnArquivo.add(separator);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(InOut.ConfirmDialog("Deseja realmente Sair?", "Atenção")){
					System.exit(0);
				}
					
			}
		});
		mnArquivo.add(mntmSair);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenu mnAluno = new JMenu("Aluno");
		mnCadastro.add(mnAluno);
		
		JMenuItem mntmCadastrarAluno = new JMenuItem("Cadastrar Aluno");
		mntmCadastrarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Menu > Cadastrar Aluno
				frameCadastroAluno.setVisible(true);
				
			}
		});
		mnAluno.add(mntmCadastrarAluno);
	
		JMenuItem mntmCadastrarGrade = new JMenuItem("Cadastrar Grade");
		mnAluno.add(mntmCadastrarGrade);
		
		JMenuItem mntmGerenciarNota = new JMenuItem("Gerenciar Nota");
		mnAluno.add(mntmGerenciarNota);
		
		JMenu mnDisciplina = new JMenu("Disciplina");
		mnCadastro.add(mnDisciplina);
		
		JMenuItem mntmCadastrarDisciplina = new JMenuItem("Cadastrar disciplina");
		mnDisciplina.add(mntmCadastrarDisciplina);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem menuItem = new JMenuItem("Ajuda");
		menuItem.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/help.png")));
		mnAjuda.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Sobre");
		menuItem_1.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/info.png")));
		mnAjuda.add(menuItem_1);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(new BorderLayout(0, 0));
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(UIManager.getColor("control"));
		mainPane.add(desktopPane);
		
		frameCadastroAluno = new JInternalFrame("Cadastrar Aluno");
		frameCadastroAluno.setIconifiable(true);
		frameCadastroAluno.setClosable(true);
		frameCadastroAluno.setBounds(0, 0, 400, 480);
		desktopPane.add(frameCadastroAluno);
		frameCadastroAluno.getContentPane().setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(10, 108, 58, 14);
		frameCadastroAluno.getContentPane().add(lblMatricula);
		lblMatricula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 132, 46, 14);
		frameCadastroAluno.getContentPane().add(lblNome);
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCPF.setBounds(10, 157, 46, 14);
		frameCadastroAluno.getContentPane().add(lblCPF);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(78, 107, 86, 20);
		frameCadastroAluno.getContentPane().add(txtMatricula);
		txtMatricula.setText("1");
		txtMatricula.setEditable(false);
		txtMatricula.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBounds(78, 130, 200, 20);
		frameCadastroAluno.getContentPane().add(txtNome);
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setEnabled(false);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(78, 155, 200, 20);
		frameCadastroAluno.getContentPane().add(txtCPF);
		txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCPF.setEnabled(false);
		txtCPF.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 182, 364, 237);
		frameCadastroAluno.getContentPane().add(scrollPane);
		
		panelAzul = new JPanel();
		panelAzul.setLayout(null);
		panelAzul.setBackground(new Color(153, 204, 255));
		panelAzul.setBounds(0, 0, 384, 50);
		frameCadastroAluno.getContentPane().add(panelAzul);
		
		btnCadastrar = new JButton("");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO botão Cadastrar
			}
		});
		btnCadastrar.setToolTipText("Cadastrar");
		btnCadastrar.setForeground(Color.BLACK);
		btnCadastrar.setBackground(Color.BLACK);
		btnCadastrar.setBounds(10, 5, 40, 40);
		panelAzul.add(btnCadastrar);
		
		btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO botão Atualizar
			}
		});
		btnAtualizar.setToolTipText("Atualizar");
		btnAtualizar.setBackground(Color.BLACK);
		btnAtualizar.setBounds(60, 5, 40, 40);
		panelAzul.add(btnAtualizar);
		
		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO botão Excluir
			}
		});
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBackground(Color.BLACK);
		btnExcluir.setBounds(110, 5, 40, 40);
		panelAzul.add(btnExcluir);
		
		btnConfirmar = new JButton("");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO botão Confirmar
			}
		});
		btnConfirmar.setToolTipText("Confirmar");
		btnConfirmar.setEnabled(false);
		btnConfirmar.setBackground(Color.BLACK);
		btnConfirmar.setBounds(280, 5, 40, 40);
		panelAzul.add(btnConfirmar);
		
		btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO botão Cancelar
			}
		});
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setBackground(Color.BLACK);
		btnCancelar.setBounds(330, 5, 40, 40);
		panelAzul.add(btnCancelar);
		
		panelPesquisa = new JPanel();
		panelPesquisa.setLayout(null);
		panelPesquisa.setBackground(new Color(153, 255, 153));
		panelPesquisa.setBounds(0, 50, 384, 50);
		frameCadastroAluno.getContentPane().add(panelPesquisa);
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
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
				//TODO botão Pesquisar
			}
		});
		btnPesquisar.setToolTipText("Pesquisar");
		btnPesquisar.setBackground(Color.BLACK);
		btnPesquisar.setBounds(280, 5, 40, 40);
		panelPesquisa.add(btnPesquisar);
		
		panelStatus = new JPanel();
		FlowLayout fl_panelStatus = (FlowLayout) panelStatus.getLayout();
		fl_panelStatus.setAlignment(FlowLayout.LEFT);
		panelStatus.setBackground(SystemColor.controlHighlight);
		panelStatus.setBounds(0, 430, 384, 20);
		frameCadastroAluno.getContentPane().add(panelStatus);
		
		lblStatus = new JLabel("Status");
		panelStatus.add(lblStatus);
		
		table = new JTable();
		table.setBounds(10, 183, 364, 236);
		frameCadastroAluno.getContentPane().add(table);		
		
		JLabel lblPorBrenoNunes = new JLabel("Por Breno Nunes");
		lblPorBrenoNunes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPorBrenoNunes.setBounds(649, 478, 92, 15);
		desktopPane.add(lblPorBrenoNunes);
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
