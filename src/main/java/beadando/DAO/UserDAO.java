package beadando.DAO;

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

/**
 * Data access object class to manage Users
 */
public class UserDAO {

    private static Logger logger = LoggerFactory.getLogger(UserDAO.class);

    private static boolean initialized = false;
    private static UserDAO userDAO;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private int nextRecordId = 0;

    private UserDAO() {
        Initialize();
    }

    public static UserDAO getInstance() {
        if (!initialized)
            userDAO = new UserDAO();
        return userDAO;
    }

    private boolean Initialize() {
        try {
            logger.trace("UserDAO initialization");
            entityManagerFactory = Persistence.createEntityManagerFactory("AppPU");
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

    public List<UserModel> GetAllRecord() {
        if (!initialized)
            return new ArrayList<>();
        TypedQuery<UserModel> query = entityManager.createQuery("SELECT e FROM UserModel e", UserModel.class);
        return query.getResultList();
    }


    /**
     *  Returns a boolean weather there is a registered user with the given username argument
     *
     * @param username name of the user
     * @return true if the username is present in the database
     */
    public boolean IsUserRegistered(String username) {
        return GetAllRecord().stream().anyMatch(e-> e.getName().equals(username));
    }


    /**
     * Returns a boolean
     *
     * @param username Login username
     * @param password Password for the login
     * @return  true if the username password pair is in the database
     */
    public boolean UserLoginVerification(String username, String password)
    {
        return GetAllRecord().stream().anyMatch(e-> e.getName().equals(username) && e.getPassword().equals(password));
    }

    public void RegisterUser(UserModel user)
    {
        user.setId(getNextId());
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        logger.trace(String.format("User registered with id:%d",user.getId()));
    }

    private int getNextId() {
        Optional<Integer> maxId = GetAllRecord().stream().map(UserModel::getId).max(Integer::compareTo);
        maxId.ifPresent(integer -> nextRecordId = integer + 1);
        return nextRecordId;
    }

}



