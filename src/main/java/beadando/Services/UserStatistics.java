package beadando.Services;

import beadando.models.OrderModel;
import beadando.models.UserModel;

import java.util.List;

/**
 * Class for calculating user statistics.
 */
public class UserStatistics {

    /**
     * @param orders All orders from the database
     * @param user Usermodel for the current user
     * @return The total number of orders.
     */
    public static int CalculateTotalNumberOfOrders(List<OrderModel> orders, UserModel user)
    {
        return ((int) orders.stream().filter(e -> e.getUser().getId() == user.getId()).count());
    }

    /**
     * @param orders All orders from the database
     * @param user Usermodel for the current user
     * @return The total amount of money spent on orders
     */
    public static int CalculateTotalMoneySpent(List<OrderModel> orders,UserModel user)
    {
        int sum = 0;
        for(OrderModel o:orders)
        {
            if (o.getUser().getId() == user.getId())
                sum += o.getPrice();
        }
        return sum;
    }

    /**
     * Calculates the average money spent on orders by the user.
     *
     * @param orders All orders from the database
     * @param user Usermodel for the current user
     * @return The average money spent on orders
     */
    public static double CalculateAverageMoneySpent(List<OrderModel> orders, UserModel user)
    {
        if (CalculateTotalNumberOfOrders(orders, user) == 0)
            return 0;
        else
            return (double) CalculateTotalMoneySpent(orders, user)/(double) CalculateTotalNumberOfOrders(orders, user);
    }

}
