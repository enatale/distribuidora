package datos;

import java.util.Date;
import java.sql.*;

import appExceptions.ApplicationException;


import entidades.Producto;


public class dataProducto {

	public void agregarProducto(Producto pr) throws ApplicationException{
		ResultSet rs=null;
		PreparedStatement stmtProd =null;
		PreparedStatement stmtPrecio = null;
		
			try {
				stmtProd= FactoryConexion.getInstancia().getConnection().prepareStatement(
						"Insert into productos (descripcion, stock)"
						+ "values (?,?)",	PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmtProd.setString(1, pr.getDescripcion());
			stmtProd.setInt(2, pr.getStock());
			stmtProd.execute();

			//Recupero el id asignado por la bd
			rs = stmtProd.getGeneratedKeys();
			if(rs!=null && rs.next()){
				pr.setCodProducto(rs.getInt(1));
			}
			stmtPrecio = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"Insert into precios (codProducto, fecha_desde, importe)"
					+"values (?,?,?)");
			stmtPrecio.setInt(1, pr.getCodProducto());
			stmtPrecio.setDate(2,new java.sql.Date(pr.getFecha().getTime()));
			stmtPrecio.setFloat(3, pr.getImporte());
			stmtPrecio.execute();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
				if(stmtProd!=null) stmtProd.close();
				if(stmtPrecio!=null) stmtPrecio.close();
				if(rs!=null) rs.close();
				FactoryConexion.getInstancia().getConnection().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
				
			}
		
	}
}
		
