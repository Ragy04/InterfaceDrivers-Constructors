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

import java.util.List;
import demo_jdbc.models.Constructor;
import demo_jdbc.respositories.ConstructorRepository;

public class MainConstructor extends Application {
	
	private ConstructorRepository constructorRepository = new ConstructorRepository();
    private TableView<Constructor> constructorTable = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
        try {
            // Configurar la tabla
            TableColumn<Constructor, Integer> constructorIdColumn = new TableColumn<>("Constructor ID");
            constructorIdColumn.setCellValueFactory(new PropertyValueFactory<>("constructorId"));
            TableColumn<Constructor, String> constructorRefColumn = new TableColumn<>("Constructor Ref");
            constructorRefColumn.setCellValueFactory(new PropertyValueFactory<>("constructorRef"));
            TableColumn<Constructor, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Constructor, String> nationalityColumn = new TableColumn<>("Nationality");
            nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
            TableColumn<Constructor, String> urlColumn = new TableColumn<>("URL");
            urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));

            constructorTable.getColumns().addAll(constructorIdColumn, constructorRefColumn, nameColumn, nationalityColumn, urlColumn);

            // Configurar el layout
            VBox vBox = new VBox(10, constructorTable);
            vBox.setPadding(new Insets(20));
            vBox.setAlignment(Pos.TOP_CENTER);

            BorderPane root = new BorderPane(vBox);
            Scene scene = new Scene(root, 600, 400);

            // Crear la escena y mostrar el escenario
            primaryStage.setTitle("Constructor Standings");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Inicializar la tabla con los datos de los constructores
            updateTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTableData() {
        List<Constructor> constructors = constructorRepository.getAllConstructors();
        constructorTable.getItems().setAll(constructors);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
