package com.edson.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TestSettingsController {
    
    Stage stage;

    @FXML
    private void addStep() {

    }

    @FXML
    private void removeSelectedItem() {
        
    }

    @FXML
    private void moveUp() {
        
    }

    @FXML
    private void moveDown() {
        
    }

    @FXML
    private void switchToPreviousPage() {
        
    }

    @FXML
    private void switchToEditForm() {
        
    }

    public void setStage(Parent part) {
        stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
