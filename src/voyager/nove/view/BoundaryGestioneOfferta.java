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

import voyager.nove.control.ControllerGestioneOfferta;
import voyager.nove.exception.PrenotazioneAttivaException;
import voyager.nove.model.viaggio.bean.OffertaBean;
import voyager.nove.utils.swing.GBCHelper;
import voyager.nove.view.dialog.offerta.DialogModificaOfferta;
import voyager.nove.view.dialog.offerta.DialogNuovaOfferta;
import voyager.nove.view.utils.TabellaOfferte;

/**
 * @name BoundaryGestioneOfferta
 *
 * @project Voyager
 *
 * @package voyager.nove.view
 *
 * @author Giacomo Marciani
 *
 */
public class BoundaryGestioneOfferta extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private static final String TITOLO = "Gestione Offerta";
	
	private static final String QUERY_KEY[] = {"Id Offerta", "Id Viaggio", "Posti Disponibili"};
	
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
	private TabellaOfferte tabellaOfferte;
	
	private ControllerGestioneOfferta controllerGestioneOfferta;
	
	public BoundaryGestioneOfferta() {
		this.controllerGestioneOfferta = ControllerGestioneOfferta.getInstance();
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
		this.tabellaOfferte = new TabellaOfferte(this.controllerGestioneOfferta.getOfferte());
		this.tabellaOfferte.getSelectionModel().addListSelectionListener(new OffertaSelezionataListener());
		this.panelTabella = new JScrollPane(tabellaOfferte);
				
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

			aggiornaTabellaOfferte();		
		}

		@Override
		public void ancestorMoved(AncestorEvent event) {
			
		}

		@Override
		public void ancestorRemoved(AncestorEvent event) {
			
		}
		
	}
	
	private void aggiornaTabellaOfferte() {
		this.tabellaOfferte.aggiornaTabella(this.controllerGestioneOfferta.getOfferte());
	}

	private class OffertaSelezionataListener implements ListSelectionListener {

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
			DialogNuovaOfferta dialog = new DialogNuovaOfferta((JFrame)SwingUtilities.getWindowAncestor(panelMe));
			dialog.setVisible(true);
			if (dialog.datiValidi()) {
				nuovo(dialog.getOffertaBean());
				aggiornaTabellaOfferte();
			}
		}		
	}
		
	private class ModificaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			OffertaBean offertaBean = tabellaOfferte.getOffertaSelezionata();
			DialogModificaOfferta dialog = new DialogModificaOfferta((JFrame)SwingUtilities.getWindowAncestor(panelMe), offertaBean);
			dialog.setVisible(true);
			if (dialog.datiValidi()) {
				modifica(dialog.getOffertaBean());
				aggiornaTabellaOfferte();
			}
		}		
	}
	
	private class RimuoviListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			OffertaBean offertaBean = tabellaOfferte.getOffertaSelezionata();
			int choice = JOptionPane.showConfirmDialog(getParent(), "Sei sicuro di voler rimuovere l'offerta " + offertaBean.getId() + "?", "Conferma Rimozione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choice == JOptionPane.YES_OPTION) {
				rimuovi(offertaBean);
				aggiornaTabellaOfferte();
			}
		}
	}
	
	private void nuovo(OffertaBean offertaBean) {
		this.controllerGestioneOfferta.nuovo(offertaBean);
	}
	
	private void modifica(OffertaBean offertaBean) {
		try {
			this.controllerGestioneOfferta.modifica(offertaBean);
		} catch (PrenotazioneAttivaException exc) {
			JOptionPane.showMessageDialog(this, "Oops! Impossibile Modificare: c'è una prenotazione attiva per questa offerta!", "Modifica Offerta", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void rimuovi(OffertaBean offertaBean) {
		try {
			this.controllerGestioneOfferta.rimuovi(offertaBean);
		} catch (PrenotazioneAttivaException exc) {
			JOptionPane.showMessageDialog(this, "Oops! Impossibile Rimuovere: c'è una prenotazione attiva per questa offerta!", "Rimuovi Offerta", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void cerca(String key, String query) {
		this.tabellaOfferte.aggiornaTabella(this.controllerGestioneOfferta.cerca(key, query));
	}		
	
}
