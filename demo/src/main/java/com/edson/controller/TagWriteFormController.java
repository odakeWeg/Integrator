package com.edson.controller;

import com.edson.tag.WriteTag;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
            writeTag.setCommunicationName(communicationName.getText());
            writeTag.setRegister(Integer.parseInt(register.getText()));
            writeTag.setValue(Integer.parseInt(value.getText()));
            writeTag.setWaitBefore(Integer.parseInt(waitBefore.getText()));
            writeTag.setWaitAfter(Integer.parseInt(waitAfter.getText()));
            setFieldValidation(true);
            stage.close();
        } catch (Exception e) {
            warningLabel1.setVisible(true);
            warningLabel2.setVisible(true);
        }
    }

}
