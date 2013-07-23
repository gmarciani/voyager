package voyager.nove.persistence.dao;

import java.util.List;

import voyager.nove.model.viaggio.Viaggio;
import voyager.nove.model.viaggio.bean.ViaggioBean;

/**
 * @name ViaggioDAO
 *
 * @project Voyager
 *
 * @package voyager.nove.persistence.dao
 *
 * @author Giacomo Marciani
 *
 */
public interface ViaggioDAO {
	
	public boolean save(Viaggio viaggio);
	
	public boolean update(Viaggio viaggio);
	
	public boolean delete(int idViaggio);
	
	public List<ViaggioBean> findAll();
	
	public List<ViaggioBean> find(String key, String query);

}
