package com.qst1.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.qst1.dao.AlunoDAO;
import com.qst1.dao.GradeEscolar;
import com.qst1.persistencia.Abrir;
import com.qst1.vo.Aluno;
import com.qst1.vo.Disciplina;
import com.recursos.InOut;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class FrmCadastroAluno extends JFrame {
	private Interacao interacao = new Interacao();
	
	private static AlunoDAO listaAluno = new AlunoDAO();
	private static GradeEscolar grade = new GradeEscolar();
	private static Disciplina disc;
	private static Aluno aluno;
	private static boolean ProgramaJaRodou = false;
		
	protected 	JTextField 	txtPesquisar;
	protected 	JTextField 	txtMatricula;
	protected 	JTextField 	txtNome;
	protected 	JTextField 	txtCPF;	
	
	protected 	JButton 	btnConfirmar;
	protected 	JButton 	btnCancelar;
	protected 	JButton 	btnAbrir;
	protected 	JButton 	btnCadastrar;
	protected 	JButton 	btnDeletar;
	protected 	JButton 	btnAtualizar;
	protected 	JButton 	btnPesquisar;
	
	protected 	JTable 		table;
	
	protected 	JPanel 		contentPane;
	protected 	JPanel 		panelAzul;	
	protected 	JPanel 		panelPesquisa;
	protected 	JPanel 		panelStatus;
	
	protected 	JScrollPane	scrollPane;
	private		JLabel 		lblStatus;
	
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
					FrmCadastroAluno frame = new FrmCadastroAluno();
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
	
	public FrmCadastroAluno() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCadastroAluno.class.getResource("/com/qst1/images/icon.png")));
		setTitle("Cadastro Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 487);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem mntmAjuda = new JMenuItem("Ajuda");
		mntmAjuda.setIcon(new ImageIcon(FrmCadastroAluno.class.getResource("/com/qst1/images/help.png")));
		mnAjuda.add(mntmAjuda);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.setIcon(new ImageIcon(FrmCadastroAluno.class.getResource("/com/qst1/images/info.png")));
		mnAjuda.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setBounds(78, 129, 200, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 132, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblDisciplina = new JLabel("Lista de Disciplinas");
		lblDisciplina.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplina.setBounds(360, 106, 204, 17);
		contentPane.add(lblDisciplina);
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCPF.setBounds(10, 157, 46, 14);
		contentPane.add(lblCPF);
		
		txtCPF = new JTextField();
		txtCPF.setEnabled(false);
		txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCPF.setColumns(10);
		txtCPF.setBounds(78, 154, 200, 20);
		contentPane.add(txtCPF);
		
		JButton btnSalvar = new JButton("");
		btnSalvar.setToolTipText("Salvar Dados");
		btnSalvar.setBackground(new Color(0, 0, 0));
		btnSalvar.setIcon(new ImageIcon(FrmCadastroAluno.class.getResource("/com/qst1/images/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Salvar os dados em JSon
				Salvar();
			}
		});
		btnSalvar.setBounds(454, 343, 50, 50);
		contentPane.add(btnSalvar);
		
		btnAbrir = new JButton("");
		btnAbrir.setToolTipText("Visualizar Dados");
		btnAbrir.setBackground(new Color(0, 0, 0));
		btnAbrir.setIcon(new ImageIcon(FrmCadastroAluno.class.getResource("/com/qst1/images/open.png")));
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Abrir.AbrirArquivo("DadosAluno.json");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAbrir.setBounds(514, 343, 50, 50);
		contentPane.add(btnAbrir);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 193, 340, 200);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Matricula", "Nome", "CPF"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.setBackground(Color.WHITE);
		
		panelAzul = new JPanel();
		panelAzul.setBackground(new Color(153, 204, 255));
		panelAzul.setBounds(0, 0, 574, 50);
		contentPane.add(panelAzul);
		panelAzul.setLayout(null);
		
		btnDeletar = new JButton("");
		btnDeletar.setToolTipText("Excluir");
		btnDeletar.setBackground(new Color(0, 0, 0));
		btnDeletar.setIcon(new ImageIcon(FrmCadastroAluno.class.getResource("/com/qst1/images/delete.png")));
		btnDeletar.setBounds(110, 5, 40, 40);
		panelAzul.add(btnDeletar);
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Deletar um Objeto
				mode = 2;				
				int row = table.getSelectedRow();
				if(row == -1){
					return;
				}
				matricula = (Integer)table.getValueAt(row, 0);
				nome = (String)table.getValueAt(row, 1);
				AtivarBotoes(true);
				LimparCampos();
				table.setEnabled(false);
				
				aluno = new Aluno();
				aluno.setMatricula(matricula);
				
				InOut.OutMessage("Para excluir o Aluno: " + nome + "\nClique no Botão confirmar");
				lblStatus.setText("Excluindo Aluno");				
			}
		});
		
		btnAtualizar = new JButton("");
		btnAtualizar.setToolTipText("Atualizar");
		btnAtualizar.setBackground(new Color(0, 0, 0));
		btnAtualizar.setIcon(new ImageIcon(FrmCadastroAluno.class.getResource("/com/qst1/images/update.png")));
		btnAtualizar.setBounds(60, 5, 40, 40);
		panelAzul.add(btnAtualizar);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Atualizar os dados
				mode = 1;
				//Interacao.DeletarCliente(Integer.parseInt(txtId.getText()));
				AtivarCampos(true);
				AtivarBotoes(true);
				LimparCampos();
			}
		});
		
		btnConfirmar = new JButton("");
		btnConfirmar.setToolTipText("Confirmar");
		btnConfirmar.setEnabled(false);		
		btnConfirmar.setBackground(new Color(0, 0, 0));
		btnConfirmar.setBounds(464, 5, 40, 40);
		panelAzul.add(btnConfirmar);
		btnConfirmar.setIcon(new ImageIcon(FrmCadastroAluno.class.getResource("/com/qst1/images/confirm.png")));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Confirmar a ação
				switch(mode){
					case 0:					///modo de criação
						Interacao.CadastrarAluno(listaAluno, txtNome.getText(),txtCPF.getText());
						txtMatricula.setText(Aluno.getGerador()+"");
						break;
					case 1:					///modo de alteração
						
						break;
					case 2:					///modo de exclusão
						listaAluno.Delete(aluno);
						break;
				}
				PreencherTabela();
				AtivarBotoes(false);
				AtivarCampos(false);
				lblStatus.setText("Pronto");
			}
		});
		
		btnCadastrar = new JButton("");
		btnCadastrar.setToolTipText("Cadastrar");
		btnCadastrar.setBounds(10, 5, 40, 40);
		panelAzul.add(btnCadastrar);
		btnCadastrar.setForeground(Color.BLACK);
		btnCadastrar.setBackground(new Color(0, 0, 0));
		btnCadastrar.setIcon(new ImageIcon(FrmCadastroAluno.class.getResource("/com/qst1/images/new.png")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Cadastrar um aluno
				mode = 0;
				AtivarCampos(true);
				AtivarBotoes(true);
				LimparCampos();
				txtNome.requestFocus();
				//Interacao.InserirCliente(txtNome.getText(), txtIdade.getText());
				lblStatus.setText("Cadastrando Aluno");			
			}
		});
		
		btnCancelar = new JButton("");
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Cancelar uma ação
				AtivarBotoes(false);
				AtivarCampos(false);
				table.setEnabled(true);
				lblStatus.setText("Pronto");
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setBackground(new Color(0, 0, 0));
		btnCancelar.setBounds(514, 5, 40, 40);
		panelAzul.add(btnCancelar);
		btnCancelar.setIcon(new ImageIcon(FrmCadastroAluno.class.getResource("/com/qst1/images/cancel.png")));
		
		panelPesquisa = new JPanel();
		panelPesquisa.setBackground(new Color(153, 255, 153));
		panelPesquisa.setBounds(0, 50, 574, 46);
		contentPane.add(panelPesquisa);
		panelPesquisa.setLayout(null);
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setFont(new Font("Cambria", Font.BOLD, 14));
		lblPesquisar.setBounds(10, 13, 70, 20);
		panelPesquisa.add(lblPesquisar);
		
		txtPesquisar = new JTextField();
		txtPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPesquisar.setBounds(90, 13, 150, 20);
		panelPesquisa.add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		btnPesquisar = new JButton("");
		btnPesquisar.setToolTipText("Pesquisar");
		btnPesquisar.setBackground(new Color(0, 0, 0));
		btnPesquisar.setBounds(250, 3, 40, 40);
		btnPesquisar.setIcon(new ImageIcon(FrmCadastroAluno.class.getResource("/com/qst1/images/search.png")));
		panelPesquisa.add(btnPesquisar);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Pesquisar um aluno
				
			}
		});
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMatricula.setBounds(10, 107, 58, 14);
		contentPane.add(lblMatricula);
		
		panelStatus = new JPanel();
		panelStatus.setBackground(SystemColor.controlHighlight);
		FlowLayout fl_panelStatus = (FlowLayout) panelStatus.getLayout();
		fl_panelStatus.setAlignment(FlowLayout.LEADING);
		panelStatus.setBounds(0, 407, 574, 20);
		contentPane.add(panelStatus);		
		
		lblStatus = new JLabel("Status");
		panelStatus.add(lblStatus);
		
		txtMatricula = new JTextField();
		txtMatricula.setEditable(false);
		txtMatricula.setText("1");
		txtMatricula.setBounds(78, 106, 86, 20);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		DefaultListModel<String> lista = new DefaultListModel<String>();
		JList listaMaterias = new JList(lista);		
		listaMaterias.setBounds(360, 133, 204, 203);
		Interacao.CadastrarDisciplinas(grade);
		
		for(Disciplina disc : grade.getListaDisc()){
			lista.addElement(disc.getNome());
		}
		contentPane.add(listaMaterias);		
		
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
		btnDeletar.setEnabled(!estado);
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
