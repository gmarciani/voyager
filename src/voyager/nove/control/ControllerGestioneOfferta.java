package voyager.nove.control;

import java.util.HashMap;
import java.util.List;

import voyager.nove.exception.PrenotazioneAttivaException;
import voyager.nove.model.viaggio.Offerta;
import voyager.nove.model.viaggio.bean.OffertaBean;
import voyager.nove.persistence.dao.OffertaDAO;
import voyager.nove.persistence.dao.OffertaJDBCDAO;
import voyager.nove.persistence.dao.PrenotazioneDAO;
import voyager.nove.persistence.dao.PrenotazioneJDBCDAO;

/**
 * @name ControllerGestioneOfferta
 *
 * @project Voyager
 *
 * @package voyager.nove.control
 *
 * @author Giacomo Marciani
 *
 */
public class ControllerGestioneOfferta {
	
	private static ControllerGestioneOfferta singletonControllerGestioneOfferta;
	private static OffertaDAO offertaDAO;
	private static PrenotazioneDAO prenotazioneDAO;
	
	private static final OffertaQueryKey QUERY_KEY = new OffertaQueryKey();

	private ControllerGestioneOfferta() {
		offertaDAO = OffertaJDBCDAO.getInstance();
		prenotazioneDAO = PrenotazioneJDBCDAO.getInstance();
	}

	public static synchronized ControllerGestioneOfferta getInstance() {
		if (singletonControllerGestioneOfferta == null) {
			singletonControllerGestioneOfferta = new ControllerGestioneOfferta();
		}		
		
		return singletonControllerGestioneOfferta;
	}
	
	public void nuovo(OffertaBean offertaBean) {
		Offerta offerta = new Offerta().fromBean(offertaBean);
		offertaDAO.save(offerta);
	}	

	public void modifica(OffertaBean offertaBean) throws PrenotazioneAttivaException {
		if (checkPrenotazioneAttiva(offertaBean)) {
			throw new PrenotazioneAttivaException();
		} else {
			Offerta offerta = new Offerta().fromBean(offertaBean);
			offertaDAO.update(offerta);
		}		
	}

	public void rimuovi(OffertaBean offertaBean) throws PrenotazioneAttivaException {
		if (checkPrenotazioneAttiva(offertaBean)) {
			throw new PrenotazioneAttivaException();
		} else {
			System.out.println(offertaBean.getId());
			offertaDAO.delete(offertaBean.getId());			
		}		
	}	
	
	public OffertaBean getOfferta(int idOfferta) {
		OffertaBean offertaBean = offertaDAO.find("id_offerta", String.valueOf(idOfferta)).get(0);
		return offertaBean;
	}
	
	public List<OffertaBean> cerca(String key, String query) {
		if (query == null || query.isEmpty()) {
			return offertaDAO.findAll();
		} else {
			return offertaDAO.find(this.getQueryKey(key), query);
		}		
	}	

	public List<OffertaBean> getOfferte() {
		return offertaDAO.findAll();
	}
	
	private String getQueryKey(String key) {
		return QUERY_KEY.get(key);
	}
	
	private boolean checkPrenotazioneAttiva(OffertaBean offertaBean) {
		if (prenotazioneDAO.find("id_offerta", String.valueOf(offertaBean.getId())).isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	private static final class OffertaQueryKey extends HashMap<String, String> {

		private static final long serialVersionUID = 1L;

		public OffertaQueryKey() {
			this.put("Id Offerta", "id_offerta");
			this.put("Id Viaggio", "id_viaggio");
			this.put("Posti Disponibili", "posti_disponibili");
		}
	}

}
