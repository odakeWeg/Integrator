package com.edson.model.dto;

import com.edson.tag.BaseTag;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TestStep {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty tagName;
    private BaseTag tag;

    public TestStep(Integer id, String tagName, BaseTag tag) {
        this.id = new SimpleIntegerProperty(id);
        this.tagName = new SimpleStringProperty(tagName);
        this.tag = tag;
    }

    public Integer getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    /* ---------------------------------------------------- */

    public String getTagName() {
        return tagName.get();
    }

    public SimpleStringProperty tagNameProperty() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName.set(tagName);
    }
    
    /* ---------------------------------------------------- */

    public BaseTag getTag() {
        return this.tag;
    }

    public void setTag(BaseTag tag) {
        this.tag = tag;
    }

}
