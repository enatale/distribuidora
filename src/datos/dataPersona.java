package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entidades.Cliente;
import entidades.Empleado;
import entidades.Persona;

public class dataPersona {
	public Persona getByUsuario(String usuario, String contraseña){
		Persona p= null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		try {
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
					//TODO VER ESTADO DEL CLIENTE
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null) stmt.close();
				try {
					FactoryConexion.getInstancia().releaseConnection();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					// con la excepciones personalizadas vuela
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return p;
		
		
	}
}
