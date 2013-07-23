package voyager.nove.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import org.jfree.chart.ChartPanel;

import voyager.nove.control.ControllerComputazioneIndici;
import voyager.nove.exception.IDElementoException;
import voyager.nove.model.indice.bean.IDElementoBean;
import voyager.nove.utils.swing.GBCHelper;

/**
 * @name BoundaryComputazioneIndici
 *
 * @project Voyager
 *
 * @package voyager.nove.view
 *
 * @author Giacomo
 *
 */
public class BoundaryComputazioneIndici extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private static final String TITOLO = "Computazione Indici";	
	
	private static final String[] AMBIENTI = {"NULL", "Terra", "Mare", "Aria"};
	private static final String[] MEZZI_TERRA = {"NULL", "Treno TN", "Treno TAV", "Autobus Base", "Autobus Lusso", "Automobile Compact", "Automobile Media", "Automobile Berlina", "Automobile Lusso"};
	private static final String[] MEZZI_MARE = {"NULL", "Traghetto", "Aliscafo", "Vela", "Panfilo"};
	private static final String[] MEZZI_ARIA = {"NULL", "Aereo L", "Aereo A", "Aereo H"};
	
	private JPanel panelMe;
	
	private JPanel panelTitolo;
	private JPanel panelFiltro;
	private JPanel panelPlot;	
	
	private JLabel labelTitolo;
	private JTextField textfieldCittaPartenza;
	private JTextField textfieldCittaArrivo;
	private JComboBox<String>comboAmbiente;
	private JComboBox<String>comboMezzo;
	private JButton buttonComputa;
	
	private ControllerComputazioneIndici controllerComputazioneIndici;
	
	public BoundaryComputazioneIndici() {
		this.controllerComputazioneIndici = ControllerComputazioneIndici.getInstance();
		this.panelMe = this;
		buildFrame();					
	}	
	
	private void buildFrame() {
		
		//Initialization
		this.setLayout(new GridBagLayout());
				
		//Panel Title
		this.panelTitolo = new JPanel();		
		this.labelTitolo = new JLabel(TITOLO);
		this.labelTitolo.setFont(new Font(this.labelTitolo.getFont().getName(), Font.BOLD, 20));		
		this.panelTitolo.add(labelTitolo);
				
		//Panel Filtro
		this.panelFiltro = new JPanel();		
		this.textfieldCittaPartenza = new JTextField("", 30); 
		this.textfieldCittaArrivo = new JTextField("", 30);
		this.comboAmbiente = new JComboBox<String>(AMBIENTI);
		this.comboAmbiente.addItemListener(new ComboAmbienteListener());
		this.comboAmbiente = new JComboBox<String>(MEZZI_TERRA);
		this.buttonComputa = new JButton("Computa");
		this.buttonComputa.addActionListener(new ComputaListener());
		this.panelFiltro.add(this.textfieldCittaPartenza);
		this.panelFiltro.add(this.textfieldCittaArrivo);
		this.panelFiltro.add(this.comboAmbiente);
		this.panelFiltro.add(this.comboMezzo);
		this.panelFiltro.add(this.buttonComputa);
				
		//Panel Plot
		this.panelPlot = new JPanel();		
				
		//Frame Pack
		this.add(this.panelTitolo, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 15, 15));
		this.add(this.panelFiltro, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.EAST).setInsets(5, 5, 5, 10));
		this.add(this.panelPlot, new GBCHelper(0, 2).setWeight(100, 100).setFill(GridBagConstraints.BOTH).setInsets(10, 10, 10, 10));		
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
	
	private class ComputaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String cittaPartenza = textfieldCittaPartenza.getText().toString();
			String cittaArrivo = textfieldCittaArrivo.getText().toString();
			String ambiente = String.valueOf(comboAmbiente.getSelectedItem());
			String mezzo = String.valueOf(comboMezzo.getSelectedItem());
			
			if (ambiente.equals("NULL")) {
				ambiente = null;
			}
			
			if (mezzo.equals("NULL")) {
				mezzo = null;
			}
			
			ChartPanel chart = computa(cittaPartenza, cittaArrivo, ambiente, mezzo);
			
			if (chart != null) {
				aggiornaChart(chart);
			} 			
		}		
	}
	
	private void aggiornaChart(ChartPanel chart) {
		this.panelPlot.add(chart);
	}
	
	private ChartPanel computa(String cittaPartenza, String cittaArrivo, String ambiente, String mezzo) {
		IDElementoBean elementoBean = new IDElementoBean()
		.setCittaPartenza(cittaPartenza)
		.setCittaArrivo(cittaArrivo)
		.setAmbiente(ambiente)
		.setMezzo(mezzo);
		
		ChartPanel chart = null;
		try {
			chart = this.controllerComputazioneIndici.getPlot(elementoBean);
		} catch (IDElementoException exc) {
			JOptionPane.showMessageDialog(this, "Oops! Si è verificato un problema durante il calcolo dell'indice!", "Computazione Indici", JOptionPane.INFORMATION_MESSAGE);
		}
		
		return chart;
	}		
	
}
