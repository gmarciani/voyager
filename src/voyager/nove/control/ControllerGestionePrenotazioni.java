package voyager.nove.control;

import java.util.HashMap;
import java.util.List;

import voyager.nove.exception.PostiNonDisponibiliException;
import voyager.nove.model.viaggio.Prenotazione;
import voyager.nove.model.viaggio.bean.PrenotazioneBean;
import voyager.nove.persistence.dao.PrenotazioneDAO;
import voyager.nove.persistence.dao.PrenotazioneJDBCDAO;

/**
 * @name ControllerGestionePrenotazioni
 *
 * @project Voyager
 *
 * @package voyager.nove.control
 *
 * @author Giacomo Marciani
 *
 */
public class ControllerGestionePrenotazioni {
	
	private static ControllerGestionePrenotazioni singletonControllerGestionePrenotazioni;
	private static PrenotazioneDAO prenotazioneDAO;
	
	private static final PrenotazioneQueryKey QUERY_KEY = new PrenotazioneQueryKey();

	private ControllerGestionePrenotazioni() {
		prenotazioneDAO = PrenotazioneJDBCDAO.getInstance();
	}

	public static synchronized ControllerGestionePrenotazioni getInstance() {
		if (singletonControllerGestionePrenotazioni == null) {
			singletonControllerGestionePrenotazioni = new ControllerGestionePrenotazioni();
		}		
		
		return singletonControllerGestionePrenotazioni;
	}
	
	public void nuovo(PrenotazioneBean prenotazioneBean) throws PostiNonDisponibiliException {
		Prenotazione prenotazione = new Prenotazione().fromBean(prenotazioneBean);
		prenotazioneDAO.save(prenotazione);			
	}	

	public void modifica(PrenotazioneBean prenotazioneBean) throws PostiNonDisponibiliException {
		PrenotazioneBean vecchiaPrenotazioneBean = prenotazioneDAO.find("id_prenotazione", String.valueOf(prenotazioneBean.getId())).get(0);
		Prenotazione vecchiaPrenotazione = new Prenotazione().fromBean(vecchiaPrenotazioneBean);
		prenotazioneDAO.delete(vecchiaPrenotazione);
		Prenotazione prenotazione = new Prenotazione().fromBean(prenotazioneBean);		
		prenotazioneDAO.save(prenotazione);		
	}

	public void rimuovi(PrenotazioneBean prenotazioneBean) {
		System.out.println("CONTROLLORE ID PRENOTAZIONE BEAN: " + prenotazioneBean.getId());
		Prenotazione prenotazione = new Prenotazione().fromBean(prenotazioneBean);
		System.out.println("CONTROLLORE ID PRENOTAZIONE: " + prenotazione.getId());
		prenotazioneDAO.delete(prenotazione);
	}	
	
	public PrenotazioneBean getPrenotazione(int idPrenotazione) {
		PrenotazioneBean prenotazioneBean = prenotazioneDAO.find("id_prenotazione", String.valueOf(idPrenotazione)).get(0);
		return prenotazioneBean;
	}
	
	public List<PrenotazioneBean> cerca(String key, String query) {
		if (query == null || query.isEmpty()) {
			return prenotazioneDAO.findAll();
		} else {
			return prenotazioneDAO.find(this.getQueryKey(key), query);
		}		
	}	

	public List<PrenotazioneBean> getPrenotazioni() {
		return prenotazioneDAO.findAll();
	}
	
	private String getQueryKey(String key) {
		return QUERY_KEY.get(key);
	}
	
	private static final class PrenotazioneQueryKey extends HashMap<String, String> {

		private static final long serialVersionUID = 1L;

		public PrenotazioneQueryKey() {
			this.put("Id Prenotazione", "id_prenotazione");
			this.put("Id Offerta", "id_offerta");
			this.put("Acquirente", "acquirente");
			this.put("Posti Prenotati", "posti_prenotati");
		}
	}

}
