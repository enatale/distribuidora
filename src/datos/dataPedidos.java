package datos;

import entidades.Producto;
import ui.Productos;

import java.sql.*;
import java.util.ArrayList;

import appExceptions.ApplicationException;

public class dataPedidos {

	
	public  ArrayList<Producto> getAll() throws ApplicationException{
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Producto prod;
		ResultSet rs= null;
		PreparedStatement stmt =null;
		try{
			stmt = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"select * from productos");
			rs=stmt.executeQuery();
			while(rs.next()){
				prod = new Producto();
				prod.setCodProducto(rs.getInt("codProducto"));
				prod.setDescripcion(rs.getString("descripcion"));
				prod.setStock(rs.getInt("stock"));
				productos.add(prod);
			}
			
		} catch (SQLException e){
			//TODO excepcion
			e.printStackTrace();			
		} finally{
			try {
				if(stmt!=null) stmt.close();
				if(rs!=null) rs.close();
				FactoryConexion.getInstancia().getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				//throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
		}
		return productos;
		
		
	}

	public ArrayList<Producto> getByDescripcion(String descripcion) throws ApplicationException {
		//TODO cambiar cuando cacho suba lo suyo para tener el importe
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Producto prod;
		ResultSet rs= null;
		PreparedStatement stmt =null;
		try{
			stmt = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"select * from productos where descripcion like ?");
			stmt.setString(1, "%"+descripcion+"%");
			rs=stmt.executeQuery();
			while(rs.next()){
				prod = new Producto();
				prod.setCodProducto(rs.getInt("codProducto"));
				prod.setDescripcion(rs.getString("descripcion"));
				prod.setStock(rs.getInt("stock"));
				prod.setImporte(50);
				productos.add(prod);
			}
			
		} catch (SQLException e){
			//TODO excepcion
			e.printStackTrace();			
		} finally{
			try {
				if(stmt!=null) stmt.close();
				if(rs!=null) rs.close();
				FactoryConexion.getInstancia().getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				//throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
		}
		return productos;
	}
}
