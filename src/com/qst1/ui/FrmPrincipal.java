package com.qst1.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

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
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import javax.swing.JToolBar;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmPrincipal extends JFrame {

	private JPanel mainPane;
	
	private static AlunoDAO listaAluno;
	private static boolean ProgramaJaRodou = false;
		
	private 	JDesktopPane 	desktopPane;
	
	protected 	JPanel 		contentPane;
	private 	JLabel		lblPesquisar;
	
	private		static		JTable		table;	
	private		static		DefaultTableModel modelo;

	private Integer matricula;
	private String nome;
	private String CPF;
	private boolean primeiraVez = true;
	
	private InternalFrameCadastroAluno 	frmCadAluno = new InternalFrameCadastroAluno();
	private InternalFrameCadastrarGradeAluno frmCadGradAluno = new InternalFrameCadastrarGradeAluno();
	private InternalFrameInserirNota frmEditNotAluno = new InternalFrameInserirNota();
	
	private JFileChooser fc = new JFileChooser();
	private String arquivo = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ManipulaDados.CadastrarDisciplinas();
		try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {			//Windows, Nimbus, Metal, CDE
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				SalvarTabela();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmPrincipal.class.getResource("/com/qst1/images/appicon2.png")));
		setTitle("Gerenciador de Faculdade");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 660);		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		mntmNovo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Menu > Novo
				Novo();
			}
		});
		mntmNovo.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/databasenew16.png")));
		mnArquivo.add(mntmNovo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Menu > Abrir Arquivo
				AbrirArquivo();
			}
		});
		mntmAbrir.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/open16.png")));
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnArquivo.add(mntmAbrir);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Menu > Salvar
				SalvarArquivo();
			}
		});
		mntmSalvar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/save16.png")));
		mntmSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnArquivo.add(mntmSalvar);
		
		JMenuItem mntmSalvarComo = new JMenuItem("Salvar como...");
		mntmSalvarComo.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/saveas16.png")));
		mntmSalvarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnArquivo.add(mntmSalvarComo);
		
		JSeparator separator_1 = new JSeparator();
		mnArquivo.add(separator_1);
		
		JMenuItem mntmImportar = new JMenuItem("Importar");
		mntmImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Menu > Importar
				Importar();
			}
		});
		mntmImportar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/databaseimport16.png")));
		mntmImportar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, (InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK)));
		mnArquivo.add(mntmImportar);
		
		JMenuItem mntmExportar = new JMenuItem("Exportar");
		mntmExportar.setEnabled(false);
		mntmExportar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/databaseexport16.png")));
		mntmExportar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, (InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK)));
		mnArquivo.add(mntmExportar);
		
		JMenuItem mntmVisualizar = new JMenuItem("Visualizar");
		mntmVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Menu > visualizar arquivo
				VisualizarArquivo();
			}
		});
		mntmVisualizar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/databasesearch16.png")));
		mnArquivo.add(mntmVisualizar);
		
		JSeparator separator = new JSeparator();
		mnArquivo.add(separator);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Menu > Sair
				SalvarTabela();
				if(InOut.ConfirmDialog("Deseja realmente Sair?", "Aten��o") == 0){
					System.exit(0);
				}else{
					return;
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
		mntmGerirAlunos.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/user16.png")));
		mntmGerirAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Menu > Cadastrar Aluno
				ChamarTelaGerirAluno();
			}
		});
		mnAluno.add(mntmGerirAlunos);
	
		JMenuItem mntmGerirGrade = new JMenuItem("Gerenciar Grade dos Alunos");
		mntmGerirGrade.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/disc16.png")));
		mntmGerirGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Menu > Gerenciar Grade
				ChamarTelaCadastrarMateria();
			}
		});
		mnAluno.add(mntmGerirGrade);
		
		JMenuItem mntmGerirNota = new JMenuItem("Gerenciar Notas do Aluno");
		mntmGerirNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Menu > Gerenciar nota aluno
				ChamarTelaGerirNota();
			}
		});
		mntmGerirNota.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/nota16.png")));
		mnAluno.add(mntmGerirNota);
		
		JMenu mnDisciplina = new JMenu("Disciplina");
		mnDisciplina.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/grade.png")));
		mnCadastro.add(mnDisciplina);
		
		JMenuItem mntmCadastrarDisciplina = new JMenuItem("Cadastrar disciplina");
		mntmCadastrarDisciplina.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/grade16.png")));
		mnDisciplina.add(mntmCadastrarDisciplina);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem menuItem = new JMenuItem("Ajuda");
		menuItem.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/help16.png")));
		mnAjuda.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Sobre");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO	Menu > Sobre
				
			}
		});
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
		
		JLabel lblTabelaAluno = new JLabel("Tabela de Alunos Cadastrados");
		lblTabelaAluno.setHorizontalAlignment(SwingConstants.CENTER);
		lblTabelaAluno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTabelaAluno.setBounds(0, 0, 379, 20);
		desktopPane.add(lblTabelaAluno);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 21, 379, 469);
		desktopPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int linha = table.getSelectedRow();
				
				matricula = Integer.parseInt(modelo.getValueAt(linha, 0).toString());				
				nome = modelo.getValueAt(linha, 1).toString();
				CPF = modelo.getValueAt(linha, 2).toString();
		
				Aluno aluno = new Aluno(nome,matricula,CPF);
				
				ManipulaDados.MudaCampos(aluno);
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
		
		JPanel panel = new JPanel();
		mainPane.add(panel, BorderLayout.SOUTH);
		desktopPane.setLayer(panel, 0);
		panel.setBackground(SystemColor.controlHighlight);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		JLabel lblPorBrenoNunes = new JLabel("Por Breno Nunes");
		panel.add(lblPorBrenoNunes);
		lblPorBrenoNunes.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		mainPane.add(toolBar, BorderLayout.NORTH);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar.add(toolBar_1);
		
		JButton btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Button > Salvar
				SalvarArquivo();
			}
		});
		
		JButton btnAbrir = new JButton("");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Button > Abrir
				AbrirArquivo();
			}
		});
		
		JButton btnNovo = new JButton("");
		toolBar_1.add(btnNovo);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Button > Exportar
				Novo();
			}
		});
		btnNovo.setToolTipText("Novo");
		btnNovo.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/databasenew32.png")));
		btnAbrir.setToolTipText("Abrir");
		toolBar_1.add(btnAbrir);
		btnAbrir.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/open.png")));
		toolBar_1.add(btnSalvar);
		btnSalvar.setToolTipText("Salvar");
		btnSalvar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/save32.png")));
		
		JButton btnSalvarComo = new JButton("");
		btnSalvarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Button > Salvar Como
				primeiraVez = true;
				SalvarArquivo();
			}
		});
		btnSalvarComo.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/saveas32.png")));
		btnSalvarComo.setToolTipText("Salvar Como...");
		toolBar_1.add(btnSalvarComo);
		
		JToolBar toolBar_3 = new JToolBar();
		toolBar.add(toolBar_3);
		
		JButton btnCadAluno = new JButton("");
		btnCadAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Button > Tela Cadastro Aluno
				ChamarTelaGerirAluno();
			}
		});
		toolBar_3.add(btnCadAluno);
		btnCadAluno.setBackground(UIManager.getColor("Button.background"));
		btnCadAluno.setToolTipText("Gerir Aluno");
		btnCadAluno.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/user32.png")));
		
		JButton btnNota = new JButton("");
		btnNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Button > Tela Inserir Nota Materia Aluno
				ChamarTelaGerirNota();
			}
		});
		btnNota.setToolTipText("Gerir Nota");
		toolBar_3.add(btnNota);
		btnNota.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/nota32.png")));
		
		JButton btnCadGradeAluno = new JButton("");
		btnCadGradeAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Button > Tela Cadastro Grade Aluno
				ChamarTelaCadastrarMateria();
			}
		});
		btnCadGradeAluno.setToolTipText("Gerir Disciplina");
		toolBar_3.add(btnCadGradeAluno);
		btnCadGradeAluno.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/disc32.png")));
		
		JButton btnCadGrade = new JButton("");
		toolBar_3.add(btnCadGrade);
		btnCadGrade.setEnabled(false);
		btnCadGrade.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/qst1/images/grade32.png")));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(140);
	}
	
	private void Importar(){
		//TODO M�todo Importar
		ManipulaDados.LimparLista();		
		ManipulaDados.Carregar("DadosAluno.json");
		AttLista();
	}
	private void Exportar(){
		//TODO M�todo Importar		
	}
	private void ChamarTelaGerirAluno(){
		//TODO M�todo ChamarTelaGerirAluno
		if(frmCadAluno.isIcon()){
			try {
				frmCadAluno.setIcon(false);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
		desktopPane.setLayer(frmCadAluno, 1);
		frmCadAluno.setBounds(389,20, 400, 260);
		desktopPane.add(frmCadAluno);
		frmCadAluno.setVisible(true);
	}
	private void ChamarTelaGerirNota(){
		//TODO M�todo ChamarTelaGerirNota
		if(frmEditNotAluno.isIcon()){
			try{
				frmEditNotAluno.setIcon(false);
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
		desktopPane.setLayer(frmEditNotAluno, 1);
		frmEditNotAluno.setBounds(389, 20, 370, 470);
		desktopPane.add(frmEditNotAluno);
		frmEditNotAluno.setVisible(true);
	}
    private void ChamarTelaCadastrarMateria(){
    	//TODO M�todo ChamarTelaCadastrarMateria
    	if(frmCadGradAluno.isIcon()){
			try {
				frmCadGradAluno.setIcon(false);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
		desktopPane.setLayer(frmCadGradAluno, 1);
		frmCadGradAluno.setBounds(389, 20, 475, 430);
		desktopPane.add(frmCadGradAluno);
		frmCadGradAluno.setVisible(true);
	}
    
    private void Novo(){
    	//TODO metodo Novo
    	SalvarTabela();
    	ManipulaDados.LimparLista();
    	AttLista();
    	setTitle("Gerenciador de Faculdade");
    }    
    private void SalvarTabela(){
    	modelo = (DefaultTableModel) table.getModel();
    	if(modelo.getRowCount() > 0){
    		int op = InOut.ConfirmDialog("Deseja salvar a tabela atual?", "Salvar");
    		if(op == 0){//sim
    			SalvarArquivo();
    		}else if(op == 1){//n�o
    			//faz nada
    		}else{//cancelou
    			return;
    		}
    	}
    }
    private void VisualizarArquivo(){
    	try {
			Abrir.AbrirArquivo(arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void AbrirArquivo(){
		//TODO Metodo abrir
    	ManipulaDados.LimparLista();
    	showOpenFileDialog();
		try{
			if(!arquivo.equals("") && arquivo.endsWith("json")){
				ManipulaDados.Carregar(arquivo);
				AttLista();
				setTitle("Gerenciador de Faculdade - "+arquivo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
   	}    
    private void SalvarArquivo(){
		//TODO M�todo Salvar
    	if(primeiraVez && arquivo.equals("")){
    		showSaveFileDialog();
    		if(!arquivo.equals("") && arquivo.endsWith("json")){
				primeiraVez = false;
		    	ManipulaDados.Salvar(arquivo);
    		}
		}else{
	    	ManipulaDados.Salvar(arquivo);
		}    	
   	}
    
    private void showOpenFileDialog() {
    	//TODO Show Open File Dialog
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("JSON file(*.json)", "json"));
        fc.setAcceptAllFileFilterUsed(true);
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            arquivo = fc.getSelectedFile().getAbsolutePath();
        }else{
        	arquivo = "";
        }
    }
    private void showSaveFileDialog() {
    	//TODO Show Save File Dialog
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("JSON file(*.json)", "json"));
        fc.setAcceptAllFileFilterUsed(true);
        int result = fc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
        	arquivo = fc.getSelectedFile().getAbsolutePath()+".json";
        }else{
        	arquivo = "";
        }
    }
    
    protected static void AttLista(){
		listaAluno = ManipulaDados.getListaAluno();
		PreencherTabela();
	}
	
	protected static void setTableEnable(boolean mode){
		table.setEnabled(mode);
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
}
