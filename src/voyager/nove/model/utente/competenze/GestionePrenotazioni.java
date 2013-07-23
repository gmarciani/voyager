package voyager.nove.model.utente.competenze;

/**
 * @name GestionePrenotazioni
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.competenze
 *
 * @author Giacomo Marciani
 *
 */
public class GestionePrenotazioni extends AbstractCompetenza {
	
	private static final long serialVersionUID = 1L;

	private static GestionePrenotazioni singletonGestionePrenotazioni;
	
	private static int ID = Competenza.GESTIONE_PRENOTAZIONI;
	private static String STRING = "Gestione Prenotazioni";

	protected GestionePrenotazioni(int id, String string) {
		super(id, string);
	}
	
	public static synchronized GestionePrenotazioni getInstance() {
		if (singletonGestionePrenotazioni == null) {
			singletonGestionePrenotazioni = new GestionePrenotazioni(ID, STRING);
			return singletonGestionePrenotazioni;
		}
		
		return singletonGestionePrenotazioni;
	}

}
