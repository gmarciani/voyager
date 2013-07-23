
package voyager.nove.model.viaggio.bean;

/**
 * @name ViaggioBean
 *
 * @project Voyager
 *
 * @package voyager.nove.model.viaggio.bean
 *
 * @author Giacomo Marciani
 *
 */
public class ViaggioBean {
	
	private int id;
	private String cittaPartenza;
	private String cittaArrivo;
	private String ambiente;
	private String mezzo;

	public ViaggioBean() {}

	public int getId() {
		return this.id;
	}

	public ViaggioBean setId(int id) {
		this.id = id;
		return this;
	}

	public String getCittaPartenza() {
		return this.cittaPartenza;
	}

	public ViaggioBean setCittaPartenza(String cittaPartenza) {
		this.cittaPartenza = cittaPartenza;
		return this;
	}

	public String getCittaArrivo() {
		return this.cittaArrivo;
	}

	public ViaggioBean setCittaArrivo(String cittaArrivo) {
		this.cittaArrivo = cittaArrivo;
		return this;
	}

	public String getAmbiente() {
		return this.ambiente;
	}

	public ViaggioBean setAmbiente(String ambiente) {
		this.ambiente = ambiente;
		return this;
	}

	public String getMezzo() {
		return this.mezzo;
	}

	public ViaggioBean setMezzo(String mezzo) {
		this.mezzo = mezzo;
		return this;
	}

}
