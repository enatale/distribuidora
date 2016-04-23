package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import appExceptions.ApplicationException;
import entidades.Cliente;
import entidades.Empleado;
import entidades.Estado_cliente;
import entidades.Persona;
import entidades.Producto;

public class dataPersona {
	
	public Persona getByUsuario(String usuario, String contraseña) throws ApplicationException{
		Persona p= null;
		ResultSet rs=null;
		ResultSet rsEstado=null;
		PreparedStatement stmt=null;
		PreparedStatement stmtEstado = null;
		try {
			FactoryConexion.getInstancia().getConnection().setAutoCommit(false);
			stmt = FactoryConexion.getInstancia().getConnection().prepareStatement("Select * from personas where usuario=? and contraseña=?");
			stmt.setString(1, usuario);
			stmt.setString(2, contraseña);
			rs = stmt.executeQuery();
			if(rs.next()){
				rs.getInt("legajo");
				if(rs.wasNull()){
					Cliente c = new Cliente();
					c.setApellido(rs.getString("apellido"));
					c.setDireccion(rs.getString("direccion"));
					c.setDni(rs.getInt("dni"));
					c.setMail(rs.getString("mail"));
					c.setNombre(rs.getString("nombre"));
					c.setTelefono(rs.getInt("telefono"));
					c.setUsuario(usuario);
					c.setCodCliente(rs.getInt("codCliente"));
					c.setCUIT(rs.getInt("CUIT"));
					stmtEstado= FactoryConexion.getInstancia().getConnection().prepareStatement(
							"Select * from estado_cliente where id_estado_cliente=?");
					stmtEstado.setInt(1, rs.getInt("id_estado_cliente"));
					rsEstado = stmtEstado.executeQuery();
					if(rsEstado.next()){
						Estado_cliente ec = new Estado_cliente(rsEstado.getInt("id_estado_cliente"),rsEstado.getString("descripcion_estado"));
						c.setEstado(ec);
					}
					
					p=c;
				} else {
					Empleado e = new Empleado();
					e.setApellido(rs.getString("apellido"));
					e.setDireccion(rs.getString("direccion"));
					e.setDni(rs.getInt("dni"));
					e.setMail(rs.getString("mail"));
					e.setNombre(rs.getString("nombre"));
					e.setTelefono(rs.getInt("telefono"));
					e.setUsuario(usuario);
					e.setLegajo(rs.getInt("legajo"));
					p=e;
				}
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al recuperar usuario de la base de datos", e.getCause());
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(rsEstado!=null)rsEstado.close();
				if(stmt!=null) stmt.close();
				if(stmtEstado!=null) stmt.close();
				FactoryConexion.getInstancia().getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				throw new ApplicationException("Error al liberar recursos de la base de datos", e.getCause());
			}
			FactoryConexion.getInstancia().releaseConnection();
			
		}
		return p;
		
		
	}

	public Persona getByDni(int dni) throws ApplicationException {
		Persona p= null;
		ResultSet rs=null;
		ResultSet rsEstado=null;
		PreparedStatement stmt=null;
		PreparedStatement stmtEstado = null;
		try {
			FactoryConexion.getInstancia().getConnection().setAutoCommit(false);
			stmt = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"Select * from personas where dni=?");
			stmt.setInt(1, dni);
			rs = stmt.executeQuery();
			if(rs.next()){
				rs.getInt("legajo");
				if(rs.wasNull()){
					Cliente c = new Cliente();
					c.setApellido(rs.getString("apellido"));
					c.setDireccion(rs.getString("direccion"));
					c.setDni(rs.getInt("dni"));
					c.setMail(rs.getString("mail"));
					c.setNombre(rs.getString("nombre"));
					c.setTelefono(rs.getInt("telefono"));
					c.setUsuario(rs.getString("usuario"));
					c.setCodCliente(rs.getInt("codCliente"));
					c.setCUIT(rs.getInt("CUIT"));
					stmtEstado= FactoryConexion.getInstancia().getConnection().prepareStatement(
							"Select * from estado_cliente where id_estado_cliente=?");
					stmtEstado.setInt(1, rs.getInt("id_estado_cliente"));
					rsEstado = stmtEstado.executeQuery();
					if(rsEstado.next()){
						Estado_cliente ec = new Estado_cliente(rsEstado.getInt("id_estado_cliente"),rsEstado.getString("descripcion_estado"));
						c.setEstado(ec);
					}
					
					p=c;
				} else {
					Empleado e = new Empleado();
					e.setApellido(rs.getString("apellido"));
					e.setDireccion(rs.getString("direccion"));
					e.setDni(rs.getInt("dni"));
					e.setMail(rs.getString("mail"));
					e.setNombre(rs.getString("nombre"));
					e.setTelefono(rs.getInt("telefono"));
					e.setUsuario(rs.getString("usuario"));
					e.setLegajo(rs.getInt("legajo"));
					p=e;
				}
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al recuperar usuario de la base de datos", e.getCause());
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(rsEstado!=null)rsEstado.close();
				if(stmt!=null) stmt.close();
				if(stmtEstado!=null) stmt.close();
				FactoryConexion.getInstancia().getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				throw new ApplicationException("Error al liberar recursos de la base de datos", e.getCause());
			}
			FactoryConexion.getInstancia().releaseConnection();
			
		}
		return p;
	}
	
	public ArrayList<Cliente> getCliListadoConfirmar() throws ApplicationException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Cliente cli=null;
		ArrayList<Cliente> CliPendiente = new ArrayList<Cliente>();
		try {
			stmt = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"Select personas.dni,personas.nombre, personas.apellido,personas.mail,personas.CUIT"
					+ " from personas"
					+ " inner join estado_cliente"
					+ " on personas.id_estado_cliente=estado_cliente.id_estado_cliente "
					+ " where estado_cliente.descripcion_estado=?");
			stmt.setString(1, "Pendiente");
			rs=stmt.executeQuery();
			while(rs.next()){
				cli = new Cliente();
				cli.setDni(rs.getInt("dni"));
				cli.setNombre(rs.getString("nombre"));
				cli.setApellido(rs.getString("apellido"));
				cli.setMail(rs.getString("mail"));
				cli.setCUIT(rs.getInt("CUIT"));
				CliPendiente.add(cli);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(stmt!=null) stmt.close();
				if(rs!=null) rs.close();
				FactoryConexion.getInstancia().getConnection().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
		}
		
		return CliPendiente;
	}

	public void actualizarEstadoCliente(String estado, int dni) throws ApplicationException{
		PreparedStatement stmtBuscarEstado =null;
		PreparedStatement stmtActualizarEstado = null;
		int id_estado=0;
		ResultSet rs = null;
		
		try {
			stmtBuscarEstado = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"select id_estado_cliente from estado_cliente where descripcion_estado=?");

			stmtBuscarEstado.setString(1, estado);
			rs=stmtBuscarEstado.executeQuery();
			if(rs.next()){
			stmtActualizarEstado = FactoryConexion.getInstancia().getConnection().prepareStatement(
					"update personas set personas.id_estado_cliente=? where personas.dni=?");
			stmtActualizarEstado.setInt(1 ,rs.getInt("id_estado_cliente"));
			stmtActualizarEstado.setInt(2, dni);
			stmtActualizarEstado.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(stmtBuscarEstado!=null) stmtBuscarEstado.close();
				if(rs!=null) rs.close();
				if(stmtActualizarEstado!=null) stmtActualizarEstado.close();
				FactoryConexion.getInstancia().getConnection().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
			
		}
	}
}
