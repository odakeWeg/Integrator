package com.edson.controller;

import com.edson.tag.CommunicationIOLinkTag;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TagCommunicationIOLinkFormController extends BaseTagForm {
    @FXML
    private TextField nameCommunication;
    @FXML
    private TextField ip;
    @FXML
    private TextField port;
    @FXML
    private TextField address;
    @FXML
    private TextField timeBetweenCommand;
    @FXML
    private Button addEditButton;

    @FXML
    private Label warningLabel1;
    @FXML
    private Label warningLabel2;

    private String name = "communicationIOLink";
    private CommunicationIOLinkTag communicationIOLinkTag;

    @Override
    public void addTagStep() {
        try{
            setTagName(name);
            communicationIOLinkTag = new CommunicationIOLinkTag();
            communicationIOLinkTag.setId(getId());
            communicationIOLinkTag.setTagName();
            communicationIOLinkTag.setCommunicationName(nameCommunication.getText());
            communicationIOLinkTag.setIp(ip.getText());
            communicationIOLinkTag.setPort(Integer.parseInt(port.getText()));
            communicationIOLinkTag.setAddress(Integer.parseInt(address.getText()));
            communicationIOLinkTag.setTimeBetweenCommand(Integer.parseInt(timeBetweenCommand.getText()));
            setTag(communicationIOLinkTag);
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
        CommunicationIOLinkTag filler = (CommunicationIOLinkTag) tag;
        nameCommunication.setText(String.valueOf(filler.getCommunicationName()));
        ip.setText(String.valueOf(filler.getIp()));
        port.setText(String.valueOf(filler.getPort()));
        address.setText(String.valueOf(filler.getAddress()));
        timeBetweenCommand.setText(String.valueOf(filler.getTimeBetweenCommand()));
    }
}
