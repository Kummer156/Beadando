package beadando.controllers;

import beadando.models.PizzaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

import static org.junit.Assert.*;

public class WindowControllerTest {

    @Test
    public void calculateCartPrice() {
        ObservableList<PizzaModel> cart = FXCollections.observableArrayList();
        PizzaModel pz = new PizzaModel();
        pz.setDesc("desc");
        pz.setId(0);
        pz.setPizza("Pizza");
        pz.setPrice(500);
        cart.add(pz);
        pz.setPrice(1300);
        cart.add(pz);
        pz.setPrice(900);
        cart.add(pz);

        assertEquals(2700,new WindowController().CalculateCartPrice(cart));
    }
}