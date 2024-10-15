/**
 * Clase principal de la aplicación de librería.
 * Esta clase se encarga de iniciar la aplicación JavaFX, cargar la interfaz de usuario
 * desde un archivo FXML y mostrar la ventana principal.
 */
package application;

	import javafx.fxml.FXMLLoader;	
	import javafx.application.Application;
	import javafx.scene.Scene;
	import javafx.scene.layout.StackPane;
	import javafx.stage.Stage;
public class Main extends Application { //Clase Principal de la aplicacion
    private static final int alto = 800; 	// Alto de la ventana
    private static final int ancho = 600; // Ancho de la ventana
    /** Metodo que se llama al iniciar la aplicacion.
     * @param primaryStage el escenario pricipal de la aplicacion.
     */
	@Override
    public final void start(final Stage primaryStage) {
		try {
			//Cargar el archivo FXML
			StackPane root = FXMLLoader.load(getClass().getResource("/fxml/Inicio.fxml"));
      Scene scene = new Scene(root , alto , ancho ) ; // Crear la escne con el panel raiz y stableceer su tamaño
			scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
			primaryStage.setScene(scene); //Configurar el escenario y mostrar la ventana
			primaryStage.setTitle("Bienvenidos"); //Titulo de la ventana
			primaryStage.show(); //Muestra la ventana
		} catch (Exception e) {
			e.printStackTrace(); //Imprimir si ocurre un erro o una excepcion
		}
	}
	/**
	 * Metodo principal que inicia la aplicacion.
	 * @param args argumentos de linea de comandos.
	 */
	public static void main(final String[] args) {
		launch(args); // llamar al metodo launch para iniciar la aplicaion
	}
}
