/*
 * @(#)PrincipalControlle.class		1.0 14/10/2024
 * 
 * No entiendo el Copyright.
 */
package controladores;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * Esta clase es el controlador de la interfaz Principal
 * contiene los botones: Politicas, Devolución y Historial.
 * Al pulsar cualquiera va arrojar la interfaz deseada.
 * 
 * @version		1.0 14/10/2024
 * @author		Picazo Reyes Ulises Nioolas
 */
public class PrincipalController {
	/*Controla la elección en los hbox tomados como botones*/
	
    @FXML
    private HBox hbDevolucion;

    @FXML
    private HBox hbHistorial;

    @FXML
    private HBox hbPolitica;
    
    @FXML
    private SplitPane spPrincipal;			//Necesario para poner la interfaz

    /**
     * Cuando el evento caé en este metodo se arroja la
     * interfaz Historial.fxml
     * @param event
     */
    @FXML
    void mostrarHistorial(MouseEvent event) {
    	colocarInterfaz("/fxml/Historial.fxml");
    }

    /**
     * Cuando el evento caé en este metodo se arroja la
     * interfaz Politicas.fxml
     * @param event
     */
    @FXML
    void mostrarPolitica(MouseEvent event) {
    	colocarInterfaz("/fxml/Politicas.fxml");
    }
    
    /**
     * Cuando el evento caé en este metodo se arroja la
     * interfaz Devoluciones.fxml
     * @param event
     */
    @FXML
    void registrarDevolucion(MouseEvent event) {
    	colocarInterfaz("/fxml/Devolucion.fxml");
    }
    
    /**
     * Método auxiliar que ayuda a mandar las interfaces deseadas.
     */
    void colocarInterfaz(String archivo) {
    	try {
    		FXMLLoader loader = new FXMLLoader(this.getClass().getResource(archivo));
    		AnchorPane panel = loader.load();
    		AnchorPane panelSuperior = (AnchorPane)spPrincipal.getItems().get(0);
    		panelSuperior.getChildren().clear();
    		panelSuperior.getChildren().add(panel);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

}
