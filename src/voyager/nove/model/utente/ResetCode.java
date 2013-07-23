package voyager.nove.model.utente;

import java.util.Date;

/**
 * @name ResetCode
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class ResetCode {
	
	private int codice;
	private Date timestamp;
	
	public ResetCode(int codice, Date timestamp) {
		this.setCodice(codice);
		this.setTimestamp(timestamp);
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
