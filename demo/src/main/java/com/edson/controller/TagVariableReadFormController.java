package com.edson.controller;

import com.edson.tag.VariableReadTag;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TagVariableReadFormController extends BaseTagForm {
    
    @FXML
    private TextField variableName;
    @FXML
    private Button addEditButton;

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
            variableReadTag.setId(getId());
            variableReadTag.setTagName();
            variableReadTag.setVariableRead(variableName.getText());
            setFieldValidation(true);
            setTag(variableReadTag);
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
        VariableReadTag filler = (VariableReadTag) tag;
        variableName.setText(String.valueOf(filler.getVariableRead()));
    }

}
