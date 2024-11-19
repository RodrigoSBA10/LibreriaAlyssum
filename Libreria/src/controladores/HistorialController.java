/*
 * @(#)HistorialControlle.class		1.0 14/10/2024
 * 
 */
package controladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.function.Predicate;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import database.DataBase;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.Devolucion;
import modelo.DevolucionModelo;
import modelo.Motivo;

/**
 * Esta clase es el controlador de la interfaz Historial. Muestra en una tabla
 * los registros de las devoluciones almacenadas en ....
 * 
 * @version 1.0 14/10/2024
 * @author Picazo Reyes Ulises Nioolas
 */
public class HistorialController {

	@FXML
	private ToggleGroup Cambio;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnReporte;

	@FXML
	private Button btnRefrescar;

	@FXML
	private ComboBox<Motivo> comboBoxMotivo;

	@FXML
	private DatePicker fechaInicio;

	@FXML
	private DatePicker fechaFinal;

	@FXML
	private TextField numTicket;

	@FXML
	private RadioButton radioNo;

	@FXML
	private RadioButton radioSi;

	@FXML
	private ScrollPane scrollDevolucion;

	@FXML
	private TableView<Devolucion> tbvRegistro;

	ObservableList<Devolucion> lista;
	FilteredList<Devolucion> listaFiltrada = null;

	@FXML
	void btnBuscar_OnClick(ActionEvent event) {
		listaFiltrada = new FilteredList<>(lista, p -> true);
		Predicate<Devolucion> filtro = devolucion -> true;
		if (numTicket.getLength() > 0) {
			filtro = filtro.and(devolucion -> devolucion.getNumTicket() == Integer.parseInt(numTicket.getText()));
		}
		if (comboBoxMotivo.getValue() != null) {
			filtro = filtro.and(devolucion -> devolucion.getMotivo().toLowerCase()
					.contains(comboBoxMotivo.getValue().getNombre().toLowerCase()));
		}
		if (fechaInicio.getValue() != null && fechaFinal.getValue() != null) {
			filtro = filtro.and(devolucion -> {
				LocalDate fecha = devolucion.getFechaLocalDate();
				return (fecha.isEqual(fechaInicio.getValue()) || fecha.isAfter(fechaInicio.getValue()))
						&& (fecha.isEqual(fechaFinal.getValue()) || fecha.isBefore(fechaFinal.getValue()));
			});
		}
		if (radioNo.isSelected()) {
			filtro = filtro.and(devolucion -> {
				return devolucion.getCambio().contains(radioNo.getText());
			});
		}
		if (radioSi.isSelected()) {
			filtro = filtro.and(devolucion -> {
				return devolucion.getCambio().contains(radioSi.getText());
			});
		}
		listaFiltrada.setPredicate(filtro);
		tbvRegistro.setItems(listaFiltrada);
	}

	@FXML
	void btnGenerarReporte(ActionEvent event) {
		// Crear un diálogo para guardar archivos
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Guardar Reporte");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
		File file = fileChooser.showSaveDialog(new Stage());
		if (file != null) {
			generarReporte(file);
		}
	}

	@FXML
	void eliminarRegistro(ActionEvent event) {
		Devolucion eliminar = tbvRegistro.getSelectionModel().getSelectedItem();
		if (eliminar != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Confirmación de Eliminación");
			alert.setHeaderText("Estás a punto de eliminar una fila.");
			alert.setContentText("¿Estás seguro de que quieres eliminar esta fila?");
			ButtonType buttonTypeSi = new ButtonType("Sí");
			ButtonType buttonTypeNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);
			alert.showAndWait().ifPresent(response -> {
				if (response == buttonTypeSi) {
					lista.remove(eliminar);
					DataBase.eliminarDevolucion(eliminar);
				}
			});
		} else {
			System.out.println("No hay ninguna fila seleccionada.");
		}
	}

	@FXML
	void btnRefrescar_OnClick(ActionEvent event) {
		comboBoxMotivo.setValue(null);
		fechaInicio.setValue(null);
		fechaFinal.setValue(null);
		Cambio.selectToggle(null);
		numTicket.setText(null);
		tbvRegistro.setItems(lista);
		listaFiltrada = null;
	}

	private void soloNumeros() {
		numTicket.setTextFormatter(new TextFormatter<>(cambio -> {
			String nuevoTexto = cambio.getControlNewText();
			if (nuevoTexto.matches("\\d*")) {
				return cambio;
			}
			return null;
		}));
	}

	private void generarReporte(File archivo) {
		Document document = new Document();

		try {
			// Hacer archivo
			PdfWriter.getInstance(document, new FileOutputStream(archivo));
			document.open();

			// Agregar titulo al reporte con tipo y tamaño de letra
			Font textoP = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24);
			Paragraph titulo = new Paragraph("Reporte de Devoluciones", textoP);
			titulo.setAlignment(Element.ALIGN_CENTER);
			document.add(titulo);
			document.add(new Paragraph("\n"));
			
			Font detallesFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
			Paragraph detalles = new Paragraph("Empresa: Librería Alyssum"
					+ "\nFecha de Reporte: " + formatoFecha(), detallesFont);
			detalles.setAlignment(Element.ALIGN_RIGHT);
			document.add(detalles);
			document.add(new Paragraph("\n"));

			// Hacer tabla
			PdfPTable table = new PdfPTable(7); // 7 columnas
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);

			// Agregar encabezados
			agregarCeldaEncabezado(table, "Número de Ticket");
			agregarCeldaEncabezado(table, "Nombre");
			agregarCeldaEncabezado(table, "Titulo");
			agregarCeldaEncabezado(table, "Fecha");
			agregarCeldaEncabezado(table, "Motivo");
			agregarCeldaEncabezado(table, "Detalle");
			agregarCeldaEncabezado(table, "Cambio");

			// LLenar la tabla con datos de devoluciones
			if (listaFiltrada == null) {
				for (Devolucion d : lista) {
					table.addCell(String.valueOf(d.getNumTicket()));
					table.addCell(d.getNombre());
					table.addCell(d.getTitulo());
					table.addCell(d.getFecha());
					table.addCell(d.getMotivo());
					table.addCell(d.getDetalle());
					table.addCell(d.getCambio());
				}
			} else {
				for (Devolucion d : listaFiltrada) {
					table.addCell(String.valueOf(d.getNumTicket()));
					table.addCell(d.getNombre());
					table.addCell(d.getTitulo());
					table.addCell(d.getFecha());
					table.addCell(d.getMotivo());
					table.addCell(d.getDetalle());
					table.addCell(d.getCambio());
				}
			}

			// Agregar la tabla al documento
			document.add(table);
			document.close();

			Alert alert = new Alert(AlertType.CONFIRMATION, "Se generó el reporte exitosamente");
			alert.showAndWait();
		} catch (FileNotFoundException e) {
			System.out.println("Ocurrio un error: " + e.getMessage());
		} catch (DocumentException e) {
			System.out.println("Ocurrio un error: " + e.getMessage());
		}
	}

	private void agregarCeldaEncabezado(PdfPTable table, String texto) {
		PdfPCell cell = new PdfPCell(new Phrase(texto, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
	}
	
	/**
     * 
	 * Este método da formato a la fecha como: Día-Mes-Año
	 * Regresando el formato en String para una mejor manipulación
	 * 
	 */
    private String formatoFecha() {
    	Date fecha = new Date();	//Instancia de fecha en tiempo real
    	SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    	String fechaFormateada = formato.format(fecha);
    	return fechaFormateada;
    }

	public void initialize() {
		soloNumeros();
		lista = DataBase.getElements();
		comboBoxMotivo.setItems(DataBase.getMotivos());
		tbvRegistro = new TableView<>(lista);
		tbvRegistro.getColumns().addAll(
				DevolucionModelo.getNumTicketColumn(),
				DevolucionModelo.getNameColumn(),
				DevolucionModelo.getTituloColumn(),
				DevolucionModelo.getFechaColumn(),
				DevolucionModelo.getMotivoColumn(),
				DevolucionModelo.getDetalleColumn(),
				DevolucionModelo.getCambioColumn());
		tbvRegistro.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		scrollDevolucion.setContent(tbvRegistro);
	}

}