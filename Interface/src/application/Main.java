package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Set;
import demo_jdbc.models.Race;
import demo_jdbc.respositories.RaceRepository;

public class Main extends Application {
	
	private RaceRepository raceRepository = new RaceRepository();
    private TableView<Race> raceTable = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
        try {
            // Crear los elementos de la UI
            Label yearLabel = new Label("Año:");
            ComboBox<Integer> yearComboBox = new ComboBox<>();
            
            // Obtener los años únicos de la base de datos
            Set<Integer> uniqueYears = raceRepository.getUniqueYears();
            yearComboBox.getItems().addAll(uniqueYears);
            yearComboBox.getSelectionModel().selectFirst();
            
            // Configurar la tabla
            TableColumn<Race, Integer> raceIdColumn = new TableColumn<>("Race ID");
            raceIdColumn.setCellValueFactory(new PropertyValueFactory<>("raceId"));
            TableColumn<Race, Integer> yearColumn = new TableColumn<>("Year");
            yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            TableColumn<Race, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            raceTable.getColumns().addAll(raceIdColumn, yearColumn, nameColumn);

            // Configurar el layout
            HBox yearBox = new HBox(10, yearLabel, yearComboBox);
            yearBox.setAlignment(Pos.CENTER_LEFT);
            yearBox.setPadding(new Insets(10));

            VBox vBox = new VBox(10, yearBox, raceTable);
            vBox.setPadding(new Insets(20));
            vBox.setAlignment(Pos.TOP_CENTER);

            BorderPane root = new BorderPane(vBox);
            Scene scene = new Scene(root, 600, 400);

            // Crear la escena y mostrar el escenario
            primaryStage.setTitle("Driver Standings");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Agregar listeners a los elementos de la UI
            yearComboBox.setOnAction(event -> updateTableData(yearComboBox.getValue()));

            // Inicializar la tabla con los datos del primer año
            updateTableData(yearComboBox.getValue());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTableData(int year) {
        List<Race> races = raceRepository.getRacesByYear(year);
        raceTable.getItems().setAll(races);
    }

    public static void main(String[] args) {
        launch(args);
    }
	
}
