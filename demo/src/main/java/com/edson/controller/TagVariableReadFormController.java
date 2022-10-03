package com.edson.controller;

import com.edson.tag.VariableReadTag;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TagVariableReadFormController extends BaseTagForm {
    
    @FXML
    private TextField variableName;

    @FXML
    private Label warningLabel1;
    @FXML
    private Label warningLabel2;

    private String name = "variableRead";
    private VariableReadTag variableReadTag;

    @Override
    void addTagStep() {
        try{
            setTagName(name);
            variableReadTag = new VariableReadTag();
            variableReadTag.setVariableRead(variableName.getText());
            setFieldValidation(true);
            stage.close();
        } catch (Exception e) {
            warningLabel1.setVisible(true);
            warningLabel2.setVisible(true);
        }
    }

}
