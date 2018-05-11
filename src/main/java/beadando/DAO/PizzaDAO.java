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
            logger.trace("PizzaDAO initialization");
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyApp");
            entityManager = entityManagerFactory.createEntityManager();
            initialized = true;

        } catch (Exception e) {
            logger.error(e.toString());
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
