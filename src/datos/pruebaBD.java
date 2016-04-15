package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class pruebaBD {

	public static void main(String[] args) {
		try {
			Statement stmt = FactoryConexion.getInstancia().getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from estado_cliente");
			if(rs.next()){
				System.out.println(rs.getInt("idEstado"));
				System.out.println(rs.getString("descripcion_estado"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
