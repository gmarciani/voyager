package voyager.nove.model.utente.ruolo;

/**
 * @name FactoryRuoli
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.ruolo
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public final class FactoryRuoli {
	
	private static FactoryRuoli singletonFactoryRuoli = null;

	private FactoryRuoli() {}

	public static synchronized FactoryRuoli getInstance() {
		if (singletonFactoryRuoli == null) {
			singletonFactoryRuoli = new FactoryRuoli();
			return singletonFactoryRuoli;
		}
		
		return singletonFactoryRuoli;
	}
	
	public Ruolo assegnaRuolo(int ruolo) {
		switch(ruolo) {
		case Ruolo.AMMINISTRATORE:
			return Amministratore.getInstance();
		case Ruolo.PROMOTORE:
			return Promotore.getInstance();
		case Ruolo.PROGETTISTA:
			return Progettista.getInstance();
		case Ruolo.VENDITORE:
			return Venditore.getInstance();
		case Ruolo.CLIENTE:
			return Cliente.getInstance();
		case Ruolo.VISITATORE:
			return Visitatore.getInstance();
		default:
			return null;
		}		
	}

}
