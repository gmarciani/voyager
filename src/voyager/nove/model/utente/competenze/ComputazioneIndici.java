package voyager.nove.model.utente.competenze;

/**
 * @name ComputazioneIndici
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.competenze
 *
 * @author Giacomo Marciani
 *
 */
public class ComputazioneIndici extends AbstractCompetenza {
	
	private static final long serialVersionUID = 1L;

	private static ComputazioneIndici singletonComputazioneIndici;
	
	private static int ID = Competenza.COMPUTAZIONE_INDICI;
	private static String STRING = "Computazione Indici";

	protected ComputazioneIndici(int id, String string) {
		super(id, string);
	}
	
	public static synchronized ComputazioneIndici getInstance() {
		if (singletonComputazioneIndici == null) {
			singletonComputazioneIndici = new ComputazioneIndici(ID, STRING);
			return singletonComputazioneIndici;
		}
		
		return singletonComputazioneIndici;
	}

}
