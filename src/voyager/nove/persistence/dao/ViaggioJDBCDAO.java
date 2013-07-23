package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import voyager.nove.model.viaggio.Viaggio;
import voyager.nove.model.viaggio.bean.ViaggioBean;
import voyager.nove.persistence.manager.ConnectionManager;
import voyager.nove.persistence.manager.JDBCConnectionManager;

/**
 * @name ViaggioJDBCDAO
 *
 * @project Voyager
 *
 * @package voyager.nove.persistence.dao
 *
 * @author Giacomo Marciani
 *
 */
public class ViaggioJDBCDAO implements ViaggioDAO {
	
	private static ConnectionManager connectionManager;
	
	private static ViaggioJDBCDAO singletonViaggioDAO; 	
	
	private ViaggioJDBCDAO() {
		connectionManager = JDBCConnectionManager.getInstance();		
	}
	
	public static synchronized ViaggioDAO getInstance() {
		if(singletonViaggioDAO == null) {
			singletonViaggioDAO = new ViaggioJDBCDAO();
		}			
		
		return singletonViaggioDAO;
	}

	@Override
	public synchronized boolean save(Viaggio viaggio) {	
		String SQL_INSERT = "INSERT INTO `catalogo` " +
				"(`citta_partenza`, `citta_arrivo`, `ambiente`, `mezzo`) " +
				"VALUES (\"" + viaggio.getCittaPartenza() + "\", " +
						"\"" + viaggio.getCittaArrivo() + "\", " +
						"\"" + viaggio.getAmbiente() + "\", " +
						"\"" + viaggio.getMezzo() + "\")";
		
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
	public synchronized boolean update(Viaggio viaggio) {
		String SQL_INSERT = "UPDATE `catalogo` SET " +
				"`citta_partenza` = \"" + viaggio.getCittaPartenza() + "\", " +
				"`citta_arrivo` =  \"" + viaggio.getCittaArrivo() + "\", " +
				"`ambiente` = \"" + viaggio.getAmbiente() + "\", " +
				"`mezzo` = \"" + viaggio.getMezzo() +  "\"" + 
				" WHERE `id_viaggio` = " + viaggio.getId();
		
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
	public boolean delete(int idViaggio) {
		String SQL_DELETE = "DELETE FROM `catalogo` WHERE `id_viaggio` = " + idViaggio;
		
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
	public synchronized List<ViaggioBean> findAll() {
		List<ViaggioBean> listaViaggi = new ArrayList<ViaggioBean>();
		String SQL_FIND_ALL = "SELECT * FROM `catalogo`";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND_ALL);
			
			while(result.next()) {
				ViaggioBean viaggioBean = new ViaggioBean()
				.setId(result.getInt("id_viaggio"))
				.setCittaPartenza(result.getString("citta_partenza"))
				.setCittaArrivo(result.getString("citta_arrivo"))
				.setAmbiente(result.getString("ambiente"))
				.setMezzo(result.getString("mezzo"));	
				
				listaViaggi.add(viaggioBean);
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}
		
		return listaViaggi;
	}
	
	@Override
	public List<ViaggioBean> find(String key, String query) {
		List<ViaggioBean> listaViaggi = new ArrayList<ViaggioBean>();
		String SQL_FIND = "SELECT * FROM `catalogo` WHERE `" + key + "` LIKE \"%" + query + "%\"";
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_FIND);
			
			while(result.next()) {
				ViaggioBean viaggioBean = new ViaggioBean()
				.setId(result.getInt("id_viaggio"))
				.setCittaPartenza(result.getString("citta_partenza"))
				.setCittaArrivo(result.getString("citta_arrivo"))
				.setAmbiente(result.getString("ambiente"))
				.setMezzo(result.getString("mezzo"));		
				
				listaViaggi.add(viaggioBean);
			}
		} catch (SQLException exc) {
			return null;
		} finally {
			connectionManager.close(connection, statement, result);
		}
		
		return listaViaggi;
	}	

}
