package voyager.nove.view.utils;

import java.util.List;

import javax.swing.*;

import voyager.nove.model.viaggio.bean.ViaggioBean;

/**
 * @name TabellaViaggi
 *
 * @project Voyager
 *
 * @package voyager.nove.view.utils
 *
 * @author Giacomo Marciani
 *
 */
public class TabellaViaggi extends JTable{
	
	private static final long serialVersionUID = 1L;
	private TabellaViaggiModel tableModel;

	public TabellaViaggi(List<ViaggioBean> listaViaggi) {
		super();
		this.tableModel = new TabellaViaggiModel(listaViaggi);
		super.setModel(tableModel);
		this.enableInputMethods(false);
		this.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);
		this.setAutoCreateRowSorter(true);
	}	
	
	public ViaggioBean getViaggioSelezionato() {
		int viewRow = this.getSelectedRow();
		int modelRow = this.convertRowIndexToModel(viewRow);
		
		return this.tableModel.getListaViaggi().get(modelRow);
	}
	
	public void aggiornaTabella(List<ViaggioBean> listaViaggi) {
		this.tableModel = new TabellaViaggiModel(listaViaggi);
		super.setModel(tableModel);
	}
	
}