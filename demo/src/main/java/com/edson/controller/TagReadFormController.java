package com.edson.controller;

import com.edson.tag.ReadTag;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
            readTag.setCommunicationName(communicationName.getText());
            readTag.setRegister(Integer.parseInt(register.getText()));
            readTag.setWaitBefore(Integer.parseInt(waitBefore.getText()));
            readTag.setWaitAfter(Integer.parseInt(waitAfter.getText()));
            setFieldValidation(true);
            stage.close();
        } catch (Exception e) {
            warningLabel1.setVisible(true);
            warningLabel2.setVisible(true);
        }
    }

}
