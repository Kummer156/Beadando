package beadando;

import beadando.DAO.UserDAO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        UserDAO userDAO = UserDAO.getInstance();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        primaryStage.setTitle("login" +(userDAO.IsInitialized() ? "" : " [Database Error]"));
        primaryStage.setOnCloseRequest(e ->
        {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
