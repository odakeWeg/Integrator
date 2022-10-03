package com.edson.controller;

import java.io.IOException;

import com.edson.tag.BaseTag;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class BaseTagForm {
    protected Stage stage;
    protected String tagName;
    protected BaseTag tag;
    protected boolean fieldValidation = false;

    public void setStage(Parent part) {
        stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void switchToPreviousPage() throws IOException {
        stage.close();
    }

    @FXML
    abstract void addTagStep();

    public String getTagName() {
        return this.tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    

    public BaseTag getTag() {
        return this.tag;
    }

    public void setTag(BaseTag tag) {
        this.tag = tag;
    }


    public boolean isFieldValidation() {
        return this.fieldValidation;
    }

    public void setFieldValidation(boolean fieldValidation) {
        this.fieldValidation = fieldValidation;
    }
    

}

    
