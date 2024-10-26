module ProyectoAlyssum {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;
	requires java.sql;
	requires ojdbc14;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base;
	opens controllers to javafx.fxml;
	opens poo to javafx.fxml, javafx.base;
}
