package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import appExceptions.ApplicationException;

public class FactoryConexion {
	
	private String dbDriver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="remar";
	private String pass="123";
	private String db="distribuidora";
	
	private Connection connection;
	private int connAbiertas;
	
	private FactoryConexion() throws ApplicationException{
		try {
			Class.forName(dbDriver);
			connection=null;
			connAbiertas=0;
		} catch (ClassNotFoundException e) {
			throw new ApplicationException("Error al seleccionar el driver para base de datos", e.getCause());
		}
		
	}
	private static FactoryConexion instancia;
	
	public static FactoryConexion getInstancia() throws ApplicationException {
		if (instancia==null){
			instancia= new FactoryConexion();
		}
		return instancia;
	}
	
	public Connection getConnection() throws ApplicationException{
		try {
			if(connection==null||connection.isClosed()){
				
				//PARA SERVIDOR
//				connection= DriverManager.getConnection(
//						"jdbc:mysql://node124276-distribuidora.j.layershift.co.uk/"+db+"?user="+user+"&password="+pass);
				
				//PARA USAR LOCAL
				connection= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+pass);
				
				
				
				connAbiertas++;
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al establecer la conexion con la base de datos", e);
			
		}
		return connection;
	}
	
	public void releaseConnection() throws ApplicationException{
		try {
			connAbiertas--;
			if(connAbiertas<=0){
				connection.close();
			}
		} catch (Exception e) {
			throw new ApplicationException("Error al cerrar la conexion con la base de datos", e);
		}
		
	}
}
