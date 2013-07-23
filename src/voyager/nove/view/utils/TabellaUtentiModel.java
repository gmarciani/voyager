package voyager.nove.view.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import voyager.nove.model.utente.bean.UtenteBean;

/**
 * @name TabellaUtentiModel
 *
 * @project Voyager
 *
 * @package voyager.nove.view.utils
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class TabellaUtentiModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private static final String[] COLUMNS = {"Nome", "Cognome", "Città", "Data di Nascita", "Sesso", "Mail", "Ruolo", "Username", "Password"};
	private List<UtenteBean> listaUtenti;	
	
	public TabellaUtentiModel(List<UtenteBean> listaUtenti) {
		this.listaUtenti = listaUtenti;
	}
	
	public int getRowCount() {
		return this.listaUtenti.size();
	}
	
	public int getColumnCount() {
		return COLUMNS.length;
	}
	
	public Object getValueAt(int row, int column) {
		UtenteBean currUtenteBean = this.listaUtenti.get(row);
		
		switch(column) {
		case 0:
			return currUtenteBean.getNome();
		case 1:
			return currUtenteBean.getCognome();
		case 2:
			return currUtenteBean.getCitta();
		case 3:
			GregorianCalendar calendar = currUtenteBean.getNascita();
			String calendarString = calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.YEAR);
			return calendarString;
		case 4:
			return currUtenteBean.getSesso();
		case 5:
			return currUtenteBean.getMail();
		case 6:
			return currUtenteBean.getRuolo().asString();
		case 7:
			return currUtenteBean.getUsername();
		case 8:
			return currUtenteBean.getPassword();
		default:
			return "";
		}
	}
	
	public String getColumnName(int column) {
		return COLUMNS[column];
	}
	
	public List<UtenteBean> getListaUtenti() {
		return this.listaUtenti;
	}
	
}
