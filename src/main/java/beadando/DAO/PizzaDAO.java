package beadando.DAO;

import beadando.models.PizzaModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object class to manage the menu.
 */
public class PizzaDAO {

    /**
     * Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(PizzaDAO.class);

    /**
     * Boolean to determine if the DAO is initialized or not.
     */
    private static boolean initialized = false;
    /**
     * The instance for the PizzaDAO.
     */
    private static PizzaDAO pizzaDAO;

    /**
     * EntityManager for the database.
     */
    private EntityManager entityManager;

    /**
     * Constructor.
     */
    private PizzaDAO() {
        Initialize();
    }

    /**
     * @return The PizzaDAO instance.
     */
    public static PizzaDAO getInstance() {
        if (!initialized)
            pizzaDAO = new PizzaDAO();
        return pizzaDAO;
    }

    /**
     * Initializes the DAO.
     */
    private void Initialize() {
        try {
            logger.trace("UserDAO initialization");
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyApp");
            entityManager = entityManagerFactory.createEntityManager();
            initialized = true;

        } catch (Exception e) {
            System.out.println(e.toString());
            initialized = false;
        }
    }

    /**
     * @return A list of PizzaModels that are in the database.
     */
    public List<PizzaModel> GetAllRecord() {
        if (!initialized)
            return new ArrayList<>();
        TypedQuery<PizzaModel> query = entityManager.createQuery("SELECT e FROM PizzaModel e", PizzaModel.class);
        return query.getResultList();
    }

}
