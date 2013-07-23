package voyager.nove.model.utente.ruolo;

import voyager.nove.model.utente.competenze.Competenza;
import voyager.nove.model.utente.competenze.Login;
import voyager.nove.model.utente.competenze.VisualizzazioneCatalogo;

/**
 * @name Visitatore
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.ruolo
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class Visitatore extends AbstractRuolo {
	
	private static final long serialVersionUID = 1L;

	private static Visitatore singletonVisitatore = null;
	
	private static int ID = Ruolo.VISITATORE;
	private static String STRING = "Visitatore";
	private static Competenza[] COMPETENZE = {VisualizzazioneCatalogo.getInstance(), Login.getInstance()};	

	protected Visitatore(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonVisitatore == null) {
			singletonVisitatore = new Visitatore(ID, STRING, COMPETENZE);
			return singletonVisitatore;
		}
		
		return singletonVisitatore;
	}
	
}
