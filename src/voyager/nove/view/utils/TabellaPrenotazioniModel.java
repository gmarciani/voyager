package voyager.nove.view.utils;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import voyager.nove.model.viaggio.bean.PrenotazioneBean;

/**
 * @name TabellaPrenotazioniModel
 *
 * @project Voyager
 *
 * @package voyager.nove.view.utils
 *
 * @author Giacomo Marciani
 *
 */
public class TabellaPrenotazioniModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private static final String[] COLUMNS = {"id Prenotazione", "id Offerta", "Acquirente", "Posti Prenotati"};
	private List<PrenotazioneBean> listaPrenotazioni;	
	
	public TabellaPrenotazioniModel(List<PrenotazioneBean> listaPrenotazioni) {
		this.listaPrenotazioni = listaPrenotazioni;
	}
	
	public int getRowCount() {
		return this.listaPrenotazioni.size();
	}
	
	public int getColumnCount() {
		return COLUMNS.length;
	}
	
	public Object getValueAt(int row, int column) {
		PrenotazioneBean currPrenotazioneBean = this.listaPrenotazioni.get(row);
		
		switch(column) {
		case 0:
			return currPrenotazioneBean.getId();
		case 1:
			return currPrenotazioneBean.getIdOfferta();
		case 2:
			return currPrenotazioneBean.getAcquirente();
		case 3:
			return currPrenotazioneBean.getPostiPrenotati();
		default:
			return "";
		}
	}
	
	public String getColumnName(int column) {
		return COLUMNS[column];
	}
	
	public List<PrenotazioneBean> getListaPrenotazioni() {
		return this.listaPrenotazioni;
	}
	
}
