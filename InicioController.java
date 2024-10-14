
/**
 * Controlador para la pantalla Inicio.
 * 
 * Esta clase maneja la lógica de la interfaz de usuario para permitir a los
 * usuarios visualizar un banner con los libros mas vendidos e ingresar a a lista de libros
 * 
 * Autor: Rodrigo Slavador
 * Fecha: 12 de octubre de 2024
 */

package application;


import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class InicioController {
	//Vista de imagen para mostrar el banner
    @FXML
    private ImageView bannerImagenes;
    // Vista de imagen para mostrar el banner 2
    @FXML
    private ImageView banner2;
    
    //Lista de imagenes que se mostraran en el banner
private List<Image> imagenesBanner = List.of(
    new Image(getClass().getResourceAsStream("/images/libro1.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro2.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro3.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro4.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro5.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro6.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro7.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro8.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro9.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro10.jpg"))
);
	
	// Indice de la imagen actual que se muestra en el banner
    private int indiceImagen=0;
    
    private int indice2=5;
    
    private int tiempo=5; // Tiempo en segundos para la rotacion
    
    /**
     * Metodo que se llama al inicilaizar al controlador
     * Este metodo configura el banner y comienza la rotacion de imagenes
     */
    @FXML
    private void initialize() {
    	cargarBanner(); // Cargar la primera imagen del banner
    	iniciarRotacion(); // Iniciar la rotacion de imagenes del banner
    }
    
    /*
     * Cargar la imagen actual del banner en el imageView
     */
    private void cargarBanner() {
    	bannerImagenes.setImage(imagenesBanner.get(indiceImagen)); // Establecer la imagen del banner
    	banner2.setImage(imagenesBanner.get(indice2));
	}
    
    /**
     * Inicia la rotacion de las imagenes del banner
     * Las imagenes cambian cada tiempo en segundos
     */
  private void iniciarRotacion() {
    	Timeline timeline=new Timeline( 
    	new KeyFrame(Duration.seconds ( tiempo ), event -> cambiarBanner())); // Cambiar la imagen del banner cada tiempo en segundos
		timeline.setCycleCount(Timeline.INDEFINITE); //Repetir indefinidamente
		timeline.play(); // Iniciar la animacion
	}
    
    /**
     * Cambia la imagen del banner a la siguiente en la lista
     * si se llega al final de la lista, vuelve al inicio.
     */
    private void cambiarBanner() {
    	indiceImagen=(indiceImagen + 1)%imagenesBanner.size();// Actualizar el indice de la imagen
    	indice2=(indice2+1)%imagenesBanner.size();
		cargarBanner(); // Cargar la nueva imagen del banner
	}
    /**
     * Manejo el evento de mostrar la lista de libros
     * Este metodo se puede conectar a un boton eb la interfaz de usuario
     * 
     * @param event El evento de accion que activa este metodo
     */
    @FXML
    private void mostrarListaLibros(final ActionEvent event) {
    	Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    	try {
			Stage primStage= new Stage();
			VBox root= (VBox)FXMLLoader.load(getClass().getResource("/fxml/ListaLibros.fxml"));
			Scene scene=new Scene(root);
			primStage.setScene(scene);
			primStage.show();
			stage.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}
