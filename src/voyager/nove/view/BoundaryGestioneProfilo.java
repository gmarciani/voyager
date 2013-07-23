package voyager.nove.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import voyager.nove.control.ControllerGestioneProfilo;
import voyager.nove.exception.DatiUtenteInconsistentiException;
import voyager.nove.exception.LoginInconsistenteException;
import voyager.nove.exception.UtenteInesistenteException;
import voyager.nove.model.utente.bean.UtenteBean;
import voyager.nove.utils.swing.DatePicker;
import voyager.nove.utils.swing.GBCHelper;

/**
 * @name BoundaryGestioneProfilo
 *
 * @project Voyager
 *
 * @package voyager.nove.view
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class BoundaryGestioneProfilo extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private static final String[] SESSO = {"Uomo", "Donna"};
	
	private static final String TITOLO = "Gestione Profilo";
	
	private JPanel panelMe;
	private JPanel panelTitolo;
	private JPanel panelMain;
	private JPanel panelBottoni;
	
	private JLabel labelTitolo;
	
	private JLabel labelNome;
	private JLabel labelCognome;
	private JLabel labelCitta;
	private JLabel labelNascita;
	private JLabel labelSesso;	
	private JLabel labelMail;
	private JLabel labelRuolo;
	private JLabel labelUsername;
	private JLabel labelPasswordVecchia;
	private JLabel labelPasswordNuova;
	private JLabel labelPasswordNuovaConferma;
	private JTextField textfieldNome;
	private JTextField textfieldCognome;
	private JTextField textfieldCitta;
	private DatePicker calendarNascita;
	private JComboBox <String>comboSesso;
	private JTextField textfieldMail;
	private JLabel labelContenutoRuolo;
	private JLabel labelContenutoUsername;
	private JCheckBox checkboxCambiaPassword;
	private JPasswordField passwordfieldPasswordVecchia;
	private JPasswordField passwordfieldPasswordNuova;
	private JPasswordField passwordfieldPasswordNuovaConferma;
	private JButton buttonOk;
	private JButton buttonAnnulla;
	
	private UtenteBean utenteBean;
	
	private boolean passwordModificata;
	
	private ControllerGestioneProfilo controllerGestisciProfilo;
		
	public BoundaryGestioneProfilo(UtenteBean utenteBean) {
		this.controllerGestisciProfilo = ControllerGestioneProfilo.getInstance();
		this.utenteBean = utenteBean;
		this.panelMe = this;
		this.passwordModificata = false;
		buildDialog();		
	}
	
	private void aggiornaUI() {
		this.textfieldNome.setText(this.utenteBean.getNome());
		this.textfieldCognome.setText(this.utenteBean.getCognome());
		this.textfieldCitta.setText(this.utenteBean.getCitta());
		this.calendarNascita.setDateAsGregorianCalendar(this.utenteBean.getNascita());
		this.comboSesso.setSelectedItem(this.utenteBean.getSesso());
		this.textfieldMail.setText(this.utenteBean.getMail());
		this.labelContenutoRuolo.setText(this.utenteBean.getRuolo().asString());
		this.labelContenutoUsername.setText(this.utenteBean.getUsername());
	}
	
	private void buildDialog() {
		
		//Initialization
		this.setLayout(new GridBagLayout());
		this.addAncestorListener(new RefreshListener());
				
		//Panel Title
		this.panelTitolo = new JPanel();
		this.panelTitolo.setLayout(new GridBagLayout());
		this.labelTitolo = new JLabel(TITOLO);
		this.panelTitolo.add(labelTitolo, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(15, 15, 15, 15));
				
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
		this.labelPasswordVecchia = new JLabel("Password Corrente");
		this.labelPasswordNuova = new JLabel("Nuova Password");
		this.labelPasswordNuovaConferma = new JLabel("Conferma Password");
		this.textfieldNome = new JTextField("", 20);
		this.textfieldCognome = new JTextField("", 20);
		this.textfieldCitta = new JTextField("", 20);
		this.calendarNascita = new DatePicker(new GregorianCalendar(27, 06, 1990));
		this.comboSesso = new JComboBox<String>(SESSO);
		this.comboSesso.setSelectedItem("Uomo");
		this.textfieldMail = new JTextField("", 20);
		this.labelContenutoRuolo = new JLabel("");
		this.labelContenutoUsername = new JLabel("");
		this.checkboxCambiaPassword = new JCheckBox("Cambia password", false);
		this.checkboxCambiaPassword.addActionListener(new CambiaPasswordListener());
		this.passwordfieldPasswordVecchia = new JPasswordField("", 20);
		this.passwordfieldPasswordVecchia.setEditable(false);
		this.passwordfieldPasswordNuova = new JPasswordField("", 20);
		this.passwordfieldPasswordNuova.setEditable(false);
		this.passwordfieldPasswordNuovaConferma = new JPasswordField("", 20);
		this.passwordfieldPasswordNuovaConferma.setEditable(false);
		
		this.panelMain.add(this.labelNome, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		this.panelMain.add(this.labelCognome, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelCitta, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelNascita, new GBCHelper(0, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelSesso, new GBCHelper(0, 4).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelMail, new GBCHelper(0, 5).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelRuolo, new GBCHelper(0, 6).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelUsername, new GBCHelper(0, 7).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelPasswordVecchia, new GBCHelper(0, 9).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelPasswordNuova, new GBCHelper(0, 10).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelPasswordNuovaConferma, new GBCHelper(0, 11).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));
		
		this.panelMain.add(this.textfieldNome, new GBCHelper(1, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		this.panelMain.add(this.textfieldCognome, new GBCHelper(1, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.textfieldCitta, new GBCHelper(1, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.calendarNascita, new GBCHelper(1, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.comboSesso, new GBCHelper(1, 4).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.textfieldMail, new GBCHelper(1, 5).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelContenutoRuolo, new GBCHelper(1, 6).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelContenutoUsername, new GBCHelper(1, 7).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.checkboxCambiaPassword, new GBCHelper(1, 8).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.passwordfieldPasswordVecchia, new GBCHelper(1, 9).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.passwordfieldPasswordNuova, new GBCHelper(1, 10).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.passwordfieldPasswordNuovaConferma, new GBCHelper(1, 11).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));

		//Panel Button
		this.panelBottoni = new JPanel();
		this.panelBottoni.setLayout(new GridLayout(1, 2, 5, 5));
		this.buttonOk = new JButton("Ok");
		this.buttonOk.addActionListener(new OkListener());
		this.buttonAnnulla = new JButton("Annulla");
		this.buttonAnnulla.addActionListener(new AnnullaListener());
		this.panelBottoni.add(this.buttonAnnulla);
		this.panelBottoni.add(this.buttonOk);
				
		//Panel Pack
		this.add(this.panelTitolo, new GBCHelper(0, 0).setWeight(100, 0).setInsets(15, 15, 15, 15));
		this.add(this.panelMain, new GBCHelper(0, 1).setWeight(100, 0).setInsets(15, 15, 15, 15));
		this.add(this.panelBottoni, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.EAST).setInsets(15, 15, 15, 15));
	}
	
	private class RefreshListener implements AncestorListener {

		@Override
		public void ancestorAdded(AncestorEvent event) {		

			String username = utenteBean.getUsername();
			
			try {
				utenteBean = controllerGestisciProfilo.findByUsername(username);
			} catch (UtenteInesistenteException e) {
				e.printStackTrace();
			}
			
			aggiornaUI();			
		}

		@Override
		public void ancestorMoved(AncestorEvent event) {
			
		}

		@Override
		public void ancestorRemoved(AncestorEvent event) {
			
		}
		
	}
	
	private class CambiaPasswordListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkboxCambiaPassword.isSelected()) {
				passwordfieldPasswordVecchia.setEditable(true);
				passwordfieldPasswordNuova.setEditable(true);
				passwordfieldPasswordNuovaConferma.setEditable(true);
				setHasModifiedPassword(true);				
				
			} else {
				passwordfieldPasswordVecchia.setEditable(false);
				passwordfieldPasswordNuova.setEditable(false);
				passwordfieldPasswordNuovaConferma.setEditable(false);
				setHasModifiedPassword(false);
			}			
		}		
	}
	
	private class OkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			aggiornaProfilo();		
		}
		
	}
	
	private class AnnullaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			panelMe.setVisible(false);
		}		
	}
	
	private void aggiornaProfilo() {
		UtenteBean nUtenteBean = new UtenteBean();
		nUtenteBean.setNome(this.textfieldNome.getText().toString());
		nUtenteBean.setCognome(this.textfieldCognome.getText().toString());
		nUtenteBean.setCitta(this.textfieldCitta.getText().toString());
		nUtenteBean.setNascita(this.calendarNascita.getDateAsGregorianCalendar());
		nUtenteBean.setSesso(String.valueOf(this.comboSesso.getSelectedItem()));
		nUtenteBean.setMail(this.textfieldMail.getText().toString());
		nUtenteBean.setUsername(this.utenteBean.getUsername());
		nUtenteBean.setRuolo(this.utenteBean.getRuolo());
		
		if (hasModifiedPassword()) {
			if (controllaPasswordVecchia() && controllaPasswordNuova()) {
				nUtenteBean.setPassword(String.valueOf(this.passwordfieldPasswordNuova.getPassword()));
			} else {
				return;
			}
		} else {
			nUtenteBean.setPassword(this.utenteBean.getPassword());
		}		

		try {
			this.controllerGestisciProfilo.aggiornaProfilo(nUtenteBean);
			JOptionPane.showMessageDialog(getParent(), "Hey! Il tuo profilo è stato correttamente aggiornato!", "Gestione Profilo", JOptionPane.INFORMATION_MESSAGE);		
		} catch (DatiUtenteInconsistentiException | LoginInconsistenteException e) {
			JOptionPane.showMessageDialog(getParent(), "Oops! Qualcosa è andato storto durante l'aggiornamento del profilo! Riprova più tardi.", "Gestione Profilo", JOptionPane.ERROR_MESSAGE);		
			e.printStackTrace();
		}		
	}
	
	private boolean controllaPasswordVecchia() {
		boolean bool = (this.utenteBean.getPassword().equals(String.valueOf(this.passwordfieldPasswordVecchia.getPassword())));
		
		if (!bool) JOptionPane.showMessageDialog(getParent(), "Oops! Password corrente errata!", "Gestione Profilo", JOptionPane.ERROR_MESSAGE);		

		
		return bool;
	}
	
	private boolean controllaPasswordNuova() {
		boolean bool = (String.valueOf(this.passwordfieldPasswordNuova.getPassword()).equals(String.valueOf(this.passwordfieldPasswordNuovaConferma.getPassword())));
		
		if (!bool) JOptionPane.showMessageDialog(getParent(), "Oops! Le password inserite non coincidono!", "Gestione Profilo", JOptionPane.ERROR_MESSAGE);
		
		return bool;
	}
	
	public boolean hasModifiedPassword() {
		return this.passwordModificata;
	}
	
	public void setHasModifiedPassword(boolean bool) {
		this.passwordModificata = bool;
	}
	
}
