package voyager.nove.model.utente.competenze;

/**
 * @name GestioneProfilo
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.competenze
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class GestioneProfilo extends AbstractCompetenza {
	
	private static final long serialVersionUID = 1L;

	private static GestioneProfilo singletonGestioneProfilo;
	
	private static int ID = Competenza.GESTIONE_PROFILO;
	private static String STRING = "Gestione Profilo";

	protected GestioneProfilo(int id, String string) {
		super(id, string);
	}
	
	public static synchronized GestioneProfilo getInstance() {
		if (singletonGestioneProfilo == null) {
			singletonGestioneProfilo = new GestioneProfilo(ID, STRING);
			return singletonGestioneProfilo;
		}
		
		return singletonGestioneProfilo;
	}

}
