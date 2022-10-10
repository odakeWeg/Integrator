package com.edson.controller;

import com.edson.tag.ReadMultipleTag;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TagReadMultipleFormController extends BaseTagForm {

    @FXML
    private TextField communicationName;
    @FXML
    private TextField register;
    @FXML
    private TextField waitBefore;
    @FXML
    private TextField waitAfter;
    @FXML
    private TextField position;
    @FXML
    private Button addEditButton;
    
    
    @FXML
    private Label warningLabel1;
    @FXML
    private Label warningLabel2;

    private String name = "readMultiple";
    private ReadMultipleTag readMultipleTag;

    @Override
    void addTagStep() {
        try{
            setTagName(name);
            readMultipleTag = new ReadMultipleTag();
            readMultipleTag.setId(getId());
            readMultipleTag.setTagName();
            readMultipleTag.setCommunicationName(communicationName.getText());
            readMultipleTag.setRegister(Integer.parseInt(register.getText()));
            readMultipleTag.setPosition(Integer.parseInt(position.getText()));
            readMultipleTag.setWaitBefore(Integer.parseInt(waitBefore.getText()));
            readMultipleTag.setWaitAfter(Integer.parseInt(waitAfter.getText()));
            setFieldValidation(true);
            setTag(readMultipleTag);
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
        ReadMultipleTag filler = (ReadMultipleTag) tag;
        communicationName.setText(String.valueOf(filler.getCommunicationName()));
        register.setText(String.valueOf(filler.getRegister()));
        position.setText(String.valueOf(filler.getPosition()));
        waitBefore.setText(String.valueOf(filler.getWaitBefore()));
        waitAfter.setText(String.valueOf(filler.getWaitAfter()));
    }
    
}
