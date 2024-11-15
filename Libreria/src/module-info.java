module Libreria {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires java.sql.rowset;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
	opens controladores to javafx.graphics, javafx.fxml, javafx.base;
	opens database to javafx.graphics, javafx.fxml, javafx.base;
	opens modelo to javafx.graphics, javafx.fxml, javafx.base;
}
