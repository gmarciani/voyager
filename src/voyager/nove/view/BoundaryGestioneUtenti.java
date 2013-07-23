package voyager.nove.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import voyager.nove.control.ControllerGestioneUtenti;
import voyager.nove.exception.DatiUtenteInconsistentiException;
import voyager.nove.exception.LoginInconsistenteException;
import voyager.nove.model.utente.bean.UtenteBean;
import voyager.nove.utils.swing.GBCHelper;
import voyager.nove.view.dialog.utente.DialogModificaUtente;
import voyager.nove.view.dialog.utente.DialogNuovoUtente;
import voyager.nove.view.utils.TabellaUtenti;

/**
 * @name BoundaryGestioneUtenti
 *
 * @project Voyager
 *
 * @package voyager.nove.view
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class BoundaryGestioneUtenti extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private static final String TITOLO = "Amministrazione Utenti";
	
	private static final String QUERY_KEY[] = {"Username", "Nome", "Cognome", "Città", "Mail"};
	
	private JPanel panelMe;
	
	private JPanel panelTitolo;
	private JPanel panelCerca;
	private JScrollPane panelTabella;
	private JPanel panelBottoni;	
	
	private JLabel labelTitolo;
	private JTextField textfieldCerca;
	private JComboBox<String>comboQueryKey;
	private JButton buttonNuovo;
	private JButton buttonModifica;
	private JButton buttonRimuovi;
	private TabellaUtenti tabellaUtenti;
	
	private ControllerGestioneUtenti controllerAmministraUtenti;
	
	public BoundaryGestioneUtenti() {
		this.controllerAmministraUtenti = ControllerGestioneUtenti.getInstance();
		this.panelMe = this;
		buildFrame();					
	}	
	
	private void buildFrame() {
		
		//Initialization
		this.setLayout(new GridBagLayout());
		this.addAncestorListener(new RefreshListener());
				
		//Panel Title
		this.panelTitolo = new JPanel();		
		this.labelTitolo = new JLabel(TITOLO);
		this.labelTitolo.setFont(new Font(this.labelTitolo.getFont().getName(), Font.BOLD, 20));		
		this.panelTitolo.add(labelTitolo);
				
		//Panel Search
		this.panelCerca = new JPanel();		
		this.textfieldCerca = new JTextField("", 30);
		this.textfieldCerca.getDocument().addDocumentListener(new CercaListener());
		this.comboQueryKey = new JComboBox<String>(QUERY_KEY);
		this.panelCerca.add(this.textfieldCerca);
		this.panelCerca.add(this.comboQueryKey);
				
		//Panel Table
		this.tabellaUtenti = new TabellaUtenti(this.controllerAmministraUtenti.getUtenti());
		this.tabellaUtenti.getSelectionModel().addListSelectionListener(new UtenteSelezionatoListener());
		this.panelTabella = new JScrollPane(tabellaUtenti);
				
		//Panel Button
		this.panelBottoni = new JPanel();
		this.panelBottoni.setLayout(new GridBagLayout());		
		this.buttonNuovo = new JButton("Nuovo");
		this.buttonModifica = new JButton("Modifica");
		this.buttonModifica.setEnabled(false);
		this.buttonRimuovi = new JButton("Rimuovi");	
		this.buttonRimuovi.setEnabled(false);
		this.buttonNuovo.addActionListener(new NuovoListener());
		this.buttonModifica.addActionListener(new ModificaListener());
		this.buttonRimuovi.addActionListener(new RimuoviListener());	
		this.panelBottoni.add(this.buttonNuovo, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 10, 0));
		this.panelBottoni.add(this.buttonModifica, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 10, 0));
		this.panelBottoni.add(this.buttonRimuovi, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 0, 0));	
				
		//Frame Pack
		this.add(this.panelTitolo, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 15, 15));
		this.add(this.panelCerca, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.EAST).setInsets(5, 5, 5, 10));
		this.add(this.panelTabella, new GBCHelper(0, 2).setWeight(100, 100).setFill(GridBagConstraints.BOTH).setInsets(10, 10, 10, 10));
		this.add(this.panelBottoni, new GBCHelper(1, 2).setWeight(0, 100).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.NORTH).setInsets(10, 5, 0, 10));		
	}
	
	private class RefreshListener implements AncestorListener {

		@Override
		public void ancestorAdded(AncestorEvent event) {		

			aggiornaTabellaUtenti();		
		}

		@Override
		public void ancestorMoved(AncestorEvent event) {}

		@Override
		public void ancestorRemoved(AncestorEvent event) {}
		
	}
	
	private void aggiornaTabellaUtenti() {
		this.tabellaUtenti.aggiornaTabella(this.controllerAmministraUtenti.getUtenti());
	}

	private class UtenteSelezionatoListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			buttonModifica.setEnabled(true);
			buttonRimuovi.setEnabled(true);
		}
		
	}
	
	private class CercaListener implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent arg0) {}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String query = textfieldCerca.getText().toString();	
			String key = comboQueryKey.getSelectedItem().toString();
			cerca(key, query);
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			String query = textfieldCerca.getText().toString();
			String key = comboQueryKey.getSelectedItem().toString();
			cerca(key, query);
		}
		
	}
	
	private class NuovoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DialogNuovoUtente dialog = new DialogNuovoUtente((JFrame)SwingUtilities.getWindowAncestor(panelMe));
			dialog.setVisible(true);
			if (dialog.datiValidi()) {
				nuovo(dialog.getUtenteBean());
				aggiornaTabellaUtenti();
			}
		}		
	}
		
	private class ModificaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			UtenteBean utenteBean = tabellaUtenti.getUtenteSelezionato();
			DialogModificaUtente dialog = new DialogModificaUtente((JFrame)SwingUtilities.getWindowAncestor(panelMe), utenteBean);
			dialog.setVisible(true);
			if (dialog.datiValidi()) {
				modifica(dialog.getUtenteBean());
				aggiornaTabellaUtenti();
			}
		}		
	}
	
	private class RimuoviListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			UtenteBean utenteBean = tabellaUtenti.getUtenteSelezionato();
			int choice = JOptionPane.showConfirmDialog(getParent(), "Sei sicuro di voler rimuovere " + utenteBean.getUsername() + "?", "Conferma Rimozione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choice == JOptionPane.YES_OPTION) {
				rimuovi(utenteBean);
				aggiornaTabellaUtenti();
			}
		}
	}
	
	private void nuovo(UtenteBean utenteBean) {
		try {
			System.out.println(utenteBean.getNome() + utenteBean.getSesso());
			this.controllerAmministraUtenti.nuovo(utenteBean);
		} catch (DatiUtenteInconsistentiException | LoginInconsistenteException e) {
			e.printStackTrace();
		}
	}
	
	private void modifica(UtenteBean utenteBean) {		
		try {
			this.controllerAmministraUtenti.modifica(utenteBean);
		} catch (DatiUtenteInconsistentiException | LoginInconsistenteException e) {
			e.printStackTrace();
		}
	}
	
	private void rimuovi(UtenteBean utenteBean) {
		try {
			this.controllerAmministraUtenti.rimuovi(utenteBean);
		} catch (DatiUtenteInconsistentiException | LoginInconsistenteException e) {
			e.printStackTrace();
		}
	}
	
	private void cerca(String key, String query) {
		this.tabellaUtenti.aggiornaTabella(this.controllerAmministraUtenti.cerca(key, query));
	}		
	
}
