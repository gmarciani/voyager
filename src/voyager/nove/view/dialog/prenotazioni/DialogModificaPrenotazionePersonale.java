package voyager.nove.view.dialog.prenotazioni;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import voyager.nove.control.ControllerGestioneOfferta;
import voyager.nove.control.ControllerGestionePrenotazioni;
import voyager.nove.model.utente.bean.UtenteBean;
import voyager.nove.model.viaggio.bean.PrenotazioneBean;
import voyager.nove.utils.swing.GBCHelper;
import voyager.nove.view.utils.TabellaOfferte;

/**
 * @name DialogModificaPrenotazionePersonale
 *
 * @project Voyager
 *
 * @package voyager.nove.view.dialog.prenotazioni
 *
 * @author Giacomo Marciani
 *
 */
public class DialogModificaPrenotazionePersonale extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
	public JDialog dialogMe;
	
	private JPanel panelMain;
	private JScrollPane panelTabella;
	private JPanel panelBottoni;	
	
	private JLabel labelOfferte;
	private JLabel labelPostiPrenotati;
	private TabellaOfferte tabellaOfferte;
	private JTextField textfieldPostiPrenotati;
	private JButton buttonOk;
	private JButton buttonAnnulla;
	
	private boolean datiValidi;
	
	private PrenotazioneBean prenotazioneBean;
	
	private ControllerGestioneOfferta controllerGestioneOfferta;
	private ControllerGestionePrenotazioni controllerGestionePrenotazioni;
	
	public DialogModificaPrenotazionePersonale(JFrame ownerFrame, PrenotazioneBean prenotazioneBean) {
		super(ownerFrame, "Modifica Prenotazione", true);
		this.controllerGestioneOfferta = ControllerGestioneOfferta.getInstance();
		this.controllerGestionePrenotazioni = ControllerGestionePrenotazioni.getInstance();
		this.prenotazioneBean = prenotazioneBean;
		setDatiValidi(true);
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
		this.labelOfferte = new JLabel("Offerte");
		this.labelPostiPrenotati = new JLabel("Posti Prenotati");	
		this.tabellaOfferte = new TabellaOfferte(this.controllerGestioneOfferta.getOfferte());
		this.tabellaOfferte.getSelectionModel().addListSelectionListener(new OffertaSelezionataListener());
		this.panelTabella = new JScrollPane(tabellaOfferte);
		this.textfieldPostiPrenotati = new JTextField("", 20);
		this.textfieldPostiPrenotati.setText(String.valueOf(this.prenotazioneBean.getPostiPrenotati()));
		
		this.panelMain.add(this.labelOfferte, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		this.panelMain.add(this.labelPostiPrenotati, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));
		
		this.panelMain.add(this.panelTabella, new GBCHelper(1, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		this.panelMain.add(this.textfieldPostiPrenotati, new GBCHelper(1, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));
		
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
	
	private class OffertaSelezionataListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			prenotazioneBean.setIdOfferta(tabellaOfferte.getOffertaSelezionata().getId());
		}
		
	}
	
	private class OkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkSintassiDati() && checkSemanticaDati()) {
				setPrenotazioneBean();
				setDatiValidi(true);
				dialogMe.setVisible(false);	
			}					
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
	
	private boolean checkSintassiDati() {
		if (this.textfieldPostiPrenotati.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Oops! Dati Mancanti!", "Modifica Prenotazione Personale", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	private boolean checkSemanticaDati() {
		if (Integer.parseInt(this.textfieldPostiPrenotati.getText().toString()) <= 0) {
			JOptionPane.showMessageDialog(this, "Oops! Dati Inconsistenti!", "Modifica Prenotazione Personale", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else {
			return true;
		}
		
	}
	
	private void setPrenotazioneBean() {
		this.prenotazioneBean.setPostiPrenotati((Integer.valueOf(this.textfieldPostiPrenotati.getText().toString())));
	}
	
	public PrenotazioneBean getPrenotazioneBean() {
		return this.prenotazioneBean;
	}

	private void setDatiValidi(boolean bool) {
		this.datiValidi = bool;
	}
	
	public boolean datiValidi() {
		return this.datiValidi;
	}
	
}
