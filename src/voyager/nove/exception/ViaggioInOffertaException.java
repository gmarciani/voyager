package voyager.nove.exception;

/**
 * @name ViaggioInOffertaException
 *
 * @project Voyager
 *
 * @package voyager.nove.exception
 *
 * @author Giacomo Marciani
 *
 */
public class ViaggioInOffertaException extends Exception{

	private static final long serialVersionUID = -6018097198605476847L;

	public ViaggioInOffertaException(){
		super();
	}
	
	public ViaggioInOffertaException(String messaggio){
		super(messaggio);
	}
}
