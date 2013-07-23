package voyager.nove.view.utils;

import java.util.List;

import javax.swing.*;

import voyager.nove.model.viaggio.bean.PrenotazioneBean;

/**
 * @name TabellaPrenotazioni
 *
 * @project Voyager
 *
 * @package voyager.nove.view.utils
 *
 * @author Giacomo Marciani
 *
 */
public class TabellaPrenotazioni extends JTable{
	
	private static final long serialVersionUID = 1L;
	private TabellaPrenotazioniModel tableModel;

	public TabellaPrenotazioni(List<PrenotazioneBean> listaPrenotazioni) {
		super();
		this.tableModel = new TabellaPrenotazioniModel(listaPrenotazioni);
		super.setModel(tableModel);
		this.enableInputMethods(false);
		this.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);
		this.setAutoCreateRowSorter(true);
	}	
	
	public PrenotazioneBean getPrenotazioneSelezionata() {
		int viewRow = this.getSelectedRow();
		int modelRow = this.convertRowIndexToModel(viewRow);
		
		return this.tableModel.getListaPrenotazioni().get(modelRow);
	}
	
	public void aggiornaTabella(List<PrenotazioneBean> listaPrenotazioni) {
		this.tableModel = new TabellaPrenotazioniModel(listaPrenotazioni);
		super.setModel(tableModel);
	}
	
}