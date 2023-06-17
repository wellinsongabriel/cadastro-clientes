package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

import dao.DAO;
import model.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCpfCnpj;
	private JTextField textFieldTelefone;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JLabel lblNewLabel_2;
	private JTextArea textAreaEndereco;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastro frame = new JCadastro(null, null);
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
	public JCadastro(Cliente clienteSelecionado, JPrincipal jPrincipal) {
		DAO dao = new DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(22, 11, 45, 14);
		contentPane.add(lblNewLabel);

		textFieldNome = new JTextField();
		textFieldNome.setBorder(new LineBorder(new Color(171, 173, 179)));
		textFieldNome.setBounds(22, 24, 390, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("CPF/CNPJ");
		lblNewLabel_1.setBounds(22, 60, 62, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Telefone");
		lblNewLabel_1_1.setBounds(223, 60, 62, 14);
		contentPane.add(lblNewLabel_1_1);

		textFieldCpfCnpj = new JTextField();
		textFieldCpfCnpj.setBorder(new LineBorder(new Color(171, 173, 179)));
		textFieldCpfCnpj.setBounds(22, 72, 189, 20);
		contentPane.add(textFieldCpfCnpj);
		textFieldCpfCnpj.setColumns(10);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setBorder(new LineBorder(new Color(171, 173, 179)));
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(223, 72, 189, 20);
		contentPane.add(textFieldTelefone);

		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(22, 100, 45, 14);
		contentPane.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setBorder(new LineBorder(new Color(171, 173, 179)));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(22, 113, 390, 20);
		contentPane.add(textFieldEmail);

		lblNewLabel_2 = new JLabel("Endereço");
		lblNewLabel_2.setBounds(22, 144, 62, 14);
		contentPane.add(lblNewLabel_2);

		textAreaEndereco = new JTextArea();
		textAreaEndereco.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaEndereco.setBounds(22, 157, 390, 39);
		contentPane.add(textAreaEndereco);

		JButton btnNewButton = new JButton(clienteSelecionado == null ? "Incluir" : "Alterar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// String id, String nome, String cpfCnpj, String email, String telefone, String
				// endereco
				Cliente cliente = new Cliente(null, textFieldNome.getText(), textFieldCpfCnpj.getText(),
						textFieldEmail.getText(), textFieldTelefone.getText(), textAreaEndereco.getText());
				if (clienteSelecionado == null) {
					if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textFieldCpfCnpj.getText())) {
						dao.cadastarCliente(cliente);
						abrirTelaPrincipal(jPrincipal);
					}else {
						JOptionPane.showMessageDialog(null, "Confira os campos Nome e CPF/CNPJ");
					}
					
				} else {
					if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textFieldCpfCnpj.getText())) {
						dao.alterarCliente(clienteSelecionado.getId(), cliente);
						abrirTelaPrincipal(jPrincipal);
					}else {
						JOptionPane.showMessageDialog(null, "Confira os campos Nome e CPF/CNPJ");
					}
					
				}
			}
		});
		btnNewButton.setBounds(323, 213, 89, 23);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Excluir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.excluirCliente(clienteSelecionado.getId());
				abrirTelaPrincipal(jPrincipal);
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setBounds(22, 213, 89, 23);
		btnNewButton_1.setVisible(false);
		contentPane.add(btnNewButton_1);

		if (clienteSelecionado != null) {
			preencherCampos(clienteSelecionado);
			btnNewButton_1.setVisible(true);
		}
	}

	private void preencherCampos(Cliente clienteSelecionado) {
		textFieldNome.setText(clienteSelecionado.getNome());
		textFieldCpfCnpj.setText(clienteSelecionado.getCpfCnpj());
		textFieldEmail.setText(clienteSelecionado.getEmail());
		textFieldTelefone.setText(clienteSelecionado.getTelefone());
		textAreaEndereco.setText(clienteSelecionado.getEndereco());
	}

	private void abrirTelaPrincipal(JPrincipal jPrincipal) {
		jPrincipal.dispose();
		dispose();
		jPrincipal = new JPrincipal();
		jPrincipal.setLocationRelativeTo(jPrincipal);
		jPrincipal.setVisible(true);
	}
}
