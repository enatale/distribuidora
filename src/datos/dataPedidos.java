package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import appExceptions.ApplicationException;
import entidades.Linea_pedido;
import entidades.Pedidos;


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
			//TODO LALALAL
			e.printStackTrace();
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
}
