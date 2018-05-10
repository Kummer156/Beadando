package beadando;

import beadando.DAO.PizzaDAO;
import beadando.models.PizzaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ResourceBundle;


public class WindowController implements Initializable {

    private PizzaDAO pizzaDAO = PizzaDAO.getInstance();
    @FXML
    public TableView table1;
    public TableView table2;
    private ObservableList<PizzaModel> data = FXCollections.observableArrayList(pizzaDAO.GetAllRecord());
    private ObservableList<PizzaModel> cart = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BuildMenuTable();
        BuildCartTable();
    }

    private void BuildMenuTable() {

        table1.setItems(data);

        TableColumn name = new TableColumn("Pizza");
        name.setCellValueFactory(new PropertyValueFactory<PizzaModel, String>("pizza"));
        TableColumn description = new TableColumn("Description");
        description.setCellValueFactory(new PropertyValueFactory<PizzaModel, String>("desc"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<PizzaModel, Integer>("price"));

        TableColumn button = new TableColumn("Action");
        button.setCellValueFactory(new PropertyValueFactory<PizzaModel, String>("button"));


        Callback<TableColumn<PizzaModel, String>, TableCell<PizzaModel, String>> cellFactory
                = //
                new Callback<TableColumn<PizzaModel, String>, TableCell<PizzaModel, String>>() {
                    @Override
                    public TableCell call(TableColumn<PizzaModel, String> param) {
                        TableCell<PizzaModel, String> cell = new TableCell<PizzaModel, String>() {

                            Button btn = new Button("Add to cart");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        PizzaModel pz = getTableView().getItems().get(getIndex());
                                        cart.add(pz);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        button.setCellFactory(cellFactory);

        table1.getColumns().addAll(name, description, price, button);
    }

    public void BuildCartTable()
    {
        TableColumn name = new TableColumn("Pizza");
        name.setCellValueFactory(new PropertyValueFactory<PizzaModel, String>("pizza"));
        TableColumn description = new TableColumn("Description");
        description.setCellValueFactory(new PropertyValueFactory<PizzaModel, String>("desc"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<PizzaModel, Integer>("price"));

        TableColumn button = new TableColumn("Action");
        button.setCellValueFactory(new PropertyValueFactory<PizzaModel, String>("button"));


        Callback<TableColumn<PizzaModel, String>, TableCell<PizzaModel, String>> cellFactory
                = //
                new Callback<TableColumn<PizzaModel, String>, TableCell<PizzaModel, String>>() {
                    @Override
                    public TableCell call(TableColumn<PizzaModel, String> param) {
                        TableCell<PizzaModel, String> cell = new TableCell<PizzaModel, String>() {

                            Button btn = new Button("X");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        cart.remove(getIndex());
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        button.setCellFactory(cellFactory);

        table2.getColumns().addAll(name, description, price, button);
    }


    public void refresh(Event event) {
        table2.setItems(cart);
        table2.refresh();
    }

    public void PlaceOrder(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order");
        alert.setHeaderText(null);
        alert.setContentText("Order completed");
        alert.showAndWait();

        cart.removeAll(cart);
        table2.setItems(cart);
        table2.refresh();


    }
}
