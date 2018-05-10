package beadando.DAO;

import beadando.models.PizzaModel;
import beadando.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PizzaDAO {

    private static Logger logger = LoggerFactory.getLogger(PizzaDAO.class);

    private static boolean initialized = false;
    private static PizzaDAO pizzaDAO;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private int nextRecordId = 0;

    private PizzaDAO() {
        Initialize();
    }

    public static PizzaDAO getInstance() {
        if (!initialized)
            pizzaDAO = new PizzaDAO();
        return pizzaDAO;
    }

    private boolean Initialize() {
        try {
            logger.trace("UserDAO initialization");
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

    public List<PizzaModel> GetAllRecord() {
        if (!initialized)
            return new ArrayList<>();
        TypedQuery<PizzaModel> query = entityManager.createQuery("SELECT e FROM PizzaModel e", PizzaModel.class);
        return query.getResultList();
    }

    private int getNextId() {
        Optional<Integer> maxId = GetAllRecord().stream().map(PizzaModel::getId).max(Integer::compareTo);
        maxId.ifPresent(integer -> nextRecordId = integer + 1);
        return nextRecordId;
    }

}
