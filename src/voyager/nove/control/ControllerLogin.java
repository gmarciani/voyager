package voyager.nove.control;

import javax.swing.JFrame;

import voyager.nove.exception.DatiUtenteInconsistentiException;
import voyager.nove.exception.LoginInconsistenteException;
import voyager.nove.exception.UtenteInesistenteException;
import voyager.nove.model.utente.FactoryResetCode;
import voyager.nove.model.utente.Login;
import voyager.nove.model.utente.ResetCode;
import voyager.nove.model.utente.Utente;
import voyager.nove.model.utente.bean.LoginBean;
import voyager.nove.model.utente.bean.UtenteBean;
import voyager.nove.persistence.dao.UtenteDAO;
import voyager.nove.persistence.dao.UtenteJDBCDAO;
import voyager.nove.utils.mailer.Mailer;
import voyager.nove.utils.mailer.StandaloneMailer;
import voyager.nove.view.BoundaryHome;
import voyager.nove.view.dialog.utente.DialogReimpostaPassword;

/**
 * @name ControllerLogin
 *
 * @project Voyager
 *
 * @package voyager.nove.control
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class ControllerLogin {
		
	private static ControllerLogin singletonControllerLogin = null;
	private static UtenteDAO utenteDAO = null;
	private static Mailer mailer = null;

	private ControllerLogin() {}

	public static synchronized ControllerLogin getInstance() {
		if (singletonControllerLogin == null) {
			singletonControllerLogin = new ControllerLogin();
		}
		
		utenteDAO = UtenteJDBCDAO.getInstance();
		mailer = StandaloneMailer.getInstance();
		
		return singletonControllerLogin;
	}
		
	public synchronized UtenteBean login(LoginBean loginBean) throws UtenteInesistenteException, LoginInconsistenteException {
		Login login = new Login().fromBean(loginBean);
		UtenteBean utenteBean = utenteDAO.findByLogin(login);
		
		return utenteBean;
	}
	
	public synchronized void home(UtenteBean utenteBean) {
		BoundaryHome home = new BoundaryHome(utenteBean);
		home.setVisible(true);		
	}

	public synchronized void mostraDialogReimpostaPassword(JFrame owner) {
		DialogReimpostaPassword dialog = new DialogReimpostaPassword(owner);
		dialog.setVisible(true);
	}
	
	public synchronized void impostaResetCode(String username) throws UtenteInesistenteException, DatiUtenteInconsistentiException, LoginInconsistenteException {		
		FactoryResetCode factoryResetCode = FactoryResetCode.getInstance();
		ResetCode resetCode = factoryResetCode.creaResetCode();
		UtenteBean utenteBean = utenteDAO.findByUsername(username).setPassword(String.valueOf(resetCode.getCodice()));
		Utente utente = new Utente().fromBean(utenteBean);
		String mail = utente.getDatiUtente().getMail();
		utenteDAO.update(utente);		
		mailer.inviaMail(mail, "Voyager ResetPassword", "Ciao " + username + "!\n\nLa tua password provvisoria è: " + resetCode.getCodice() + "\n\nSaluti dal team di Voyager.");
	}	

}
