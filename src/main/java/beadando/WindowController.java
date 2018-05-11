package beadando;

import beadando.DAO.OrderDAO;
import beadando.DAO.PizzaDAO;
import beadando.DAO.UserDAO;
import beadando.models.OrderModel;
import beadando.models.PizzaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller for the Application.
 */
public class WindowController implements Initializable {

    /**
     * Data access object for the UserModel.
     */
    private UserDAO userDAO = UserDAO.getInstance();
    /**
     * Data access object for the PizzaModel.
     */
    private PizzaDAO pizzaDAO = PizzaDAO.getInstance();
    /**
     * Data access object for the OrderModel.
     */
    private OrderDAO orderDAO = OrderDAO.getInstance();
    /**
     * TableView for the Window.fxml.
     */
    public TableView table1;
    /**
     * TableView for the Window.fxml.
     */
    public TableView table2;
    /**
     * An ObservableList<PizzaModel> for storing the items on the menu.
     */
    private ObservableList<PizzaModel> data = FXCollections.observableArrayList(pizzaDAO.GetAllRecord());
    /**
     * An ObservableList<PizzaModel> for storing the items currently in the cart.
     */
    private ObservableList<PizzaModel> cart = FXCollections.observableArrayList();

    /**
     * @param location URL
     * @param resources ResourceBundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BuildMenuTable();
        BuildCartTable();
        System.out.println(userDAO.GetLoggedInUser());
    }

    /**
     * Creates the table for the menu.
     */
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

    /**
     * Creates the cart table.
     */
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


    /**
     * Refreshes the table on switching tabs.
     *
     * @param event javaFX event
     */
    public void refresh(Event event) {
        table2.setItems(cart);
        table2.refresh();
    }

    /**
     * Creates the OrderModel for the order.
     */
    private void CreateOrder()
    {
        String items = "";
        for (PizzaModel pz:cart)
        {
            if (items.isEmpty())
                items = items + pz.getId();
            else
                items = items + ", " +pz.getId();
        }

        OrderModel order = new OrderModel();
        order.setUser(userDAO.GetLoggedInUser());
        order.setItemIds(items);
        orderDAO.NewOrder(order);
    }

    /**
     * Places the order for the currently logged in user then clears the items from the cart.
     *
     * @param actionEvent javaFX event
     */
    public void PlaceOrder(ActionEvent actionEvent) {

        CreateOrder();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order");
        alert.setHeaderText(null);
        alert.setContentText("Order completed");
        alert.showAndWait();

        cart.removeAll(cart);
        table2.setItems(cart);
        table2.refresh();


    }

    /**
     * Method for users to log out and get to the login screen.
     *
     * @param actionEvent javaFX event
     * @throws IOException a
     */
    public void Logout(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(parent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setTitle("Login");
        appStage.setScene(scene);
    }
}
