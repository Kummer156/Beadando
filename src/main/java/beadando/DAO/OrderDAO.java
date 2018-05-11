package beadando.DAO;

import beadando.models.OrderModel;
import beadando.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAO {


    private static Logger logger = LoggerFactory.getLogger(UserDAO.class);

    private static boolean initialized = false;
    private static OrderDAO orderDAO;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private int nextRecordId = 0;

    private OrderDAO() {
        Initialize();
    }

    public static OrderDAO getInstance() {
        if (!initialized)
            orderDAO = new OrderDAO();
        return orderDAO;
    }

    private boolean Initialize() {
        try {
            logger.trace("OrderDAO initialization");
            entityManagerFactory = Persistence.createEntityManagerFactory("MyApp");
            entityManager = entityManagerFactory.createEntityManager();
            initialized = true;
        } catch (Exception e) {
            System.out.println(e.toString());
            initialized = false;
        }
        return initialized;
    }

    public boolean IsInitialized() {
        return initialized;
    }

    public void NewOrder(OrderModel order)
    {
        order.setOrderId(getNextId());
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();

    }

    public List<OrderModel> GetAllRecord() {
        if (!initialized)
            return new ArrayList<>();
        TypedQuery<OrderModel> query = entityManager.createQuery("SELECT e FROM OrderModel e", OrderModel.class);
        return query.getResultList();
    }

    private int getNextId() {
        Optional<Integer> maxId = GetAllRecord().stream().map(OrderModel::getOrderId).max(Integer::compareTo);
        maxId.ifPresent(integer -> nextRecordId = integer + 1);
        return nextRecordId;
    }

}
