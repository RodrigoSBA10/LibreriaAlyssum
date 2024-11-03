/*
 * @(#)DevolucionControlle.class		1.0 14/10/2024
 * 
 */
package controladores;

import java.text.SimpleDateFormat;
import java.util.Date;

import database.DataBase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import modelo.Devolucion;
import modelo.Ticket;

/**
 * Esta clase es el controlador de la interfaz Devolución.
 * Solicita y adquiere los datos necesarios para Registrar una
 * devolución.
 * 
 * @version		1.0 14/10/2024
 * @author		Picazo Reyes Ulises Nioolas
 */
public class DevolucionController {

	//Componentes para extraer los datos.
	@FXML
    private Label lbFecha;
	
	@FXML
    private ToggleGroup aplicaCambio;
	
	@FXML
    private TextArea areaTexto;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private ComboBox<String> comboBox = new ComboBox<String>();

    @FXML
    private RadioButton rbNo;

    @FXML
    private RadioButton rbSi;

    @FXML
    private TextField txfNumeroTicket;
    
    ObservableList<Ticket> lista;
    
    /**
     * 
     * 
	 * 
	 */

    @FXML
    void cancelarDevolucion(ActionEvent event) {
    	txfNumeroTicket.setText("");
    	areaTexto.setText("");
    	if(rbNo.isSelected()) {
    		rbSi.setSelected(true);
    	}
    }
    
    /**
     * 
	 * Método que registra la devolución. Verifica
	 * 
	 */

    @FXML
    void registrarDevolucion(ActionEvent event) {
    	String numero = txfNumeroTicket.getText();
    	String detalle = areaTexto.getText();
    	String motivo = comboBox.getValue();
    	String fecha = lbFecha.getText();
    	Alert alert;
    	if(!numero.equals("")) {
    		if(!detalle.equals("")) {
    			if(!(comboBox.getValue() == null)) {
    				lista = DataBase.getElementsTicket();
    				int number = Integer.parseInt(numero);
    				if(existeTicket(number)) {
    					if(rbSi.isSelected()) {
    						mandarDatos(number, fecha, motivo, detalle, rbSi.getText());
    					} else {
    						mandarDatos(number, fecha, motivo, detalle, rbNo.getText());
    					}
    				} else {
    					alert = new Alert(AlertType.INFORMATION,"El Ticket no existe.");
                		alert.show();
    				}
    			} else {
    				alert = new Alert(AlertType.ERROR,"Selecciona el motivo.");
            		alert.show();
    			}
    		} else {
    			alert = new Alert(AlertType.ERROR,"Llena la descripción.");
        		alert.show();
    		}
    	} else {
    		alert = new Alert(AlertType.ERROR,"Ingresa el número de Ticket.");
    		alert.show();
    	}
    }
    
    /**
     * 
	 * Este método ayuda a poner en el ComboBox los motivos al seleccionar
	 * la intefaz de Registrar Devolución. Así mismo coloca la fecha actual.
	 * 
	 */
    
    public void initialize() {
    	comboBox.getItems().addAll("Defecto de fabrica", "Páginas en blaco", 
    								"Mal encuadernamiento", "Portada errónea", 
    								"Libro dañado", "Fallas de impresión", 
    								"Número de paginas salteadas", "Otro");
    	lbFecha.setText(formatoFecha()); // Tiene como parametro un método.
    	soloNumeros();			// Se llama al método soloNumeros().
    }
    
    /**
     * 
	 * Este método da formato a la fecha como: Día-Mes-Año
	 * Regresando el formato en String para una mejor manipulación
	 * 
	 */
    private String formatoFecha() {
    	Date fecha = new Date();	//Instancia de fecha en tiempo real
    	SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yy");
    	String fechaFormateada = formato.format(fecha);
    	return fechaFormateada;
    }
    
    /**
     * 
	 * Este método hace que el TextFiel se restrinja a solo números
	 * del rango [0-9].
	 * 
	 */
    
    private void soloNumeros() {
    	txfNumeroTicket.setTextFormatter(new TextFormatter<>(cambio -> {
    		String nuevoTexto = cambio.getControlNewText();
    		if (nuevoTexto.matches("\\d*")) {
    			return cambio;
    		}
    		return null;
    	}));
    }
    
    private void mandarDatos(int numero, String fecha, String motivo, String detalle, String cambio) {
    	DataBase.registrarDevolucion(numero, fecha, motivo, detalle, cambio);
    }
    
    private boolean existeTicket(int numero) {
    	for (Ticket t: lista) {
    		if(t.isNumber(numero)) {
    			return true;
    		}
    	}
    	return false;
    }
}