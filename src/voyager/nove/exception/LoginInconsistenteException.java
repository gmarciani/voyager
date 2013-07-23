package voyager.nove.exception;

import voyager.nove.model.utente.Login;

/**
 * @name LoginInconsistenteException
 *
 * @project Voyager
 *
 * @package voyager.nove.exception
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class LoginInconsistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LoginInconsistenteException() {}

	public LoginInconsistenteException(Login login) {
		super("SignIn inconsistente : " + login.getUsername() + " " + login.getPassword());
	}

}
