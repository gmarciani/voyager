package voyager.nove.model.utente;

import voyager.nove.exception.DatiUtenteInconsistentiException;
import voyager.nove.exception.LoginInconsistenteException;
import voyager.nove.model.utente.bean.UtenteBean;
import voyager.nove.model.utente.ruolo.Ruolo;

/**
 * @name Utente
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class Utente implements Comparable<Utente> {
	
	private DatiUtente dati;
	private Ruolo ruolo;
	private Login login;

	public Utente(DatiUtente dati, Ruolo ruolo, Login login) {
		this.setDatiUtente(dati);
		this.setRuolo(ruolo);
		this.setLogin(login);
	}
	
	public Utente() {
		
	}

	@Override
	public int compareTo(Utente other) {
		return getLogin().getUsername().compareTo(other.getLogin().getUsername());
	}

	public DatiUtente getDatiUtente() {
		return this.dati;
	}
	
	private void setDatiUtente(DatiUtente dati) {
		this.dati = dati;
	}
	
	private void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public Ruolo getRuolo() {
		return this.ruolo;
	}
	
	private void setLogin(Login login) {
		this.login = login;
	}

	public Login getLogin() {
		return this.login;
	}	
	
	public Utente fromBean(UtenteBean bean) throws DatiUtenteInconsistentiException, LoginInconsistenteException {
		DatiUtente bDati = new DatiUtente(bean.getNome(), bean.getCognome(), bean.getMail(), bean.getCitta(), bean.getNascita(), bean.getSesso());
		Ruolo bRuolo = bean.getRuolo();
		Login bLogin = new Login(bean.getUsername(), bean.getPassword());
		return new Utente(bDati, bRuolo, bLogin);	
	}

}
