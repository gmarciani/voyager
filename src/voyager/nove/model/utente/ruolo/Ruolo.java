package voyager.nove.model.utente.ruolo;

import java.io.Serializable;

import voyager.nove.model.utente.competenze.Competenza;

/**
 * @name Ruolo
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.ruolo
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public interface Ruolo extends Comparable<Ruolo>, Serializable {
	
	public static final int NONE = -1;
	
	public static final int AMMINISTRATORE = 0;
	
	public static final int PROMOTORE = 1;
	
	public static final int PROGETTISTA = 2;
	
	public static final int VENDITORE = 3;
	
	public static final int CLIENTE = 4;
	
	public static final int VISITATORE = 5;	
	
	int getId();
	
	String asString();
	
	Competenza[] getCompetenze();

}
