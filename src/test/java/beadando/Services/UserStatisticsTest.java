package beadando.Services;

import beadando.models.OrderModel;
import beadando.models.UserModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserStatisticsTest {

    private List<OrderModel> orders = new ArrayList<>();
    private UserModel testUser = new UserModel();
    private UserModel testUser2 = new UserModel();


    @Before
    public void setUp() throws Exception {

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

        System.out.println(testUser.toString() + testUser2.toString());

        orders.add(new OrderModel(0,testUser,"1, 1, 3, 4",1500));
        orders.add(new OrderModel(1,testUser,"1, 0, 3, 4",900));
        orders.add(new OrderModel(2,testUser2,"1, 5, 2, 4",500));
        orders.add(new OrderModel(3,testUser,"1, 2, 3, 4",2000));

    }

    @Test
    public void calculateTotalNumberOfOrders() {
        System.out.println(UserStatistics.CalculateTotalNumberOfOrders(orders,testUser));
        assertEquals(3,UserStatistics.CalculateTotalNumberOfOrders(orders,testUser));
        assertEquals(1,UserStatistics.CalculateTotalNumberOfOrders(orders,testUser2));
    }

    @Test
    public void calculateTotalMoneySpent() {
        assertEquals(4400,UserStatistics.CalculateTotalMoneySpent(orders,testUser));
        assertEquals(500,UserStatistics.CalculateTotalMoneySpent(orders,testUser2));
    }

    @Test
    public void calculateAverageMoneySpent() {
        assertEquals(4400.0/3.0,UserStatistics.CalculateAverageMoneySpent(orders,testUser),0.1);
        assertEquals(500.0/1.0,UserStatistics.CalculateAverageMoneySpent(orders,testUser2),0.1);
    }
}