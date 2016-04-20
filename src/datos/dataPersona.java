package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import appExceptions.ApplicationException;
import entidades.Cliente;
import entidades.Empleado;
import entidades.Estado_cliente;
import entidades.Persona;

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
			//TODO borrar e.print..
			e.printStackTrace();
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
}
