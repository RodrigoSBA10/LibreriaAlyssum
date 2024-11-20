/*
 * Clase para especificar las acciones de la ventana
 * Agregar Reseña
 * 
 * @autor Aurora Morales
 * 
 * Version: 21
 * */

package controladores;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.Libro;
import modelo.Reseña;

/**
 * Controla las reseñas y las agrega a la base de datos.
 */
public class ControllerAgregarReseña {

  @FXML
  private Button agregar;  //Boton para confirmar y guardar

  @FXML
  private Button btn1;  //Botones para selecionar la calificacion
  
  @FXML
  private Button btn2;  //Botones para selecionar la calificacion
  
  @FXML
  private Button btn3;  //Botones para selecionar la calificacion
  
  @FXML
  private Button btn4;  //Botones para selecionar la calificacion
  
  @FXML
  private Button btn5; //Botones para selecionar la calificacion

  @FXML
  private Button cancelar; //Boton para cancelar toda la accion

  @FXML
  private TextArea textArea; //Espacio de texto para escribir la reseña
  
  @FXML
  private ImageView imageView; // Para mostrar la imagen seleccionada
  
  @FXML
  private MediaView mediaView;  // Para mostrar el video
  
  private MediaPlayer mediaPlayer;
  
  int valoracion = 0; //Variable que inicializa la valoracion
  
  @FXML  
  private TextField nombreUsuario; //Nombre del usuario en el espacio de texto
  
  private String nombre; //Variable para cantener el nombre del usuario
  
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
    if (textArea.getText() == null) {
      Alert alert = new Alert(AlertType.ERROR, "Debe ingresar el contenido de la reseña");
      alert.showAndWait();
    }

    // Establece el nombre del usuario como "Anónimo" si no se ingresó uno
    if (nombreUsuario.getText().isEmpty()) {
      nombre = "Anonimo";
      nombreUsuario.setText(nombre);
    }

    //Crea una reseña y la guarda en la base de datos
    Reseña res = new Reseña(nombreUsuario.getText(), valoracion, textArea.getText());
      
    // Confirma que la reseña se guardó
    Alert alert = new Alert(AlertType.CONFIRMATION, "Su reseña fue guardada");
    alert.showAndWait();
      
    guardarEnBaseDeDatos(res);
    
    //Cerrar ventana 
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    
  }
  
  private void guardarEnBaseDeDatos(Reseña res) {
    //Informacion para acceder a la base de datos
    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
    String usuario = "System";
    String contrasena = "aurelio666";
        
    //Consulta para agregar reseñas
    String sql2 = "INSERT INTO RESENA (NOMBRE_USUARIO, ID_LIBRO, VALORACION, DESCRIPCION)"
                + " VALUES (?, (SELECT ID_LIBRO FROM LIBRO WHERE TITULO = ?), ?, ?)";

    try (Connection connection = DriverManager.getConnection(url, usuario, contrasena)) { 
      // Insertar la reseña en la base de datos
      try (PreparedStatement insertStatement = connection.prepareStatement(sql2)) {
        insertStatement.setString(1, res.getNombre()); // Nombre del usuario
        insertStatement.setString(2, lib.getTitulo()); // ID del libro
        insertStatement.setInt(3, res.getValoracion()); // Valoración
        insertStatement.setString(4, res.getDescripcion()); // Descripción
        
        //Se guarda en la tabla
        int resultado = insertStatement.executeUpdate();
                
        if (resultado > 0) {
          System.out.println("Se agrego el libro con el titulo: " + lib.getTitulo());
        } else {
          System.out.println("No se agrego");
        }
      }
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  @FXML
  void agregarMultimedia(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Seleccionar archivo multimedia");
    fileChooser.getExtensionFilters().addAll(
         new FileChooser.ExtensionFilter("Archivos Multimedia", "*.png",
                  "*.jpg", "*.jpeg", "*.mp4", "*.avi", "*.mov", "*.mkv",
                  "*.flv", "*.wmv"),
         new FileChooser.ExtensionFilter("Todos los Archivos", "*.*"));

    File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
    if (file != null) {
      String filePath = file.toURI().toString();

      if (filePath.endsWith(".png") || filePath.endsWith(".jpg") || filePath.endsWith(".jpeg")) {
        // Cargar imagen en ImageView y mostrarla
        Image image = new Image(filePath);
        imageView.setImage(image);
        textArea.appendText("Imagen adjunta: " + file.getName() + "\n");
      } else if (filePath.endsWith(".mp4") || filePath.endsWith(".avi") 
              || filePath.endsWith(".mov") || filePath.endsWith(".flv") 
              || filePath.endsWith(".wmv") || filePath.endsWith(".mkv")) {
        // Configurar el MediaPlayer y el MediaView para reproducir el video
        Media media = new Media(filePath);
        
        //Detener cualquier video anterior si está en reproducción
        if (mediaPlayer != null) { 
          mediaPlayer.stop(); 
        }
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play(); // Reproducir el video
        textArea.appendText("Video adjunto: " + file.getName() + "\n");
      }
    }
  }
  
  @FXML
  void regresarBoton(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }
  
  public void setLibro(Libro lib) {
    this.lib = lib;
    System.out.println("Titulo: " + lib.getTitulo());
  }
}