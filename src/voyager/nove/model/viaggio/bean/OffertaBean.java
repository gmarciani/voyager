package voyager.nove.model.viaggio.bean;

import java.util.Date;

/**
 * @name OffertaBean
 *
 * @project Voyager
 *
 * @package voyager.nove.model.viaggio.bean
 *
 * @author Giacomo Marciani
 *
 */
public class OffertaBean {
	
	private int id;
	private int idViaggio;
	private Date dataPartenza;
	private Date dataArrivo;
	private int postiDisponibili;

	public OffertaBean() {}

	public int getId() {
		return id;
	}

	public OffertaBean setId(int id) {
		this.id = id;
		return this;
	}

	public int getIdViaggio() {
		return idViaggio;
	}

	public OffertaBean setIdViaggio(int idViaggio) {
		this.idViaggio = idViaggio;
		return this;
	}

	public Date getDataPartenza() {
		return dataPartenza;
	}

	public OffertaBean setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
		return this;
	}

	public Date getDataArrivo() {
		return dataArrivo;
	}

	public OffertaBean setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
		return this;
	}

	public int getPostiDisponibili() {
		return postiDisponibili;
	}

	public OffertaBean setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
		return this;
	}

}
