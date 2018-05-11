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
 * Data access object class to manage Users.
 */
public class UserDAO {

    /**
     * Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(UserDAO.class);

    /**
     * Boolean to determine if the DAO is initialized or not.
     */
    private static boolean initialized = false;
    /**
     * The data access object.
     */
    private static UserDAO userDAO;

    /**
     * UserModel for the currently logged in user.
     */
    private static UserModel loggedInUser;

    /**
     * EntityManager for the database.
     */
    private EntityManager entityManager;
    /**
     * Next highest possible ID.
     */
    private int nextRecordId = 0;

    /**
     * Constructor.
     */
    private UserDAO() {
        Initialize();
    }

    /**
     * @return The UserDAO instance.
     */
    public static UserDAO getInstance() {
        if (!initialized)
            userDAO = new UserDAO();
        return userDAO;
    }

    /**
     * Initializes The DAO.
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
     * @return Returns true if the DAO is initialized.
     */
    public boolean IsInitialized() {
        return initialized;
    }

    /**
     * @return all the users in a List of UserModels from the database.
     */
    public List<UserModel> GetAllRecord() {
        if (!initialized)
            return new ArrayList<>();
        TypedQuery<UserModel> query = entityManager.createQuery("SELECT e FROM UserModel e", UserModel.class);
        return query.getResultList();
    }


    /**
     *  Returns a boolean whether there is a registered user with the given username argument.
     *
     * @param username name of the user
     * @return true if the username is present in the database
     */
    public boolean IsUserRegistered(String username) {
        return GetAllRecord().stream().anyMatch(e-> e.getName().equals(username));
    }


    /**
     * Checks whether the user can login or not and sets the currently logged in user.
     *
     * @param username Login username
     * @param password Password for the login
     * @return  true if the username password pair is in the database
     */
    public boolean UserLoginVerification(String username, String password)
    {
        GetAllRecord().stream().filter(e -> e.getName().equals(username)).findFirst().ifPresent(e-> loggedInUser = e);
        return GetAllRecord().stream().anyMatch(e-> e.getName().equals(username) && e.getPassword().equals(password));
    }

    /**
     * registers the user to the database.
     *
     * @param user Usermodel
     */
    public void RegisterUser(UserModel user)
    {
        user.setId(getNextId());
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        logger.trace(String.format("User registered with id:%d",user.getId()));
    }

    /**
     * @return The currently logged in user
     */
    public UserModel GetLoggedInUser()
    {
        return loggedInUser;
    }

    /**
     * @return The highest id +1
     */
    private int getNextId() {
        Optional<Integer> maxId = GetAllRecord().stream().map(UserModel::getId).max(Integer::compareTo);
        maxId.ifPresent(integer -> nextRecordId = integer + 1);
        return nextRecordId;
    }

}



