/*
 * Clase main para mandas a llamar la primera ventana
 * 
 * Fecha: 28/09/2024
 * 
 * @autor Aurora Morales
 * 
 * Version: 3
 * */

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
* Metodo main con la extencion Appication
* 
* @param primaryStage paramtro del argumento
* */

public class Main extends Application {
  @Override
public void start(Stage primaryStage) {
    try {
      AnchorPane root = FXMLLoader.load(this.getClass().getResource("/fxml/ValoracionLibro.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
      primaryStage.setTitle("Favoritos");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
  * Metodo para 
  * @param args algumento
  * 
  * @return no regresa nada
  * */
  public static void main(String[] args) {
    launch(args);
  }
}