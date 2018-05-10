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


public class LoginController {
    public TextField txt1;
    public PasswordField txt2;

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    public void login(ActionEvent actionEvent) throws IOException {

        UserDAO user = UserDAO.getInstance();

        if(user.UserLoginVerification(txt1.getText(), txt2.getText())) {
            logger.trace(String.format("Login attempt with %s %s",txt1.getText(), txt2.getText()));
            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Window.fxml"));
            Scene scene = new Scene(parent);
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setTitle("Window");
            appStage.setScene(scene);
        }

    }

    public void register(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Registration.fxml"));
        Scene scene = new Scene(parent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setTitle("Registration");
        appStage.setScene(scene);
        //appStage.show();

    }
}
