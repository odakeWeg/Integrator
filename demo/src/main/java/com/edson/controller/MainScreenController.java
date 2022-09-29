package com.edson.controller;

import java.io.IOException;

import com.edson.App;
import com.edson.util.ViewConfigurationPathUtil;

import javafx.fxml.FXML;

public class MainScreenController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot(ViewConfigurationPathUtil.VIEW_PATH + "secondary");
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot(ViewConfigurationPathUtil.VIEW_PATH + "settings");
    }

    @FXML
    private void switchToTesting() throws IOException {
        App.setRoot(ViewConfigurationPathUtil.VIEW_PATH + "secondary");
    }
}
