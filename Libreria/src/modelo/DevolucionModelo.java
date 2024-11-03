package modelo;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class DevolucionModelo {

	public static TableColumn<Devolucion, Integer> getNumTicketColumn(){
		TableColumn<Devolucion, Integer> numero = new TableColumn<>("#Ticket");
		numero.setCellValueFactory(new PropertyValueFactory<>("numTicket"));
		numero.setPrefWidth(50);
		return numero;
	};
	
	public static TableColumn<Devolucion, String> getNameColumn(){
		TableColumn<Devolucion, String> nombre = new TableColumn<>("Nombre");
		nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		nombre.setPrefWidth(100);
		return nombre;
	};
	
	public static TableColumn<Devolucion, String> getTituloColumn(){
		TableColumn<Devolucion, String> titulo = new TableColumn<>("Titulo");
		titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		titulo.setPrefWidth(100);
		return titulo;
	};
	
	public static TableColumn<Devolucion, String> getFechaColumn(){
		TableColumn<Devolucion, String> fecha = new TableColumn<>("Fecha");
		fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		fecha.setPrefWidth(70);
		return fecha;
	};
	
	public static TableColumn<Devolucion, String> getMotivoColumn(){
		TableColumn<Devolucion, String> motivo = new TableColumn<>("Motivo");
		motivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
		motivo.setPrefWidth(120);
		return motivo;
	};
	
	public static TableColumn<Devolucion, String> getDetalleColumn(){
		TableColumn<Devolucion, String> detalle = new TableColumn<>("Detalle");
		detalle.setCellValueFactory(new PropertyValueFactory<>("detalle"));
		detalle.setPrefWidth(200);
		return detalle;
	};
	
	public static TableColumn<Devolucion, String> getCambioColumn(){
		TableColumn<Devolucion, String> cambio = new TableColumn<>("Cambio");
		cambio.setCellValueFactory(new PropertyValueFactory<>("cambio"));
		cambio.setPrefWidth(50);
		return cambio;
	};
}
