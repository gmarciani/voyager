package voyager.nove.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import voyager.nove.control.ControllerGestioneCatalogo;
import voyager.nove.utils.swing.GBCHelper;
import voyager.nove.view.utils.TabellaViaggi;

/**
 * @name BoundaryVisualizzazioneCatalogo
 *
 * @project Voyager
 *
 * @package voyager.nove.view
 *
 * @author Giacomo Marciani
 *
 */
public class BoundaryVisualizzazioneCatalogo extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private static final String TITOLO = "Catalogo Voyager";	
	
	private static final String QUERY_KEY[] = {"Id Viaggio", "Città Partenza", "Città Arrivo", "Ambiente", "Mezzo"};
	
	private JPanel panelMe;
	
	private JPanel panelTitolo;
	private JPanel panelCerca;
	private JScrollPane panelTabella;	
	
	private JLabel labelTitolo;
	private JTextField textfieldCerca;
	private JComboBox<String>comboQueryKey;
	private TabellaViaggi tabellaViaggi;
	
	private ControllerGestioneCatalogo controllerGestioneCatalogo;
	
	public BoundaryVisualizzazioneCatalogo() {
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
		this.panelTabella = new JScrollPane(tabellaViaggi);
				
		//Frame Pack
		this.add(this.panelTitolo, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 15, 15));
		this.add(this.panelCerca, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.EAST).setInsets(5, 5, 5, 10));
		this.add(this.panelTabella, new GBCHelper(0, 2).setWeight(100, 100).setFill(GridBagConstraints.BOTH).setInsets(10, 10, 10, 10));		
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
	
	private void cerca(String key, String query) {
		this.tabellaViaggi.aggiornaTabella(this.controllerGestioneCatalogo.cerca(key, query));
	}		
	
}
