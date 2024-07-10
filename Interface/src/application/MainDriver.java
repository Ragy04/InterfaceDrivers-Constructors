package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.List;
import demo_jdbc.models.Driver;
import demo_jdbc.respositories.DriverRepository;

public class MainDriver extends Application {
	
	private DriverRepository driverRepository = new DriverRepository();
    private TableView<Driver> driverTable = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
        try {
            // Configurar la tabla
            TableColumn<Driver, Integer> driverIdColumn = new TableColumn<>("Driver ID");
            driverIdColumn.setCellValueFactory(new PropertyValueFactory<>("driverId"));
            TableColumn<Driver, String> driverRefColumn = new TableColumn<>("Driver Ref");
            driverRefColumn.setCellValueFactory(new PropertyValueFactory<>("driverRef"));
            TableColumn<Driver, Integer> numberColumn = new TableColumn<>("Number");
            numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
            TableColumn<Driver, String> codeColumn = new TableColumn<>("Code");
            codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
            TableColumn<Driver, String> forenameColumn = new TableColumn<>("Forename");
            forenameColumn.setCellValueFactory(new PropertyValueFactory<>("forename"));
            TableColumn<Driver, String> surnameColumn = new TableColumn<>("Surname");
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
            TableColumn<Driver, Date> dobColumn = new TableColumn<>("Date of Birth");
            dobColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
            TableColumn<Driver, String> nationalityColumn = new TableColumn<>("Nationality");
            nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
            TableColumn<Driver, String> urlColumn = new TableColumn<>("URL");
            urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));

            driverTable.getColumns().addAll(driverIdColumn, driverRefColumn, numberColumn, codeColumn, forenameColumn, surnameColumn, dobColumn, nationalityColumn, urlColumn);

            // Configurar el layout
            VBox vBox = new VBox(10, driverTable);
            vBox.setPadding(new Insets(20));
            vBox.setAlignment(Pos.TOP_CENTER);

            BorderPane root = new BorderPane(vBox);
            Scene scene = new Scene(root, 800, 400);

            // Crear la escena y mostrar el escenario
            primaryStage.setTitle("Driver Standings");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Inicializar la tabla con los datos de los conductores
            updateTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTableData() {
        List<Driver> drivers = driverRepository.getAllDrivers();
        driverTable.getItems().setAll(drivers);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
