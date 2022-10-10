package com.edson.controller;

import com.edson.tag.VerifyTag;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TagVerifyFormController extends BaseTagForm {

    @FXML
    private TextField targetStep;
    @FXML
    private TextField referenceValue;
    @FXML
    private TextField tolerancyPercentage;
    @FXML
    private TextField calculateBy;
    @FXML
    private Button addEditButton;
    
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
            verifyTag.setId(getId());
            verifyTag.setTagName();
            verifyTag.setTargetStep(Integer.parseInt(targetStep.getText()));
            verifyTag.setReferenceValue(Integer.parseInt(referenceValue.getText()));
            verifyTag.setTolerancyPercentage(Integer.parseInt(tolerancyPercentage.getText()));
            verifyTag.setCalculateBy(calculateBy.getText());
            setFieldValidation(true);
            setTag(verifyTag);
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
        VerifyTag filler = (VerifyTag) tag;
        targetStep.setText(String.valueOf(filler.getTargetStep()));
        referenceValue.setText(String.valueOf(filler.getReferenceValue()));
        tolerancyPercentage.setText(String.valueOf(filler.getTolerancyPercentage()));
        calculateBy.setText(String.valueOf(filler.getCalculateBy()));
    }

}
