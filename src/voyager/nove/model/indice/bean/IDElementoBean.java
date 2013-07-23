package voyager.nove.model.indice.bean;

/**
 * @name IDElementoBean
 *
 * @project Voyager
 *
 * @package voyager.nove.model.indice.bean
 *
 * @author Giacomo Marciani
 *
 */
public class IDElementoBean {
	
	private String cittaPartenza;
	private String cittaArrivo;
	private String ambiente;
	private String mezzo;

	public IDElementoBean() {}

	public String getCittaPartenza() {
		return cittaPartenza;
	}

	public IDElementoBean setCittaPartenza(String cittaPartenza) {
		this.cittaPartenza = cittaPartenza;
		return this;
	}

	public String getCittaArrivo() {
		return cittaArrivo;
	}

	public IDElementoBean setCittaArrivo(String cittaArrivo) {
		this.cittaArrivo = cittaArrivo;
		return this;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public IDElementoBean setAmbiente(String ambiente) {
		this.ambiente = ambiente;
		return this;
	}

	public String getMezzo() {
		return mezzo;
	}

	public IDElementoBean setMezzo(String mezzo) {
		this.mezzo = mezzo;
		return this;
	}

}
