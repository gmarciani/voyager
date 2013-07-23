package voyager.nove.view.dialog.catalogo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import voyager.nove.control.ControllerGestioneCatalogo;
import voyager.nove.model.viaggio.bean.ViaggioBean;
import voyager.nove.utils.swing.GBCHelper;

/**
 * @name DialogModificaViaggio
 *
 * @project Voyager
 *
 * @package voyager.nove.view.dialog.catalogo
 *
 * @author Giacomo Marciani
 *
 */
public class DialogModificaViaggio extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
	private static final String[] AMBIENTI = {"Terra", "Mare", "Aria"};
	private static final String[] MEZZI_TERRA = {"Treno TN", "Treno TAV", "Autobus Base", "Autobus Lusso", "Automobile Compact", "Automobile Media", "Automobile Berlina", "Automobile Lusso"};
	private static final String[] MEZZI_MARE = {"Traghetto", "Aliscafo", "Vela", "Panfilo"};
	private static final String[] MEZZI_ARIA = {"Aereo L", "Aereo A", "Aereo H"};	
	
	public JDialog dialogMe;
	
	private JPanel panelMain;
	private JPanel panelBottoni;
	
	private JLabel labelCittaPartenza;
	private JLabel labelCittaArrivo;
	private JLabel labelAmbiente;
	private JLabel labelMezzo;
	private JTextField textfieldCittaPartenza;
	private JTextField textfieldCittaArrivo;
	private JComboBox <String>comboAmbiente;
	private JComboBox <String>comboMezzo;
	private JButton buttonOk;
	private JButton buttonAnnulla;
	
	private boolean datiValidi;
	
	private ViaggioBean viaggioBean;
	
	private ControllerGestioneCatalogo controllerGestioneCatalogo;
	
	public DialogModificaViaggio(JFrame ownerFrame, ViaggioBean viaggioBean) {
		super(ownerFrame, "Modifica Viaggio", true);
		this.controllerGestioneCatalogo = ControllerGestioneCatalogo.getInstance();
		this.viaggioBean = viaggioBean;
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
		this.labelCittaPartenza = new JLabel("Città Partenza");
		this.labelCittaArrivo = new JLabel("Città Arrivo");
		this.labelAmbiente = new JLabel("Ambiente");
		this.labelMezzo = new JLabel("Mezzo");
		this.textfieldCittaPartenza = new JTextField("", 20);
		this.textfieldCittaArrivo = new JTextField("", 20);
		this.comboAmbiente = new JComboBox<String>(AMBIENTI);
		this.comboAmbiente.addItemListener(new ComboAmbienteListener());
		this.comboMezzo = new JComboBox<String>(MEZZI_TERRA);
		
		this.textfieldCittaPartenza.setText(this.viaggioBean.getCittaPartenza());
		this.textfieldCittaArrivo.setText(this.viaggioBean.getCittaArrivo());
		this.comboAmbiente.setSelectedItem(this.viaggioBean.getAmbiente());
		this.comboMezzo.setSelectedItem(this.viaggioBean.getMezzo());
		
		this.panelMain.add(this.labelCittaPartenza, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		this.panelMain.add(this.labelCittaArrivo, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelAmbiente, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelMezzo, new GBCHelper(0, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));
		
		this.panelMain.add(this.textfieldCittaPartenza, new GBCHelper(1, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		this.panelMain.add(this.textfieldCittaArrivo, new GBCHelper(1, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.comboAmbiente, new GBCHelper(1, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.comboMezzo, new GBCHelper(1, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));
		
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
	
	private class ComboAmbienteListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			String ambienteSelezionato = String.valueOf(comboAmbiente.getSelectedItem());
			
			if (ambienteSelezionato.equals("Terra")) {
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(MEZZI_TERRA);
				comboMezzo.setModel(model);
			} else if (ambienteSelezionato.equals("Mare")) {
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(MEZZI_MARE);
				comboMezzo.setModel(model);
			} else if (ambienteSelezionato.equals("Aria")) {
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(MEZZI_ARIA);
				comboMezzo.setModel(model);
			}
			
		}
		
	}
	
	private class OkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkSintassiDati() && checkSemanticaDati()) {
				setViaggioBean();
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
		if (this.textfieldCittaPartenza.getText().isEmpty() || this.textfieldCittaArrivo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Oops! Dati Mancanti!", "Modifica Viaggio", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	private boolean checkSemanticaDati() {
		if (this.textfieldCittaPartenza.getText().toString().equals(this.textfieldCittaArrivo.getText().toString())) {
			JOptionPane.showMessageDialog(this, "Oops! Dati Inconsistenti!", "Modifica Viaggio", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	private void setViaggioBean() {
		this.viaggioBean.setCittaPartenza(this.textfieldCittaPartenza.getText().toString());
		this.viaggioBean.setCittaArrivo(this.textfieldCittaArrivo.getText().toString());
		this.viaggioBean.setAmbiente(String.valueOf(this.comboAmbiente.getSelectedItem()));
		this.viaggioBean.setMezzo(String.valueOf(this.comboMezzo.getSelectedItem()));
	}
	
	public ViaggioBean getViaggioBean() {
		return this.viaggioBean;
	}

	private void setDatiValidi(boolean bool) {
		this.datiValidi = bool;
	}
	
	public boolean datiValidi() {
		return this.datiValidi;
	}
	
}
