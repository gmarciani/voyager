package voyager.nove.model.utente.competenze;

/**
 * @name AbstractCompetenza
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.competenze
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public abstract class AbstractCompetenza implements Competenza {
	
	private static final long serialVersionUID = 1L;
	
	private int ID;
	private String STRING = new String();
	private String COMPACT_STRING = new String();

	protected AbstractCompetenza(int id, String string) {
		this.ID = id;
		this.STRING = string;
		this.COMPACT_STRING = string.replaceAll("\\s","");
	}

	@Override
	public int getId() {
		return ID;
	}

	@Override
	public String asString() {
		return STRING;
	}
	
	@Override
	public String asCompactString() {
		return COMPACT_STRING;
	}	

}
