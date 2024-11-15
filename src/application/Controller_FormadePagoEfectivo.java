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
	        // Carga el archivo FXML de la nueva vista
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormadePago.fxml"));
	        Pane root = loader.load();

	        // Obtén el controlador de la nueva vista
	        Controller_FormadePagoTarjeta controladorDestino = loader.getController();

	        // Pasa el dato del total al controlador de la nueva vista
	        float total = Float.parseFloat(txt_total_pagar.getText());
	        controladorDestino.setTotal(total);

	        // Cambia de escena
	        Scene scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();

	        // Cierra la ventana actual
	        Stage currentStage = (Stage) txt_cambio.getScene().getWindow();
	        currentStage.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

    }
    
    public void setTotal(float total) {
        txt_total_pagar.setText(String.format("%.2f", total));
    }

}

