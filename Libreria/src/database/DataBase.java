package database;

import java.sql.*;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import modelo.Devolucion;
import modelo.Motivo;
import modelo.Ticket;

public class DataBase {
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String usuario = "SYSTEM";
	private static final String password = "Mayor28bases";

	// Método para obtener la conexión
	public static Connection getConexion() {
		Connection conn = null;
		try {
			// Cargar el controlador JDBC de oracle
			Class.forName("oracle.jdbc.OracleDriver");
			// Establecer la conexión
			conn = DriverManager.getConnection(url, usuario, password);
			System.out.println("Conexión Exitosa.");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador JDBC: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error al establecer la conexión: " + e.getMessage());
		}
		return conn;
	}

	// Método para cerrar la conexión
	public static void closeConexion(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Conexión cerrada correctamente.");
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e.getMessage());
			}
		}
	}

	// Método para obtener los elementos de la tabla
	public static ObservableList<Devolucion> getElements() {
		ObservableList<Devolucion> lista = FXCollections.observableArrayList();
		Connection conn = getConexion();
		String query = "SELECT * FROM DEVOLUCION";
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yy");
		try {
			Statement declaracion = conn.createStatement();
			ResultSet resultD = declaracion.executeQuery(query);

			while (resultD.next()) {
				Devolucion datos = new Devolucion(
						resultD.getInt(1),
						resultD.getInt(2),
						resultD.getString(3),
						resultD.getString(4),
						resultD.getDate(5),
						formato.format(resultD.getDate(5)),
						resultD.getString(6),
						resultD.getString(7),
						resultD.getString(8));
				lista.add(datos);
			}
			closeConexion(conn);
		} catch (SQLException e) {
			System.out.println("Error en obtener elementos: " + e.getMessage());
			closeConexion(conn);
		}
		return lista;
	}

	// Método que registra una devolución y actualiza la cantidad del libro que se regreso dependiendo del cambio
	public static void registrarDevolucion(int numero, String fecha, String motivo, String detalles, String cambio) {
		
		Connection conn = getConexion();
		Alert alert;
		if (conn != null) {
			String query = "INSERT INTO DEVOLUCION (IDDEVOLUCION, NUMERO, NOMBRE, TITULO,"
                    + " FECHA, MOTIVO, DETALLES, CAMBIO) VALUES (SEQ_DEVOLUCION_ID.NEXTVAL, "
                    + "(SELECT NUMERO FROM TICKET WHERE NUMERO = ?), "
                    + "(SELECT NOMBRE || ' ' || APELLIDO FROM CLIENTE C JOIN "
                    + "TICKET T ON C.IDCLIENTE = T.IDCLIENTE WHERE T.NUMERO = ?), "
                    + "(SELECT TITULO FROM TICKET T JOIN LIBRO L ON T.IDLIBRO = L.IDLIBRO WHERE T.NUMERO = ?), "
                    + "TO_DATE(?, 'DD-MM-YY'), ?, ?, ?)";
			String actualizar = "UPDATE LIBRO SET CANTIDAD = CANTIDAD + 1 WHERE TITULO = (SELECT TITULO FROM "
					+ "TICKET T JOIN LIBRO L ON T.IDLIBRO = L.IDLIBRO WHERE T.NUMERO = ?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(query);
	            pstmt.setInt(1, numero);
	            pstmt.setInt(2, numero);
	            pstmt.setInt(3, numero);
	            pstmt.setString(4, fecha); // Aquí se pasa la fecha en el formato deseado
	            pstmt.setString(5, motivo);
	            pstmt.setString(6, detalles);
	            pstmt.setString(7, cambio);

				int exitoFila = pstmt.executeUpdate();
				
				if(!cambio.equalsIgnoreCase("NO")) {
					PreparedStatement act = conn.prepareStatement(actualizar);
					act.setInt(1, numero);
					act.executeUpdate();
				}
				
				closeConexion(conn);
				alert = new Alert(Alert.AlertType.INFORMATION, "Se registró " + exitoFila + " devolución.");
				alert.show();
			} catch (SQLException e) {
				System.out.println("Ocurrió un error: " + e.getMessage());
				closeConexion(conn);
			}
		} else {
			alert = new Alert(AlertType.ERROR, "Error al concetarse con la base de datos.");
			alert.show();
			closeConexion(conn);
		}
	}
	
	// Método para obtener los elementos de la tabla Ticket
	public static ObservableList<Ticket> getElementsTicket() {
			ObservableList<Ticket> lista = FXCollections.observableArrayList();
			Connection conn = getConexion();
			String query = "SELECT * FROM TICKET";
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yy");
			try {
				Statement declaracion = conn.createStatement();
				ResultSet resultD = declaracion.executeQuery(query);

				while (resultD.next()) {
					Ticket datos = new Ticket(
							resultD.getInt(1),
							resultD.getInt(2),
							resultD.getInt(3),
							resultD.getInt(4),
							resultD.getInt(5),
							formato.format(resultD.getDate(6)),
							resultD.getDouble(7));
					lista.add(datos);
				}
				closeConexion(conn);
			} catch (SQLException e) {
				System.out.println("Error en obtener elementos: " + e.getMessage());
				closeConexion(conn);
			}
			return lista;
		}
	
	// Método para extraer los motivos de la DataBase
	public static ObservableList<Motivo> getMotivos(){
		ObservableList<Motivo> lista = FXCollections.observableArrayList();
		Connection conn = getConexion();
		String query = "SELECT * FROM MOTIVO";
		
		try {
			Statement declaracion = conn.createStatement();
			ResultSet resultD = declaracion.executeQuery(query);

			while (resultD.next()) {
				Motivo datos = new Motivo(
						resultD.getInt(1),
						resultD.getString(2)
						);
				lista.add(datos);
			}
			closeConexion(conn);
		} catch (SQLException e) {
			System.out.println("Error en obtener elementos: " + e.getMessage());
			closeConexion(conn);
		}
		
		return lista;
	}
	
	// Método que elimina una Devolución
	public static void eliminarDevolucion(Devolucion devolucion) {
		int numero = devolucion.getId();
		String query = "DELETE FROM DEVOLUCION WHERE IDDEVOLUCION = ?";
		Alert alert;
		Connection conn = getConexion();
		
		try {
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, numero);
			int fAfectada = pstm.executeUpdate();
			
			if(fAfectada > 0) {
				alert = new Alert(Alert.AlertType.INFORMATION, "Ticket: " + numero + " eliminado");
				alert.show();
			} else {
				alert = new Alert(Alert.AlertType.ERROR, "Ticket: " + numero + " no encontrado");
				alert.show();
			}
			
			closeConexion(conn);
		} catch (SQLException e) {
			System.out.println("ERROR al eliminar la Devolución: " + e.getMessage());
			closeConexion(conn);
		}
	}
}
