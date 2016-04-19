package datos;

import entidades.Producto;

import java.sql.*;
import java.util.ArrayList;

import appExceptions.ApplicationException;

public class dataPedidos {

	
	public  ArrayList<Producto> getAll(int desde, int hasta) throws ApplicationException{
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Producto prod;
		ResultSet rs= null;
		//ResultSet rsValores=null;
		//PreparedStatement stmtValores =null;
		PreparedStatement stmt = null;
		try{
			stmt= FactoryConexion.getInstancia().getConnection().prepareStatement(
					"select productos.codProducto,productos.descripcion,productos.stock,precios.importe "
					+ "from productos "
					+ "inner join precios "
					+ "on precios.codProducto=productos.codProducto "
					+ "inner join (select precios.codProducto, max(precios.fecha_desde) 'fecha_desde'"
					+ "	from precios"
					+ "	where precios.fecha_desde <= current_date()"
					+ "	group by precios.codProducto)val_prod "
					+ "on precios.codProducto=val_prod.codProducto and precios.fecha_desde=val_prod.fecha_desde "
					+ "group by productos.codproducto"
					+ " limit ?,?");
			stmt.setInt(1, desde);
			stmt.setInt(2, hasta);
			rs=stmt.executeQuery();
			while(rs.next()){
				prod = new Producto();
				prod.setCodProducto(rs.getInt("codProducto"));
				prod.setDescripcion(rs.getString("descripcion"));
				prod.setImporte(rs.getFloat("importe"));
				productos.add(prod);
			}
			
		} catch (SQLException e){
			//TODO excepcion
			e.printStackTrace();			
		} finally{
			try {
				if(stmt!=null) stmt.close();
				if(rs!=null) rs.close();
				FactoryConexion.getInstancia().getConnection().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
		}
		return productos;
		
		
	}
}
