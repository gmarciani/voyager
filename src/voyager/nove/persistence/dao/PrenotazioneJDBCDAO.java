package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import voyager.nove.exception.PostiNonDisponibiliException;
import voyager.nove.model.viaggio.Prenotazione;
import voyager.nove.model.viaggio.bean.PrenotazioneBean;
import voyager.nove.persistence.manager.ConnectionManager;
import voyager.nove.persistence.manager.JDBCConnectionManager;

/**
 * @name PrenotazioneJDBCDAO
 *
 * @project Voyager
 *
 * @package voyager.nove.persistence.dao
 *
 * @author Giacomo Marciani
 *
 */
public class PrenotazioneJDBCDAO implements PrenotazioneDAO {
	
	private static ConnectionManager connectionManager;
	
	private static PrenotazioneJDBCDAO singletonPrenotazioneDAO; 	
	
	private PrenotazioneJDBCDAO() {
		connectionManager = JDBCConnectionManager.getInstance();		
	}
	
	public static synchronized PrenotazioneDAO getInstance() {
		if(singletonPrenotazioneDAO == null) {
			singletonPrenotazioneDAO = new PrenotazioneJDBCDAO();
		}			
		
		return singletonPrenotazioneDAO;
	}

	@Override
	public synchronized boolean save(Prenotazione prenotazione) throws PostiNonDisponibiliException {	
		String SQL_INSERT_PRENOTAZIONE = "INSERT INTO `prenotazioni` " +
				"(`id_offerta`, `acquirente`, `posti_prenotati`) " +
				"VALUES (" + prenotazione.getIdOfferta() + ", " +
						"\"" + prenotazione.getAcquirente() + "\", " +
						 + prenotazione.getPostiPrenotati() + ")";
		
		String SQL_UPDATE_OFFERTA = "UPDATE `offerta` SET " +
				"`posti_disponibili` = (`posti_disponibili` - " + prenotazione.getPostiPrenotati() + ")" +
				" WHERE `id_offerta` = " + prenotazione.getIdOfferta();
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_UPDATE_OFFERTA);
			statement.executeUpdate(SQL_INSERT_PRENOTAZIONE);
		} catch (SQLException exc) {
			if (exc.getErrorCode() == 1690) {
				throw new PostiNonDisponibiliException();
			} else {
				exc.printStackTrace();
			}			
			return false;
		} finally {
			connectionManager.close(connection, statement);
		}
		
		return true;
	}

	@Override
	public synchronized boolean update(Prenotazione prenotazione) {
		String SQL_INSERT = "UPDATE `prenotazioni` SET " +
				"`id_offerta` = " + prenotazione.getIdOfferta() + ", " +
				"`acquirente` =  \"" + prenotazione.getAcquirente() + "\", " +
				"`posti_prenotati` = " + prenotazione.getPostiPrenotati() +  
				" WHERE `id_prenotazione` = " + prenotazione.getId();
		
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
	public synchronized boolean delete(Prenotazione prenotazione) {
		String SQL_UPDATE_OFFERTA = "UPDATE `offerta` SET " +
				"`posti_disponibili` = (`posti_disponibili` + " + prenotazione.getPostiPrenotati() + ")" +
						" WHERE `id_offerta` = " + prenotazione.getIdOfferta();
				
		String SQL_DELETE_PRENOTAZIONE = "DELETE FROM `prenotazioni` WHERE `id_prenotazione` = " + prenotazione.getId();
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL_UPDATE_OFFERTA);
			statement.executeUpdate(SQL_DELETE_PRENOTAZIONE);
		} catch (SQLException exc) {
			exc.printStackTrace();
			return false;
		} finally {
			connectionManager.close(connection, statement);
		}
		
		return true;
	}
	
	@Override
	public synchronized List<PrenotazioneBean> findAll() {
		List<PrenotazioneBean> listaPrenotazioni = new ArrayList<PrenotazioneBean>();
		String SQL_FIND_ALL = "SELECT * FROM `prenotazioni`";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND_ALL);
			
			while(result.next()) {
				PrenotazioneBean prenotazioneBean = new PrenotazioneBean()
				.setId(result.getInt("id_prenotazione"))
				.setIdOfferta(result.getInt("id_offerta"))
				.setAcquirente(result.getString("acquirente"))
				.setPostiPrenotati(result.getInt("posti_prenotati"));
				
				listaPrenotazioni.add(prenotazioneBean);
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}
		
		return listaPrenotazioni;
	}
	
	@Override
	public List<PrenotazioneBean> find(String key, String query) {
		List<PrenotazioneBean> listaPrenotazioni = new ArrayList<PrenotazioneBean>();
		String SQL_FIND = "SELECT * FROM `prenotazioni` WHERE `" + key + "` LIKE \"" + query + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND);
			
			while(result.next()) {
				PrenotazioneBean prenotazioneBean = new PrenotazioneBean()
				.setId(result.getInt("id_prenotazione"))
				.setIdOfferta(result.getInt("id_offerta"))
				.setAcquirente(result.getString("acquirente"))
				.setPostiPrenotati(result.getInt("posti_prenotati"));
				
				listaPrenotazioni.add(prenotazioneBean);
			}
		} catch (SQLException exc) {
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}
		
		return listaPrenotazioni;
	}

}
