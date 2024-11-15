package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_FormadePagoEfectivo {

    @FXML
    private Button btn_efectivo;

    @FXML
    private Button btn_finalizarCompra;

    @FXML
    private Button btn_tarjeta;

    @FXML
    private Label txt_cambio;

    @FXML
    private Label txt_total_pagar;

    @FXML
    void Cambio_vistaTarjeta(ActionEvent event) {
    	try {
			Pane root = FXMLLoader.load(this.getClass().getResource("FormadePago.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Stage currentStage = (Stage) txt_cambio.getScene().getWindow();
		currentStage.close();

    }

}

