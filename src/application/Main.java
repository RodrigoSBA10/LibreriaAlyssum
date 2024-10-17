package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * @author Jesus Alfredo
 * Este es el metodo inicial de la aplicaccion
 */
public class Main extends Application {
/**
 * @author Jesus Alfredo
 * Este es el metodo que prepara la ecena
 * @param primaryStage es un ecenario
 */
public void start(Stage primaryStage) {
try {
	        Pane root = FXMLLoader.load(this.getClass().getResource("Sistema_vista.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
/**
 * @author Jesus Alfredo
 * Este es el metodo main de la aplicaccion
 * @param args son los argumentos
 */
	public static void main(String[] args) {
		launch(args);
	}
}
