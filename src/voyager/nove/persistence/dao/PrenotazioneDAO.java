package voyager.nove.persistence.dao;

import java.util.List;

import voyager.nove.exception.PostiNonDisponibiliException;
import voyager.nove.model.viaggio.Prenotazione;
import voyager.nove.model.viaggio.bean.PrenotazioneBean;

/**
 * @name PrenotazioneDAO
 *
 * @project Voyager
 *
 * @package voyager.nove.persistence.dao
 *
 * @author Giacomo Marciani
 *
 */
public interface PrenotazioneDAO {
	
	public boolean save(Prenotazione prenotazione) throws PostiNonDisponibiliException;
	
	public boolean update(Prenotazione prenotazione);
	
	public boolean delete(Prenotazione prenotazione);
	
	public List<PrenotazioneBean> findAll();
	
	public List<PrenotazioneBean> find(String key, String query);

}
