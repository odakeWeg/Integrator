package com.edson.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.NoArgsConstructor;

@XmlRootElement(name = "test")
@XmlAccessorType (XmlAccessType.FIELD)
public class TestTag extends BaseTag{

    private String name;

    @Override
    String executeCommand() {
        setLog();
        return "OK";
    }

    private void setLog() {
        String logToAdd = "------------ Teste: " + name + " ------------\n";
        dataCenter.getController().getTestRoutineLog().setText(dataCenter.getController().getTestRoutineLog().getText() + logToAdd);
    }

    @Override
    public void setTagName() {
        this.tagName = "test";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
