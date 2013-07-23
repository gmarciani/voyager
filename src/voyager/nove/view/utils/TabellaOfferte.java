package voyager.nove.view.utils;

import java.util.List;

import javax.swing.*;

import voyager.nove.model.viaggio.bean.OffertaBean;

/**
 * @name TabellaOfferte
 *
 * @project Voyager
 *
 * @package voyager.nove.view.utils
 *
 * @author Giacomo Marciani
 *
 */
public class TabellaOfferte extends JTable{
	
	private static final long serialVersionUID = 1L;
	private TabellaOfferteModel tableModel;

	public TabellaOfferte(List<OffertaBean> listaOfferta) {
		super();
		this.tableModel = new TabellaOfferteModel(listaOfferta);
		super.setModel(tableModel);
		this.enableInputMethods(false);
		this.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);
		this.setAutoCreateRowSorter(true);
	}	
	
	public OffertaBean getOffertaSelezionata() {
		int viewRow = this.getSelectedRow();
		int modelRow = this.convertRowIndexToModel(viewRow);
		
		return this.tableModel.getListaOfferte().get(modelRow);
	}
	
	public void aggiornaTabella(List<OffertaBean> listaOfferte) {
		this.tableModel = new TabellaOfferteModel(listaOfferte);
		super.setModel(tableModel);
	}
	
}