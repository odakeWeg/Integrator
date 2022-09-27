package com.edson.controller;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.edson.test.TestExecutor;
import com.edson.test.TestInitializer;

import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        TestInitializer teste = new TestInitializer();
        
        try {
            teste.unmarshalingFromXML();
            //teste.marshalingExample();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //App.setRoot("primary");
    }
}