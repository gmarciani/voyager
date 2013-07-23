
package voyager.nove.model.viaggio;

import voyager.nove.model.viaggio.bean.ViaggioBean;

/**
 * @name Viaggio
 *
 * @project Voyager
 *
 * @package voyager.nove.model.viaggio
 *
 * @author Giacomo Marciani
 *
 */
public class Viaggio {
	
	private int id;
	private String cittaPartenza;
	private String cittaArrivo;
	private String ambiente;
	private String mezzo;
	
	public Viaggio(int id, String cittaPartenza, String cittaArrivo, String ambiente, String mezzo) {
		this.setId(id);
		this.setCittaPartenza(cittaPartenza);
		this.setCittaArrivo(cittaArrivo);
		this.setAmbiente(ambiente);
		this.setMezzo(mezzo);		
	}
	
	public Viaggio(String cittaPartenza, String cittaArrivo, String ambiente, String mezzo) {
		this.setCittaPartenza(cittaPartenza);
		this.setCittaArrivo(cittaArrivo);
		this.setAmbiente(ambiente);
		this.setMezzo(mezzo);		
	}

	public Viaggio() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCittaPartenza() {
		return this.cittaPartenza;
	}

	public void setCittaPartenza(String cittaPartenza) {
		this.cittaPartenza = cittaPartenza;
	}

	public String getCittaArrivo() {
		return this.cittaArrivo;
	}

	public void setCittaArrivo(String cittaArrivo){
		this.cittaArrivo = cittaArrivo;
	}

	public String getAmbiente() {
		return this.ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getMezzo() {
		return this.mezzo;
	}

	public void setMezzo(String mezzo) {
		this.mezzo = mezzo;
	}
	
	public Viaggio fromBean(ViaggioBean viaggioBean) {
		if (viaggioBean.getId() > 0) {
			return new Viaggio(viaggioBean.getId(), viaggioBean.getCittaPartenza(), viaggioBean.getCittaArrivo(), viaggioBean.getAmbiente(), viaggioBean.getMezzo());
		} else {
			return new Viaggio(viaggioBean.getCittaPartenza(), viaggioBean.getCittaArrivo(), viaggioBean.getAmbiente(), viaggioBean.getMezzo());
		}		
	}

}
