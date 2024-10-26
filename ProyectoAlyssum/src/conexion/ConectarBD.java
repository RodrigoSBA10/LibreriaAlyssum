package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConectarBD {//inicia clase
	public Connection cn;
	public ResultSet rs;
	public Statement stmt;
	
	public Connection ConectarBDOracle() {//inicia metodo
		try {//inicia try
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			cn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","PAlyssum","uacm123");
		    stmt=cn.createStatement();
		    System.out.println("Conexion exitosa a bd");
		} catch (SQLException e) {
			Alert alerta= new Alert(AlertType.ERROR);
			alerta.setTitle("ERROR");
			alerta.setContentText("No se pudo conectar a la base de datos");
			alerta.show();
			
		}//termina catch
		return cn;
	}
	
	public void cerrarConexion() {
        if (cn != null) {
            try {
                cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }//termina catch
        }//termina if
    }//termina metodo

}//termina clase
 