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
