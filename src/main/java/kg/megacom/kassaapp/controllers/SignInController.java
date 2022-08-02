package kg.megacom.kassaapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kg.megacom.kassaapp.Main;
import kg.megacom.kassaapp.models.User;
import kg.megacom.kassaapp.services.UserService;

public class SignInController {


    @FXML
    private Button btnSignIn;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void OnActionSignIn(ActionEvent event) {
        Alert alert;
        if (txtLogin.getText().trim().isEmpty() && txtPassword.getText().trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Введите логин/пароль для входа");
            alert.show();
            return;}

        User user = UserService.INSTANCE.searchLoginAndPassword(txtLogin.getText().trim(), txtPassword.getText().trim());

        if (user==null) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Пользователь не найден");
            alert.show();
            return;
        }
        if (user.getLogin().equals(txtLogin.getText().trim()) && user.getPassword().equals(txtPassword.getText().trim())){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainForm.fxml"));

            try {
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                MainController controller = loader.getController();
                controller.setDate(user);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.WARNING,"Неверные данные!");
            alert.show();
            return;
        }

    }
            @FXML
        void initialize () {

        }


}
