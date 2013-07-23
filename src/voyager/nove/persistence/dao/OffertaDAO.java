package voyager.nove.persistence.dao;

import java.util.List;

import voyager.nove.model.viaggio.Offerta;
import voyager.nove.model.viaggio.bean.OffertaBean;

/**
 * @name OffertaDAO
 *
 * @project Voyager
 *
 * @package voyager.nove.persistence.dao
 *
 * @author Giacomo Marciani
 *
 */
public interface OffertaDAO {
	
	public boolean save(Offerta offerta);
	
	public boolean update(Offerta offerta);
	
	public boolean delete(int idOfferta);
	
	public List<OffertaBean> findAll();
	
	public List<OffertaBean> find(String key, String query);

}
