package com.edson.controller;

import com.edson.tag.CommunicationTag;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TagCommunicationFormController extends BaseTagForm {

    @FXML
    private TextField nameCommunication;
    @FXML
    private TextField stopBits;
    @FXML
    private TextField portName;
    @FXML
    private TextField parity;
    @FXML
    private TextField baudRate;
    @FXML
    private TextField timeOut;
    @FXML
    private TextField dataBits;
    @FXML
    private TextField address;

    @FXML
    private Label warningLabel1;
    @FXML
    private Label warningLabel2;

    private String name = "communication";
    private CommunicationTag communicationTag;

    @Override
    public void addTagStep() {
        try{
            setTagName(name);
            communicationTag = new CommunicationTag();
            communicationTag.setCommunicationName(nameCommunication.getText());
            communicationTag.setStopBits(Integer.parseInt(stopBits.getText()));
            communicationTag.setPortName(portName.getText());
            communicationTag.setParity(parity.getText());
            communicationTag.setBaudRate(Integer.parseInt(baudRate.getText()));
            communicationTag.setTimeout(Integer.parseInt(timeOut.getText()));
            communicationTag.setDataBits(Integer.parseInt(dataBits.getText()));
            communicationTag.setAddress(Integer.parseInt(address.getText()));
            setFieldValidation(true);
            stage.close();
        } catch (Exception e) {
            warningLabel1.setVisible(true);
            warningLabel2.setVisible(true);
        }
        
    }
    
}
