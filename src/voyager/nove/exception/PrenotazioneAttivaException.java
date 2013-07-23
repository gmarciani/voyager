package voyager.nove.exception;

/**
 * @name PrenotazioneAttivaException
 *
 * @project Voyager
 *
 * @package voyager.nove.exception
 *
 * @author Giacomo Marciani
 *
 */
public class PrenotazioneAttivaException extends Exception{

	private static final long serialVersionUID = -6018097198605476847L;

	public PrenotazioneAttivaException(){
		super();
	}
	
	public PrenotazioneAttivaException(String messaggio){
		super(messaggio);
	}
}
