package com.qst1.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.qst1.persistencia.Abrir;

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

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Form extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtIdade;
	private JButton btnAbrir;
	private JButton btnCadastrar;
	private JButton btnDeletar;
	private JButton btnAtualizar;
	private JTextField txtPesquisar;
	private JTable table;
	private JPanel panel;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JPanel panel_1;
	private JTextField txtId;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {			//Windows, Nimbus, 
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form frame = new Form();
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
	
	public Form() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Form.class.getResource("/com/qst1/images/icon.png")));
		setTitle("Cadastro de Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 487);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem mntmAjuda = new JMenuItem("Ajuda");
		mntmAjuda.setIcon(new ImageIcon(Form.class.getResource("/com/qst1/images/help.png")));
		mnAjuda.add(mntmAjuda);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.setIcon(new ImageIcon(Form.class.getResource("/com/qst1/images/info.png")));
		mnAjuda.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setBounds(66, 129, 165, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 132, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdade.setBounds(10, 157, 46, 14);
		contentPane.add(lblIdade);
		
		txtIdade = new JTextField();
		txtIdade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIdade.setColumns(10);
		txtIdade.setBounds(66, 154, 165, 20);
		contentPane.add(txtIdade);
		
		JButton btnSalvar = new JButton("");
		btnSalvar.setBackground(new Color(0, 0, 0));
		btnSalvar.setIcon(new ImageIcon(Form.class.getResource("/com/qst1/images/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNome.setText("");
				txtIdade.setText("");
				//String msg = "Nome: " + c.getNome() + "\nIdade: " + c.getIdade();				
			}
		});
		btnSalvar.setBounds(380, 343, 50, 50);
		contentPane.add(btnSalvar);
		
		btnAbrir = new JButton("");
		btnAbrir.setBackground(new Color(0, 0, 0));
		btnAbrir.setIcon(new ImageIcon(Form.class.getResource("/com/qst1/images/open.png")));
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Abrir.AbrirArquivo("Arquivo.txt");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAbrir.setBounds(440, 343, 50, 50);
		contentPane.add(btnAbrir);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setBounds(10, 212, 360, 181);
		contentPane.add(table);
		
		panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 500, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnDeletar = new JButton("");
		btnDeletar.setBackground(new Color(0, 0, 0));
		btnDeletar.setIcon(new ImageIcon(Form.class.getResource("/com/qst1/images/delete.png")));
		btnDeletar.setBounds(110, 5, 40, 40);
		panel.add(btnDeletar);
		
		btnAtualizar = new JButton("");
		btnAtualizar.setBackground(new Color(0, 0, 0));
		btnAtualizar.setIcon(new ImageIcon(Form.class.getResource("/com/qst1/images/update.png")));
		btnAtualizar.setBounds(60, 5, 40, 40);
		panel.add(btnAtualizar);
		
		btnConfirmar = new JButton("");
		btnConfirmar.setBackground(new Color(0, 0, 0));
		btnConfirmar.setBounds(400, 5, 40, 40);
		panel.add(btnConfirmar);
		btnConfirmar.setIcon(new ImageIcon(Form.class.getResource("/com/qst1/images/confirm.png")));
		
		btnCadastrar = new JButton("");
		btnCadastrar.setBounds(10, 5, 40, 40);
		panel.add(btnCadastrar);
		btnCadastrar.setForeground(Color.BLACK);
		btnCadastrar.setBackground(new Color(0, 0, 0));
		btnCadastrar.setIcon(new ImageIcon(Form.class.getResource("/com/qst1/images/new.png")));
		
		btnCancelar = new JButton("");
		btnCancelar.setBackground(new Color(0, 0, 0));
		btnCancelar.setBounds(450, 5, 40, 40);
		panel.add(btnCancelar);
		btnCancelar.setIcon(new ImageIcon(Form.class.getResource("/com/qst1/images/cancel.png")));
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 255, 153));
		panel_1.setBounds(0, 50, 500, 46);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setFont(new Font("Cambria", Font.BOLD, 14));
		lblPesquisar.setBounds(10, 13, 70, 20);
		panel_1.add(lblPesquisar);
		
		txtPesquisar = new JTextField();
		txtPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPesquisar.setBounds(90, 13, 150, 20);
		panel_1.add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.setBackground(new Color(0, 0, 0));
		btnPesquisar.setBounds(250, 3, 40, 40);
		btnPesquisar.setIcon(new ImageIcon(Form.class.getResource("/com/qst1/images/search.png")));
		panel_1.add(btnPesquisar);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(10, 107, 46, 14);
		contentPane.add(lblId);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.controlHighlight);
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		panel_2.setBounds(0, 407, 500, 20);
		contentPane.add(panel_2);
		
		JLabel lblStatus = new JLabel("Pronto");
		panel_2.add(lblStatus);
		
		txtId = new JTextField();
		txtId.setText("0");
		txtId.setBounds(66, 106, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Interacao.InserirCliente(txtNome.getText(), txtIdade.getText());
				lblStatus.setText("Cliente Cadastrado");
				txtNome.setText("");
				txtIdade.setText("");
			}
		});
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Interacao.DeletarCliente(Integer.parseInt(txtId.getText()));
			}
		});
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
