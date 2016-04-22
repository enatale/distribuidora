package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import appExceptions.ApplicationException;
import entidades.Cliente;
import entidades.Estado_pedido;
import entidades.Linea_pedido;
import entidades.Pedidos;
import entidades.Producto;
import ui.Productos;


public class dataPedidos {

	public void registrarPedido(Pedidos pedido, int dni) throws ApplicationException {
		PreparedStatement stmtPedido = null;
		PreparedStatement stmtLineas = null;
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
			stmtLineas = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"Insert into linea_pedido (numero_pedido,codProducto,cantidad) values (?,?,?)");
			stmtLineas.setInt(1, pedido.getNumero_pedido());
			for (Linea_pedido lp : pedido.getLineas()) {
				stmtLineas.setInt(2, lp.getProducto().getCodProducto());
				stmtLineas.setInt(3, lp.getCantidad());
				stmtLineas.execute();
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

	/*public ArrayList<Pedidos> getPedidosPendientes(Cliente cliente) throws ApplicationException {
		ArrayList<Pedidos> pedidos = new ArrayList<Pedidos>();
		Pedidos ped;
		dataProducto dprod = new dataProducto();
		PreparedStatement stmtPedido = null;
		PreparedStatement stmtLineas = null;
		ResultSet rs = null;
		try {
			FactoryConexion.getInstancia().getConnection().setAutoCommit(false);
			//fecha_pedido id_estado_pedido dni
			stmtPedido = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"Select * from pedidos where dni=? and id_estado_pedido=1");
			stmtPedido.setInt(1, cliente.getDni());
			rs=stmtPedido.executeQuery();
			while(rs.next()){
				ped=new Pedidos();
				ped.setCliente(cliente);
				ped.setEstado(new Estado_pedido(1, "pendiente"));
				ped.setFecha_cancelacion(rs.getDate("fecha_cancelacion"));
				ped.setFecha_pedido(rs.getDate("fecha_pedido"));
				ped.setNumero_pedido(rs.getInt("numero_pedido"));
				pedidos.add(ped);
			}
			rs.close();
			stmtLineas = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"select * from linea_pedido where numero_pedido=?");
			for (Pedidos pedido : pedidos) {
				ArrayList<Linea_pedido> lineas = new ArrayList<Linea_pedido>();
				stmtLineas.setInt(1, pedido.getNumero_pedido());
				rs=stmtLineas.executeQuery();
				while(rs.next()){
					
					int cantidad= rs.getInt("cantidad");
					Producto prod = dprod.getByCodigo(rs.getInt("codProducto"));
					Linea_pedido lp = new Linea_pedido(prod, cantidad);
					lineas.add(lp);
				}
				pedido.setLineas(lineas);
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
		return pedidos;
	}*/

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
			//TODO lalalal
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
}
