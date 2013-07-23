package voyager.nove.exception;

/**
 * @name PostiNonDisponibiliException
 *
 * @project Voyager
 *
 * @package voyager.nove.exception
 *
 * @author Giacomo Marciani
 *
 */
public class PostiNonDisponibiliException extends Exception{

	private static final long serialVersionUID = -6018097198605476847L;

	public PostiNonDisponibiliException(){
		super();
	}
	
	public PostiNonDisponibiliException(String messaggio){
		super(messaggio);
	}
}
