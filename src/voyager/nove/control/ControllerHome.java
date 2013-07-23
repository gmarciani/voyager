package voyager.nove.control;

import javax.swing.JPanel;
import voyager.nove.model.utente.bean.UtenteBean;
import voyager.nove.model.utente.competenze.Competenza;
import voyager.nove.utils.swing.ScreenUtils;
import voyager.nove.view.BoundaryComputazioneIndici;
import voyager.nove.view.BoundaryGestioneCatalogo;
import voyager.nove.view.BoundaryGestioneOfferta;
import voyager.nove.view.BoundaryGestionePrenotazioni;
import voyager.nove.view.BoundaryGestionePrenotazioniPersonali;
import voyager.nove.view.BoundaryGestioneUtenti;
import voyager.nove.view.BoundaryGestioneProfilo;
import voyager.nove.view.BoundaryLogin;
import voyager.nove.view.BoundaryVisualizzazioneCatalogo;

/**
 * @name ControllerHome
 *
 * @project Voyager
 *
 * @package voyager.nove.control
 *
 * @author Giacomo Marciani
 *
 */
public class ControllerHome {
	
	private static ControllerHome singletonControllerHome = null;
	
	private ControllerHome() {}
	
	public static synchronized ControllerHome getInstance() {
		if(singletonControllerHome == null) {
			singletonControllerHome = new ControllerHome();
		}
		
		return singletonControllerHome;
	}
	
	public synchronized JPanel getBoundary(UtenteBean utenteBean, int competenza) {
		switch(competenza) {
		case Competenza.GESTIONE_PROFILO:
			return new BoundaryGestioneProfilo(utenteBean);
		case Competenza.AMMINISTRAZIONE_UTENTI:
			return new BoundaryGestioneUtenti();
		case Competenza.GESTIONE_CATALOGO:
			return new BoundaryGestioneCatalogo();
		case Competenza.GESTIONE_OFFERTA:
			return new BoundaryGestioneOfferta();
		case Competenza.GESTIONE_PRENOTAZIONI:
			return new BoundaryGestionePrenotazioni();
		case Competenza.GESTIONE_PRENOTAZIONI_PERSONALI:
			return new BoundaryGestionePrenotazioniPersonali(utenteBean);
		case Competenza.VISUALIZZAZIONE_CATALOGO:
			return new BoundaryVisualizzazioneCatalogo();
		case Competenza.COMPUTAZIONE_INDICI:
			return new BoundaryComputazioneIndici();
		default:
			return new BoundaryVisualizzazioneCatalogo();
		}
	}
	
	public synchronized void logout() {
		BoundaryLogin boundary = new BoundaryLogin();
		boundary.setLocation(ScreenUtils.getCentralScreenLocation(boundary));
		boundary.setVisible(true);
	}

}
