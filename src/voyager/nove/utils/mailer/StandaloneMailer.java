package voyager.nove.utils.mailer;

/**
 * @name StandaloneMailer
 *
 * @project Voyager
 *
 * @package voyager.nove.utils.mailer
 *
 * @author Giacomo Marciani
 *
 */
public final class StandaloneMailer extends AbstractMailer {
	
	private static StandaloneMailer singletonMailer = null;

	protected StandaloneMailer() {}

	public static synchronized StandaloneMailer getInstance() {
		if (singletonMailer == null) {
			singletonMailer = new StandaloneMailer();
		}
		
		return singletonMailer;
	}

}
