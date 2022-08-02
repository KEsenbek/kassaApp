package kg.megacom.kassaapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import kg.megacom.kassaapp.models.Position;
import kg.megacom.kassaapp.services.PositionService;
import kg.megacom.kassaapp.services.impl.PositionServiceImpl;

import java.sql.SQLException;

public class PositionEditController {

   // private PositionServiceImpl positionServiceImpl = PositionServiceImpl.getInstance();

    private Position position;

    public void setPosition(Position position) {
        this.position = position;

        if (position != null) {
            txtPositionName.setText(position.getName());

        }
    }

    @FXML
    private TextField txtPositionName;

    @FXML
    void onCancelClicked(ActionEvent event) {

    }

    @FXML
    void onSaveClicked(ActionEvent event) {
        String positionName = txtPositionName.getText();

        position.setName(positionName);

        String result = "Успешно";
        Alert.AlertType alertType = Alert.AlertType.INFORMATION;

        try {
            PositionService.INSTANCE.save(position);
            txtPositionName.getScene().getWindow().hide();
        } catch (SQLException e) {
            e.printStackTrace();


        switch (e.getErrorCode()) {
            case 19:
                result = "Название должно быть уникальным";
                break;
            case 0:
                result = "Ошибка подключения к базе данных";
                break;
            default:
                result = "Системная ошибка!";
        }

        alertType = Alert.AlertType.ERROR;
        } finally {
             Alert alert = new Alert(alertType,result, ButtonType.OK);
            alert.showAndWait();
        }
        }



}
