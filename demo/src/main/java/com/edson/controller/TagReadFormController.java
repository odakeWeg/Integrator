package com.edson.controller;

import com.edson.tag.ReadTag;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TagReadFormController extends BaseTagForm {

    @FXML
    private TextField communicationName;
    @FXML
    private TextField register;
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

    private String name = "read";
    private ReadTag readTag;

    @Override
    void addTagStep() {
        try{
            setTagName(name);
            readTag = new ReadTag();
            readTag.setTagName();
            readTag.setCommunicationName(communicationName.getText());
            readTag.setRegister(Integer.parseInt(register.getText()));
            readTag.setWaitBefore(Integer.parseInt(waitBefore.getText()));
            readTag.setWaitAfter(Integer.parseInt(waitAfter.getText()));
            setFieldValidation(true);
            setTag(readTag);
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
        ReadTag filler = (ReadTag) tag;
        communicationName.setText(String.valueOf(filler.getCommunicationName()));
        register.setText(String.valueOf(filler.getRegister()));
        waitBefore.setText(String.valueOf(filler.getWaitBefore()));
        waitAfter.setText(String.valueOf(filler.getWaitAfter()));
    }

}
