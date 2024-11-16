package application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

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
	private TextField txtf_direroRecibido;
	private Float iva;
	private ObservableList<Tabla_venta> tl_venta;

	@FXML
	void initialize() {
		// Agregar un listener para calcular el cambio cuando el usuario escriba en el
		// campo
		txtf_direroRecibido.setOnKeyReleased(this::calcularCambio);
	}

	@FXML
	void Cambio_vistaTarjeta(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FormadePago.fxml"));
			Pane root = loader.load();

			Controller_FormadePagoTarjeta controladorDestino = loader.getController();
			float total = Float.parseFloat(txt_total_pagar.getText());
			controladorDestino.setTotal(total);

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

			Stage currentStage = (Stage) txt_cambio.getScene().getWindow();
			currentStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void calcularCambio(KeyEvent event) {
		try {
			// Obtener el total a pagar y el dinero recibido
			float total = Float.parseFloat(txt_total_pagar.getText());
			float recibido = Float.parseFloat(txtf_direroRecibido.getText());

			// Calcular el cambio
			float cambio = recibido - total;

			// Actualizar el texto de la etiqueta de cambio
			txt_cambio.setText(String.format("%.2f", cambio));
		} catch (NumberFormatException e) {
			// Si no se puede convertir el texto en un número, se muestra vacío
			txt_cambio.setText("0.00");
		}
	}

	public void setTotal(float total) {
		txt_total_pagar.setText(String.format("%.2f", total));
	}

	public void setTabla(ObservableList<Tabla_venta> tabla) {
		this.tl_venta = tabla;

	}

	public void generarTicket(String idVenta, String productos, float total,float iva, float recibido, float cambio) {
		// Define el nombre del archivo, puede incluir el ID de la venta o la fecha para
		// hacerlo único
		String ruta = "C://Users//Jesus Alfredo//Documents//ticketsApp//";
		String nombreArchivo = ruta + "Ticket_Venta_" + idVenta + ".txt";

		try (FileWriter writer = new FileWriter(nombreArchivo)) {
			// Escribir el contenido del ticket
			writer.write("========== TICKET DE COMPRA ==========\n");
			writer.write("ID de la Venta: " + idVenta + "\n");
			writer.write("--------------------------------------\n");
			writer.write("Productos:\n");
			writer.write(productos + "\n");
			writer.write("--------------------------------------\n");
			writer.write(String.format("Total IVA: %.2f\n", iva));
			writer.write(String.format("Total a Pagar: %.2f\n", total));
			writer.write(String.format("Dinero Recibido: %.2f\n", recibido));
			writer.write(String.format("Cambio: %.2f\n", cambio));
			writer.write("--------------------------------------\n");
			writer.write("Gracias por su compra.\n");
			writer.write("======================================\n");

			// Confirmación en consola
			System.out.println("Ticket generado: " + nombreArchivo);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error al generar el ticket.");
		}
	}

	public String generarDetalleProductos(ObservableList<Tabla_venta> listaProductos) {
		StringBuilder detalle = new StringBuilder();

		// Itera sobre la lista y construye el detalle
		for (Tabla_venta producto : listaProductos) {
			detalle.append(String.format("%dx %s\n", producto.getCantidad(), // Cantidad
					producto.getProducto() // Nombre del producto
			));
		}

		return detalle.toString();
	}

	@FXML
	void finalizarYticket(ActionEvent event) {
		try {
			// Obtén los datos necesarios
			String idVenta = UUID.randomUUID().toString(); // ID único para la venta
			String productos = generarDetalleProductos(tl_venta); // Detalle de productos
			float total = Float.parseFloat(txt_total_pagar.getText());
			float recibido = Float.parseFloat(txtf_direroRecibido.getText());
			float iva = this.iva;
			float cambio = recibido - total;

			// Llama al método para generar el ticket
			generarTicket(idVenta, productos, total,iva, recibido, cambio);

			// Mensaje de confirmación
			System.out.println("Compra finalizada. Ticket generado.");
		} catch (NumberFormatException e) {
			System.err.println("Error: Verifica los datos de entrada.");
		}
		try {
			Pane root = FXMLLoader.load(this.getClass().getResource("Sistema_vista.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Stage currentStage = (Stage) txt_cambio.getScene().getWindow();
		currentStage.close();

	}
	public void setIva(float iva) {
		this.iva = iva;
		
	}
}
