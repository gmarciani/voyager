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

import voyager.nove.control.ControllerGestioneCatalogo;
import voyager.nove.exception.ViaggioInOffertaException;
import voyager.nove.model.viaggio.bean.ViaggioBean;
import voyager.nove.utils.swing.GBCHelper;
import voyager.nove.view.dialog.catalogo.DialogModificaViaggio;
import voyager.nove.view.dialog.catalogo.DialogNuovoViaggio;
import voyager.nove.view.utils.TabellaViaggi;

/**
 * @name BoundaryGestioneCatalogo
 *
 * @project Voyager
 *
 * @package voyager.nove.view
 *
 * @author Giacomo Marciani
 *
 */
public class BoundaryGestioneCatalogo extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private static final String TITOLO = "Gestione Catalogo";	
	
	private static final String QUERY_KEY[] = {"Id Viaggio", "Città Partenza", "Città Arrivo", "Ambiente", "Mezzo"};
	
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
	private TabellaViaggi tabellaViaggi;
	
	private ControllerGestioneCatalogo controllerGestioneCatalogo;
	
	public BoundaryGestioneCatalogo() {
		this.controllerGestioneCatalogo = ControllerGestioneCatalogo.getInstance();
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
		this.tabellaViaggi = new TabellaViaggi(this.controllerGestioneCatalogo.getViaggi());
		this.tabellaViaggi.getSelectionModel().addListSelectionListener(new ViaggioSelezionatoListener());
		this.panelTabella = new JScrollPane(tabellaViaggi);
				
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

			aggiornaTabellaViaggi();		
		}

		@Override
		public void ancestorMoved(AncestorEvent event) {
			
		}

		@Override
		public void ancestorRemoved(AncestorEvent event) {
			
		}
		
	}
	
	private void aggiornaTabellaViaggi() {
		this.tabellaViaggi.aggiornaTabella(this.controllerGestioneCatalogo.getViaggi());
	}

	private class ViaggioSelezionatoListener implements ListSelectionListener {

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
			DialogNuovoViaggio dialog = new DialogNuovoViaggio((JFrame)SwingUtilities.getWindowAncestor(panelMe));
			dialog.setVisible(true);
			if (dialog.datiValidi()) {
				nuovo(dialog.getViaggioBean());
				aggiornaTabellaViaggi();
			}
		}		
	}
		
	private class ModificaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ViaggioBean viaggioBean = tabellaViaggi.getViaggioSelezionato();
			DialogModificaViaggio dialog = new DialogModificaViaggio((JFrame)SwingUtilities.getWindowAncestor(panelMe), viaggioBean);
			dialog.setVisible(true);
			if (dialog.datiValidi()) {
				modifica(dialog.getViaggioBean());
				aggiornaTabellaViaggi();
			}
		}		
	}
	
	private class RimuoviListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ViaggioBean viaggioBean = tabellaViaggi.getViaggioSelezionato();
			int choice = JOptionPane.showConfirmDialog(getParent(), "Sei sicuro di voler rimuovere il viaggio " + viaggioBean.getId() + "?", "Conferma Rimozione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choice == JOptionPane.YES_OPTION) {
				rimuovi(viaggioBean);
				aggiornaTabellaViaggi();
			}
		}
	}
	
	private void nuovo(ViaggioBean viaggioBean) {
		System.out.println(viaggioBean.getCittaPartenza()+viaggioBean.getCittaArrivo()+viaggioBean.getAmbiente()+viaggioBean.getMezzo());
		this.controllerGestioneCatalogo.nuovo(viaggioBean);
	}
	
	private void modifica(ViaggioBean viaggioBean) {
		try {
			this.controllerGestioneCatalogo.modifica(viaggioBean);
		} catch (ViaggioInOffertaException exc) {
			JOptionPane.showMessageDialog(this, "Oops! Impossibile Modificare: c'è un offerta attiva per questo viaggio!", "Modifica Viaggio", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void rimuovi(ViaggioBean viaggioBean) {
		try {
			this.controllerGestioneCatalogo.rimuovi(viaggioBean);
		} catch (ViaggioInOffertaException e) {
			JOptionPane.showMessageDialog(this, "Oops! Impossibile Rimuovere: c'è un offerta attiva per questo viaggio!", "Rimuovi Viaggio", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void cerca(String key, String query) {
		this.tabellaViaggi.aggiornaTabella(this.controllerGestioneCatalogo.cerca(key, query));
	}		
	
}
