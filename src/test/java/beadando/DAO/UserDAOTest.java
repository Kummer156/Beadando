package beadando.DAO;

import beadando.models.UserModel;
import org.firebirdsql.management.User;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class UserDAOTest {

    private EntityManager entityManager;
    private UserModel user = new UserModel();
    private static UserModel loggedInUser;

    @Before
    public void setUp() throws Exception {

        boolean databaseConnection = false;

        try {

            user.setId(-1);
            user.setName("User");
            user.setPassword("Pw");
            user.setEmail("TestEmail");
            user.setAddress("TestAddress");
            user.setPhonenumber("phone");

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyApp");
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            databaseConnection = true;

        }
        catch (Exception e)
        {

        }

        Assume.assumeTrue(databaseConnection);

    }

    @After
    public void tearDown() throws Exception {

        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();

    }

    @Test
    public void isUserRegistered() {

        assertTrue(UserDAO.getInstance().IsUserRegistered(user.getName()));
    }

    @Test
    public void userLoginVerification() {

        assertTrue(UserDAO.getInstance().UserLoginVerification(user.getName(),user.getPassword()));
    }

    @Test
    public void registerUserAndDeleteUser() {
        UserDAO userDAO = UserDAO.getInstance();
        UserModel tempUser = new UserModel();

        tempUser.setName("TempUser");
        tempUser.setPassword("randomtext");
        tempUser.setEmail("TestEmail2");
        tempUser.setAddress("TestAddress");
        tempUser.setPhonenumber("phone");

        userDAO.RegisterUser(tempUser);
        assertTrue(userDAO.IsUserRegistered(tempUser.getName()));
        userDAO.DeleteUser(tempUser);
        assertFalse(userDAO.IsUserRegistered(tempUser.getName()));
    }
}