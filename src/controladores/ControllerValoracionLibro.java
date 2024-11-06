/*
 * Clase para ver las valoracines de los libros y seleccionar por genero
 * 
 * Fecha: 13/10/2024
 * 
 * @autor Aurora Morales
 * 
 * Version: 12
 * */

package controladores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.Libro;


/*Cuerpo de la clase ControllerValoracionLibro
 * */

public class ControllerValoracionLibro extends Application {

  @FXML
  private TableView<Libro> tablaDeLibros; //Tabla de libros
  
  @FXML
  private TableColumn<Libro, String> ColumnAutor;

  @FXML
  private TableColumn<Libro, String> ColumnGenero;

  @FXML
  private TableColumn<Libro, Integer> ColumnNumReseñas;

  @FXML
  private TableColumn<Libro, String> ColumnTitulo;

  @FXML
  private MenuItem itemNovela_Historica;  //Item de Novela historica
  
  @FXML
  private MenuItem itemNovela_Negra;  //Item de Novela negra, thriller o suspense 
  
  @FXML
  private MenuItem itemCiencia_Ficciona;  //Item de Ciencia Ficción
  
  @FXML
  private MenuItem itemDistopia;  //Item de Distopía
  
  @FXML
  private MenuItem itemAventura;  //Item de Aventuras
  
  @FXML
  private MenuItem itemFantasia;  //Item de Fantasía
  
  @FXML
  private MenuItem itemContemporaneo;  //Item de Contemporáneo
  
  @FXML
  private MenuItem itemTerror;  //Item de Terror
  
  @FXML
  private MenuItem itemParanormal;  //Item de Paranormal
  
  @FXML
  private MenuItem itemPoesia; //Item de Poesía

  @FXML
  private MenuItem itemJuvenil; //Item de Juvenil

  @FXML
  private MenuItem itemInfantil;  //Item de Infantil
  
  @FXML
  private MenuItem itemAutoayuda;  //Item de Autoayuda y superación personal
  
  @FXML
  private MenuItem itemSalud;  //Item de Salud y depotes
  
  @FXML
  private MenuItem itemLibros_Practicos;  //Item de Libros prácticos o manuales
  
  @FXML
  private MenuItem itemMemorias;  //Item de Memorias
  
  @FXML
  private MenuItem itemBiografias;  //Item de Biografías
  
  @FXML
  private MenuItem itemCocina;  //Item de Cocina
  
  @FXML
  private MenuItem itemViajes;  //Item de Viajes
  
  @FXML
  private MenuItem itemLibros_Tecnicos;  //Item de Libros técnicos y especialezados
  
  @FXML
  private MenuItem itemConsulta_Referencia;  //Item De consulta y referencia
  
  @FXML
  private MenuItem itemDivulgativos;  // Item De consulta y referencia
  
  @FXML
  private MenuItem itemLibros_Texto;  // Item de Libros de texto
  
  @FXML
  private MenuItem itemArte;  //Item de Arte
  
  @FXML
  private MenuButton miMenuButton; //Menu de botones
  
  @FXML
  private Button buttonIrAnterior;  //Boton para ver las imagenes hacia atras

  @FXML
  private Button buttonIrSiguiente;  //Boton para ver lsa imagenes hacia ademante
  
  @FXML
  private ImageView marcoImagen;  //Espacio para ver las imagenes de las portadas de libros
  
  //Lista de imagenes de las portadas de los libros
  private List<Image> listaImagenes = List.of(
     new Image(getClass().getResourceAsStream("/image/ElNombreDeLaRosa.jpg")), 
     new Image(getClass().getResourceAsStream("/image/ElClanDelOsoCavernario.jpg")), 
     new Image(getClass().getResourceAsStream("/image/ElEspejismo.jpg")),
     new Image(getClass().getResourceAsStream("/image/ElHombreIlustrado.jpg")),
     new Image(getClass().getResourceAsStream("/image/ElAñoDeGracia.jpg")),
     new Image(getClass().getResourceAsStream("/image/LaHeredera.jpg")),
     new Image(getClass().getResourceAsStream("/image/ChoqueDeReyes.jpg")),
     new Image(getClass().getResourceAsStream("/image/LasModernas.jpg")),
     new Image(getClass().getResourceAsStream("/image/Dracula.jpg")),
     new Image(getClass().getResourceAsStream("/image/Immaturi.jpg")),
     new Image(getClass().getResourceAsStream("/image/LibroDePoemas.jpg")),
     new Image(getClass().getResourceAsStream("/image/LaSerpienteYLasAlasDeLaNoche.jpg")),
     new Image(getClass().getResourceAsStream("/image/SeAmableContigoMismo.jpg")),
     new Image(getClass().getResourceAsStream("/image/GranDiccionario.jpg")),
     new Image(getClass().getResourceAsStream("/image/ElPrincipito.jpg"))
     );
  
  //Contador para ir reccorriendo la lista de las portadas de los libros
  private int indice = 0;
  
  //Metodo para cargar la primera imagen
  public void cargarImagen(int indice) {
    marcoImagen.setImage(listaImagenes.get(indice));
  }
  
  //Metodo para ir a la siguiente imagen
  private void siguienteImagen() {
    indice = (indice + 1) % listaImagenes.size(); // Bucle al inicio si es el final
    cargarImagen(indice);
  }
  
  //Metodo para ir a la imagen anterior
  private void anteriorImagen() {
    //Bucle al final si es el inicio
    indice = (indice - 1 + listaImagenes.size()) % listaImagenes.size(); 
    cargarImagen(indice);
  }
  
  /*Metodo que inicializa las funciones de toda la ventana
   * */
  public void initialize() {
    //Carga la primera imagen
    cargarImagen(indice);
    // Configurar temporizador para cambio automático cada 3 segundos
    Timeline temporizador = new Timeline(new KeyFrame(Duration.seconds(3),
        event -> siguienteImagen()));
    temporizador.setCycleCount(Timeline.INDEFINITE);
    temporizador.play();
      
    // Configurar botones para cambiar de imagen manualmente
    buttonIrSiguiente.setOnAction(event -> siguienteImagen());
    buttonIrAnterior.setOnAction(event -> anteriorImagen());
    
    //Liga la propiedad titulo de la clase Libro
    ColumnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    //Liga la propiedad autor de la clase Libro
    ColumnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
    //Liga la propiedad genero de la clase Libro
    ColumnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
    //Liga la propiedad numero de reseñas de la clase Libro
    ColumnNumReseñas.setCellValueFactory(new PropertyValueFactory<>("NumeroReseña"));

    ObservableList<Libro> colum = agregarLibroBD();
	
    tablaDeLibros.setItems(colum); //Inserta la lista de los libros a la tabla

    /*Busca en toda la lista de libros los libros por el genero Novela negra thiller,
    thriller o suspense */
    itemNovela_Negra.setOnAction(event -> {
      tablaDeLibros.setItems(itemNovela_NegraBuscar(event));
    });
    itemArte.setOnAction(event -> {
      tablaDeLibros.setItems(itemArteBuscar(event));
    });
    itemAutoayuda.setOnAction(event -> {
      tablaDeLibros.setItems(itemAutoayudaBuscar(event));
    });
    itemAventura.setOnAction(event -> {
      tablaDeLibros.setItems(itemAventuraBuscar(event));
    });
    itemBiografias.setOnAction(event -> {
      tablaDeLibros.setItems(itemBiografiasBuscar(event));
    });
    itemCiencia_Ficciona.setOnAction(event -> {
      tablaDeLibros.setItems(itemCiencia_FiccionBuscar(event));
    });
    itemCocina.setOnAction(event -> {
      tablaDeLibros.setItems(itemCocinaBuscar(event));
    });
    itemConsulta_Referencia.setOnAction(event -> {
      tablaDeLibros.setItems(itemConsulta_ReferenciaBuscar(event));
    });
    itemContemporaneo.setOnAction(event -> {
      tablaDeLibros.setItems(itemContemporaneoBuscar(event));
    });
    itemDistopia.setOnAction(event -> {
      tablaDeLibros.setItems(itemDistopiaBuscar(event));
    });
    itemDivulgativos.setOnAction(event -> {
      tablaDeLibros.setItems(itemDivulgativosBuscar(event));
    });
    itemInfantil.setOnAction(event -> {
      tablaDeLibros.setItems(itemInfantilBuscar(event));
    });
    itemJuvenil.setOnAction(event -> {
      tablaDeLibros.setItems(itemJuvenilBuscar(event));
    });
    itemLibros_Practicos.setOnAction(event -> {
      tablaDeLibros.setItems(itemLibros_PracticosBuscar(event));
    });
    itemLibros_Tecnicos.setOnAction(event -> {
      tablaDeLibros.setItems(itemLibros_TecnicosBuscar(event));
    });
    itemLibros_Texto.setOnAction(event -> {
      tablaDeLibros.setItems(itemLibros_TextoBuscar(event));
    });
    itemMemorias.setOnAction(event -> {
      tablaDeLibros.setItems(itemMemoriasBuscar(event));
    });
    itemNovela_Historica.setOnAction(event -> {
      tablaDeLibros.setItems(itemNovela_HistoricaBuscar(event));
    });
    itemParanormal.setOnAction(event -> {
      tablaDeLibros.setItems(itemParanormalBuscar(event));
    });
    itemPoesia.setOnAction(event -> {
      tablaDeLibros.setItems(itemPoesiaBuscar(event));
    });
    itemSalud.setOnAction(event -> {
      tablaDeLibros.setItems(itemSaludBuscar(event));
    });
    itemTerror.setOnAction(event -> {
      tablaDeLibros.setItems(itemTerrorBuscar(event));
    });
    itemViajes.setOnAction(event -> {
      tablaDeLibros.setItems(itemViajesBuscar(event));
    });

    //Evento para seleccionar un libro
    tablaDeLibros.setOnMouseClicked(evento ->  {
      if (evento.getClickCount() == 2) {
        try {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ReseñaLibro.fxml"));
          Parent root = loader.load();
          ControllerReseñaLibro cotrolador = loader.getController();
          Libro libroSeleccionado = tablaDeLibros.getSelectionModel().getSelectedItem();
          cotrolador.setLibro(libroSeleccionado);
          cotrolador.agregarTabla();
          Scene scene = new Scene(root);
          Stage stage = new Stage();
          stage.initModality(Modality.APPLICATION_MODAL);
          stage.setScene(scene);
          stage.showAndWait();
          stage.setTitle("Reseñas");

        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }	
      }
    });
  }

  /*Metodo para agregar las columanas de la tabla 
   * */
  public ObservableList<Libro> agregarLibroBD() {
    //Crea una lista observable de listas de libros
    ObservableList<Libro> columnas = FXCollections.observableArrayList();

    //Informacion de mi base de datos
    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
    String usuario = "System";
    String contraseña = "aurelio666";

    try {
      // Establecer conexión con la base de datos
      Connection connection = DriverManager.getConnection(url, usuario, contraseña);

      // Crear el statement y ejecutar la consulta
      Statement statement = connection.createStatement();
      String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
                    "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
                    "FROM LIBRO L " +
                    "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +
                    "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +
                    "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +
                    "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE";
      ResultSet resultado = statement.executeQuery(sql);
      // Recorrer los resultados y añadirlos a la lista
      while (resultado.next()) {
        String titulo = resultado.getString("TITULO");
        String autor = resultado.getString("AUTOR");
        String genero = resultado.getString("GENERO");
        int numReseña = resultado.getInt("NUMERO_RESEÑAS");

        columnas.add(new Libro(titulo, autor, genero, numReseña));
      }

      // Cerrar la conexión
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return columnas;
  }


  @Override
  public void start(Stage stage) throws Exception {
    // Cargar el archivo FXML
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/ValoracionLibro.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("Libros");
    stage.show();
    stage.setFullScreen(true);
  }
  
  @FXML
  ObservableList<Libro> itemArteBuscar(ActionEvent event) {

    miMenuButton.setText(itemArte.getText());

    ObservableList<Libro> columnas = FXCollections.observableArrayList();

    //Informacion de mi base de datos
    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
    String usuario = "System";
    String contraseña = "aurelio666";

    try {
      // Establecer conexión con la base de datos
      Connection connection = DriverManager.getConnection(url, usuario, contraseña);

      // Crear el statement y ejecutar la consulta
      Statement statement = connection.createStatement();
      String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
                  "FROM LIBRO L " +
                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +
                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +
                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +
                  "WHERE G.NOMBRE = 'Arte' " +
                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
      ResultSet resultado = statement.executeQuery(sql);

      // Recorrer los resultados y añadirlos a la lista
      while (resultado.next()) {
        String titulo = resultado.getString("TITULO");
        String autor = resultado.getString("AUTOR");
        String genero = resultado.getString("GENERO");
        int numReseña = resultado.getInt("NUMERO_RESEÑAS");

        columnas.add(new Libro(titulo, autor, genero, numReseña));
      }

      // Cerrar la conexión
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return columnas;
  }

  @FXML
  ObservableList<Libro> itemAutoayudaBuscar(ActionEvent event) {
    miMenuButton.setText(itemAutoayuda.getText());

    ObservableList<Libro> columnas = FXCollections.observableArrayList();

    //Informacion de mi base de datos
    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
    String usuario = "System";
    String contraseña = "aurelio666";

    try {
      // Establecer conexión con la base de datos
      Connection connection = DriverManager.getConnection(url, usuario, contraseña);

      // Crear el statement y ejecutar la consulta
      Statement statement = connection.createStatement();
      String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
                    "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
                    "FROM LIBRO L " + 
                    "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
                    "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
                    "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
                    "WHERE G.NOMBRE = 'Autoayuda y superación' " +  
                    "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
      ResultSet resultado = statement.executeQuery(sql);
      // Recorrer los resultados y añadirlos a la lista
      while (resultado.next()) {
        String titulo = resultado.getString("TITULO");
        String autor = resultado.getString("AUTOR");
        String genero = resultado.getString("GENERO");
        int numReseña = resultado.getInt("NUMERO_RESEÑAS");

        columnas.add(new Libro(titulo, autor, genero, numReseña));
      }

      // Cerrar la conexión
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return columnas;
  }

  @FXML
  ObservableList<Libro> itemAventuraBuscar(ActionEvent event) {
    miMenuButton.setText(this.itemAventura.getText());
    ObservableList<Libro> columnas = FXCollections.observableArrayList();

    //Informacion de mi base de datos
    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
    String usuario = "System";
    String contraseña = "aurelio666";

    try {
      // Establecer conexión con la base de datos
      Connection connection = DriverManager.getConnection(url, usuario, contraseña);

      // Crear el statement y ejecutar la consulta
      Statement statement = connection.createStatement();
      String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
                   "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
                   "FROM LIBRO L " + 
                   "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
                   "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
                   "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
                   "WHERE G.NOMBRE = 'Aventuras' " +  
                   "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
      ResultSet resultado = statement.executeQuery(sql);
      // Recorrer los resultados y añadirlos a la lista
      while (resultado.next()) {
        String titulo = resultado.getString("TITULO");
        String autor = resultado.getString("AUTOR");
        String genero = resultado.getString("GENERO");
        int numReseña = resultado.getInt("NUMERO_RESEÑAS");

        columnas.add(new Libro(titulo, autor, genero, numReseña));
      }

      // Cerrar la conexión
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return columnas;
  }

  @FXML
  ObservableList<Libro> itemBiografiasBuscar(ActionEvent event) {
    miMenuButton.setText(this.itemBiografias.getText());
    
	ObservableList<Libro> columnas = FXCollections.observableArrayList();

    //Informacion de mi base de datos
    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
    String usuario = "System";
    String contraseña = "aurelio666";

    try {
      // Establecer conexión con la base de datos
      Connection connection = DriverManager.getConnection(url, usuario, contraseña);

      // Crear el statement y ejecutar la consulta
      Statement statement = connection.createStatement();
      String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
                   "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
                   "FROM LIBRO L " + 
                   "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
                   "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
                   "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
                   "WHERE G.NOMBRE = 'Biografías' " +  
                   "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
      ResultSet resultado = statement.executeQuery(sql);
      // Recorrer los resultados y añadirlos a la lista
      while (resultado.next()) {
        String titulo = resultado.getString("TITULO");
        String autor = resultado.getString("AUTOR");
        String genero = resultado.getString("GENERO");
        int numReseña = resultado.getInt("NUMERO_RESEÑAS");

        columnas.add(new Libro(titulo, autor, genero, numReseña));
      }

      //Cerrar la conexión
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return columnas;
  }

  @FXML
  ObservableList<Libro> itemCiencia_FiccionBuscar(ActionEvent event) {
    miMenuButton.setText(this.itemCiencia_Ficciona.getText());

    ObservableList<Libro> columnas = FXCollections.observableArrayList();

    //Informacion de mi base de datos
    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
    String usuario = "System";
    String contraseña = "aurelio666";

    try {
      // Establecer conexión con la base de datos
      Connection connection = DriverManager.getConnection(url, usuario, contraseña);

      // Crear el statement y ejecutar la consulta
      Statement statement = connection.createStatement();
      String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
                   "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
                   "FROM LIBRO L " + 
                   "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
                   "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
                   "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
                   "WHERE G.NOMBRE = 'Ciencia Ficción' " +  
                   "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
      ResultSet resultado = statement.executeQuery(sql);

      // Recorrer los resultados y añadirlos a la lista
      while (resultado.next()) {
        String titulo = resultado.getString("TITULO");
        String autor = resultado.getString("AUTOR");
        String genero = resultado.getString("GENERO");
        int numReseña = resultado.getInt("NUMERO_RESEÑAS");

        columnas.add(new Libro(titulo, autor, genero, numReseña));
      }

      // Cerrar la conexión
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return columnas;
  }

  @FXML
  ObservableList<Libro> itemCocinaBuscar(ActionEvent event) {
    miMenuButton.setText(this.itemCocina.getText());

    ObservableList<Libro> columnas = FXCollections.observableArrayList();

    //Informacion de mi base de datos
    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
    String usuario = "System";
    String contraseña = "aurelio666";
    
    try {
      // Establecer conexión con la base de datos
      Connection connection = DriverManager.getConnection(url, usuario, contraseña);

      // Crear el statement y ejecutar la consulta
      Statement statement = connection.createStatement();
      String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
                   "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
                   "FROM LIBRO L " + 
                   "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
                   "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
                   "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
                   "WHERE G.NOMBRE = 'Cocina' " +  
                   "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
      ResultSet resultado = statement.executeQuery(sql);
        
      // Recorrer los resultados y añadirlos a la lista
      while (resultado.next()) {
        String titulo = resultado.getString("TITULO");
        String autor = resultado.getString("AUTOR");
        String genero = resultado.getString("GENERO");
        int numReseña = resultado.getInt("NUMERO_RESEÑAS");

        columnas.add(new Libro(titulo, autor, genero, numReseña));
      }

      // Cerrar la conexión
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return columnas;
  }

  @FXML
  ObservableList<Libro> itemConsulta_ReferenciaBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemConsulta_Referencia.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();
	  
		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'De consulta y referencia' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;  
  }

  @FXML
  ObservableList<Libro> itemContemporaneoBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemContemporaneo.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();
	  
		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Contemporáneo' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemDistopiaBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemDistopia.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();
	  
		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Distopía' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemDivulgativosBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemDivulgativos.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();
	  
		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Divulgativos' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemFantasiaBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemFantasia.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();
	  
		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Divulgativos' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemInfantilBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemInfantil.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();
	  
		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Infaltil' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemJuvenilBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemJuvenil.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();
	  
		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Juvenil' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemLibros_PracticosBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemLibros_Practicos.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();
	  
		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Libros prácticos o manuales' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemLibros_TecnicosBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemLibros_Tecnicos.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();
	  
		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Libros técnicos y especialezados' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemLibros_TextoBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemLibros_Texto.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();
	  
		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Libros de texto' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemMemoriasBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemMemorias.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();
	  
		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Memorias' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemNovela_HistoricaBuscar(ActionEvent event) {
	  miMenuButton.setText(itemNovela_Historica.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();
	  
		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Novela historica' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemNovela_NegraBuscar(ActionEvent event) {
	  miMenuButton.setText(itemNovela_Negra.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();

		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Novela negra, thriller o suspense' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemParanormalBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemParanormal.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();

		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Paranormal' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemPoesiaBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemPoesia.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();

		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Poesía' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemSaludBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemSalud.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();

		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Salud y deporte' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemTerrorBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemTerror.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();

		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Terror' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }

  @FXML
  ObservableList<Libro> itemViajesBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemViajes.getText());
	  
	  ObservableList<Libro> columnas = FXCollections.observableArrayList();

		//Informacion de mi base de datos
		    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
			String usuario = "System";
			String contraseña = "aurelio666";
			
			try {
	          // Establecer conexión con la base de datos
	          Connection connection = DriverManager.getConnection(url, usuario, contraseña);

	          // Crear el statement y ejecutar la consulta
	          Statement statement = connection.createStatement();
	          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, " +
	                  "COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS " +
	                  "FROM LIBRO L " + 
	                  "JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR " +  
	                  "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO " +  
	                  "LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO " +  
	                  "WHERE G.NOMBRE = 'Viajes' " +  
	                  "GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE"; 
	          ResultSet resultado = statement.executeQuery(sql);
	          // Recorrer los resultados y añadirlos a la lista
	          while (resultado.next()) {
	              String titulo = resultado.getString("TITULO");
	              String autor = resultado.getString("AUTOR");
	              String genero = resultado.getString("GENERO");
	              int numReseña = resultado.getInt("NUMERO_RESEÑAS");

	              columnas.add(new Libro(titulo, autor,genero,numReseña));
	          }

	       // Cerrar la conexión
	          connection.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
			return columnas;
  }
  
  public void loadLibro() {
	  tablaDeLibros.getItems();
	  
	  tablaDeLibros.setOnMouseClicked(evento -> {
	  		
	  		if(evento.getClickCount() == 2) {
	  			try {
	  		      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ReseñaLibro.fxml"));
	  		      Parent root = loader.load();
	  		      Scene scene = new Scene(root);
	  		      Stage stage = new Stage();
	  		      stage.initModality(Modality.APPLICATION_MODAL);
	  		      stage.setScene(scene);
	  		      stage.showAndWait();
	  		    } catch (IOException e) {
	  		      // TODO Auto-generated catch block
	  		      e.printStackTrace();
	  		    }
	  		}
	  	});
  }
  
  public void Imprimir(ObservableList<Libro> dato) {
	  for (Libro libro : dato) {
		System.out.println(libro.toString());
	}
  }
}
