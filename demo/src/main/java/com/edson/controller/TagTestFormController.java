package com.edson.controller;

import com.edson.tag.TestTag;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TagTestFormController extends BaseTagForm {

    @FXML
    private TextField testName;
    @FXML
    private Button addEditButton;
    
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
            testTag.setId(getId());
            testTag.setTagName();
            testTag.setName(testName.getText());
            setFieldValidation(true);
            setTag(testTag);
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
        TestTag filler = (TestTag) tag;
        testName.setText(String.valueOf(filler.getName()));
    }

}
