package com.qst1.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.persistencia.Abrir;
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
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

public class FrmPrincipal extends JFrame {

	private JPanel mainPane;
	
	private ManipulaDados interacao = new ManipulaDados();
	
	private static AlunoDAO listaAluno;
	private static GradeEscolar grade = new GradeEscolar();
	private static Disciplina disc;
	private static Aluno aluno;
	private static boolean ProgramaJaRodou = false;
		
	private 	JDesktopPane 	desktopPane;
	protected 	JButton 	btnAbrir;
	
	protected 	JPanel 		contentPane;
	private 	JLabel		lblPesquisar;
	
	private		static		JTable		table;	
	private		static		DefaultTableModel modelo;
	
	private static int mode; 

	private Integer matricula;
	private String nome;
	private String CPF;
	
	private InternalFrameCadastroAluno 	frmCadAluno = new InternalFrameCadastroAluno();;
	private InternalFrameCadastrarGradeAluno frmCadGradAluno = new InternalFrameCadastrarGradeAluno();;
	
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
		setBounds(100, 100, 865, 596);		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		mntmNovo.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/novo16.png")));
		mntmNovo.setEnabled(false);
		mnArquivo.add(mntmNovo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Menu > Abrir Arquivo
				try {
					Abrir.AbrirArquivo("DadosAluno.json");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mntmAbrir.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/open16.png")));
		mnArquivo.add(mntmAbrir);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Menu > Salvar
				ManipulaDados.Salvar();
			}
		});
		mntmSalvar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/save16.png")));
		mnArquivo.add(mntmSalvar);
		
		JMenuItem mntmImportar = new JMenuItem("Importar");
		mntmImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Menu > Importar
				ManipulaDados.Carregar();
				AttLista();
			}
		});
		mntmImportar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/upload16.png")));
		mnArquivo.add(mntmImportar);
		
		JSeparator separator = new JSeparator();
		mnArquivo.add(separator);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Menu > Sair
				if(InOut.ConfirmDialog("Deseja realmente Sair?", "Atenção")){
					System.exit(0);
				}					
			}
		});
		mnArquivo.add(mntmSair);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenu mnAluno = new JMenu("Aluno");
		mnAluno.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/student.png")));
		mnCadastro.add(mnAluno);
		
		JMenuItem mntmGerirAlunos = new JMenuItem("Gerenciar Alunos");
		mntmGerirAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Menu > Cadastrar Aluno
				if(frmCadAluno.isIcon()){
					try {
						frmCadAluno.setIcon(false);
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
				}
				desktopPane.setLayer(frmCadAluno, 1);
				frmCadAluno.setBounds(430, 10, 400, 260);
				desktopPane.add(frmCadAluno);
				frmCadAluno.setVisible(true);
			}
		});
		mnAluno.add(mntmGerirAlunos);
	
		JMenuItem mntmGerirGrade = new JMenuItem("Gerenciar Grade dos Alunos");
		mntmGerirGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Menu > Gerenciar Grade
				if(frmCadGradAluno.isIcon()){
					try {
						frmCadGradAluno.setIcon(false);
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
				}
				desktopPane.setLayer(frmCadGradAluno, 1);
				frmCadGradAluno.setBounds(430, 10, 400, 260);
				desktopPane.add(frmCadGradAluno);
				frmCadGradAluno.setVisible(true);
			}
		});
		mnAluno.add(mntmGerirGrade);
		
		JMenuItem mntmGerirNota = new JMenuItem("Gerenciar Notas do Aluno");
		mnAluno.add(mntmGerirNota);
		
		JMenu mnDisciplina = new JMenu("Disciplina");
		mnDisciplina.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/grade.png")));
		mnCadastro.add(mnDisciplina);
		
		JMenuItem mntmCadastrarDisciplina = new JMenuItem("Cadastrar disciplina");
		mnDisciplina.add(mntmCadastrarDisciplina);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem menuItem = new JMenuItem("Ajuda");
		menuItem.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/help16.png")));
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
		desktopPane.setLayout(null);
		
		JPanel panel = new JPanel();
		desktopPane.setLayer(panel, 0);
		panel.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBounds(0, 501, 839, 25);
		desktopPane.add(panel);
		
		JLabel lblPorBrenoNunes = new JLabel("Por Breno Nunes");
		panel.add(lblPorBrenoNunes);
		lblPorBrenoNunes.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblTabelaAluno = new JLabel("Tabela de Alunos Cadastrados");
		lblTabelaAluno.setHorizontalAlignment(SwingConstants.CENTER);
		lblTabelaAluno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTabelaAluno.setBounds(0, 0, 420, 20);
		desktopPane.add(lblTabelaAluno);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 21, 379, 469);
		desktopPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int linha = table.getSelectedRow();
				
				matricula = Integer.parseInt(modelo.getValueAt(linha, 0).toString());				
				nome = modelo.getValueAt(linha, 1).toString();
				CPF = modelo.getValueAt(linha, 2).toString();
		
				Dado dados = new Dado(matricula,nome,CPF);
				
				ManipulaDados.setDados(dados);
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Matricula", "Nome", "CPF"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(140);
	}
	
	protected static void PreencherTabela(){
		try{
			modelo = (DefaultTableModel) table.getModel();
			if(modelo.getRowCount() > 0){
				modelo.setRowCount(0);
			}
			if(listaAluno.getLista().size() <= 0){
				return;
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
	
	protected static void AttLista(){
		listaAluno = ManipulaDados.getLista();
		PreencherTabela();
	}
	
	protected static void setTableEnable(boolean mode){
		table.setEnabled(mode);
	}
}
