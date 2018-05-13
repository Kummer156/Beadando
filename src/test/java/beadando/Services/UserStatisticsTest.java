package beadando.Services;

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

import beadando.models.OrderModel;
import beadando.models.PizzaModel;
import beadando.models.UserModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserStatisticsTest {

    private List<OrderModel> orders = new ArrayList<>();
    private List<PizzaModel> menu = new ArrayList<>();
    private UserModel testUser = new UserModel();
    private UserModel testUser2 = new UserModel();


    @Before
    public void setUp() throws Exception {

        PizzaModel testPizza = new PizzaModel();

        testPizza.setPrice(300);
        testPizza.setPizza("Pizza1");
        testPizza.setId(0);
        testPizza.setDesc("desc");

        testUser.setId(0);
        testUser.setName("User");
        testUser.setPassword("Pw");
        testUser.setEmail("TestEmail");
        testUser.setAddress("TestAddress");
        testUser.setPhonenumber("phone");

        testUser2.setId(1);
        testUser2.setName("User1");
        testUser2.setPassword("Pw1");
        testUser2.setEmail("TestEmail");
        testUser2.setAddress("TestAddress");
        testUser2.setPhonenumber("phone");

        orders.add(new OrderModel(0,testUser,"1, 1, 3, 4",1500));
        orders.add(new OrderModel(1,testUser,"1, 0, 3, 4",900));
        orders.add(new OrderModel(2,testUser2,"1, 5, 2, 4",500));
        orders.add(new OrderModel(3,testUser,"1, 2, 3, 4",2000));

        menu.add(testPizza);
        testPizza.setPizza("Pizza1");
        testPizza.setId(1);
        menu.add(testPizza);
        testPizza.setPizza("Pizza2");
        testPizza.setId(2);
        menu.add(testPizza);
        testPizza.setPizza("Pizza3");
        testPizza.setId(3);
        menu.add(testPizza);
        testPizza.setPizza("Pizza4");
        testPizza.setId(4);
        menu.add(testPizza);
        testPizza.setPizza("Pizza5");
        testPizza.setId(5);
    }

    @Test
    public void calculateTotalNumberOfOrders() {
        assertEquals(3,UserStatistics.CalculateTotalNumberOfOrders(orders,testUser));
        assertEquals(1,UserStatistics.CalculateTotalNumberOfOrders(orders,testUser2));
        assertEquals(0,UserStatistics.CalculateTotalNumberOfOrders(new ArrayList<>(),testUser));
    }

    @Test
    public void calculateTotalMoneySpent() {
        assertEquals(4400,UserStatistics.CalculateTotalMoneySpent(orders,testUser));
        assertEquals(500,UserStatistics.CalculateTotalMoneySpent(orders,testUser2));
        assertEquals(0,UserStatistics.CalculateTotalNumberOfOrders(new ArrayList<>(),testUser));
    }

    @Test
    public void calculateAverageMoneySpent() {
        assertEquals(4400.0/3.0,UserStatistics.CalculateAverageMoneySpent(orders,testUser),0.1);
        assertEquals(500.0/1.0,UserStatistics.CalculateAverageMoneySpent(orders,testUser2),0.1);
        assertEquals(0,UserStatistics.CalculateTotalNumberOfOrders(new ArrayList<>(),testUser));
    }

    @Test
    public void mostExpensiveOrder() {
        assertEquals(2000,UserStatistics.MostExpensiveOrder(orders,testUser));
        assertEquals(500,UserStatistics.MostExpensiveOrder(orders,testUser2));
        assertEquals(0,UserStatistics.MostExpensiveOrder(new ArrayList<>(),testUser));
    }
}
