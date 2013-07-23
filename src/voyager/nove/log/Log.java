package voyager.nove.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @name Log
 *
 * @project Voyager
 *
 * @package voyager.nove.log
 *
 * @author Giacomo Marciani
 *
 */
public class Log {
	
	private static final String LOG_DIR = "Log/";
	
	private static final String LOG_GESTIONE_UTENTI = LOG_DIR + "Log_GestioneUtenti.txt";
	private static final String LOG_GESTIONE_CATALOGO = LOG_DIR + "Log_GestioneCatalogo.txt";
	private static final String LOG_GESTIONE_OFFERTA = LOG_DIR + "Log_GestioneOfferta.txt";
	private static final String LOG_GESTIONE_PRENOTAZIONE = LOG_DIR + "Log_GestionePrenotazione.txt";
	
	private static Log singletonLog;
	
	private Log(){}
	
	public static Log getInstance(){
		if(singletonLog == null)
			singletonLog = new Log();
		return singletonLog;
	}
	
	public synchronized void scrivi(int tipoEvento, String soggetto, String oggetto){
		
		File dir = new File(LOG_DIR);
		
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		String pathLogFile = getLogFile(tipoEvento);
		
		try{
			
			FileWriter file = new FileWriter(pathLogFile, true);
			PrintWriter outw = new PrintWriter(file);
			
			outw.print(" * ");
			outw.print(getHeader());
			outw.print(soggetto);
			outw.print(" --> ");
			outw.print(oggetto);
			outw.println(";");
			
			outw.close();
			file.close();
			
		}catch(IOException exc){			
			System.err.println("Oops! Qualcosa e' andato storto durante l'aggiornamento del Log! " +
					"[" + tipoEvento + ", " + soggetto + ", " + oggetto + "]");
			exc.printStackTrace();			
		}
	}
	
	public synchronized void scrivi(int tipoEvento, String soggetto, String oggetto, String dettagli){
		
		String pathLogFile = getLogFile(tipoEvento);
		
		try{
			
			FileWriter file = new FileWriter(pathLogFile, true);
			PrintWriter outw = new PrintWriter(file);
			
			outw.print(" * ");
			outw.print(getHeader());
			outw.print(soggetto);
			outw.print(" --> ");
			outw.print(oggetto);
			outw.print(":");
			outw.print(dettagli);
			outw.println(";");
			
			outw.close();
			file.close();
			
		}catch(IOException exc){			
			System.err.println("Oops! Qualcosa e' andato storto durante l'aggiornamento del Log! " +
					"[" + tipoEvento + ", " + soggetto + ", " + oggetto + ", " + dettagli + "]");
			exc.printStackTrace();			
		}
	}
	
	private static String getLogFile(int tipoEvento) {
		String pathLogFile = null;
		
		switch(tipoEvento) {
		
		case Evento.GESTIONE_UTENTI:
			pathLogFile = LOG_GESTIONE_UTENTI;
			break;
			
		case Evento.GESTIONE_CATALOGO:
			pathLogFile = LOG_GESTIONE_CATALOGO;
			break;
			
		case Evento.GESTIONE_OFFERTA:
			pathLogFile = LOG_GESTIONE_OFFERTA;
			break;
			
		case Evento.GESTIONE_PRENOTAZIONE:
			pathLogFile = LOG_GESTIONE_PRENOTAZIONE;
			break;
			
		default:
			break;
		}
		
		return pathLogFile;
	}
	
	private static String getHeader(){
		Calendar calendario = new GregorianCalendar(TimeZone.getDefault());
		
		int giorno = calendario.get(Calendar.DAY_OF_MONTH);
		int mese = calendario.get(Calendar.MONTH);
		int anno = calendario.get(Calendar.YEAR);
		
		int ora = calendario.get(Calendar.HOUR_OF_DAY);
		int minuti = calendario.get(Calendar.MINUTE);
		int secondi = calendario.get(Calendar.SECOND);
		
		StringBuilder header = new StringBuilder();
		
		header.append("[");
		header.append(giorno);
		header.append("/");
		header.append(mese);
		header.append("/");
		header.append(anno);
		header.append(" ");
		header.append(ora);
		header.append(":");
		header.append(minuti);
		header.append(":");
		header.append(secondi);
		header.append("] ");
		
		return header.toString();
	}
	
	public static void main(String[] args){
		Log log = Log.getInstance();
		log.scrivi(Evento.GESTIONE_UTENTI, "Aministratore", "Aggiunto utente");
	}
	
}
