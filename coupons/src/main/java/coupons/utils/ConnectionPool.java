package coupons.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class ConnectionPool implements ISingleton {

	/////////////////////// Singleton Section /////////////////////////////////
	
	/**
	 * the private instance 
	 */
	private static ConnectionPool _inst;
	/**
	 * magic number for connections
	 * @TODO: move this to a configuration file
	 */
	private final Semaphore connectionsSemaphore;

	/**
	 * private constructor
	 * @throws SQLException - if the connection is not set correctly isn't configured 
	 */
	private ConnectionPool() throws SQLException {
		
		connectionsSemaphore = new Semaphore(ConnectionPool.MAX_DB_CONNECTIONS);
		connections = new HashSet<Connection>(); 
		for (int i = 0; i<ConnectionPool.MAX_DB_CONNECTIONS; i++) {
			Connection con = JdbcUtils.getConnection(this.username,this.password,this.host,this.port);
			connections.add(con);
		}
	}

	/**
	 * thread safe singleton access & lazy-initialization of the ConnectionPool object.
	 * 
	 * @return an instance of the connectionPool
	 * @throws SQLException - if the connection is not set correctly isn't configured 
	 */
	public static synchronized ConnectionPool getInstance() throws SQLException{
		if (ConnectionPool._inst==null) {
			ConnectionPool._inst= new ConnectionPool();
		}
		return ConnectionPool._inst;
	}

	public static void main(String[] args) throws SQLException  {
		//test db connection
//		Connection connection = JdbcUtils.getConnection(this.username,this.password,this.host,this.port);
//		if (connection != null) {
//			System.out.println("Connected");
//		}	
//		connection.close();
	}
	
	/////////////////////// Members Section /////////////////////////////////
	private Set<Connection> connections;
	private String host = "127.0.0.1";	
	private String username = "root";	
	private String password = "mambojambo";	
	private String port = "3306";	
	final   String propFileName = "config.properties";
	private static int MAX_DB_CONNECTIONS = 50; 
	/////////////////////// Methods Section /////////////////////////////////
	
	/**
	 * get a connection from the pool, may block till one is ready.
	 * 
	 * @return a connection.
	 * @throws SQLException.
	 * @TODO: write a test.
	 */
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		synchronized (connections) {
			try {
				connectionsSemaphore.acquire();
				connection= connections.iterator().next();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		return connection;
	}
	
	
	
	/**
	 * return a thread to the pool and notify next waiting thread
	 * @param connection
	 * @TODO: write a test
	 */
	public void returnConnection(Connection connection) {
		
		synchronized (connections) {
			connections.add(connection);
			this.connectionsSemaphore.notify();
		}
	}

	/**
	 * close all connections - use only in finalize()
	 * @throws SQLException 
	 * @TODO: write a test
	 */
	public synchronized void closeAllConnections() throws SQLException   {
		for(Connection connection: connections) {
			// TODO : try to avoid closing in mid-transaction. 
			if(!connection.isClosed())
				connection.close();
		}
	}
	
	/**
	 * closes all connections on shutdown.
	 * 
	 */
	@Override
	public void finalize() throws SQLException{
		this.closeAllConnections();
	}

	public void getPropValues() throws IOException {
 
		Properties prop = new Properties();
		try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);) {
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			// get the property value 
			this.password = prop.getProperty("password");
			this.host = prop.getProperty("host");
			this.port = prop.getProperty("port");
			this.username = prop.getProperty("username");
			ConnectionPool.MAX_DB_CONNECTIONS=Integer.parseInt( prop.getProperty("max_connections"));
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
}
