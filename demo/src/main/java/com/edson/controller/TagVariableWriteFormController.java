package com.edson.controller;

import com.edson.tag.VariableWriteTag;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TagVariableWriteFormController extends BaseTagForm {
    
    @FXML
    private TextField communicationName;
    @FXML
    private TextField variableName;
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

    private String name = "variableWrite";
    private VariableWriteTag variableWriteTag;

    @Override
    void addTagStep() {
        try{
            setTagName(name);
            variableWriteTag.setCommunicationName(communicationName.getText());
            variableWriteTag.setVariableWriteName(variableName.getText());
            variableWriteTag.setRegister(Integer.parseInt(register.getText()));
            variableWriteTag.setWaitBefore(Integer.parseInt(waitBefore.getText()));
            variableWriteTag.setWaitAfter(Integer.parseInt(waitAfter.getText()));
            setFieldValidation(true);
            stage.close();
        } catch (Exception e) {
            warningLabel1.setVisible(true);
            warningLabel2.setVisible(true);
        }
    }

}
