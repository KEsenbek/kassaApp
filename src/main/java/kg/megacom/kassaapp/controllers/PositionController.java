package kg.megacom.kassaapp.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kg.megacom.kassaapp.Main;
import kg.megacom.kassaapp.models.Category;
import kg.megacom.kassaapp.models.Position;
import kg.megacom.kassaapp.services.CategoryService;
import kg.megacom.kassaapp.services.PositionService;

import java.io.IOException;
import java.util.List;

public class PositionController {
    private PositionService positionService = PositionService.getInstance();
    @FXML
    private ListView<Position> listViewCategories;

    @FXML
    private MenuItem mnItemAdd;

    @FXML
    private MenuItem mnItemDelete;

    @FXML
    private MenuItem mnItemEdit;

    @FXML
    void onMenuItemClicked(ActionEvent event) {
        if (event.getSource().equals(mnItemAdd)){
            addPosition();
        } else if (event.getSource().equals(mnItemEdit)) {
            editPosition();
        } else if (event.getSource().equals(mnItemDelete)) {
            deletePosition();
    }

}

    private void addPosition() { Stage stage  = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("positionEditForm.fxml"));

        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            PositionEditController controller = loader.getController();
            controller.setPosition(new Position());

            stage.showAndWait();

            refreshList();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void deletePosition() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Вы уверены?", ButtonType.YES,ButtonType.NO);
        ButtonType result = alert.showAndWait().get();

        if (result.equals(ButtonType.YES)){
            Position position = listViewCategories.getSelectionModel().getSelectedItem();
            positionService.delete(position.getId());
            refreshList();
        }
    }

    private void editPosition() {
        Stage stage  = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("positionEditForm.fxml"));

        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            Position position = listViewCategories.getSelectionModel().getSelectedItem();
            PositionEditController controller = loader.getController();
            controller.setPosition(position);

            stage.showAndWait();

            refreshList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {
        refreshList();
    }

    private void refreshList() {
        List<Position> positions = positionService.getPosition();
        listViewCategories.setItems(FXCollections.observableList(positions));

    }
}

