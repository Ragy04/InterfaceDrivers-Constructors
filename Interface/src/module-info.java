module Interface {
	requires javafx.controls;
	requires java.sql;
	requires java.desktop;
	requires javafx.fxml;
	
	opens demo_jdbc.models to javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
    
    exports demo_jdbc.models;
}
