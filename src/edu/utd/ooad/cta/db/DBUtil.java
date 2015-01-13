package edu.utd.ooad.cta.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.collections.Lists;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import edu.utd.ooad.cta.config.ToolConfiguration;

public class DBUtil {
	/**
	 * Logger to log events
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DBUtil.class);

	/**
	 * Class variable
	 */
	private static DBUtil instance;

	/**
	 * Field to store connection object
	 */
	private Connection conn;

	/**
	 * 
	 * @return DatabaseUtility
	 */
	public static DBUtil getInstance() {
		if (instance == null) {
			instance = new DBUtil();
		}
		return instance;
	}

	/**
	 * 
	 * @return Connection
	 * @throws SQLException
	 *             SQLException
	 */
	public Connection getConnection() throws SQLException {
		if (conn == null || conn.isClosed()) {
			DBUtil.getInstance().establishConnection();
		}
		return conn;
	}

	/**
	 * private constructor
	 */
	private DBUtil() {
		establishConnection();
	}

	/**
	 * Method to establish the connection with db.
	 */
	private void establishConnection() {
		ToolConfiguration toolConfig = ToolConfiguration.getInstance();
		toolConfig.loadDatabaseProperties();
		try {
			Class.forName(DatabaseConfig.getInstance().getProperty("connection.driver_class"));
			conn = DriverManager.getConnection(DatabaseConfig.getInstance().getProperty("connection.url"),
					DatabaseConfig.getInstance().getProperty("connection.username"), DatabaseConfig.getInstance()
							.getProperty("connection.password"));
		} catch (SQLException e) {
			throw new DatabaseUtilityException("failed to establish the connection with database.", e);
		} catch (Throwable e) {
			throw new DatabaseUtilityException("failed to establish the connection with database.", e);
		}
	}

	public ResultSet executeQuery(String query) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			LOGGER.debug("Current query is : [{}]", query);
			stmt = getConnection().prepareStatement(query);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			// } finally {
			// try {
			// // rs.close();
			// //stmt.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
		}
		return rs;
	}

	public ResultSet executeQuery(String query, List<Object> params) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = getConnection().prepareStatement(query);
			for (int i = 1; i <= params.size(); i++) {
				Object o = params.get(i - 1);
				if (o instanceof String) {
					stmt.setString(i, o.toString());
				} else if (o instanceof Integer) {
					stmt.setInt(i, Integer.valueOf(o.toString()));
				} else if (o instanceof Double) {
					stmt.setDouble(i, Double.valueOf(o.toString()));
				} else if (o instanceof Date) {
					stmt.setDate(i, (java.sql.Date) o);
				} else if (o instanceof Timestamp) {
					stmt.setTimestamp(i, (Timestamp) o);
				} else if (o instanceof Boolean) {
					stmt.setBoolean(i, (Boolean) o);
				} else {
					stmt.setString(i, o.toString());
				}
			}
			LOGGER.debug("{}", stmt);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			// } finally {
			// try {
			// stmt.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
		}
		return rs;
	}

	public int executeUpdate(String query) throws Exception {
		int rows = 0;
		PreparedStatement stmt = null;
		LOGGER.debug("sql query : {}", query);
		try {
			stmt = getConnection().prepareStatement(query);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
			// } finally {
			// try {
			// stmt.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
		}
		return rows;
	}

	public int executeUpdate(String query, Object value) throws Exception {
		List<Object> values = Lists.newArrayList();
		values.add(value);
		return executeUpdate(query, values);
	}
}
