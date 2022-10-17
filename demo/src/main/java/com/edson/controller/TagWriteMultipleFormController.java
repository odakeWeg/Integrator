package com.edson.controller;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.edson.tag.WriteMultipleTag;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TagWriteMultipleFormController extends BaseTagForm {

    @FXML
    private TextField communicationName;
    @FXML
    private TextField register;
    @FXML
    private TextField value;
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

    private String name = "writeMultiple";
    private WriteMultipleTag writeMultipleTag;

    @Override
    void addTagStep() {
        try{
            setTagName(name);
            writeMultipleTag = new WriteMultipleTag();
            writeMultipleTag.setId(getId());
            writeMultipleTag.setTagName();
            writeMultipleTag.setCommunicationName(communicationName.getText());
            writeMultipleTag.setRegister(Integer.parseInt(register.getText()));
            writeMultipleTag.setValue(convertStringToArray(value.getText()));
            writeMultipleTag.setWaitBefore(Integer.parseInt(waitBefore.getText()));
            writeMultipleTag.setWaitAfter(Integer.parseInt(waitAfter.getText()));
            setFieldValidation(true);
            setTag(writeMultipleTag);
            stage.close();
        } catch (Exception e) {
            warningLabel1.setVisible(true);
            warningLabel2.setVisible(true);
        }
    }

    private int[] convertStringToArray(String value) {
        String[] splited = value.split(",");
        int[] values = new int[splited.length];
        for (int i = 0; i < splited.length; i++) {
            values[i] = Integer.parseInt(splited[i]);
        }
        return values;
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
        WriteMultipleTag filler = (WriteMultipleTag) tag;
        communicationName.setText(String.valueOf(filler.getCommunicationName()));
        register.setText(String.valueOf(filler.getRegister()));
        value.setText(convertArrayToString(filler.getValueArray()));
        waitBefore.setText(String.valueOf(filler.getWaitBefore()));
        waitAfter.setText(String.valueOf(filler.getWaitAfter()));
    }

    public String convertArrayToString(int[] splited) {
        return StringUtils.join(ArrayUtils.toObject(splited), ",");
    }
}
