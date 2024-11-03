/*
 * @(#)Main.class		1.0 14/10/2024
 * 
 * No entiendo el Copyright.
 */
package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Esta clase es la que llama al principal para poder mostrar
 * las distintas ventanas emerjentes. Solo muestra el titulo y las selecciones
 * posible.
 * 
 * @version		1.0 14/10/2024
 * @author		Picazo Reyes Ulises Nioolas
 */
public class Main extends Application {
	
	/**
	 * Este método permite abrir las interfaces del SceneBulder.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/Principal.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
