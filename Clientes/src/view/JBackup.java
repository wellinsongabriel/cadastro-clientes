package view;

import java.awt.EventQueue;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Backup;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class JBackup extends JFrame {

	private JPanel contentPane;
	private ArrayList<String> arquivosBackup;
	private Backup backup;
	private String[] nomesBackup;
	private String itemSelecionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JBackup frame = new JBackup();
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
	public JBackup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 30, 735, 200);
		contentPane.add(scrollPane);
		
		backup = new Backup();
		arquivosBackup = new ArrayList<>();
		arquivosBackup = backup.listarArquivos();
		nomesBackup = arquivosBackup.toArray(new String[arquivosBackup.size()]);
		
		JList list = new JList();
		list.setListData(nomesBackup);
		
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Gerar Backup");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(btnNewButton, "Deseja gerar o backup? ")==JOptionPane.YES_NO_OPTION) {
					backup.gerarBackup();
					arquivosBackup = backup.listarArquivos();
					nomesBackup =  arquivosBackup.toArray(new String[arquivosBackup.size()]);
					list.setListData(nomesBackup);
					revalidate();
					repaint();
				}
			}
		});
		btnNewButton.setBounds(10, 250, 117, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Restaurar Backup");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(btnNewButton, "Deseja restaurar o backup? ")==JOptionPane.YES_NO_OPTION) {
					try {
						backup.restaurarBackup(itemSelecionado);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setBounds(155, 250, 117, 23);
		contentPane.add(btnNewButton_1);
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					if(list.getSelectedIndex() == -1) {
						list.setSelectedIndex(e.getFirstIndex());						
					}
					itemSelecionado = ((JList<String>)e.getSource()).getSelectedValue();
					if(itemSelecionado != null) {
						btnNewButton_1.setEnabled(true);
					}
				}
				
			}
		});
	}
}
