package voyager.nove.model.utente.competenze;

/**
 * @name GestioneCatalogo
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.competenze
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class GestioneCatalogo extends AbstractCompetenza {
	
	private static final long serialVersionUID = 1L;

	private static GestioneCatalogo singletonGestioneCatalogo;
	
	private static int ID = Competenza.GESTIONE_CATALOGO;
	private static String STRING = "Gestione Catalogo";

	protected GestioneCatalogo(int id, String string) {
		super(id, string);
	}
	
	public static synchronized GestioneCatalogo getInstance() {
		if (singletonGestioneCatalogo == null) {
			singletonGestioneCatalogo = new GestioneCatalogo(ID, STRING);
			return singletonGestioneCatalogo;
		}
		
		return singletonGestioneCatalogo;
	}

}
