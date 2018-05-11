package beadando.DAO;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Data access object class to manage the orders.
 */
public class OrderDAO {


    /**
     * Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(UserDAO.class);

    /**
     * Boolean to determine if the DAO is initialized or not.
     */
    private static boolean initialized = false;
    /**
     * The instance for the OrderDAO.
     */
    private static OrderDAO orderDAO;

    /**
     * EntityManager for the database.
     */
    private EntityManager entityManager;
    /**
     * Next Highest id for a new record.
     */
    private int nextRecordId = 0;

    /**
     * Constructor.
     */
    private OrderDAO() {
        Initialize();
    }

    /**
     * @return The OrderDAO instance.
     */
    public static OrderDAO getInstance() {
        if (!initialized)
            orderDAO = new OrderDAO();
        return orderDAO;
    }

    /**
     * Initializes The DAO.
     */
    private void Initialize() {
        try {
            logger.trace("OrderDAO initialization");
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyApp");
            entityManager = entityManagerFactory.createEntityManager();
            initialized = true;
        } catch (Exception e) {
            logger.error(e.toString());
            initialized = false;
        }
    }

    /**
     * Creates a new OrderModel record in the database with the parameters.
     *
     * @param order Ordermodel of a new order
     */
    public void NewOrder(OrderModel order)
    {
        order.setOrderId(getNextId());
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
        logger.info(String.format("New order added with id:%d",order.getOrderId()));

    }

    /**
     * @return all the orders in a List of Ordermodels from the database.
     */
    public List<OrderModel> GetAllRecord() {
        if (!initialized)
            return new ArrayList<>();
        TypedQuery<OrderModel> query = entityManager.createQuery("SELECT e FROM OrderModel e", OrderModel.class);
        return query.getResultList();
    }

    /**
     * @return The highest id +1
     */
    private int getNextId() {
        Optional<Integer> maxId = GetAllRecord().stream().map(OrderModel::getOrderId).max(Integer::compareTo);
        maxId.ifPresent(integer -> nextRecordId = integer + 1);
        return nextRecordId;
    }

}
