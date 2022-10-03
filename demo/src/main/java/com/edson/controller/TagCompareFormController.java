package com.edson.controller;

import com.edson.tag.CompareTag;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TagCompareFormController extends BaseTagForm {

    @FXML
    private TextField referenceStep;
    @FXML
    private TextField targetStep;
    @FXML
    private TextField tolerancyPercentage;

    @FXML
    private Label warningLabel1;
    @FXML
    private Label warningLabel2;

    private String name = "compare";
    private CompareTag compareTag;

    @Override
    void addTagStep() {
        try{
            setTagName(name);
            compareTag = new CompareTag();
            compareTag.setReferenceStep(Integer.parseInt(referenceStep.getText()));
            compareTag.setTargetStep(Integer.parseInt(targetStep.getText()));
            compareTag.setTolerancyPercentage(Integer.parseInt(tolerancyPercentage.getText()));
            setFieldValidation(true);
            stage.close();
        } catch (Exception e) {
            warningLabel1.setVisible(true);
            warningLabel2.setVisible(true);
        }
    }
    
}
