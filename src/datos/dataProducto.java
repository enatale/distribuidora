package datos;

import java.util.ArrayList;
import java.sql.*;

import appExceptions.ApplicationException;


import entidades.Producto;


public class dataProducto {
	
	public  int getCantProductos() throws ApplicationException{
		
		int cantProductos=0;
		ResultSet rs= null;
		Statement stmt = null;
		try{
			stmt= FactoryConexion.getInstancia().getConnection().createStatement();
			rs=stmt.executeQuery("select count(codProducto) 'cantidad' from productos");
			while(rs.next()){
				cantProductos=rs.getInt("cantidad");
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
		return cantProductos;
		
		
	}
	
	
	
	public  ArrayList<Producto> getAll(int desde, int hasta) throws ApplicationException{
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Producto prod;
		ResultSet rs= null;
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
					+ " order by productos.descripcion"
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
	public ArrayList<Producto> getByDescripcion(String descripcion,int desde, int hasta) throws ApplicationException {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Producto prod;
		ResultSet rs= null;
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
					+ "where productos.descripcion like ?"
					+ "group by productos.codproducto"
					+ " order by productos.descripcion"
					+ " limit ?,?");
			stmt.setString(1, "%"+descripcion+"%");
			stmt.setInt(2, desde);
			stmt.setInt(3, hasta);
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



	public Producto getByCodigo(int cod) throws ApplicationException {
		Producto prod = null;
		ResultSet rs= null;
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
							+ "where productos.codProducto= ?");
			stmt.setInt(1, cod);
			rs=stmt.executeQuery();
			while(rs.next()){
				prod = new Producto();
				prod.setCodProducto(rs.getInt("codProducto"));
				prod.setDescripcion(rs.getString("descripcion"));
				prod.setImporte(rs.getFloat("importe"));
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
		return prod;
	}



	public int getStock(int codProducto) throws ApplicationException {
		int stock=0;
		ResultSet rs= null;
		PreparedStatement stmt = null;
		try{
			stmt= FactoryConexion.getInstancia().getConnection().prepareStatement("select stock from productos where codProducto=?");
			stmt.setInt(1, codProducto);
			rs=stmt.executeQuery();
			if(rs.next()){
				stock=rs.getInt("stock");
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
		return stock;	
	}



	public void descontarStock(int cantidad,int codProducto) throws ApplicationException {
		PreparedStatement stmt = null;
		try{
			stmt= FactoryConexion.getInstancia().getConnection().prepareStatement(""
					+ "update productos set stock=stock-? where codProducto=?");
			stmt.setInt(1, cantidad);
			stmt.setInt(2, codProducto);
			stmt.execute();			
		} catch (SQLException e){
			//TODO excepcion
			e.printStackTrace();			
		} finally{
			try {
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().getConnection().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
		}
	}
}
		
