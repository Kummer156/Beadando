package beadando;

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
import java.io.IOException;

public class RegistrationController {
    public TextField unamebox;
    public TextField pwbox1;
    public TextField pwbox2;
    public TextField emailbox;
    public TextField city;
    public TextField hnumber;
    public TextField phonenumber;
    public TextField street;

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


    public void back(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(parent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setTitle("Login");
        appStage.setScene(scene);
    }

    public void register(ActionEvent actionEvent) throws IOException {
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
