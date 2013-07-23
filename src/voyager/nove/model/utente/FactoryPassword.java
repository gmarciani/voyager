package voyager.nove.model.utente;

import java.util.Random;

/**
 * @name FactoryPassword
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public final class FactoryPassword {
	
	private static FactoryPassword singletonFactoryPassword = null;

	private FactoryPassword() {}

	public static synchronized FactoryPassword getInstance() {
		if (singletonFactoryPassword == null) {
			singletonFactoryPassword = new FactoryPassword();
			return singletonFactoryPassword;
		}
		
		return singletonFactoryPassword;
	}
	
	public String creaPassword() {
		Random randomGenerator = new Random();
		String password = String.valueOf(randomGenerator.nextInt(Integer.MAX_VALUE) + 1);
		return password;
	}

}
