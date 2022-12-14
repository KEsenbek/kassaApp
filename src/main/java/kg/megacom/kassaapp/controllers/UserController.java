package kg.megacom.kassaapp.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kg.megacom.kassaapp.Main;
import kg.megacom.kassaapp.db.UserDB;
import kg.megacom.kassaapp.models.User;
import kg.megacom.kassaapp.services.UserService;
import kg.megacom.kassaapp.services.impl.UserServiceImpl;

public class UserController {

    //private UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();


    @FXML
    private TableView<User> tbUsers;

    @FXML
    private TableColumn<String, User> colmLogin;

    @FXML
    private TableColumn<String, User> colmName;

    @FXML
    private TableColumn<String, User> colmPassword;

    @FXML
    private TableColumn<String, User> colmPosition;
    @FXML
    private MenuItem mnItemAdd;

    @FXML
    private MenuItem mnItemDelete;

    @FXML
    private MenuItem mnItemEdit;

    @FXML
    private MenuItem mnItemPosition;

    @FXML
    void onMenuItemClicked(ActionEvent event) throws SQLException {
        if (event.getSource().equals(mnItemAdd)){
            addUser();
        } else if (event.getSource().equals(mnItemEdit)) {
            editUser();
            refreshList();
        } else if (event.getSource().equals(mnItemDelete)) {
            deleteUser();
        }else if (event.getSource().equals((mnItemPosition))) {
            showPositionForm();
        }

    }

    private void showPositionForm() {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("positionsForm.fxml"));

        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }

    private void editUser() {
        showUsersForm(tbUsers.getSelectionModel().getSelectedItem());
    }

    private void deleteUser() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"???? ???????????????", ButtonType.YES,ButtonType.NO);
        ButtonType result = alert.showAndWait().get();

        if (result.equals(ButtonType.YES)){
            User user = tbUsers.getSelectionModel().getSelectedItem();
            UserDB.INSTANCE.delete(user.getId());
            refreshList();
        }
    }



    private void addUser() { showUsersForm(new User());

    }

    private void showUsersForm (User user) {
        Stage stage  = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("userEditForm.fxml"));

        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            UserEditController controller = loader.getController();
            controller.setUser(user);

            stage.showAndWait();

            refreshList();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {
       colmName.setCellValueFactory(new PropertyValueFactory<>("name"));
       colmLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
       colmPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
       colmPosition.setCellValueFactory(new PropertyValueFactory<>("position"));



        refreshList();
    }

    private void refreshList() {
        List<User> users = UserDB.INSTANCE.selectUsers();
        tbUsers.setItems(FXCollections.observableList(users));

    }

}
