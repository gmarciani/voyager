package voyager.nove.model.utente.competenze;

/**
 * @name VisualizzazioneCatalogo
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.competenze
 *
 * @author Giacomo Marciani
 *
 */
public class VisualizzazioneCatalogo extends AbstractCompetenza {
	
	private static final long serialVersionUID = 1L;

	private static VisualizzazioneCatalogo singletonVisualizzazioneCatalogo;
	
	private static int ID = Competenza.VISUALIZZAZIONE_CATALOGO;
	private static String STRING = "Visualizzazione Catalogo";

	protected VisualizzazioneCatalogo(int id, String string) {
		super(id, string);
	}
	
	public static synchronized VisualizzazioneCatalogo getInstance() {
		if (singletonVisualizzazioneCatalogo == null) {
			singletonVisualizzazioneCatalogo = new VisualizzazioneCatalogo(ID, STRING);
			return singletonVisualizzazioneCatalogo;
		}
		
		return singletonVisualizzazioneCatalogo;
	}

}
