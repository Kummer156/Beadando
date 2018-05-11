package beadando;

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

import beadando.DAO.UserDAO;
import beadando.models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Controller for the registration window.
 */
public class RegistrationController {

    /**
     * Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    /**
     * Username for the registration.
     */
    public TextField unamebox;
    /**
     * Password for the registration.
     */
    public TextField pwbox1;
    /**
     * Password for confirmation.
     */
    public TextField pwbox2;
    /**
     * Email for the registration.
     */
    public TextField emailbox;
    /**
     * City name for the registration.
     */
    public TextField city;
    /**
     * House number for the registration.
     */
    public TextField hnumber;
    /**
     * Phone number for the registration.
     */
    public TextField phonenumber;
    /**
     * Street name for the registration.
     */
    public TextField street;

    /**
     * Creates a new UserModel for the registration.
     *
     * @return UserModel
     */
    private UserModel CreateUser()
    {
        UserModel user = new UserModel();

        user.setName(unamebox.getText());
        user.setPassword(pwbox1.getText());
        user.setEmail(emailbox.getText());
        user.setAddress(city.getText() + street.getText() + hnumber.getText());
        user.setPhonenumber(phonenumber.getText());

        return user;
    }


    /**
     * Opens the login view.
     *
     * @param actionEvent java
     */
    public void back(ActionEvent actionEvent){
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        } catch (IOException e) {
            logger.error(e.toString());
        }
        Scene scene = new Scene(parent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setTitle("Login");
        appStage.setScene(scene);
    }

    /**
     * Checks whether the user can register or not.
     *
     * @param actionEvent javaFX event
     */
    public void register(ActionEvent actionEvent){
        String alerts = "";

        UserDAO userDAO = UserDAO.getInstance();

        if (unamebox.getText().isEmpty())
        {
            alerts = alerts + "No username given\n";
        }
        else if(userDAO.IsUserRegistered(unamebox.getText()))
        {
            alerts = alerts + "Username already taken\n";
        }

        if(!pwbox1.getText().equals(pwbox2.getText()))
        {
            alerts = alerts + "Passwords are not matching\n";
        }
        if (emailbox.getText().isEmpty())
        {
            alerts = alerts + "Invalid email\n";
        }


        if (!alerts.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText(alerts);
            alert.showAndWait();
        }
        else
        {
            userDAO.RegisterUser(CreateUser());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration");
            alert.setHeaderText(null);
            alert.setContentText("Succesful Registration");
            alert.showAndWait();
            back(actionEvent);
        }
    }
}
