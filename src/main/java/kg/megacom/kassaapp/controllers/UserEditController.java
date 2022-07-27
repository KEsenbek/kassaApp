package kg.megacom.kassaapp.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import kg.megacom.kassaapp.models.Position;
import kg.megacom.kassaapp.models.User;
import kg.megacom.kassaapp.services.CounterService;
import kg.megacom.kassaapp.services.PositionService;
import kg.megacom.kassaapp.services.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserEditController {

    private Stage stage;
private PositionService positionService = new PositionService();



private User user;

    public void setUser (User user) {this.user=user;

        if(user.getId()!= null) {
            txtName.setText(user.getName());
            txtLogin.setText(user.getLogin());
            pfPassword.setText(user.getPassword());
            cbPositions.getSelectionModel().select(user.getPosition());

        }

    }


    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<Position> cbPositions;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtName;



    @FXML
    void onCancelClicked(ActionEvent event) {

    }

    @FXML
    void onSaveClicked(ActionEvent event) {



        Position position = cbPositions.getSelectionModel().getSelectedItem();
        String name = txtName.getText();
        String login = txtLogin.getText();
        String password = pfPassword.getText();


        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setPosition(position);


        UserService.getInstance().save(user);

        System.out.println("Данные добавлены");

    }

    @FXML
    void initialize() {

        List<User> users = UserService.getInstance().selectUsers();
        cbPositions.setItems(FXCollections.observableList(PositionService.getInstance().getPosition()));

    }


}
