package beadando;

import beadando.DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
     * @throws IOException exception
     */
    public void login(ActionEvent actionEvent) throws IOException {

        UserDAO user = UserDAO.getInstance();
        user = UserDAO.getInstance();

        if(user.UserLoginVerification(txt1.getText(), txt2.getText())) {
            logger.trace(String.format("Login attempt with %s %s",txt1.getText(), txt2.getText()));
            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Window.fxml"));
            Scene scene = new Scene(parent);
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setTitle("Window");
            appStage.setScene(scene);
        }

    }

    /**
     * Changes scene to the registration window.
     *
     * @param actionEvent javaFX event
     * @throws IOException exception
     */
    public void register(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Registration.fxml"));
        Scene scene = new Scene(parent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setTitle("Registration");
        appStage.setScene(scene);
        //appStage.show();

    }
}
