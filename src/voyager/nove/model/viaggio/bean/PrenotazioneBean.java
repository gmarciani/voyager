
package voyager.nove.model.viaggio.bean;

/**
 * @name PrenotazioneBean
 *
 * @project Voyager
 *
 * @package voyager.nove.model.viaggio.bean
 *
 * @author Giacomo Marciani
 *
 */
public class PrenotazioneBean {
	
	private int id;
	private int idOfferta;
	private String acquirente;
	private int postiPrenotati;

	public PrenotazioneBean() {}

	public int getId() {
		return id;
	}

	public PrenotazioneBean setId(int id) {
		this.id = id;
		return this;
	}

	public int getIdOfferta() {
		return idOfferta;
	}

	public PrenotazioneBean setIdOfferta(int idOfferta) {
		this.idOfferta = idOfferta;
		return this;
	}
	
	public String getAcquirente() {
		return acquirente;
	}

	public PrenotazioneBean setAcquirente(String acquirente) {
		this.acquirente = acquirente;
		return this;
	}

	public int getPostiPrenotati() {
		return postiPrenotati;
	}

	public PrenotazioneBean setPostiPrenotati(int postiPrenotati) {
		this.postiPrenotati = postiPrenotati;
		return this;
	}
	
}
