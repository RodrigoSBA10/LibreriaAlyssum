/*
 * Clase para ver las valoracines de los libros y seleccionar por genero
 * 
 * Fecha: 13/10/2024
 * 
 * @autor Aurora Morales
 * 
 * Version: 2
 * */

package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


/*
 * Cuerpo de la clase ControllerValoracionLibro
 * @author Aurora Morales
 * */
public class ControllerValoracionLibro extends Application{

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
  public void initialize() {
	    //Liga la propiedad titulo de la clase Libro
	  	ColumnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
	    //Liga la propiedad autor de la clase Libro
	    ColumnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
	    
	    ColumnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));

	    //Liga la propiedad numero de reseñas de la clase Libro
	    ColumnNumReseñas.setCellValueFactory(new PropertyValueFactory<>("NumeroReseña"));
	    
	    ObservableList<Libro> colum = agregarLibroBD();
	    tablaDeLibros.setItems(colum);
  }

  /*
   * Metodo para agregar las columanas de la tabla 
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

                columnas.add(new Libro(titulo, autor,genero,numReseña));
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
      Parent root = FXMLLoader.load(getClass().getResource("ValoracionLibro.fxml"));

      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Cupones");
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
          String sql = "SELECT L.TITULO, A.NOMBRE AS AUTOR, G.NOMBRE AS GENERO, COUNT(R.ID_RESENA) AS NUMERO_RESEÑAS" +
          		"FROM LIBRO L" +
          		"JOIN AUTOR A ON L.ID_AUTOR = A.ID_AUTOR" +
          	    "JOIN GENERO G ON L.ID_GENERO = G.ID_GENERO" +
          		"LEFT JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO" +
          		"WHERE G.NOMBRE = 'Arte'" +
          		"GROUP BY L.TITULO, A.NOMBRE, G.NOMBRE;";
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
  void itemAutoayudaBuscar(ActionEvent event) {
	  miMenuButton.setText(itemAutoayuda.getText());
  }

  @FXML
  void itemAventuraBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemAventura.getText());
  }

  @FXML
  void itemBiografiasBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemBiografias.getText());
  }

  @FXML
  void itemCiencia_FiccionBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemCiencia_Ficciona.getText());
  }

  @FXML
  void itemCocinaBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemCocina.getText());
  }

  @FXML
  void itemConsulta_ReferenciaBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemConsulta_Referencia.getText());
  }

  @FXML
  void itemContemporaneoBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemContemporaneo.getText());
  }

  @FXML
  void itemDistopiaBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemDistopia.getText());
  }

  @FXML
  void itemDivulgativosBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemDivulgativos.getText());
  }

  @FXML
  void itemFantasiaBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemFantasia.getText());
  }

  @FXML
  void itemInfantilBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemInfantil.getText());
  }

  @FXML
  void itemJuvenilBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemJuvenil.getText());
  }

  @FXML
  void itemLibros_PracticosBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemLibros_Practicos.getText());
  }

  @FXML
  void itemLibros_TecnicosBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemLibros_Tecnicos.getText());
  }

  @FXML
  void itemLibros_TextoBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemLibros_Texto.getText());
  }

  @FXML
  void itemMemoriasBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemMemorias.getText());
  }

  @FXML
  void itemNovela_HistoricaBuscar(ActionEvent event) {
	  miMenuButton.setText(itemNovela_Historica.getText());
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
  void itemParanormalBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemParanormal.getText());
  }

  @FXML
  void itemPoesiaBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemPoesia.getText());
  }

  @FXML
  void itemSaludBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemSalud.getText());
  }

  @FXML
  void itemTerrorBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemTerror.getText());
  }

  @FXML
  void itemViajesBuscar(ActionEvent event) {
	  miMenuButton.setText(this.itemViajes.getText());
  }
  
  public void loadLibro() {
	  tablaDeLibros.getItems();
	  
	  tablaDeLibros.setOnMouseClicked(evento -> {
	  		
	  		if(evento.getClickCount() == 2) {
	  			try {
	  		      FXMLLoader loader = new FXMLLoader(getClass().getResource("ReseñaLibro.fxml"));
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
}
