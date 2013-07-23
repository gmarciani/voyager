package voyager.nove.view.utils;

import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import voyager.nove.model.viaggio.bean.OffertaBean;
import voyager.nove.utils.DateUtils;

/**
 * @name TabellaOfferteModel
 *
 * @project Voyager
 *
 * @package voyager.nove.view.utils
 *
 * @author Giacomo Marciani
 *
 */
public class TabellaOfferteModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private static final String[] COLUMNS = {"id Offerta", "id Viaggio", "Partenza", "Arrivo", "Posti Disponibili"};
	private List<OffertaBean> listaOfferte;	
	
	public TabellaOfferteModel(List<OffertaBean> listaOfferte) {
		this.listaOfferte = listaOfferte;
	}
	
	public int getRowCount() {
		return this.listaOfferte.size();
	}
	
	public int getColumnCount() {
		return COLUMNS.length;
	}
	
	public Object getValueAt(int row, int column) {
		OffertaBean currOffertaBean = this.listaOfferte.get(row);
		
		switch(column) {
		case 0:
			return currOffertaBean.getId();
		case 1:
			return currOffertaBean.getIdViaggio();
		case 2:
			Date dataPartenza = currOffertaBean.getDataPartenza();
			String dataPartenzaString = DateUtils.getStringFromDate(dataPartenza);
			return dataPartenzaString;
		case 3:
			Date dataArrivo = currOffertaBean.getDataArrivo();			
			String dataArrivoString = DateUtils.getStringFromDate(dataArrivo);
			return dataArrivoString;
		case 4:
			return currOffertaBean.getPostiDisponibili();
		default:
			return "";
		}
	}
	
	public String getColumnName(int column) {
		return COLUMNS[column];
	}
	
	public List<OffertaBean> getListaOfferte() {
		return this.listaOfferte;
	}
	
}
