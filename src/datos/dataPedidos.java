package datos;

import entidades.Producto;

import java.sql.*;
import java.util.ArrayList;

public class dataPedidos {

	
	public  ArrayList<Producto> getAll() throws ClassNotFoundException{
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
