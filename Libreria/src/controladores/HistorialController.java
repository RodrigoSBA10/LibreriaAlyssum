/*
 * @(#)HistorialControlle.class		1.0 14/10/2024
 * 
 */
package controladores;

import java.time.LocalDate;
import java.util.function.Predicate;
import database.DataBase;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

	public void initialize() {
		soloNumeros();
		lista = DataBase.getElements();
		comboBoxMotivo.setItems(DataBase.getMotivos());
		tbvRegistro = new TableView<>(lista);
		tbvRegistro.getColumns().addAll(DevolucionModelo.getNumTicketColumn(), DevolucionModelo.getNameColumn(),
				DevolucionModelo.getTituloColumn(), DevolucionModelo.getFechaColumn(),
				DevolucionModelo.getMotivoColumn(), DevolucionModelo.getDetalleColumn(),
				DevolucionModelo.getCambioColumn());
		tbvRegistro.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		scrollDevolucion.setContent(tbvRegistro);
	}

}