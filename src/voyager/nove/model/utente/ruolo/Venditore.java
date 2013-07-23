package voyager.nove.model.utente.ruolo;

import voyager.nove.model.utente.competenze.Competenza;
import voyager.nove.model.utente.competenze.GestionePrenotazioni;
import voyager.nove.model.utente.competenze.GestioneProfilo;
import voyager.nove.model.utente.competenze.Login;

/**
 * @name Venditore
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.ruolo
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class Venditore extends AbstractRuolo{
	
	private static final long serialVersionUID = 1L;

	private static Venditore singletonVenditore = null;
	
	private static int ID = Ruolo.VENDITORE;
	private static String STRING = "Venditore";
	private static Competenza[] COMPETENZE = {GestionePrenotazioni.getInstance(), GestioneProfilo.getInstance(), Login.getInstance()};

	protected Venditore(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonVenditore == null) {
			singletonVenditore = new Venditore(ID, STRING, COMPETENZE);
			return singletonVenditore;
		}
		
		return singletonVenditore;
	}

}
