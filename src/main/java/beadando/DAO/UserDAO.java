package beadando.DAO;

import beadando.models.UserModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

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

    public boolean IsUserRegistered(String username) {
        return GetAllRecord().stream().anyMatch(e-> e.getName().equals(username));
    }

    public void RegisterUser(UserModel user)
    {
        user.setId(getNextId());
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    private int getNextId() {
        Optional<Integer> maxId = GetAllRecord().stream().map(UserModel::getId).max(Integer::compareTo);
        maxId.ifPresent(integer -> nextRecordId = integer + 1);
        return nextRecordId;
    }

    public void Save(UserModel record) {

    }
}



