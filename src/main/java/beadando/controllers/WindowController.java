package beadando.controllers;

/*-
 * #%L
 * Beadando
 * %%
 * Copyright (C) 2016 - 2018 Faculty of Informatics, University of Debrecen
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import beadando.DAO.OrderDAO;
import beadando.DAO.PizzaDAO;
import beadando.DAO.UserDAO;
import beadando.models.OrderModel;
import beadando.models.PizzaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller for the Application.
 */
public class WindowController implements Initializable {
    /**
     * Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(WindowController.class);
    /**
     * Label for total price.
     */
    public Label total;
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
     * @param location  URL
     * @param resources ResourceBundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BuildMenuTable();
        BuildCartTable();
    }

    /**
     * Creates the table for the menu.
     */
    private void BuildMenuTable() {

        logger.trace("Building menu table");

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
                                        total.setText("Total price:" + CalculateCartPrice(cart));
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
    private void BuildCartTable() {
        logger.trace("Building cart table");
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
                                        total.setText("Total price:" + CalculateCartPrice(cart));
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
     * Calculates the total price.
     *
     * @param cart ObservableList<PizzaModel>
     * @return The total price of items in the cart
     */
    public int CalculateCartPrice(ObservableList<PizzaModel> cart)
    {
        int price = 0;
        for(PizzaModel pz:cart)
            price += pz.getPrice();

        return price;
    }

    /**
     * Creates the OrderModel for the order.
     */
    private void CreateOrder() {
        StringBuilder items = new StringBuilder();
        for (PizzaModel pz : cart) {
            if (items.length() == 0)
                items.append(pz.getId());
            else
                items.append(", ").append(pz.getId());
        }

        OrderModel order = new OrderModel();
        order.setUser(userDAO.GetLoggedInUser());
        order.setItemIds(items.toString());
        orderDAO.NewOrder(order);
    }

    /**
     * Places the order for the currently logged in user then clears the items from the cart.
     *
     * @param actionEvent javaFX event
     */
    public void PlaceOrder(ActionEvent actionEvent) {

        if (cart.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Order");
            alert.setHeaderText(null);
            alert.setContentText("There is nothing to order");
            alert.showAndWait();
        } else {
            CreateOrder();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order");
            alert.setHeaderText(null);
            alert.setContentText("Order completed");
            alert.showAndWait();

            cart.removeAll(cart);
            table2.setItems(cart);
            total.setText("Total price:" + CalculateCartPrice(cart));
            table2.refresh();

        }
    }

    /**
     * Method for users to log out and get to the login screen.
     *
     * @param actionEvent javaFX event
     */
    public void Logout(ActionEvent actionEvent) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        } catch (IOException e) {
            logger.error(e.toString());
        }
        Scene scene = new Scene(parent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setTitle("Login");
        appStage.setScene(scene);
    }
}
