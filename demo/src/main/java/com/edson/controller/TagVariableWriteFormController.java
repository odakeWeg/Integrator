package com.edson.controller;

import com.edson.tag.VariableWriteTag;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private Button addEditButton;

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
            variableWriteTag = new VariableWriteTag();
            variableWriteTag.setId(getId());
            variableWriteTag.setTagName();
            variableWriteTag.setCommunicationName(communicationName.getText());
            variableWriteTag.setVariableWriteName(variableName.getText());
            variableWriteTag.setRegister(Integer.parseInt(register.getText()));
            variableWriteTag.setWaitBefore(Integer.parseInt(waitBefore.getText()));
            variableWriteTag.setWaitAfter(Integer.parseInt(waitAfter.getText()));
            setFieldValidation(true);
            setTag(variableWriteTag);
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
        VariableWriteTag filler = (VariableWriteTag) tag;
        communicationName.setText(String.valueOf(filler.getCommunicationName()));
        variableName.setText(String.valueOf(filler.getVariableWriteName()));
        register.setText(String.valueOf(filler.getRegister()));
        waitBefore.setText(String.valueOf(filler.getWaitBefore()));
        waitAfter.setText(String.valueOf(filler.getWaitAfter()));
    }
    
}
