package voyager.nove.model.utente.ruolo;

import voyager.nove.model.utente.competenze.Competenza;

/**
 * @name AbstractRuolo
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.ruolo
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public abstract class AbstractRuolo implements Ruolo{
	
	private static final long serialVersionUID = 1L;
	
	private int ID;
	private String STRING = new String();
	private Competenza[] COMPETENZE = null;
	
	protected AbstractRuolo(int id, String string, Competenza[] competenze) {
		ID = id;
		STRING = string;
		COMPETENZE = competenze;
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
	public Competenza[] getCompetenze() {
		return COMPETENZE;
	}
	
	@Override
	public int compareTo(Ruolo other) {
		if (this.getId() < other.getId()) {
			return 1;
		} else if (this.getId() > other.getId()) {
			return -1;
		} else {
			return 0;
		}
	}

}
