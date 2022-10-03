package com.edson.controller;

import com.edson.tag.CompareTag;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TagCompareFormController extends BaseTagForm {

    @FXML
    private TextField referenceStep;
    @FXML
    private TextField targetStep;
    @FXML
    private TextField tolerancyPercentage;
    @FXML
    private Button addEditButton;

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
            compareTag.setId(getId());
            compareTag.setTagName();
            compareTag.setReferenceStep(Integer.parseInt(referenceStep.getText()));
            compareTag.setTargetStep(Integer.parseInt(targetStep.getText()));
            compareTag.setTolerancyPercentage(Integer.parseInt(tolerancyPercentage.getText()));
            setFieldValidation(true);
            setTag(compareTag);
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
        CompareTag filler = (CompareTag) tag;
        referenceStep.setText(String.valueOf(filler.getReferenceStep()));
        targetStep.setText(String.valueOf(filler.getTargetStep()));
        tolerancyPercentage.setText(String.valueOf(filler.getTolerancyPercentage()));
    }
    
}
