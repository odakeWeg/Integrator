package com.edson.controller;

import java.io.IOException;
import java.util.Optional;

import com.edson.App;
import com.edson.test.TestInitializer;
import com.edson.util.ViewConfigurationPathUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

public class AutomatedTestController {

    @FXML
    private TextArea testRoutineLog;

    @FXML
    private Label status;
    @FXML
    private Rectangle statusRectangle;
    
    @FXML
    private void requestSerialAndStartTest() {
        try{
            int serial = Integer.parseInt(requestSerial());

            TestInitializer testInitializer = new TestInitializer(String.valueOf(serial), this);
            testInitializer.start();
            //showResultMessage(testInitializer.getResult());
        } catch (NumberFormatException e) {
            Alert userAlert = new Alert(AlertType.ERROR);
            userAlert.setTitle("Validação de início");
            userAlert.setHeaderText("Falha ao iniciar o test");
            userAlert.setContentText("Favor inserir uma serial válida");
            userAlert.showAndWait();
        }
    }

    private String requestSerial() {
        TextInputDialog serialRequest = new TextInputDialog();
		serialRequest.setTitle("Request Serial");
		serialRequest.setHeaderText("Leia código serial do produto:");
		serialRequest.setContentText("Serial:");
		Optional<String> serial = serialRequest.showAndWait();
        if(serial.isPresent()){
            return serial.get();
        } else {
            return "";
        }
    }

    @FXML
    private void cancelTest() {
        //@TODO: Efficient way to stop thread
    }

    @FXML
    private void switchToPreviousPage() throws IOException {
        App.setRoot(ViewConfigurationPathUtil.VIEW_PATH + "mainScreen");
    }

    public TextArea getTestRoutineLog() {
        return this.testRoutineLog;
    }

    public void setTestRoutineLog(TextArea testRoutineLog) {
        this.testRoutineLog = testRoutineLog;
    }

    public Label getStatus() {
        return this.status;
    }

    public Rectangle getStatusRectangle() {
        return this.statusRectangle;
    }

}
