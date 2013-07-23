package voyager.nove.control;

import java.util.HashMap;
import java.util.List;

import voyager.nove.exception.ViaggioInOffertaException;
import voyager.nove.model.viaggio.Viaggio;
import voyager.nove.model.viaggio.bean.ViaggioBean;
import voyager.nove.persistence.dao.OffertaDAO;
import voyager.nove.persistence.dao.OffertaJDBCDAO;
import voyager.nove.persistence.dao.ViaggioDAO;
import voyager.nove.persistence.dao.ViaggioJDBCDAO;

/**
 * @name ControllerGestioneCatalogo
 *
 * @project Voyager
 *
 * @package voyager.nove.control
 *
 * @author Giacomo Marciani
 *
 */
public class ControllerGestioneCatalogo {
	
	private static ControllerGestioneCatalogo singletonControllerGestioneCatalogo;
	private static ViaggioDAO viaggioDAO;
	private static OffertaDAO offertaDAO;
	
	private static final ViaggioQueryKey QUERY_KEY = new ViaggioQueryKey();

	private ControllerGestioneCatalogo() {
		viaggioDAO = ViaggioJDBCDAO.getInstance();
		offertaDAO = OffertaJDBCDAO.getInstance();
	}

	public static synchronized ControllerGestioneCatalogo getInstance() {
		if (singletonControllerGestioneCatalogo == null) {
			singletonControllerGestioneCatalogo = new ControllerGestioneCatalogo();
		}		
		
		return singletonControllerGestioneCatalogo;
	}
	
	public void nuovo(ViaggioBean viaggioBean) {
		Viaggio viaggio = new Viaggio().fromBean(viaggioBean);
		System.out.println(viaggio.getCittaPartenza()+viaggio.getCittaArrivo()+viaggio.getAmbiente()+viaggio.getMezzo());
		viaggioDAO.save(viaggio);
	}	

	public void modifica(ViaggioBean viaggioBean) throws ViaggioInOffertaException {
		if (checkOffertaAttiva(viaggioBean)) {
			throw new ViaggioInOffertaException();
		} else {
			Viaggio viaggio = new Viaggio().fromBean(viaggioBean);
			System.out.println(viaggio.getCittaPartenza()+viaggio.getCittaArrivo()+viaggio.getAmbiente()+viaggio.getMezzo());
			viaggioDAO.update(viaggio);
		}		
	}

	public void rimuovi(ViaggioBean viaggioBean) throws ViaggioInOffertaException {
		if (checkOffertaAttiva(viaggioBean)) {
			throw new ViaggioInOffertaException();
		} else {
			viaggioDAO.delete(viaggioBean.getId());
		}		
	}	
	
	public ViaggioBean getViaggio(int idViaggio) {
		ViaggioBean viaggioBean = viaggioDAO.find("id_viaggio", String.valueOf(idViaggio)).get(0);
		return viaggioBean;
	}
	
	public List<ViaggioBean> cerca(String key, String query) {
		if (query == null || query.isEmpty()) {
			return viaggioDAO.findAll();
		} else {
			return viaggioDAO.find(this.getQueryKey(key), query);
		}		
	}	

	public List<ViaggioBean> getViaggi() {
		return viaggioDAO.findAll();
	}
	
	private String getQueryKey(String key) {
		return QUERY_KEY.get(key);
	}
	
	private boolean checkOffertaAttiva(ViaggioBean viaggioBean) {
		if (offertaDAO.find("id_viaggio", String.valueOf(viaggioBean.getId())).isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	private static final class ViaggioQueryKey extends HashMap<String, String> {

		private static final long serialVersionUID = 1L;

		public ViaggioQueryKey() {
			this.put("Id Viaggio", "id_viaggio");
			this.put("Città Partenza", "citta_partenza");
			this.put("Città Arrivo", "citta_arrivo");
			this.put("Ambiente", "ambiente");
			this.put("Mezzo", "mezzo");
		}
	}
	
}
