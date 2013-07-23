
package voyager.nove.model.viaggio;

import voyager.nove.model.viaggio.bean.PrenotazioneBean;

/**
 * @name Prenotazione
 *
 * @project Voyager
 *
 * @package voyager.nove.model.viaggio
 *
 * @author Giacomo Marciani
 *
 */
public class Prenotazione {
	
	private int id;
	private int idOfferta;
	private String acquirente;
	private int postiPrenotati;
	
	public Prenotazione(int id, int idOfferta, String acquirente, int postiPrenotati) {
		this.setId(id);
		this.setIdOfferta(idOfferta);
		this.setAcquirente(acquirente);
		this.setPostiPrenotati(postiPrenotati);
	}
	
	public Prenotazione(int idOfferta, String acquirente, int postiPrenotati) {
		this.setIdOfferta(idOfferta);
		this.setAcquirente(acquirente);
		this.setPostiPrenotati(postiPrenotati);
	}

	public Prenotazione() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdOfferta() {
		return idOfferta;
	}

	public void setIdOfferta(int idOfferta) {
		this.idOfferta = idOfferta;
	}
	
	public String getAcquirente() {
		return acquirente;
	}

	public void setAcquirente(String acquirente) {
		this.acquirente = acquirente;
	}

	public int getPostiPrenotati() {
		return postiPrenotati;
	}

	public void setPostiPrenotati(int postiPrenotati) {
		this.postiPrenotati = postiPrenotati;
	}
	
	public Prenotazione fromBean(PrenotazioneBean prenotazioneBean) {
		if (prenotazioneBean.getId() > 0) {
			return new Prenotazione(prenotazioneBean.getId(), prenotazioneBean.getIdOfferta(), prenotazioneBean.getAcquirente(), prenotazioneBean.getPostiPrenotati());
		} else {
			return new Prenotazione(prenotazioneBean.getIdOfferta(), prenotazioneBean.getAcquirente(), prenotazioneBean.getPostiPrenotati());
		}		
	}	

}
