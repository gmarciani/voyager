package voyager.nove.model.indice;

import voyager.nove.exception.IDElementoException;
import voyager.nove.model.indice.bean.IDElementoBean;

/**
 * @name IDElemento
 *
 * @project Voyager
 *
 * @package voyager.nove.model.indice
 *
 * @author Giacomo Marciani
 *
 */
public class IDElemento {
	
	private String cittaPartenza;
	private String cittaArrivo;
	private String ambiente;
	private String mezzo;
	
	public IDElemento(String cittaPartenza, String cittaArrivo, String ambiente, String mezzo) {
		this.setCittaPartenza(cittaPartenza);
		this.setCittaArrivo(cittaArrivo);
		this.setAmbiente(ambiente);
		this.setMezzo(mezzo);
	}

	public IDElemento() {}

	public String getCittaPartenza() {
		return cittaPartenza;
	}

	public void setCittaPartenza(String cittaPartenza) {
		this.cittaPartenza = cittaPartenza;
	}

	public String getCittaArrivo() {
		return cittaArrivo;
	}

	public void setCittaArrivo(String cittaArrivo) {
		this.cittaArrivo = cittaArrivo;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getMezzo() {
		return mezzo;
	}

	public void setMezzo(String mezzo) {
		this.mezzo = mezzo;
	}

	public IDElemento getSuperElemento() throws IDElementoException {
		if (this.getCittaPartenza() != null && this.getCittaArrivo() != null 
				&& this.getAmbiente() != null && this.getMezzo() != null) {
			return new IDElemento(this.getCittaPartenza(), this.getCittaArrivo(), this.getAmbiente(), null);
		} else if (this.getCittaPartenza() != null && this.getCittaArrivo() != null
				&& this.getAmbiente() != null) {
			return new IDElemento(this.getCittaPartenza(), this.getCittaArrivo(), null, null);
		} else {
			throw new IDElementoException();
		}
	}
	
	public IDElemento fromBean(IDElementoBean elementoBean) {
		return new IDElemento(elementoBean.getCittaPartenza(), elementoBean.getCittaArrivo(), elementoBean.getAmbiente(), elementoBean.getMezzo());
	}

}
