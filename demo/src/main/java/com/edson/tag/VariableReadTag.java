package com.edson.tag;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "variableRead")
@XmlAccessorType (XmlAccessType.FIELD)
public class VariableReadTag extends BaseReadTag {

    private String variableRead;
    private String testResult;
    private int value;

    @Override
    String executeCommand() {
        value = Integer.parseInt(dataCenter.getSapDataMap().getDataMap().get(variableRead));
        testResult = "OK";
        setLog();
        return testResult;
    }

    private void setLog() {
        String logToAdd = id + ") Variavel lida: " + value + " - " + testResult + "\n";
        dataCenter.getController().getTestRoutineLog().setText(dataCenter.getController().getTestRoutineLog().getText() + logToAdd);
    }

    @Override
    public void setTagName() {
        this.tagName = "variableRead";
    }

    @Override
    public int getValue() {
        return this.value;
    }

    public String getVariableRead() {
        return this.variableRead;
    }

    public void setVariableRead(String variableRead) {
        this.variableRead = variableRead;
    }
    
}
