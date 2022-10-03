package com.edson.controller;

import com.edson.tag.TestTag;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TagTestFormController extends BaseTagForm {

    @FXML
    private TextField testName;
    
    @FXML
    private Label warningLabel1;
    @FXML
    private Label warningLabel2;

    private String name = "test";
    private TestTag testTag;

    @Override
    void addTagStep() {
        try{
            setTagName(name);
            testTag = new TestTag();
            testTag.setName(testName.getText());
            setFieldValidation(true);
            stage.close();
        } catch (Exception e) {
            warningLabel1.setVisible(true);
            warningLabel2.setVisible(true);
        }
    }

}
