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
