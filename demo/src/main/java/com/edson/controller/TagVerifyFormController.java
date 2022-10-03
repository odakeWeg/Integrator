package com.edson.controller;

import com.edson.tag.VerifyTag;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TagVerifyFormController extends BaseTagForm {

    @FXML
    private TextField targetStep;
    @FXML
    private TextField referenceValue;
    @FXML
    private TextField tolerancyPercentage;
    
    @FXML
    private Label warningLabel1;
    @FXML
    private Label warningLabel2;

    private String name = "verify";
    private VerifyTag verifyTag;

    @Override
    void addTagStep() {
        try{
            setTagName(name);
            verifyTag = new VerifyTag();
            verifyTag.setTargetStep(Integer.parseInt(targetStep.getText()));
            verifyTag.setReferenceValue(Integer.parseInt(referenceValue.getText()));
            verifyTag.setTolerancyPercentage(Integer.parseInt(tolerancyPercentage.getText()));
            setFieldValidation(true);
            stage.close();
        } catch (Exception e) {
            warningLabel1.setVisible(true);
            warningLabel2.setVisible(true);
        }
    }

}
