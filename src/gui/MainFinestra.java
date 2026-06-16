package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.border.SoftBevelBorder;

import control.Authenticator;
import control.GestoreSanitaria;
import entity.EntityClienteRegistrato;
import exception.DAOException;
import exception.OperationException;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class MainFinestra extends JFrame {

	private static final long serialVersionUID = 1L;
	private GestoreSanitaria gs;
	private EntityClienteRegistrato ec = null;
	private Authenticator at;
	private JPanel contentPane;
	private JTextField campoEmail;
	private JTextField campoProdotto;
	private JTextField campoNome;
	private JTextField campoCognome;
	private JTextField campoIndirizzo;
	private JTextField campoEmailReg;
	private JTextField campoTelefono;
	private JTextField textField_9;
	private JPasswordField campoPasswordReg;
	private JTextField textField_7;
	private JPasswordField campoPassword;
	private JButton btnApriMenuReg;
	private JButton btnLogin;
	private JButton btnCatalogo;
	private JButton btnAggiungiAlCarrello;
	private JButton btnVisualizzaDescrizione;
	private JButton btnAcquista;
	private JButton btnRegistrati;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFinestra frame = new MainFinestra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainFinestra() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 862, 787);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel pannelloPrincipale = new JPanel();
		pannelloPrincipale.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pannelloPrincipale.setBackground(SystemColor.activeCaption);
		contentPane.add(pannelloPrincipale, "PannelloPrincipale");
		pannelloPrincipale.setLayout(null);
		
		JPanel pannelloLogin = new JPanel();
		pannelloLogin.setBackground(SystemColor.activeCaption);
		pannelloLogin.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pannelloLogin.setBounds(0, 0, 836, 206);
		pannelloPrincipale.add(pannelloLogin);
		pannelloLogin.setLayout(null);
		
		campoEmail = new JTextField();
		campoEmail.setBackground(SystemColor.control);
		campoEmail.setFont(new Font("Cambria", Font.PLAIN, 14));
		campoEmail.setBounds(10, 42, 422, 31);
		campoEmail.setColumns(10);
		pannelloLogin.add(campoEmail);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 12, 422, 19);
		pannelloLogin.add(lblNewLabel_1);
		
		btnLogin = new JButton("Login");
		btnLogin.setBackground(SystemColor.control);
		btnLogin.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnLogin.setBounds(10, 151, 116, 44);
		pannelloLogin.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 84, 422, 14);
		pannelloLogin.add(lblNewLabel);
		
		btnApriMenuReg = new JButton("Registrati");
		btnApriMenuReg.setBackground(SystemColor.control);
		btnApriMenuReg.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnApriMenuReg.setBounds(478, 42, 277, 98);
		pannelloLogin.add(btnApriMenuReg);
		
		campoPassword = new JPasswordField();
		campoPassword.setBackground(SystemColor.control);
		campoPassword.setBounds(10, 109, 422, 31);
		pannelloLogin.add(campoPassword);
		
		JPanel pannello = new JPanel();
		pannello.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pannello.setBackground(new Color(166, 163, 218));
		pannello.setBounds(0, 204, 836, 534);
		pannelloPrincipale.add(pannello);
		pannello.setLayout(null);
		
		btnCatalogo = new JButton("Visualizza Catalogo");
		btnCatalogo.setBackground(SystemColor.control);
		btnCatalogo.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnCatalogo.setBounds(238, 11, 322, 52);
		pannello.add(btnCatalogo);
		
		campoProdotto = new JTextField();
		campoProdotto.setBackground(SystemColor.control);
		campoProdotto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		campoProdotto.setBounds(10, 98, 427, 57);
		pannello.add(campoProdotto);
		campoProdotto.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Selezionare Prodotto");
		lblNewLabel_2.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 73, 427, 14);
		pannello.add(lblNewLabel_2);
		
		btnAggiungiAlCarrello = new JButton("Aggiungi al carrello");
		btnAggiungiAlCarrello.setBackground(SystemColor.control);
		btnAggiungiAlCarrello.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnAggiungiAlCarrello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAggiungiAlCarrello.setBounds(596, 98, 193, 23);
		pannello.add(btnAggiungiAlCarrello);
		
		btnVisualizzaDescrizione = new JButton("Visualizza descrizione");
		btnVisualizzaDescrizione.setBackground(SystemColor.control);
		btnVisualizzaDescrizione.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnVisualizzaDescrizione.setBounds(596, 132, 193, 23);
		pannello.add(btnVisualizzaDescrizione);
		
		JComboBox selettoreQuantita = new JComboBox();
		selettoreQuantita.setBackground(SystemColor.control);
		selettoreQuantita.setFont(new Font("Cambria", Font.PLAIN, 14));
		selettoreQuantita.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		selettoreQuantita.setMaximumRowCount(10);
		selettoreQuantita.setBounds(447, 101, 125, 54);
		pannello.add(selettoreQuantita);
		
		btnAcquista = new JButton("Acquista");
		btnAcquista.setBackground(SystemColor.control);
		btnAcquista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAcquista.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnAcquista.setBounds(238, 198, 322, 57);
		pannello.add(btnAcquista);
		
		JLabel lblNewLabel_3 = new JLabel("Finalizza acquisto carrello");
		lblNewLabel_3.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(227, 166, 322, 14);
		pannello.add(lblNewLabel_3);
		
		JPanel pannelloRegistrazione = new JPanel();
		pannelloRegistrazione.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pannelloRegistrazione.setBackground(SystemColor.inactiveCaption);
		contentPane.add(pannelloRegistrazione, "PannelloRegistrazione");
		pannelloRegistrazione.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panelloLabelReg = new JPanel();
		panelloLabelReg.setBackground(SystemColor.inactiveCaption);
		panelloLabelReg.setBorder(new EmptyBorder(0, 0, 0, 0));
		pannelloRegistrazione.add(panelloLabelReg);
		panelloLabelReg.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Nome");
		lblNewLabel_5.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(149, 86, 99, 35);
		panelloLabelReg.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Cognome");
		lblNewLabel_5_1.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_5_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setBounds(149, 144, 99, 35);
		panelloLabelReg.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("Indirizzo");
		lblNewLabel_5_2.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_5_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2.setBounds(149, 203, 99, 35);
		panelloLabelReg.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_3 = new JLabel("Email");
		lblNewLabel_5_3.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_5_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_5_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_3.setBounds(149, 260, 99, 35);
		panelloLabelReg.add(lblNewLabel_5_3);
		
		JLabel lblNewLabel_5_4 = new JLabel("Password");
		lblNewLabel_5_4.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_5_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_5_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_4.setBounds(149, 317, 99, 35);
		panelloLabelReg.add(lblNewLabel_5_4);
		
		JLabel lblNewLabel_5_5 = new JLabel("Telefono");
		lblNewLabel_5_5.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_5_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_5.setBounds(149, 373, 99, 35);
		panelloLabelReg.add(lblNewLabel_5_5);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(168, 492, 1, 1);
		panelloLabelReg.add(verticalGlue);
		
		JPanel pannelloCampiTesto = new JPanel();
		pannelloCampiTesto.setBackground(SystemColor.inactiveCaption);
		pannelloCampiTesto.setBorder(new EmptyBorder(0, 0, 0, 0));
		pannelloRegistrazione.add(pannelloCampiTesto);
		pannelloCampiTesto.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Registrati");
		lblNewLabel_4.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(-260, 11, 779, 24);
		pannelloCampiTesto.add(lblNewLabel_4);
		
		campoNome = new JTextField();
		campoNome.setBackground(SystemColor.control);
		campoNome.setFont(new Font("Cambria", Font.PLAIN, 14));
		campoNome.setBounds(10, 86, 238, 35);
		pannelloCampiTesto.add(campoNome);
		campoNome.setColumns(10);
		
		campoCognome = new JTextField();
		campoCognome.setBackground(SystemColor.control);
		campoCognome.setFont(new Font("Cambria", Font.PLAIN, 14));
		campoCognome.setColumns(10);
		campoCognome.setBounds(10, 143, 238, 35);
		pannelloCampiTesto.add(campoCognome);
		
		campoIndirizzo = new JTextField();
		campoIndirizzo.setBackground(SystemColor.control);
		campoIndirizzo.setFont(new Font("Cambria", Font.PLAIN, 14));
		campoIndirizzo.setColumns(10);
		campoIndirizzo.setBounds(10, 202, 238, 35);
		pannelloCampiTesto.add(campoIndirizzo);
		
		campoEmailReg = new JTextField();
		campoEmailReg.setBackground(SystemColor.control);
		campoEmailReg.setFont(new Font("Cambria", Font.PLAIN, 14));
		campoEmailReg.setColumns(10);
		campoEmailReg.setBounds(10, 260, 238, 35);
		pannelloCampiTesto.add(campoEmailReg);
		
		campoTelefono = new JTextField();
		campoTelefono.setBackground(SystemColor.control);
		campoTelefono.setFont(new Font("Cambria", Font.PLAIN, 14));
		campoTelefono.setColumns(10);
		campoTelefono.setBounds(10, 374, 238, 35);
		pannelloCampiTesto.add(campoTelefono);
		
		btnRegistrati = new JButton("Completa Registrazione");
		btnRegistrati.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistrati.setBounds(10, 438, 238, 41);
		pannelloCampiTesto.add(btnRegistrati);
		
		campoPasswordReg = new JPasswordField();
		campoPasswordReg.setBackground(SystemColor.control);
		campoPasswordReg.setBounds(10, 317, 238, 35);
		pannelloCampiTesto.add(campoPasswordReg);
		
		JPanel pannelloRiempitivo = new JPanel();
		pannelloRiempitivo.setBackground(SystemColor.inactiveCaption);
		pannelloRiempitivo.setBorder(new EmptyBorder(0, 0, 0, 0));
		pannelloRegistrazione.add(pannelloRiempitivo);
		pannelloRiempitivo.setLayout(null);
		
		JPanel pannelloUtenteRegistrato = new JPanel();
		pannelloUtenteRegistrato.setBackground(SystemColor.activeCaption);
		contentPane.add(pannelloUtenteRegistrato, "PannelloUtenteReg");
		pannelloUtenteRegistrato.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel pannelloRegProdotti = new JPanel();
		pannelloRegProdotti.setBackground(SystemColor.activeCaption);
		pannelloRegProdotti.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pannelloUtenteRegistrato.add(pannelloRegProdotti);
		pannelloRegProdotti.setLayout(null);
		
		textField_9 = new JTextField();
		textField_9.setBackground(SystemColor.control);
		textField_9.setFont(new Font("Cambria", Font.PLAIN, 14));
		textField_9.setBounds(10, 52, 353, 47);
		pannelloRegProdotti.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Prodotto:");
		lblNewLabel_6.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(10, 11, 353, 30);
		pannelloRegProdotti.add(lblNewLabel_6);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBackground(SystemColor.control);
		comboBox_1.setMaximumRowCount(10);
		comboBox_1.setFont(new Font("Cambria", Font.PLAIN, 14));
		comboBox_1.setBounds(392, 52, 108, 47);
		pannelloRegProdotti.add(comboBox_1);
		
		JButton btnNewButton_4_1 = new JButton("Aggiungi al carrello");
		btnNewButton_4_1.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnNewButton_4_1.setBounds(10, 110, 165, 23);
		pannelloRegProdotti.add(btnNewButton_4_1);
		
		JButton btnNewButton_5_1 = new JButton("Visualizza descrizione");
		btnNewButton_5_1.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnNewButton_5_1.setBounds(198, 110, 165, 23);
		pannelloRegProdotti.add(btnNewButton_5_1);
		
		JButton btnNewButton_6 = new JButton("Finalizza Acquisto");
		btnNewButton_6.setBackground(SystemColor.control);
		btnNewButton_6.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnNewButton_6.setBounds(10, 144, 353, 47);
		pannelloRegProdotti.add(btnNewButton_6);
		
		JPanel pannelloGestioneOrdini = new JPanel();
		pannelloGestioneOrdini.setBackground(SystemColor.activeCaption);
		pannelloGestioneOrdini.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pannelloUtenteRegistrato.add(pannelloGestioneOrdini);
		pannelloGestioneOrdini.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 11, 378, 217);
		pannelloGestioneOrdini.add(scrollPane);
		
		JTextArea txtrAdasd = new JTextArea();
		txtrAdasd.setBackground(SystemColor.control);
		txtrAdasd.setFont(new Font("Cambria", Font.PLAIN, 13));
		scrollPane.setRowHeaderView(txtrAdasd);
		
		JTextArea txtrStoricoOrdini = new JTextArea();
		txtrStoricoOrdini.setBackground(SystemColor.control);
		txtrStoricoOrdini.setFont(new Font("Cambria", Font.PLAIN, 14));
		txtrStoricoOrdini.setText("Storico Ordini");
		scrollPane.setColumnHeaderView(txtrStoricoOrdini);
		
		JButton btnNewButton_7 = new JButton("Visualizza Storico");
		btnNewButton_7.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnNewButton_7.setBackground(SystemColor.control);
		btnNewButton_7.setBounds(10, 11, 355, 49);
		pannelloGestioneOrdini.add(btnNewButton_7);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.activeCaption);
		panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pannelloUtenteRegistrato.add(panel_4);
		panel_4.setLayout(null);
		
		textField_7 = new JTextField();
		textField_7.setBackground(SystemColor.control);
		textField_7.setFont(new Font("Cambria", Font.PLAIN, 14));
		textField_7.setBounds(10, 56, 356, 46);
		panel_4.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Inserire id ordine da tracciare...");
		lblNewLabel_7.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(10, 11, 356, 34);
		panel_4.add(lblNewLabel_7);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(SystemColor.control);
		textArea.setBounds(451, 27, 375, 97);
		panel_4.add(textArea);

		registraAzioni();
		
	}
	
	public void registraAzioni() {
		
		btnApriMenuReg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) contentPane.getLayout();
				cl.show(contentPane, "PannelloRegistrazione" );
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				String email = campoEmail.getText().trim();
				String password = new String(campoPassword.getPassword()).trim();
				at = new Authenticator();
				try {
					ec = at.login(email, password);
					if(ec!=null) {
						CardLayout cl = (CardLayout) contentPane.getLayout();
						cl.show(contentPane,"PannelloUtenteReg");
					}
				} catch (DAOException e1) {
					JOptionPane.showMessageDialog(contentPane, e1,"Errore login",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		btnRegistrati.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = campoEmailReg.getText().trim();
				if(!email.contains("@")) {
					JOptionPane.showMessageDialog(contentPane, "Errore Email non contiene @");
					return;
				}
				String indirizzo = campoIndirizzo.getText().trim();
				String pw = new String(campoPasswordReg.getPassword()).trim();
				String nome = campoNome.getText().trim();
				String cognome = campoCognome.getText().trim();
				String Telefono = campoTelefono.getText().trim();
				for(int i=0;i<Telefono.length();i++) {
					if(!Character.isDigit(Telefono.charAt(i)) || Telefono.length()>10 ) {
						JOptionPane.showMessageDialog(contentPane, "Errore numero di telefono non conforme!");
						return;
					}
				}
				
				at = new Authenticator();
				try {
					at.registrati(nome, cognome, email, pw, indirizzo, Telefono);
					JOptionPane.showMessageDialog(contentPane, "Grazie della registrazione "+nome);
					CardLayout cl = (CardLayout) contentPane.getLayout();
					cl.show(contentPane,"PannelloPrincipale");
				} catch (DAOException e1) {
					JOptionPane.showMessageDialog(contentPane, e1,"Errore Registrazione",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
	}
}
