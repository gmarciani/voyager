package voyager.nove.model.utente.ruolo;

import voyager.nove.model.utente.competenze.AmministrazioneUtenti;
import voyager.nove.model.utente.competenze.Competenza;
import voyager.nove.model.utente.competenze.ComputazioneIndici;
import voyager.nove.model.utente.competenze.GestioneCatalogo;
import voyager.nove.model.utente.competenze.GestioneOfferta;
import voyager.nove.model.utente.competenze.GestionePrenotazioni;
import voyager.nove.model.utente.competenze.GestioneProfilo;
import voyager.nove.model.utente.competenze.Login;

/**
 * @name Amministratore
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.ruolo
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class Amministratore extends AbstractRuolo {
	
	private static final long serialVersionUID = 1L;

	private static Amministratore singletonAmministratore = null;
	
	static int ID = Ruolo.AMMINISTRATORE;
	static String STRING = "Amministratore";
	static Competenza[] COMPETENZE = {AmministrazioneUtenti.getInstance(), GestioneCatalogo.getInstance(), GestioneOfferta.getInstance(), GestionePrenotazioni.getInstance(), ComputazioneIndici.getInstance(), GestioneProfilo.getInstance(), Login.getInstance()};

	protected Amministratore(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}
	
	public static synchronized Amministratore getInstance() {
		if (singletonAmministratore == null) {
			singletonAmministratore = new Amministratore(ID, STRING, COMPETENZE);
			return singletonAmministratore;
		}
		
		return singletonAmministratore;
	}

}
