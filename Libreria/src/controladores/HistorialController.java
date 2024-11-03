/*
 * @(#)HistorialControlle.class		1.0 14/10/2024
 * 
 */
package controladores;


import database.DataBase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modelo.Devolucion;
import modelo.DevolucionModelo;

/**
 * Esta clase es el controlador de la interfaz Historial.
 * Muestra en una tabla los registros de las devoluciones
 * almacenadas en ....
 * 
 * @version		1.0 14/10/2024
 * @author		Picazo Reyes Ulises Nioolas
 */
public class HistorialController {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnEliminar;

    @FXML
    private ScrollPane scrollDevolucion;

    @FXML
    private TableView<Devolucion> tbvRegistro;

    @FXML
    private TextField txfNombre;
    
    ObservableList<Devolucion> lista;

    @FXML
    void btnBuscar_OnClick(ActionEvent event) {

    }

    @FXML
    void eliminarRegistro(ActionEvent event) {

    }
    
    public void initialize() {
    	lista = DataBase.getElements();
    	
    	tbvRegistro = new TableView<>(lista);
    	tbvRegistro.getColumns().addAll(
    			DevolucionModelo.getNumTicketColumn(),
    			DevolucionModelo.getNameColumn(),
    			DevolucionModelo.getTituloColumn(),
    			DevolucionModelo.getFechaColumn(),
    			DevolucionModelo.getMotivoColumn(),
    			DevolucionModelo.getDetalleColumn(),
    			DevolucionModelo.getCambioColumn()
    			);
    	tbvRegistro.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    	scrollDevolucion.setContent(tbvRegistro);
    }

}