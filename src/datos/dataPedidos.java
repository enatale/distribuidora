package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import appExceptions.ApplicationException;
import entidades.Estado_pedido;
import entidades.Linea_pedido;
import entidades.Pedidos;
import entidades.Producto;

public class dataPedidos {

	public void registrarPedido(Pedidos pedido, int dni) throws ApplicationException {
		PreparedStatement stmtPedido = null;
		PreparedStatement stmtLineas = null;
		PreparedStatement stmtStock = null;
		dataProducto dprod = new dataProducto();
		ResultSet rs = null;
		try {
			FactoryConexion.getInstancia().getConnection().setAutoCommit(false);
			//fecha_pedido id_estado_pedido dni
			stmtPedido = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"Insert into pedidos(fecha_pedido,id_estado_pedido,dni) values (current_date(),1,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmtPedido.setInt(1, dni);
			stmtPedido.execute();
			rs = stmtPedido.getGeneratedKeys();
			if(rs!=null && rs.next()){
				pedido.setNumero_pedido(rs.getInt(1));
			}
			for (Linea_pedido lp : pedido.getLineas()) {
				dprod.descontarStock(stmtStock, lp.getCantidad(), lp.getProducto().getCodProducto());
				this.insertLinea(stmtLineas, pedido.getNumero_pedido(), lp.getProducto().getCodProducto(), lp.getCantidad());
			}			
			FactoryConexion.getInstancia().getConnection().commit();
			
		} catch (SQLException e) {
			try {
				FactoryConexion.getInstancia().getConnection().rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Error al recuperar estado en la base de datos", e);
			}
			throw new ApplicationException("Error al registrar nuevo pedido en la base de datos", e);
		}
		finally {
			try {
				if(stmtPedido!=null) stmtPedido.close();
				if(stmtLineas!=null) stmtLineas.close();
				if(rs!=null) rs.close();
				FactoryConexion.getInstancia().getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
		}
	}

	public ArrayList<Integer> getNrosPedidosPendientes(int dni) throws ApplicationException {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		int nro;
		ResultSet rs= null;
		PreparedStatement stmt = null;
		try{
			stmt= FactoryConexion.getInstancia().getConnection().prepareStatement("Select numero_pedido from pedidos where dni=? and id_estado_pedido=1");
			stmt.setInt(1, dni);
			rs=stmt.executeQuery();
			while(rs.next()){
				nro = rs.getInt("numero_pedido");
				numeros.add(nro);
			}
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new ApplicationException("Error al recuperar los pedidos pendientes", null);			
		} finally{
			try {
				if(stmt!=null) stmt.close();
				if(rs!=null) rs.close();
				FactoryConexion.getInstancia().getConnection().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
		}
		return numeros;
	}

	public Pedidos getByNroPedido(int nroPedido) throws ApplicationException {
		Pedidos ped = null;
		Producto prod = null;
		dataPersona dper = new dataPersona();
		PreparedStatement stmtPedido = null;
		PreparedStatement stmtLineas = null;
		PreparedStatement stmtProducto = null;
		ResultSet rs = null;
		ResultSet rsLineas = null;
		ResultSet rsProducto = null;
		try {
			FactoryConexion.getInstancia().getConnection().setAutoCommit(false);
			stmtPedido = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"Select * from pedidos where numero_pedido=?");
			stmtPedido.setInt(1, nroPedido);
			rs=stmtPedido.executeQuery();
			if(rs.next()){
				ped=new Pedidos();
				ped.setEstado(new Estado_pedido(1, "pendiente"));
				ped.setFecha_cancelacion(rs.getDate("fecha_cancelacion"));
				ped.setFecha_pedido(rs.getDate("fecha_pedido"));
				ped.setNumero_pedido(rs.getInt("numero_pedido"));
				ped.setCliente(dper.getByDni(rs.getInt("dni")));
				
				stmtLineas = FactoryConexion.getInstancia().getConnection().prepareStatement(
						"select * from linea_pedido where numero_pedido=?");
				ArrayList<Linea_pedido> lineas = new ArrayList<Linea_pedido>();
				stmtLineas.setInt(1, ped.getNumero_pedido());
				rsLineas=stmtLineas.executeQuery();
				Linea_pedido lp;
				while(rsLineas.next()){
					int cod=rsLineas.getInt("codProducto");
					int cantidad= rsLineas.getInt("cantidad");
					
					stmtProducto = FactoryConexion.getInstancia().getConnection().prepareStatement(
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
					stmtProducto.setInt(1, cod);
					rsProducto=stmtProducto.executeQuery();
					while(rsProducto.next()){
						prod = new Producto();
						prod.setCodProducto(rsProducto.getInt("codProducto"));
						prod.setDescripcion(rsProducto.getString("descripcion"));
						prod.setImporte(rsProducto.getFloat("importe"));
					}
					lp = new Linea_pedido(prod, cantidad);
					lineas.add(lp);
				}
				ped.setLineas(lineas);
			}
			
		} catch (SQLException e) {
			throw new ApplicationException("Error al recuperar pedido de la base de datos", e);
		}
		finally {
			
			try {
				FactoryConexion.getInstancia().getConnection().setAutoCommit(true);
				if(stmtPedido!=null) stmtPedido.close();
				if(stmtLineas!=null) stmtLineas.close();
				if(rs!=null) rs.close();
				if(rsLineas!=null) rsLineas.close();
				if(stmtProducto!=null) stmtProducto.close();
				if(rsProducto!=null) rsProducto.close();
				FactoryConexion.getInstancia().releaseConnection();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e.getCause());
			}
		}
		return ped;
	}

	public void deleteLinea(int nroPedido, int codProducto, int cantidad) throws ApplicationException {
		PreparedStatement stmt = null;
		PreparedStatement stmtStock = null;
		try {
			FactoryConexion.getInstancia().getConnection().setAutoCommit(false);
			stmt = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"Delete from linea_pedido where codProducto=? and numero_pedido=?");
			stmt.setInt(1, codProducto);
			stmt.setInt(2, nroPedido);
			stmt.execute();
			stmtStock= FactoryConexion.getInstancia().getConnection().prepareStatement(""
					+ "update productos set stock=stock+? where codProducto=?");
			stmtStock.setInt(1, cantidad);
			stmtStock.setInt(2, codProducto);
			stmtStock.execute();
			FactoryConexion.getInstancia().getConnection().commit();
		} catch (SQLException e) {
			try {
				FactoryConexion.getInstancia().getConnection().rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Error en rollback.", e.getCause());
			}
			throw new ApplicationException("Error al eliminar linea de pedido", e.getCause());
		} finally {
				try {
					FactoryConexion.getInstancia().getConnection().setAutoCommit(true);
					if(stmt!=null) stmt.close();
					if(stmtStock!=null) stmtStock.close();
					FactoryConexion.getInstancia().releaseConnection();
				} catch (SQLException e) {
					throw new ApplicationException("Error al cerrar conexiones con la base de datos", e.getCause());
				}
		}
	}

	public void cancelarPedido(int nroPedido) throws ApplicationException {
		PreparedStatement stmtLineas = null;
		PreparedStatement stmtEstado=null;
		PreparedStatement stmtStock = null;
		ResultSet rsLineas=null;
		try {
			FactoryConexion.getInstancia().getConnection().setAutoCommit(false);
			stmtLineas = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"Select * from linea_pedido where numero_pedido=?");
			stmtLineas.setInt(1, nroPedido);
			rsLineas=stmtLineas.executeQuery();
			while(rsLineas.next()){
				int cantidad = rsLineas.getInt("cantidad");
				int codProducto = rsLineas.getInt("codProducto");
				stmtStock = FactoryConexion.getInstancia().getConnection().prepareStatement(
						"update productos set stock=stock+? where codProducto=?");
				stmtStock.setInt(1, cantidad);
				stmtStock.setInt(2, codProducto);
				stmtStock.execute();	
			}
			stmtEstado = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"update pedidos set fecha_cancelacion=current_date(),id_estado_pedido=5 where numero_pedido=?");
			stmtEstado.setInt(1, nroPedido);
			stmtEstado.execute();
			FactoryConexion.getInstancia().getConnection().commit();
		} catch (SQLException e) {
			try {
				FactoryConexion.getInstancia().getConnection().rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Error en rollback.", e.getCause());
			}
			throw new ApplicationException("Error al cancelar pedido", e.getCause());
		} finally {
				try {
					FactoryConexion.getInstancia().getConnection().setAutoCommit(true);
					if(stmtLineas!=null) stmtLineas.close();
					if(stmtStock!=null) stmtStock.close();
					if(stmtEstado!=null) stmtEstado.close();
					if(rsLineas!=null) rsLineas.close();
					FactoryConexion.getInstancia().releaseConnection();
				} catch (SQLException e) {
					throw new ApplicationException("Error al cerrar conexiones con la base de datos", e.getCause());
				}
		}
	}

	public void updateLinea(int nroPedido, int codProducto, int cantNueva) throws ApplicationException {
		PreparedStatement stmtLinea = null;
		PreparedStatement stmtSotck = null;
		PreparedStatement stmtCantidad = null;
		ResultSet rsCantidad=null;
		dataProducto dprod = new dataProducto();
		int cantVieja=0;
		try {
			FactoryConexion.getInstancia().getConnection().setAutoCommit(false);
			stmtCantidad = FactoryConexion.getInstancia().getConnection().prepareStatement(""
					+ "Select cantidad from linea_pedido where codProducto=? and numero_pedido=?");
			stmtCantidad.setInt(1, codProducto);
			stmtCantidad.setInt(2, nroPedido);
			rsCantidad = stmtCantidad.executeQuery();
			if(rsCantidad.next()){
				cantVieja= rsCantidad.getInt("cantidad");
				dprod.aumentarStock(stmtSotck, codProducto, cantVieja-cantNueva);
			}
			stmtLinea = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"update linea_pedido set cantidad=? where numero_pedido=? and codProducto=?");
			stmtLinea.setInt(1, cantNueva);
			stmtLinea.setInt(2, nroPedido);
			stmtLinea.setInt(3, codProducto);
			stmtLinea.execute();
			FactoryConexion.getInstancia().getConnection().commit();
		} catch (SQLException e) {
			try {
				FactoryConexion.getInstancia().getConnection().rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Error al modificar linea de pedido"	, e.getCause());
			}
			throw new ApplicationException("Error al modificar linea de pedido"	, e.getCause());
		} finally {
				try {
					FactoryConexion.getInstancia().getConnection().setAutoCommit(true);
					if(stmtLinea != null)stmtLinea.close();
					if(stmtSotck != null) stmtSotck.close();
					if(stmtCantidad != null) stmtCantidad.close();
					if(rsCantidad != null) rsCantidad.close();
					FactoryConexion.getInstancia().releaseConnection();
				} catch (SQLException e) {
					throw new ApplicationException("Error al cerrar conexiones con la base de datos", e.getCause());
				}
			
		}
		
	}

	public void insertLinea(int nroPedido, int codigo, int cantidad) throws ApplicationException {
		PreparedStatement stmt = null;
		PreparedStatement stmtStock = null;
		dataProducto dprod = new dataProducto();
		try {
			FactoryConexion.getInstancia().getConnection().setAutoCommit(false);
			this.insertLinea(stmt, nroPedido, codigo, cantidad);
			dprod.descontarStock(stmtStock, cantidad, codigo);
			FactoryConexion.getInstancia().getConnection().commit();
		} catch (SQLException e) {
			try {
				FactoryConexion.getInstancia().getConnection().rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Error en rollback.", e.getCause());
			};
			if (e.getErrorCode()==1062) {
				throw new ApplicationException("Ya existe ese producto en el pedido", e.getCause());
			} else throw new ApplicationException("Error al agregar línea de pedidos", e.getCause());
		} finally {
			try {
				FactoryConexion.getInstancia().getConnection().setAutoCommit(true);
				if(stmt != null)stmt.close();
				if(stmtStock != null)stmtStock.close();
				FactoryConexion.getInstancia().releaseConnection();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e.getCause());
			}	
		}
		
	}
	
	private void insertLinea(PreparedStatement stmtLineas, int nroPedido, int codigo, int cantidad)
			throws SQLException, ApplicationException {
		stmtLineas = FactoryConexion.getInstancia().getConnection().prepareStatement(
				"Insert into linea_pedido (numero_pedido,codProducto,cantidad) values (?,?,?)");
			stmtLineas.setInt(1, nroPedido);
			stmtLineas.setInt(2, codigo);
			stmtLineas.setInt(3, cantidad);
			stmtLineas.execute();
	}


	public void actualizarEstadoPedido(int numPed, String estado) throws ApplicationException{
		PreparedStatement stmtEstado =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmtEstado = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"select * from estado_pedido where descripcion_estado=?");
		stmtEstado.setString(1, estado);
		rs=stmtEstado.executeQuery();
		if(rs.next()){
		stmt=FactoryConexion.getInstancia().getConnection().prepareStatement(
				 "update pedidos set id_estado_pedido=? where numero_pedido=?");
		stmt.setInt(1, rs.getInt("id_estado_pedido"));
		stmt.setInt(2, numPed);
		stmt.execute();
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(stmtEstado!=null) stmtEstado.close();
				if(stmt!=null) stmt.close();
				if(rs!=null) rs.close();
				FactoryConexion.getInstancia().getConnection().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
			
		}
	}
}
