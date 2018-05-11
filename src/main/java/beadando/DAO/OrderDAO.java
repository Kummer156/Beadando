package beadando.DAO;

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
            System.out.println(e.toString());
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
