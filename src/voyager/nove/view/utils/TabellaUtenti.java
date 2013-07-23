package voyager.nove.view.utils;

import java.util.List;

import javax.swing.*;

import voyager.nove.model.utente.bean.UtenteBean;

/**
 * @name TabellaUtenti
 *
 * @project Voyager
 *
 * @package voyager.nove.view.utils
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class TabellaUtenti extends JTable{
	
	private static final long serialVersionUID = 1L;
	private TabellaUtentiModel tableModel;

	public TabellaUtenti(List<UtenteBean> listaUtenti) {
		super();
		this.tableModel = new TabellaUtentiModel(listaUtenti);
		super.setModel(tableModel);
		this.enableInputMethods(false);
		this.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);
		this.setAutoCreateRowSorter(true);
	}	
	
	public UtenteBean getUtenteSelezionato() {
		int viewRow = this.getSelectedRow();
		int modelRow = this.convertRowIndexToModel(viewRow);
		
		return this.tableModel.getListaUtenti().get(modelRow);
	}
	
	public void aggiornaTabella(List<UtenteBean> listaUtenti) {
		this.tableModel = new TabellaUtentiModel(listaUtenti);
		super.setModel(tableModel);
	}
	
}