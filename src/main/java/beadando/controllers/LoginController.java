package beadando.controllers;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * Controller for the login window.
 */
public class LoginController {
    /**
     * TextField for the username.
     */
    public TextField txt1;
    /**
     * PasswordField for the password.
     */
    public PasswordField txt2;

    /**
     * Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * Attempts to login with the given username and password.
     *
     * @param actionEvent javaFX event
     */
    public void login(ActionEvent actionEvent){

        UserDAO user = UserDAO.getInstance();

        if(user.UserLoginVerification(txt1.getText(), txt2.getText())) {
            logger.info(String.format("User: %s successfully logged in",txt1.getText()));
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("/fxml/Window.fxml"));
            } catch (IOException e) {
                logger.error(e.toString());
            }
            Scene scene = new Scene(parent);
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setTitle("Window");
            appStage.setScene(scene);
        }
        else {
            logger.trace(String.format("invalid login attempt with %s %s",txt1.getText(), txt2.getText()));

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Username or Password");
            alert.showAndWait();

        }

    }

    /**
     * Changes scene to the registration window.
     *
     * @param actionEvent javaFX event
     */
    public void register(ActionEvent actionEvent){
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/Registration.fxml"));
        } catch (IOException e) {
            logger.error(e.toString());
        }
        Scene scene = new Scene(parent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setTitle("Registration");
        appStage.setScene(scene);
        //appStage.show();

    }
}
