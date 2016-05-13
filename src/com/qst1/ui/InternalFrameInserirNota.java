package com.qst1.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;

public class InternalFrameInserirNota extends JInternalFrame {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalFrameInserirNota frame = new InternalFrameInserirNota();
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
	public InternalFrameInserirNota() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setFrameIcon(new ImageIcon(InternalFrameInserirNota.class.getResource("/com/qst1/images/nota32.png")));
		setTitle("Inserir Nota");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 530, 385);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel statusBar = new JPanel();
		statusBar.setBackground(SystemColor.controlHighlight);
		FlowLayout fl_statusBar = (FlowLayout) statusBar.getLayout();
		fl_statusBar.setAlignment(FlowLayout.LEFT);
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		
		JLabel lblPronto = new JLabel("Pronto");
		statusBar.add(lblPronto);
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 184, 50);
		mainPanel.add(panel);
		
		JButton btnNota = new JButton("");
		btnNota.setIcon(new ImageIcon(InternalFrameInserirNota.class.getResource("/com/qst1/images/notaedit32.png")));
		btnNota.setToolTipText("Adicionar Disciplina");
		btnNota.setForeground(Color.BLACK);
		btnNota.setBackground(Color.BLACK);
		btnNota.setBounds(10, 5, 40, 40);
		panel.add(btnNota);
		
		JButton btnConfirmar = new JButton("");
		btnConfirmar.setIcon(new ImageIcon(InternalFrameInserirNota.class.getResource("/com/qst1/images/notaconfirm32.png")));
		btnConfirmar.setToolTipText("Remover Disciplina");
		btnConfirmar.setBackground(Color.BLACK);
		btnConfirmar.setBounds(84, 5, 40, 40);
		panel.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(InternalFrameInserirNota.class.getResource("/com/qst1/images/notacancel32.png")));
		btnCancelar.setToolTipText("Remover Disciplina");
		btnCancelar.setBackground(Color.BLACK);
		btnCancelar.setBounds(134, 5, 40, 40);
		panel.add(btnCancelar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(153, 255, 153));
		panel_1.setBounds(184, 0, 330, 50);
		mainPanel.add(panel_1);
		
		JLabel label = new JLabel("Pesquisar:");
		label.setFont(new Font("Cambria", Font.BOLD, 14));
		label.setBounds(10, 13, 70, 20);
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(90, 13, 181, 20);
		panel_1.add(textField);
		
		JButton button_2 = new JButton("");
		button_2.setToolTipText("Pesquisar pela Matr\u00EDcula do Aluno");
		button_2.setBackground(Color.BLACK);
		button_2.setBounds(281, 5, 40, 40);
		panel_1.add(button_2);

	}
}
