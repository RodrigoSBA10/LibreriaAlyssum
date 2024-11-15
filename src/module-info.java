module Libreria {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires javafx.media;
	requires transitive itextpdf;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
	opens controladores to javafx.graphics, javafx.fxml, javafx.base;
	opens modelo to javafx.graphics, javafx.fxml, javafx.base;
}
