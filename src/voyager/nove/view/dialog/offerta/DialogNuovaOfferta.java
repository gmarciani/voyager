package voyager.nove.view.dialog.offerta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import voyager.nove.control.ControllerGestioneCatalogo;
import voyager.nove.control.ControllerGestioneOfferta;
import voyager.nove.model.viaggio.bean.OffertaBean;
import voyager.nove.utils.swing.DateTimePicker;
import voyager.nove.utils.swing.GBCHelper;
import voyager.nove.view.utils.TabellaViaggi;

/**
 * @name DialogNuovaOfferta
 *
 * @project Voyager
 *
 * @package voyager.nove.view.dialog.offerta
 *
 * @author Giacomo Marciani
 *
 */
public class DialogNuovaOfferta extends JDialog{
	
	private static final long serialVersionUID = 1L;	
	
	public JDialog dialogMe;
	
	private JPanel panelMain;
	private JScrollPane panelTabella;
	private JPanel panelBottoni;	
	
	private JLabel labelCatalogo;
	private JLabel labelDataPartenza;
	private JLabel labelDataArrivo;
	private JLabel labelPostiDisponibili;
	private TabellaViaggi tabellaViaggi;
	private DateTimePicker dataPartenza;
	private DateTimePicker dataArrivo;
	private JTextField textfieldPostiDisponibili;
	private JButton buttonOk;
	private JButton buttonAnnulla;
	
	private boolean datiValidi;
	
	private OffertaBean offertaBean;
	
	private ControllerGestioneCatalogo controllerGestioneCatalogo;
	private ControllerGestioneOfferta controllerGestioneOfferta;
	
	public DialogNuovaOfferta(JFrame ownerFrame) {
		super(ownerFrame, "Nuova Offerta", true);
		this.controllerGestioneCatalogo = ControllerGestioneCatalogo.getInstance();
		this.controllerGestioneOfferta = ControllerGestioneOfferta.getInstance();
		this.offertaBean = new OffertaBean();
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
		this.labelCatalogo = new JLabel("Catalogo");
		this.labelDataPartenza = new JLabel("Data Partenza");
		this.labelDataArrivo = new JLabel("Data Arrivo");	
		this.labelPostiDisponibili = new JLabel("Posti Disponibili");
		this.tabellaViaggi = new TabellaViaggi(this.controllerGestioneCatalogo.getViaggi());
		this.tabellaViaggi.getSelectionModel().addListSelectionListener(new ViaggioSelezionatoListener());
		this.panelTabella = new JScrollPane(tabellaViaggi);
		this.dataPartenza = new DateTimePicker();
		this.dataArrivo = new DateTimePicker();
		this.textfieldPostiDisponibili = new JTextField("", 4);
		
		this.panelMain.add(this.labelCatalogo, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		this.panelMain.add(this.labelDataPartenza, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelDataArrivo, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelPostiDisponibili, new GBCHelper(0, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));
		
		this.panelMain.add(this.panelTabella, new GBCHelper(1, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		this.panelMain.add(this.dataPartenza, new GBCHelper(1, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.dataArrivo, new GBCHelper(1, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.textfieldPostiDisponibili, new GBCHelper(1, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));
		
		//Panel Button
		this.panelBottoni = new JPanel();
		this.panelBottoni.setLayout(new GridLayout(1, 2, 5, 5));
		this.buttonOk = new JButton("Ok");
		this.buttonOk.setEnabled(false);
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
	
	private class ViaggioSelezionatoListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			buttonOk.setEnabled(true);
		}
		
	}
	
	private class OkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkSintassiDati() && checkSemanticaDati()) {
				setOffertaBean();
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
		if (this.textfieldPostiDisponibili.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Oops! Dati Mancanti!", "Nuova Offerta", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	private boolean checkSemanticaDati() {
		if (this.dataArrivo.getDate().equals(this.dataPartenza.getDate())
				|| Integer.parseInt(this.textfieldPostiDisponibili.getText().toString()) < 0) {
			JOptionPane.showMessageDialog(this, "Oops! Dati Inconsistenti!", "Nuova Offerta", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	private void setOffertaBean() {
		this.offertaBean.setIdViaggio(this.tabellaViaggi.getViaggioSelezionato().getId());
		this.offertaBean.setDataPartenza(this.dataPartenza.getDate());
		this.offertaBean.setDataArrivo(this.dataArrivo.getDate());
		this.offertaBean.setPostiDisponibili(Integer.valueOf(this.textfieldPostiDisponibili.getText().toString()));
	}
	
	public OffertaBean getOffertaBean() {
		return this.offertaBean;
	}

	private void setDatiValidi(boolean bool) {
		this.datiValidi = bool;
	}
	
	public boolean datiValidi() {
		return this.datiValidi;
	}
	
}
