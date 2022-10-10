package com.edson.controller;

import com.edson.tag.WriteStringTag;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TagWriteStringFormController extends BaseTagForm {
    
    @FXML
    private TextField communicationName;
    @FXML
    private TextField register;
    @FXML
    private TextField value;
    @FXML
    private TextField waitBefore;
    @FXML
    private TextField waitAfter;
    @FXML
    private Button addEditButton;
    
    @FXML
    private Label warningLabel1;
    @FXML
    private Label warningLabel2;

    private String name = "writeString";
    private WriteStringTag writeStringTag;

    @Override
    void addTagStep() {
        try{
            setTagName(name);
            writeStringTag = new WriteStringTag();
            writeStringTag.setId(getId());
            writeStringTag.setTagName();
            writeStringTag.setCommunicationName(communicationName.getText());
            writeStringTag.setRegister(Integer.parseInt(register.getText()));
            writeStringTag.setValue(value.getText());
            writeStringTag.setWaitBefore(Integer.parseInt(waitBefore.getText()));
            writeStringTag.setWaitAfter(Integer.parseInt(waitAfter.getText()));
            setFieldValidation(true);
            setTag(writeStringTag);
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
        WriteStringTag filler = (WriteStringTag) tag;
        communicationName.setText(String.valueOf(filler.getCommunicationName()));
        register.setText(String.valueOf(filler.getRegister()));
        value.setText(String.valueOf(filler.getValue()));
        waitBefore.setText(String.valueOf(filler.getWaitBefore()));
        waitAfter.setText(String.valueOf(filler.getWaitAfter()));
    }
    
}
