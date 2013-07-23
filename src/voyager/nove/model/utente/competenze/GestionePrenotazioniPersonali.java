package voyager.nove.model.utente.competenze;

/**
 * @name GestionePrenotazioniPersonali
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.competenze
 *
 * @author Giacomo Marciani
 *
 */
public class GestionePrenotazioniPersonali extends AbstractCompetenza {
	
	private static final long serialVersionUID = 1L;

	private static GestionePrenotazioniPersonali singletonGestionePrenotazioniPersonali;
	
	private static int ID = Competenza.GESTIONE_PRENOTAZIONI_PERSONALI;
	private static String STRING = "Gestione Prenotazioni Personali";

	protected GestionePrenotazioniPersonali(int id, String string) {
		super(id, string);
	}
	
	public static synchronized GestionePrenotazioniPersonali getInstance() {
		if (singletonGestionePrenotazioniPersonali == null) {
			singletonGestionePrenotazioniPersonali = new GestionePrenotazioniPersonali(ID, STRING);
			return singletonGestionePrenotazioniPersonali;
		}
		
		return singletonGestionePrenotazioniPersonali;
	}

}
