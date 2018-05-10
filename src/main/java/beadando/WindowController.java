package beadando;

import beadando.DAO.PizzaDAO;
import beadando.models.PizzaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class WindowController implements Initializable {

    private PizzaDAO pizzaDAO = PizzaDAO.getInstance();
    @FXML
    public TableView table1;

    private ObservableList<PizzaModel> data = FXCollections.observableArrayList(pizzaDAO.GetAllRecord());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BuildTable();
        //CreateRecord();
    }

    private void BuildTable() {
        table1.setItems(data);

        TableColumn name = new TableColumn("Pizza");
        name.setCellValueFactory(new PropertyValueFactory<PizzaModel, String>("pizza"));
        TableColumn description = new TableColumn("description");
        description.setCellValueFactory(new PropertyValueFactory<PizzaModel, String>("desc"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<PizzaModel, Integer>("price"));

        table1.getColumns().addAll(name, description, price);

    }

    private void CreateRecord()
    {
        List<PizzaModel> records = pizzaDAO.GetAllRecord();
        for(PizzaModel r:records)
        {
            table1.getItems().add(r);
        }
    }



}
