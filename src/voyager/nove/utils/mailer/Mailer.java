package voyager.nove.utils.mailer;

/**
 * @name Mailer
 *
 * @project Voyager
 *
 * @package voyager.nove.utils.mailer
 *
 * @author Giacomo Marciani
 *
 */
public interface Mailer {
	
	static final String MAIL = "giacomo.marciani.voyager@gmail.com";
	static final String USERNAME = "giacomo.marciani.voyager@gmail.com";	
	static final String PASSWORD = "ispwvoyager";
	
	void inviaMail(String mailDestinatario, String oggetto, String messaggio);

}
