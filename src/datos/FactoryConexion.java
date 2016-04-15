package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConexion {
	private static FactoryConexion instancia;
	
	private String dbDriver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="remar";
	private String pass="123";
	private String db="distribuidora";
	
	private Connection connection;
	private int connAbiertas;
	
	
	private FactoryConexion() throws ClassNotFoundException{
		Class.forName(dbDriver);
		connection=null;
		connAbiertas=0;
	}
	
	public static FactoryConexion getInstancia() throws ClassNotFoundException{
		if (instancia==null){
			instancia= new FactoryConexion();
		}
		return instancia;
	}
	
	public Connection getConnection() throws SQLException{
		if(connection==null||connection.isClosed()){
			connection= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+pass);
			connAbiertas++;
		}
		return connection;
	}
	
	public void releaseConnection() throws SQLException{
		connAbiertas--;
		if(connAbiertas<=0){
			connection.close();
		}
	}
}
