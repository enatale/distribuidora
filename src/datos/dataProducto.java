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
					"select productos.codProducto,productos.descripcion,productos.stock,precios.importe, precios.fecha_desde "
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
				prod.setFecha(rs.getDate("fecha_desde"));
				prod.setStock(rs.getInt("stock"));
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
			descontarStock(stmt,cantidad, codProducto);			
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

	protected void descontarStock(PreparedStatement stmt, int cantidad, int codProducto) throws SQLException, ApplicationException {
		stmt= FactoryConexion.getInstancia().getConnection().prepareStatement(""
				+ "update productos set stock=stock-? where codProducto=?");
		stmt.setInt(1, cantidad);
		stmt.setInt(2, codProducto);
		stmt.execute();
	}

	public void modificarProducto(Producto pr, String descripcion, int stock,java.util.Date fecha, float importe) throws ApplicationException{
		PreparedStatement stmtProducto = null;
		PreparedStatement stmtPrecio=null;

		try {
		FactoryConexion.getInstancia().getConnection().setAutoCommit(false);
		stmtProducto = FactoryConexion.getInstancia().getConnection().prepareStatement(
				"update productos set descripcion=? ,stock=? "
				+ " where codProducto=?");
		stmtProducto.setString(1, descripcion);
		stmtProducto.setInt(2, stock);
		stmtProducto.setInt(3, pr.getCodProducto());
		stmtProducto.execute();
		stmtPrecio = FactoryConexion.getInstancia().getConnection().prepareStatement(
				"update precios set importe = ?, fecha_desde=? "
				+ "where codProducto=? and fecha_desde = ?");
		stmtPrecio.setFloat(1, pr.getImporte());
		stmtPrecio.setDate(2, new java.sql.Date(fecha.getTime()));
		stmtPrecio.setInt(3, pr.getCodProducto());
		stmtPrecio.setDate(4, new java.sql.Date(pr.getFecha().getTime()));
		stmtPrecio.execute();
		} catch (SQLException e) {
			try {
				FactoryConexion.getInstancia().getConnection().rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Error al modificar producto de la base de datos", e);
			}
			throw new ApplicationException("Error al modificar producto de la base de datos", e);
		
		} finally {
			try {
				if(stmtProducto != null) stmtProducto.close();
				if(stmtPrecio != null) stmtPrecio.close();
				FactoryConexion.getInstancia().getConnection().setAutoCommit(true);
				FactoryConexion.getInstancia().getConnection().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al liberar recursos de la base de datos", e);
			}
		}
		
	}
	
	public void actualizarPrecio(Producto pr, float importe, java.util.Date fecha_desde) throws ApplicationException{

		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"insert into precios (codProducto, fecha_desde, importe)"
					+" values (?,?,?)");
			stmt.setInt(1,pr.getCodProducto());
			stmt.setDate(2,new java.sql.Date(fecha_desde.getTime()));
			stmt.setFloat(3, importe);
			stmt.execute();
		} catch (SQLException e) {
			throw new ApplicationException("Error al agregar nuevo precio del producto de la base de datos", e);
		
		} finally{
			try {
				if(stmt != null) stmt.close();
				FactoryConexion.getInstancia().getConnection().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al liberar recursos de la base de datos", e);
			}
			
		}
	}

	public void actualizarStock (Producto pr, int cantidad)throws ApplicationException{
		PreparedStatement stmt =null;
		
		try {
			stmt= FactoryConexion.getInstancia().getConnection().prepareStatement(
					"update productos set stock=stock+? where codProducto=?");

			stmt.setInt(1, cantidad);
			stmt.setInt(2, pr.getCodProducto());
			stmt.execute();
		} catch (SQLException e) {
			//throw new ApplicationException("Error al actualizar el stock del producto de la base de datos", e);
			e.printStackTrace();
		} finally{
			try {
				if(stmt != null) stmt.close();
				FactoryConexion.getInstancia().getConnection().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al liberar recursos de la base de datos", e);
			}
		}
	}

	public void aumentarStock(int codProducto, int cantidad) throws ApplicationException {
		PreparedStatement stmt = null;
		try{
			aumentarStock(stmt, codProducto, cantidad);			
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

	protected void aumentarStock(PreparedStatement stmt,int codProducto, int cantidad) throws SQLException, ApplicationException {
		stmt= FactoryConexion.getInstancia().getConnection().prepareStatement(""
				+ "update productos set stock=stock+? where codProducto=?");
		stmt.setInt(1, cantidad);
		stmt.setInt(2, codProducto);
		stmt.execute();
	}
	
}

		
