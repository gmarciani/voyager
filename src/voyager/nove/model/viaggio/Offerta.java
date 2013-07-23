package voyager.nove.model.viaggio;

import java.util.Date;

import voyager.nove.model.viaggio.bean.OffertaBean;

/**
 * @name Offerta
 *
 * @project Voyager
 *
 * @package voyager.nove.model.viaggio
 *
 * @author Giacomo Marciani
 *
 */
public class Offerta {
	
	private int id;
	private int idViaggio;
	private Date dataPartenza;
	private Date dataArrivo;
	private int postiDisponibili;
	
	public Offerta(int id, int idViaggio, Date dataPartenza, Date dataArrivo, int postiDisponibilit) {
		this.setId(id);
		this.setIdViaggio(idViaggio);
		this.setDataPartenza(dataPartenza);
		this.setDataArrivo(dataArrivo);
		this.setPostiDisponibili(postiDisponibilit);
	}
	
	public Offerta(int idViaggio, Date dataPartenza, Date dataArrivo, int postiDisponibilit) {
		this.setIdViaggio(idViaggio);
		this.setDataPartenza(dataPartenza);
		this.setDataArrivo(dataArrivo);
		this.setPostiDisponibili(postiDisponibilit);
	}

	public Offerta() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdViaggio() {
		return idViaggio;
	}

	public void setIdViaggio(int idViaggio) {
		this.idViaggio = idViaggio;
	}

	public Date getDataPartenza() {
		return dataPartenza;
	}

	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}

	public Date getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	public int getPostiDisponibili() {
		return postiDisponibili;
	}

	public void setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}
	
	public Offerta fromBean(OffertaBean offertaBean) {
		if (offertaBean.getId() > 0) {
			return new Offerta(offertaBean.getId(), offertaBean.getIdViaggio(), offertaBean.getDataPartenza(), offertaBean.getDataArrivo(), offertaBean.getPostiDisponibili());
		} else {
			return new Offerta(offertaBean.getIdViaggio(), offertaBean.getDataPartenza(), offertaBean.getDataArrivo(), offertaBean.getPostiDisponibili());
		}		
	}

}
