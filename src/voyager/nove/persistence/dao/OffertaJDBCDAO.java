package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import voyager.nove.model.viaggio.Offerta;
import voyager.nove.model.viaggio.bean.OffertaBean;
import voyager.nove.persistence.manager.ConnectionManager;
import voyager.nove.persistence.manager.JDBCConnectionManager;
import voyager.nove.utils.DateUtils;

/**
 * @name OffertaJDBCDAO
 *
 * @project Voyager
 *
 * @package voyager.nove.persistence.dao
 *
 * @author Giacomo Marciani
 *
 */
public class OffertaJDBCDAO implements OffertaDAO {
	
	private static ConnectionManager connectionManager;
	
	private static OffertaJDBCDAO singletonOffertaDAO; 	
	
	private OffertaJDBCDAO() {
		connectionManager = JDBCConnectionManager.getInstance();		
	}
	
	public static synchronized OffertaDAO getInstance() {
		if(singletonOffertaDAO == null) {
			singletonOffertaDAO = new OffertaJDBCDAO();
		}			
		
		return singletonOffertaDAO;
	}

	@Override
	public synchronized boolean save(Offerta offerta) {
		String SQL_INSERT = "INSERT INTO `offerta` " +
				"(`id_viaggio`, `data_partenza`, `data_arrivo`, `posti_disponibili`) " +
				"VALUES (" + offerta.getIdViaggio() + ", " +
						"\"" + DateUtils.getStringFromDate(offerta.getDataPartenza()) + "\", " +
						"\"" + DateUtils.getStringFromDate(offerta.getDataArrivo()) + "\", " +
						offerta.getPostiDisponibili() + ")";
		
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
	public synchronized boolean update(Offerta offerta) {
		String SQL_INSERT = "UPDATE `offerta` SET " +
				"`id_viaggio` =  " + offerta.getIdViaggio() + ", " +
				"`data_partenza` = \"" + DateUtils.getStringFromDate(offerta.getDataPartenza()) + "\", " +
				"`data_arrivo` = \"" + DateUtils.getStringFromDate(offerta.getDataArrivo()) + "\", " +
				"`posti_disponibili` = " + offerta.getPostiDisponibili() + 
				" WHERE `id_offerta` = " + offerta.getId();
		
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
	public synchronized boolean delete(int idOfferta) {
		String SQL_DELETE = "DELETE FROM `offerta` WHERE `id_offerta` = " + idOfferta;
		
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
	public synchronized List<OffertaBean> findAll() {
		List<OffertaBean> listaOfferte = new ArrayList<OffertaBean>();
		String SQL_FIND_ALL = "SELECT * FROM `offerta`";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND_ALL);
			
			while(result.next()) {
				OffertaBean offertaBean = new OffertaBean()
				.setId(result.getInt("id_offerta"))
				.setIdViaggio(result.getInt("id_viaggio"))
				.setDataPartenza((Timestamp)result.getObject("data_partenza"))
				.setDataArrivo((Timestamp)result.getObject("data_arrivo"))
				.setPostiDisponibili(result.getInt("posti_disponibili"));
				listaOfferte.add(offertaBean);
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}
		
		return listaOfferte;
	}
	
	@Override
	public List<OffertaBean> find(String key, String query) {
		List<OffertaBean> listaOfferte = new ArrayList<OffertaBean>();
		String SQL_FIND = "SELECT * FROM `offerta` WHERE `" + key + "` LIKE \"" + query + "\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND);
			
			while(result.next()) {
				OffertaBean offertaBean = new OffertaBean()
				.setId(result.getInt("id_offerta"))
				.setIdViaggio(result.getInt("id_viaggio"))
				.setDataPartenza(result.getDate("data_partenza"))
				.setDataArrivo(result.getDate("data_arrivo"))
				.setPostiDisponibili(result.getInt("posti_disponibili"));	
				
				listaOfferte.add(offertaBean);
			}
		} catch (SQLException exc) {
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}
		
		return listaOfferte;
	}

}
