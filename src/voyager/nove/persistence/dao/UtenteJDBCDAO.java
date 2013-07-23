package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import voyager.nove.exception.UtenteInesistenteException;
import voyager.nove.model.utente.Login;
import voyager.nove.model.utente.Utente;
import voyager.nove.model.utente.bean.UtenteBean;
import voyager.nove.model.utente.ruolo.FactoryRuoli;
import voyager.nove.persistence.manager.ConnectionManager;
import voyager.nove.persistence.manager.JDBCConnectionManager;

/**
 * @name UtenteJDBCDAO
 *
 * @project Voyager
 *
 * @package voyager.nove.persistence.dao
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class UtenteJDBCDAO implements UtenteDAO {
	
	private static ConnectionManager connectionManager;
	
	private static UtenteJDBCDAO singletonUtenteDAO; 	
	
	private UtenteJDBCDAO() {
		connectionManager = JDBCConnectionManager.getInstance();		
	}
	
	public static synchronized UtenteDAO getInstance() {
		if(singletonUtenteDAO == null) {
			singletonUtenteDAO = new UtenteJDBCDAO();
		}			
		
		return singletonUtenteDAO;
	}

	@Override
	public synchronized boolean save(Utente utente) {	
		GregorianCalendar cal = utente.getDatiUtente().getNascita();
		String data = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
		String SQL_INSERT = "INSERT INTO `utenti` " +
				"(`username`, `password`, `ruolo`, `nome`, `cognome`, `citta`, `nascita`, `sesso`, `mail`) " +
				"VALUES (\"" + utente.getLogin().getUsername() + "\", " +
						"\"" + utente.getLogin().getPassword() + "\", " +
						"" + utente.getRuolo().getId() + ", " +
						"\"" + utente.getDatiUtente().getNome() + "\", " +
						"\"" + utente.getDatiUtente().getCognome() + "\", " +
						"\"" + utente.getDatiUtente().getCitta() + "\", " +
						"\"" + data + "\", " +
						"\"" + utente.getDatiUtente().getSesso() + "\", " +
						"\"" + utente.getDatiUtente().getMail() + "\")";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_INSERT);
		} catch (SQLException exc) {
			exc.printStackTrace();
			return false;
		} finally {
			connectionManager.close(connection, statement);
		}
		
		return true;
	}

	@Override
	public synchronized boolean update(Utente utente) {
		GregorianCalendar cal = utente.getDatiUtente().getNascita();
		String data = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
		String SQL_INSERT = "UPDATE `utenti` SET " +
				"`nome` = \"" + utente.getDatiUtente().getNome() + "\", " +
				"`cognome` =  \"" + utente.getDatiUtente().getCognome() + "\", " +
				"`citta` = \"" + utente.getDatiUtente().getCitta() + "\", " +
				"`nascita` = \"" + data + "\", " +
				"`sesso` = \"" + utente.getDatiUtente().getSesso() + "\", " +
				"`mail` = \"" + utente.getDatiUtente().getMail() + "\", " +
				"`ruolo` = " + utente.getRuolo().getId() + ", " +
				"`password` = \"" + utente.getLogin().getPassword() + "\" " + 
				"WHERE `username` = \"" + utente.getLogin().getUsername() + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_INSERT);
		} catch (SQLException exc) {
			exc.printStackTrace();
			return false;
		} finally {
			connectionManager.close(connection, statement);
		}
		
		return true;
	}

	@Override
	public synchronized boolean delete(String username) {
		String SQL_DELETE = "DELETE FROM `utenti` WHERE `username` = \"" + username + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_DELETE);
		} catch (SQLException exc) {
			exc.printStackTrace();
			return false;
		} finally {
			connectionManager.close(connection, statement);
		}
		
		return true;
	}
	
	@Override
	public synchronized List<UtenteBean> findAll() {
		List<UtenteBean> listaUtenti = new ArrayList<UtenteBean>();
		String SQL_FIND_ALL = "SELECT * FROM `utenti`";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND_ALL);
			
			while(result.next()) {
				UtenteBean utenteBean = new UtenteBean();
				utenteBean.setNome(result.getString("nome"));
				utenteBean.setCognome(result.getString("cognome"));
				utenteBean.setCitta(result.getString("citta"));
				Date data = result.getDate("nascita");
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(data);
				utenteBean.setNascita(cal);
				utenteBean.setSesso(result.getString("sesso"));
				utenteBean.setMail(result.getString("mail"));
				utenteBean.setRuolo(FactoryRuoli.getInstance().assegnaRuolo(result.getInt("ruolo")));
				utenteBean.setUsername(result.getString("username"));
				utenteBean.setPassword(result.getString("password"));	
				
				listaUtenti.add(utenteBean);
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}
		
		return listaUtenti;
	}
	
	@Override
	public List<UtenteBean> find(String key, String query) {
		List<UtenteBean> listaUtenti = new ArrayList<UtenteBean>();
		String SQL_FIND = "SELECT * FROM `utenti` WHERE `" + key + "` LIKE \"%" + query + "%\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND);
			
			while(result.next()) {
				UtenteBean utenteBean = new UtenteBean();
				utenteBean.setNome(result.getString("nome"));
				utenteBean.setCognome(result.getString("cognome"));
				utenteBean.setCitta(result.getString("citta"));
				Date data = result.getDate("nascita");
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(data);
				utenteBean.setNascita(cal);
				utenteBean.setSesso(result.getString("sesso"));
				utenteBean.setMail(result.getString("mail"));
				utenteBean.setRuolo(FactoryRuoli.getInstance().assegnaRuolo(result.getInt("ruolo")));
				utenteBean.setUsername(result.getString("username"));
				utenteBean.setPassword(result.getString("password"));	
				
				listaUtenti.add(utenteBean);
			}
		} catch (SQLException exc) {
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}
		
		return listaUtenti;
	}

	@Override
	public synchronized UtenteBean findByUsername(String username) throws UtenteInesistenteException {
		String SQL_FIND_BY_USERNAME = "SELECT * FROM `utenti` WHERE `username` = \"" + username + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		UtenteBean utenteBean = null;
		
		try {
			
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND_BY_USERNAME);
			
			if(result.next()) {
				utenteBean = new UtenteBean();
				utenteBean.setNome(result.getString("nome"));
				utenteBean.setCognome(result.getString("cognome"));
				utenteBean.setCitta(result.getString("citta"));
				Date data = result.getDate("nascita");
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(data);
				utenteBean.setNascita(cal);
				utenteBean.setSesso(result.getString("sesso"));
				utenteBean.setMail(result.getString("mail"));
				utenteBean.setRuolo(FactoryRuoli.getInstance().assegnaRuolo(result.getInt("ruolo")));
				utenteBean.setUsername(result.getString("username"));
				utenteBean.setPassword(result.getString("password"));
				
				return utenteBean;
			} else {
				throw new UtenteInesistenteException();
			}
			
		} catch (SQLException exc) {
			exc.printStackTrace();
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}				
	}

	@Override
	public synchronized UtenteBean findByLogin(Login login) throws UtenteInesistenteException {
		String SQL_FIND_BY_LOGIN = "SELECT * FROM `utenti` " +
				"WHERE `username` = \"" + login.getUsername() + "\" " + 
				"and `password` = \"" + login.getPassword() + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		UtenteBean utenteBean = null;
		
		try {
			
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND_BY_LOGIN);
			
			if(result.next()) {
				utenteBean = new UtenteBean();
				utenteBean.setNome(result.getString("nome"));
				utenteBean.setCognome(result.getString("cognome"));
				utenteBean.setCitta(result.getString("citta"));
				Date data = result.getDate("nascita");
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(data);
				utenteBean.setNascita(cal);
				utenteBean.setSesso(result.getString("sesso"));
				utenteBean.setMail(result.getString("mail"));
				utenteBean.setRuolo(FactoryRuoli.getInstance().assegnaRuolo(result.getInt("ruolo")));
				utenteBean.setUsername(result.getString("username"));
				utenteBean.setPassword(result.getString("password"));
			} else {
				throw new UtenteInesistenteException();
			}
			
		} catch (SQLException exc) {
			exc.printStackTrace();
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}
		
		return utenteBean;
	}

	@Override
	public boolean verifyLogin(Login login)	throws UtenteInesistenteException {
		String SQL_FIND_BY_LOGIN = "SELECT * FROM `utenti` " +
				"WHERE `username` = \"" + login.getUsername() + "\" " + 
				"and `password` = \"" + login.getPassword() + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND_BY_LOGIN);
			
			if(result.next()) {
				return true;
			} else {
				throw new UtenteInesistenteException();
			}
			
		} catch (SQLException exc) {
			exc.printStackTrace();
			return false;
		} finally {
			connectionManager.close(connection, statement, result);
		}
	}

	

}
