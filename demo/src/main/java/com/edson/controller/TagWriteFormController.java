package com.edson.controller;

import com.edson.tag.WriteTag;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TagWriteFormController extends BaseTagForm {

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

    private String name = "write";
    private WriteTag writeTag;

    @Override
    void addTagStep() {
        try{
            setTagName(name);
            writeTag = new WriteTag();
            writeTag.setTagName();
            writeTag.setCommunicationName(communicationName.getText());
            writeTag.setRegister(Integer.parseInt(register.getText()));
            writeTag.setValue(Integer.parseInt(value.getText()));
            writeTag.setWaitBefore(Integer.parseInt(waitBefore.getText()));
            writeTag.setWaitAfter(Integer.parseInt(waitAfter.getText()));
            setFieldValidation(true);
            setTag(writeTag);
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
        WriteTag filler = (WriteTag) tag;
        communicationName.setText(String.valueOf(filler.getCommunicationName()));
        register.setText(String.valueOf(filler.getRegister()));
        value.setText(String.valueOf(filler.getValue()));
        waitBefore.setText(String.valueOf(filler.getWaitBefore()));
        waitAfter.setText(String.valueOf(filler.getWaitAfter()));
    }

}
