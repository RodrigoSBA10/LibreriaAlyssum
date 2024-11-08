/*
 * Clase para especificar las acciones de la ventana
 * Agregar Reseña
 * 
 * @autor Aurora Morales
 * 
 * Version: 9
 * */

package controladores;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.Libro;
import modelo.Reseña;

/*
 * Inicio de metodo ControllerAgregarReseña
 * 
 * */
public class ControllerAgregarReseña {

  @FXML
  private Button agregar;  //Boton para confirmar y guardar

  @FXML
  private Button btn1, btn2, btn3, btn4, btn5; //Botones para selecionar la calificacion

  @FXML
  private Button cancelar; //Boton para cancelar toda la accion

  @FXML
  private TextArea textArea; //Espacio de texto para escribir la reseña
  
  @FXML
  private Button agregarLiga;
  
  @FXML
  private ImageView imageView; // Para mostrar la imagen seleccionada
  
  @FXML
  private MediaView mediaView;  // Para mostrar el video
  
  public List<Reseña> lista;
  
  int valoracion = 0; //Variable para 
  
  private Libro lib = new Libro();
  
  @FXML
  void ClickButton1(ActionEvent event) {
	  btn1.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
	  valoracion = 1;
  }
  
  @FXML
  void ClickButton2(ActionEvent event) {
	  btn1.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
	  btn2.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
	  valoracion = 2;
  }
    
  @FXML
  void ClickButton3(ActionEvent event) {
    btn3.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn2.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn1.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    valoracion = 3;
  }
    
  @FXML
  void ClickButton4(ActionEvent event) {
    btn4.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn3.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn2.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn1.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    valoracion = 4;
  }
    
  @FXML
  void ClickButton5(ActionEvent event) {
    btn5.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn4.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn3.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn2.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn1.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    valoracion = 5;
  }
    
  @FXML
  void agregarReseña(ActionEvent event) {
	  //Muestra un error si no hay contenido en la descripcion
	  if(textArea.getText() == null) {
		  Alert alert = new Alert(AlertType.ERROR, "Debe ingresar el contenido de la reseña");
	      alert.showAndWait();
	  }
	  
      Reseña res = new Reseña( valoracion, textArea.getText());
      guardarEnBaseDeDatos(res);
      
      Alert alert = new Alert(AlertType.CONFIRMATION, "Su reseña fue guardada");
      alert.showAndWait();
      System.out.println(res.toString());
      lib.setNumeroReseña(1);
      ControllerReseñaLibro control =new ControllerReseñaLibro();
      control.agregar(res);
      
      //Cerrar ventana 
      Node source = (Node)event.getSource();
      Stage stage = (Stage)source.getScene().getWindow();
      stage.close();

  }
  
  private void guardarEnBaseDeDatos(Reseña res) {
	    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
		String usuario = "System";
		String contraseña = "aurelio666";
	    
	    String sql = "INSERT INTO reseñas (ID_LIBRO, VALORACION, DESCRIPCION) VALUES (?, ?, ?)";
	    
	    try (Connection connection = DriverManager.getConnection(url, usuario, contraseña);
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	      
	      statement.setInt(1, res.getValoracion());
	      statement.setString(2, res.getDescripcion());
	      
	      int filasInsertadas = statement.executeUpdate();
	      if (filasInsertadas > 0) {
	          System.out.println("La reseña fue agregada correctamente a la base de datos.");
	      }
	    } catch (SQLException e) {
	        System.err.println("Error al insertar la reseña en la base de datos.");
	        e.printStackTrace();
	    }
	  }
  
  /*Metodo para agregar videos o fotos
   * */
  @FXML
  void agregarMultimedia(ActionEvent event) {
	  FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Seleccionar archivo multimedia");
	    fileChooser.getExtensionFilters().addAll(
	    		new FileChooser.ExtensionFilter("Archivos Multimedia", "*.png", "*.jpg", "*.jpeg",
	    				"*.mp4", "*.avi", "*.mov", "*.mkv", "*.flv", "*.wmv"),
	    	    new FileChooser.ExtensionFilter("Todos los Archivos", "*.*")
	    );

	    File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
	    if (file != null) {
	        String filePath = file.toURI().toString();
	        
	        if (filePath.endsWith(".png") || filePath.endsWith(".jpg") || filePath.endsWith(".jpeg")) {
	            // Cargar imagen en ImageView y agregar referencia en TextArea
	            Image image = new Image(filePath);
	            imageView.setImage(image);
	            mediaView.setMediaPlayer(null); // Limpiar MediaView si se cargó una imagen
	            textArea.appendText("Imagen adjunta: " + file.getName() + "\n");
	        } else if (filePath.endsWith(".mp4") || filePath.endsWith(".avi") || 
	        		filePath.endsWith(".mov") || filePath.endsWith(".mkv")
	        		|| filePath.endsWith(".flv")) {
	            // Cargar video en MediaView y agregar referencia en TextArea
	            Media media = new Media(filePath);
	            MediaPlayer mediaPlayer = new MediaPlayer(media);
	            mediaView.setMediaPlayer(mediaPlayer);
	            mediaPlayer.play(); // Opcional: empezar a reproducir el video
	            imageView.setImage(null); // Limpiar ImageView si se cargó un video
	            textArea.appendText("Video adjunto: " + file.getName() + "\n");
	        }
	    }
  }
  
  @FXML
  void regresarBoton(ActionEvent event) {
	Node source = (Node)event.getSource();
  	Stage stage = (Stage)source.getScene().getWindow();
  	stage.close();
  }
}