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
import org.apache.commons.lang.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class for calculating user statistics.
 */
public class UserStatistics {

    /**
     * @param orders All orders from the database
     * @param user   Usermodel for the current user
     * @return The total number of orders.
     */
    public static int CalculateTotalNumberOfOrders(List<OrderModel> orders, UserModel user) {
        return ((int) orders.stream().filter(e -> e.getUser().getId() == user.getId()).count());
    }

    /**
     * @param orders All orders from the database
     * @param user   Usermodel for the current user
     * @return The total amount of money spent on orders
     */
    public static int CalculateTotalMoneySpent(List<OrderModel> orders, UserModel user) {
        int sum = 0;
        for (OrderModel o : orders) {
            if (o.getUser().getId() == user.getId())
                sum += o.getPrice();
        }
        return sum;
    }

    /**
     * Calculates the average money spent on orders by the user.
     *
     * @param orders All orders from the database
     * @param user   Usermodel for the current user
     * @return The average money spent on orders
     */
    public static double CalculateAverageMoneySpent(List<OrderModel> orders, UserModel user) {
        if (CalculateTotalNumberOfOrders(orders, user) == 0)
            return 0;
        else
            return (double) CalculateTotalMoneySpent(orders, user) / (double) CalculateTotalNumberOfOrders(orders, user);
    }

    /**
     * @param orders All the orders from the databese
     * @param user Usermodel for the current user
     * @return The price of the most expensive order by the user
     */
    public static int MostExpensiveOrder(List<OrderModel> orders, UserModel user) {
        final OrderModel[] result = {null};
        orders.stream().filter(e -> e.getUser().getId() == user.getId()).max(Comparator.comparingInt(OrderModel::getPrice)).ifPresent(e -> result[0] = e);
        if (result[0] == null)
            return 0;
        return result[0].getPrice();
    }

    /**
     * @param orders All the orders from the databese
     * @param user Usermodel for the current user
     * @param menu List of all available pizza
     * @return The name of the pizza that was ordered the most by the user
     */
    public static String FavouriteItem(List<OrderModel> orders, UserModel user, List<PizzaModel> menu) {
        final String[] allItemsOrdered = {""};
        int id = 0;
        int idOcc = 0;
        orders.stream().filter(e -> e.getUser().getId() == user.getId()).forEach(e -> allItemsOrdered[0] += e.getItemIds() + ", ");
        for (PizzaModel pz : menu)
        {
            int tempOcc;
            tempOcc = StringUtils.countMatches(allItemsOrdered[0], Integer.toString(pz.getId()));
            if (tempOcc > idOcc)
            {
                idOcc = tempOcc;
                id = pz.getId();
            }
        }
        int finalId = id;
        final PizzaModel[] result = new PizzaModel[1];
        menu.stream().filter(e-> e.getId() == finalId).findAny().ifPresent(e-> result[0] = e);
        return result[0].getPizza();
    }

}
