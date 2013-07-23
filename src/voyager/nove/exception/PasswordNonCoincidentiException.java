package voyager.nove.exception;

/**
 * @name PasswordNonCoincidentiException
 *
 * @project Voyager
 *
 * @package voyager.nove.exception
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class PasswordNonCoincidentiException extends Exception {

	private static final long serialVersionUID = 1L;

	public PasswordNonCoincidentiException() {
		super();
	}

	public PasswordNonCoincidentiException(String msg) {
		super(msg);
	}

}
