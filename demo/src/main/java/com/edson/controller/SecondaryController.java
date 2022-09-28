package com.edson.controller;

import java.io.IOException;

import com.edson.test.TestInitializer;

import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        TestInitializer testInitializer = new TestInitializer("1234567890");
        testInitializer.start();
        //App.setRoot("primary");
    }
}