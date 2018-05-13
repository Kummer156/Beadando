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

import beadando.models.UserModel;
import javafx.application.Platform;
import javafx.scene.control.Alert;
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

        try{
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyApp");
        }
        catch (Exception e)
        {
            logger.error("No database connection");
            initialized = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database error");
            alert.setHeaderText(null);
            alert.setContentText("No database connection, closing application");
            alert.showAndWait();
            Platform.exit();
            System.exit(0);
        }

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
            logger.error(e.toString());
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
        logger.info(String.format("User registered with id:%d",user.getId()));
    }

    /**
     * Deletes the user from the database.
     *
     * @param user Usermodel
     */
    public void DeleteUser(UserModel user)
    {
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        logger.info(String.format("User deleted with id:%d",user.getId()));
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



