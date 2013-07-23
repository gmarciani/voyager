package voyager.nove.persistence.dao;

import java.util.List;

import voyager.nove.exception.UtenteInesistenteException;
import voyager.nove.model.utente.Login;
import voyager.nove.model.utente.Utente;
import voyager.nove.model.utente.bean.UtenteBean;

/**
 * @name UtenteDAO
 *
 * @project Voyager
 *
 * @package voyager.nove.persistence.dao
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public interface UtenteDAO {
	
	public boolean save(Utente utente);
	
	public boolean update(Utente utente);
	
	public boolean delete(String username);
	
	public List<UtenteBean> findAll();
	
	public List<UtenteBean> find(String key, String query);
	
	public UtenteBean findByLogin(Login login) throws UtenteInesistenteException;
	
	public UtenteBean findByUsername(String username) throws UtenteInesistenteException;
	
	public boolean verifyLogin(Login login) throws UtenteInesistenteException;

}
