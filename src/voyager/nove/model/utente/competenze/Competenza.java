package voyager.nove.model.utente.competenze;

import java.io.Serializable;

/**
 * @name Competenza
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.competenze
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public interface Competenza extends Serializable {
	
	public static final int LOGIN = 0;
	
	public static final int GESTIONE_PROFILO = 1;
	
	public static final int AMMINISTRAZIONE_UTENTI = 2;
	
	public static final int GESTIONE_CATALOGO = 3;
	
	public static final int GESTIONE_OFFERTA = 4;
	
	public static final int GESTIONE_PRENOTAZIONI = 5;
	
	public static final int GESTIONE_PRENOTAZIONI_PERSONALI = 6;
	
	public static final int VISUALIZZAZIONE_CATALOGO = 7;
	
	public static final int COMPUTAZIONE_INDICI = 8;
	
	int getId();
	
	String asString();
	
	String asCompactString();
	
}
