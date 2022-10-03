package com.edson.controller;

import com.edson.tag.BaseTag;
import com.edson.tag.CommunicationTag;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private Button addEditButton;

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
            communicationTag.setTagName();
            communicationTag.setCommunicationName(nameCommunication.getText());
            communicationTag.setStopBits(Integer.parseInt(stopBits.getText()));
            communicationTag.setPortName(portName.getText());
            communicationTag.setParity(parity.getText());
            communicationTag.setBaudRate(Integer.parseInt(baudRate.getText()));
            communicationTag.setTimeout(Integer.parseInt(timeOut.getText()));
            communicationTag.setDataBits(Integer.parseInt(dataBits.getText()));
            communicationTag.setAddress(Integer.parseInt(address.getText()));
            setTag(communicationTag);
            setFieldValidation(true);
            stage.close();
        } catch (Exception e) {
            warningLabel1.setVisible(true);
            warningLabel2.setVisible(true);
        }
    }

    @Override
    public void setEditStage(Parent parent) {
        stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        setFields();
        stage.showAndWait();
    }

    public void setFields() {
        addEditButton.setText("Editar");
        CommunicationTag filler = (CommunicationTag) tag;
        nameCommunication.setText(String.valueOf(filler.getCommunicationName()));
        stopBits.setText(String.valueOf(filler.getStopBits()));
        portName.setText(String.valueOf(filler.getPortName()));
        parity.setText(String.valueOf(filler.getParity()));
        baudRate.setText(String.valueOf(filler.getBaudRate()));
        timeOut.setText(String.valueOf(filler.getTimeout()));
        dataBits.setText(String.valueOf(filler.getDataBits()));
        address.setText(String.valueOf(filler.getAddress()));
    }
    
}
