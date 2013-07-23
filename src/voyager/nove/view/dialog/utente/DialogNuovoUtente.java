package voyager.nove.view.dialog.utente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import voyager.nove.control.ControllerGestioneUtenti;
import voyager.nove.model.utente.bean.UtenteBean;
import voyager.nove.model.utente.ruolo.FactoryRuoli;
import voyager.nove.utils.swing.DatePicker;
import voyager.nove.utils.swing.GBCHelper;

/**
 * @name DialogNuovaPrenotazione
 *
 * @project Voyager
 *
 * @package voyager.nove.view.utils
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class DialogNuovoUtente extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
	private static final String[] SESSO = {"Uomo", "Donna"};
	private static final String[] RUOLI = {"Amministratore", "Promotore", "Progettista", "Venditore", "Cliente"};
	
	public JDialog dialogMe;
	
	private JPanel panelMain;
	private JPanel panelBottoni;
	
	private JLabel labelNome;
	private JLabel labelCognome;
	private JLabel labelCitta;
	private JLabel labelNascita;
	private JLabel labelSesso;	
	private JLabel labelMail;
	private JLabel labelRuolo;
	private JLabel labelUsername;
	private JLabel labelPassword;
	private JTextField textfieldNome;
	private JTextField textfieldCognome;
	private JTextField textfieldCitta;
	private DatePicker calendarNascita;
	private JComboBox <String>comboSesso;
	private JTextField textfieldMail;
	private JComboBox <String>comboRuolo;
	private JCheckBox checkboxGeneraPassword;
	private JTextField textfieldUsername;
	private JPasswordField passwordfieldPassword;
	private JButton buttonOk;
	private JButton buttonAnnulla;
	
	private boolean datiValidi;
	
	private UtenteBean utenteBean;
	
	private ControllerGestioneUtenti controllerAmministraUtenti;
	
	public DialogNuovoUtente(JFrame ownerFrame) {
		super(ownerFrame, "Nuovo Utente", true);
		this.controllerAmministraUtenti = ControllerGestioneUtenti.getInstance();
		this.utenteBean = new UtenteBean();
		setDatiValidi(false);
		buildDialog();		
	}
	
	private void buildDialog() {
		
		//Initialization
		this.setResizable(false);
		this.setLayout(new GridBagLayout());		
		this.dialogMe = this;
		
		//Panel Main
		this.panelMain = new JPanel();
		this.panelMain.setLayout(new GridBagLayout());
		this.labelNome = new JLabel("Nome");
		this.labelCognome = new JLabel("Cognome");
		this.labelCitta = new JLabel("Città");
		this.labelNascita = new JLabel("Data di nascita");
		this.labelSesso = new JLabel("Sesso");
		this.labelMail = new JLabel("Mail");
		this.labelRuolo = new JLabel("Ruolo");		
		this.labelUsername = new JLabel("Username");
		this.labelPassword = new JLabel("Password");
		this.textfieldNome = new JTextField("", 20);
		this.textfieldCognome = new JTextField("", 20);
		this.textfieldCitta = new JTextField("", 20);
		this.calendarNascita = new DatePicker();
		this.comboSesso = new JComboBox<String>(SESSO);
		this.textfieldMail = new JTextField("", 20);
		this.comboRuolo = new JComboBox<String>(RUOLI);	
		this.textfieldUsername = new JTextField("", 20);
		this.passwordfieldPassword = new JPasswordField("", 20);
		this.checkboxGeneraPassword = new JCheckBox("Genera Password", false);
		this.checkboxGeneraPassword.addActionListener(new GeneratePasswordListener());
		this.panelMain.add(this.labelNome, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		this.panelMain.add(this.labelCognome, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelCitta, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelNascita, new GBCHelper(0, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelSesso, new GBCHelper(0, 4).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelMail, new GBCHelper(0, 5).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelRuolo, new GBCHelper(0, 6).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelUsername, new GBCHelper(0, 7).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelPassword, new GBCHelper(0, 9).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));
		
		this.panelMain.add(this.textfieldNome, new GBCHelper(1, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		this.panelMain.add(this.textfieldCognome, new GBCHelper(1, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.textfieldCitta, new GBCHelper(1, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.calendarNascita, new GBCHelper(1, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.comboSesso, new GBCHelper(1, 4).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.textfieldMail, new GBCHelper(1, 5).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.comboRuolo, new GBCHelper(1, 6).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.textfieldUsername, new GBCHelper(1, 7).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.checkboxGeneraPassword, new GBCHelper(1, 8).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.passwordfieldPassword, new GBCHelper(1, 9).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));
		
		//Panel Button
		this.panelBottoni = new JPanel();
		this.panelBottoni.setLayout(new GridLayout(1, 2, 5, 5));
		this.buttonOk = new JButton("Ok");
		this.buttonOk.addActionListener(new OkListener());
		this.buttonAnnulla = new JButton("Cancel");
		this.buttonAnnulla.addActionListener(new AnnullaListener());
		this.panelBottoni.add(buttonAnnulla);
		this.panelBottoni.add(buttonOk);		
		
		//Dialog Pack
		this.add(this.panelMain, new GBCHelper(0, 0).setWeight(100, 0).setInsets(15, 15, 15, 15));
		this.add(this.panelBottoni, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.EAST).setInsets(15, 15, 15, 15));
		this.pack();		
		
		//Default button
		this.getRootPane().setDefaultButton(buttonOk);
	}
	
	private class GeneratePasswordListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkboxGeneraPassword.isSelected()) {
				generaPassword();
				passwordfieldPassword.setEditable(false);
			} else {
				passwordfieldPassword.setText("");
				passwordfieldPassword.setEditable(true);
			}
		}		
	}
	
	private class OkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setUtenteBean();
			setDatiValidi(true);
			dialogMe.setVisible(false);			
		}		
	}
	
	private class AnnullaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setDatiValidi(false);
			dialogMe.setVisible(false);
			dialogMe.dispose();
		}		
	}
	
	private void generaPassword() {	
		this.passwordfieldPassword.setText(this.controllerAmministraUtenti.generaPassword());
	}
	
	private void setUtenteBean() {
		this.utenteBean.setNome(this.textfieldNome.getText().toString());
		this.utenteBean.setCognome(this.textfieldCognome.getText().toString());
		this.utenteBean.setCitta(this.textfieldCitta.getText().toString());
		this.utenteBean.setNascita(this.calendarNascita.getDateAsGregorianCalendar());
		this.utenteBean.setSesso(String.valueOf(this.comboSesso.getSelectedItem()));
		this.utenteBean.setMail(this.textfieldMail.getText().toString());
		this.utenteBean.setUsername(this.textfieldUsername.getText().toString());
		this.utenteBean.setRuolo(FactoryRuoli.getInstance().assegnaRuolo(this.comboRuolo.getSelectedIndex()));
		this.utenteBean.setPassword(String.valueOf(this.passwordfieldPassword.getPassword()));
	}
	
	public UtenteBean getUtenteBean() {
		return this.utenteBean;
	}

	private void setDatiValidi(boolean bool) {
		this.datiValidi = bool;
	}
	
	public boolean datiValidi() {
		return this.datiValidi;
	}
	
}
