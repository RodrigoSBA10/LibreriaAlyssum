/*
 * @(#)PoliticasControlle.class		1.0 14/10/2024
 *
 */
package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Esta clase es el controlador de la interfaz Politicas.
 * Mostrará un mensaje con las politicas de devolución de
 * la librería.
 * 
 * @version		1.0 14/10/2024
 * @author		Picazo Reyes Ulises Nioolas
 */
public class PoliticasController {

    @FXML
    private TextArea textArea;
    
    //Variable que va almacenar las politicas
    private String politicas = "\n\n\tPOLÍTICA DE DEVOLUCIONES\n\n"
    		+ "\t-Una vez adquirido el libro se tiene un plazo (posterior a la compra) de 15 "
    		+ "\n\t días naturales para poder pedri devolución o cambio, cumpliendo alguna de las "
    		+ "\n\t siguientes razones:"
    		+ "\n\t\t*Por defecto de fabrica."
    		+ "\n\t\t*Hojas en blanco."
    		+ "\n\t\t*Empastado inverdito."
    		+ "\n\t\t*Fallas de impresión."
    		+ "\n\t\t*Número de páginas salteadas."
    		+ "\n\t\t*No corresponde la portada."
    		+ "\n\n\t-No se hacen cambios por error de compra, a excepción que el ticket "
    		+ "\n\t registre un libro que no corresponde a la compra."
    		+ "\n\n\t---NO HAY CANCELACIONES------"
    		+ "\n\n\t-En caso de cumplir con alguna de estas razones:"
    		+ "\n\t\t*Notificar la causa de la devolución, solicitar número de pedido."
    		+ "\n\t\t*La devolución debe solicitarse en la misma sucursal donde se adquirió el"
    		+ "\n\t\tLibro. Es indispensable presentar el ticket de compra."
    		+ "\n\t\t*Los libros con defecto se podrán cambiar por otro ejemplar del mismo titulo."
    		+ "\n\t\t Si la sucursal en la cual se comproó no cuenta con existencias, se solicitará"
    		+ "\n\t\t a otra; si no hay libros disponibles y en buen estado, se le dará la opción"
    		+ "\n\t\t de algún otro ejemplar que este al mismo precio, en caso de que no, se le"
    		+ "\n\t\t enviará indicará cuando se tenga otro ejemplar del libro original de la compra."
    		+ "\n\t\t*No se recibirán libros dañados o maltratados ni libros forrados, a excepción"
    		+ "\n\t\t de aquellos con defectos ya señalados.";
    
    /**
     * 
	 * Este método permite colocar las politicas dentro
	 * del TextArea al momento que se llama la interfaz de Políticas de Devoluciones.
	 * 
	 */
    public void initialize() {
    	textArea.setText(politicas);
    }
}
