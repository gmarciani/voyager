package voyager.nove.view.utils;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import voyager.nove.model.viaggio.bean.ViaggioBean;

/**
 * @name TabellaViaggiModel
 *
 * @project Voyager
 *
 * @package voyager.nove.view.utils
 *
 * @author Giacomo Marciani
 *
 */
public class TabellaViaggiModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private static final String[] COLUMNS = {"id Viaggio", "Partenza", "Arrivo", "Ambiente", "Mezzo"};
	private List<ViaggioBean> listaViaggi;	
	
	public TabellaViaggiModel(List<ViaggioBean> listaViaggi) {
		this.listaViaggi = listaViaggi;
	}
	
	public int getRowCount() {
		return this.listaViaggi.size();
	}
	
	public int getColumnCount() {
		return COLUMNS.length;
	}
	
	public Object getValueAt(int row, int column) {
		ViaggioBean currViaggioBean = this.listaViaggi.get(row);
		
		switch(column) {
		case 0:
			return currViaggioBean.getId();
		case 1:
			return currViaggioBean.getCittaPartenza();
		case 2:
			return currViaggioBean.getCittaArrivo();
		case 3:
			return currViaggioBean.getAmbiente();
		case 4:
			return currViaggioBean.getMezzo();
		default:
			return "";
		}
	}
	
	public String getColumnName(int column) {
		return COLUMNS[column];
	}
	
	public List<ViaggioBean> getListaViaggi() {
		return this.listaViaggi;
	}
	
}
