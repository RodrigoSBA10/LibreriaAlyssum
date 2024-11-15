package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_FormadePagoTarjeta {
	
	@FXML
	private Button btn_efectivo;

    @FXML
    private Button btn_tarjeta;

    @FXML
    private ChoiceBox<String> choicebox_1;

    @FXML
    private ChoiceBox<Integer> choicebox_2;

    @FXML
    private PasswordField txtf_codigoseguridad;

    @FXML
    private TextField txtf_numerotarjeta;

    @FXML
    private TextField txtf_titular;
    
    void meses(){
    	for (int mes = 1; mes <= 12; mes++) {
            choicebox_1.getItems().add(String.format("%02d", mes)); // Añade 01, 02, ..., 12
        }	
    }
    void años() {
    	int anioActual = java.time.Year.now().getValue();
        for (int anio = anioActual; anio <= anioActual + 10; anio++) {
            choicebox_2.getItems().add(anio);
        }

    }
    
    @FXML
    public void initialize() {
    	meses();
    	años();
    }
    @FXML
    void Cambio_vistaEfectivo(ActionEvent event) {
    	try {
			Pane root = FXMLLoader.load(this.getClass().getResource("FormadePagoEfectivo.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Stage currentStage = (Stage) txtf_codigoseguridad.getScene().getWindow();
		currentStage.close();


    }

}
