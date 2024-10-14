package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class controladorInscripcion {

    @FXML
    private AnchorPane boton1;

    @FXML
    private Button btnInsTEsc;

    @FXML
    private Button btnInscCLec;

    @FXML
    private Button btnInscTDib;

    @FXML
    void InscripcionClubdeLectura(ActionEvent event) throws IOException {
    	Stage stage= new Stage();
    	System.out.println(getClass().getResource("boton1.fxml"));
    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("boton1.fxml"));
    	Scene scene = new Scene(root);
    	stage.setTitle ("Incripcion");
    	stage.setScene(scene);
    	stage.show();
    	
    	
    	

    }

    @FXML
    void InscripcionTallerdeDIbujo(ActionEvent event) {

    }

    @FXML
    void InscripcionTallerdeLectura(ActionEvent event) {

    }

}


