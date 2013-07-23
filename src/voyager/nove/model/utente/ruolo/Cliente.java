package voyager.nove.model.utente.ruolo;

import voyager.nove.model.utente.competenze.Competenza;
import voyager.nove.model.utente.competenze.GestionePrenotazioniPersonali;
import voyager.nove.model.utente.competenze.GestioneProfilo;
import voyager.nove.model.utente.competenze.Login;

/**
 * @name Cliente
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.ruolo
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class Cliente extends AbstractRuolo {
	
	private static final long serialVersionUID = 1L;

	private static Cliente singletonCliente = null;
	
	private static int ID = Ruolo.CLIENTE;
	private static String STRING = "Cliente";
	private static Competenza[] COMPETENZE = {GestionePrenotazioniPersonali.getInstance(), GestioneProfilo.getInstance(), Login.getInstance()};

	protected Cliente(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonCliente == null) {
			singletonCliente = new Cliente(ID, STRING, COMPETENZE);
			return singletonCliente;
		}
		
		return singletonCliente;
	}

}
