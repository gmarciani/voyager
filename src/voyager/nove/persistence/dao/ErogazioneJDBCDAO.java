package voyager.nove.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import voyager.nove.model.indice.IDElemento;
import voyager.nove.persistence.manager.ConnectionManager;
import voyager.nove.persistence.manager.JDBCConnectionManager;

/**
 * @name ErogazioneJDBCDAO
 *
 * @project Voyager
 *
 * @package voyager.nove.persistence.dao
 *
 * @author Giacomo Marciani
 *
 */
public class ErogazioneJDBCDAO implements ErogazioneDAO {
	
	private static ConnectionManager connectionManager;
	
	private static ErogazioneJDBCDAO singletonIndiceDAO; 	
	
	private ErogazioneJDBCDAO() {
		connectionManager = JDBCConnectionManager.getInstance();		
	}
	
	public static synchronized ErogazioneDAO getInstance() {
		if(singletonIndiceDAO == null) {
			singletonIndiceDAO = new ErogazioneJDBCDAO();
		}			
		
		return singletonIndiceDAO;
	}

	@Override
	public synchronized long read(IDElemento elemento) {
		String SQL_READ_PROFONDITA_MEZZO = "SELECT * FROM " +
				"((`voyager`.`prenotazioni` INNER JOIN `voyager`.`offerta` " +
				"ON `voyager`.`prenotazioni`.`id_offerta` = `voyager`.`offerta`.`id_offerta`) " +
				"INNER JOIN `voyager`.`catalogo` " +
				"ON `voyager`.`offerta`.`id_viaggio` = `voyager`.`catalogo`.`id_viaggio`) " +
				"WHERE (`voyager`.`catalogo`.`citta_partenza` = \"" + elemento.getCittaPartenza() + "\" " +
						"AND `voyager`.`catalogo`.`citta_arrivo` = \"" + elemento.getCittaArrivo() + "\" " +
								"AND `voyager`.`catalogo`.`ambiente` = \"" + elemento.getAmbiente() + "\" " +
										"AND `voyager`.`catalogo`.`mezzo` = \"" + elemento.getMezzo() + "\")";
		
		String SQL_READ_PROFONDITA_AMBIENTE = "SELECT * FROM " +
				"((`voyager`.`prenotazioni` INNER JOIN `voyager`.`offerta` " +
				"ON `voyager`.`prenotazioni`.`id_offerta` = `voyager`.`offerta`.`id_offerta`) " +
				"INNER JOIN `voyager`.`catalogo` " +
				"ON `voyager`.`offerta`.`id_viaggio` = `voyager`.`catalogo`.`id_viaggio`) " +
				"WHERE (`voyager`.`catalogo`.`citta_partenza` = \"" + elemento.getCittaPartenza() + "\" " +
						"AND `voyager`.`catalogo`.`citta_arrivo` = \"" + elemento.getCittaArrivo() + "\" " +
								"AND `voyager`.`catalogo`.`ambiente` = \"" + elemento.getAmbiente() + "\")";
		
		String SQL_READ_PROFONDITA_CITTA = "SELECT * FROM " +
				"((`voyager`.`prenotazioni` INNER JOIN `voyager`.`offerta` " +
				"ON `voyager`.`prenotazioni`.`id_offerta` = `voyager`.`offerta`.`id_offerta`) " +
				"INNER JOIN `voyager`.`catalogo` " +
				"ON `voyager`.`offerta`.`id_viaggio` = `voyager`.`catalogo`.`id_viaggio`) " +
				"WHERE (`voyager`.`catalogo`.`citta_partenza` = \"" + elemento.getCittaPartenza() + "\" " +
						"AND `voyager`.`catalogo`.`citta_arrivo` = \"" + elemento.getCittaArrivo() + "\")";
		
		String SQL_READ_PROFONDITA_SELEZIONATA;
		
		if (elemento.getMezzo() == null) {
			SQL_READ_PROFONDITA_SELEZIONATA = SQL_READ_PROFONDITA_AMBIENTE;
		} else if (elemento.getAmbiente() == null) {
			SQL_READ_PROFONDITA_SELEZIONATA = SQL_READ_PROFONDITA_CITTA;
		} else {
			SQL_READ_PROFONDITA_SELEZIONATA = SQL_READ_PROFONDITA_MEZZO;
		}
		
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		long erogazione = 0;
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(SQL_READ_PROFONDITA_SELEZIONATA);
			
			while(result.next()) {
				erogazione += result.getInt("posti_prenotati");
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
			return -1;
		} finally {
			connectionManager.close(connection, statement);
		}
		
		return erogazione;
	}

}
