package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDBConnection {

	public static void main(String[] args) {

		// URL de conexión para la base de datos Oracle
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // Cambia esto según tu configuración
		String username = "SYSTEM"; // Cambia por tu nombre de usuario
		String password = "Admin-2812"; // Cambia por tu contraseña

		try {
			// Cargar el controlador JDBC
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establecer la conexión
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			if (connection != null) {
				System.out.println("Conexión exitosa a la base de datos Oracle!");
			} else {
				System.out.println("Fallo en la conexión a la base de datos Oracle.");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("No se encontró el controlador JDBC.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al establecer la conexión.");
			e.printStackTrace();
		}
	}
}
