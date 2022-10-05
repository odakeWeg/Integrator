package com.edson.tag;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "variableRead")
@XmlAccessorType (XmlAccessType.FIELD)
public class VariableReadTag extends BaseTag {

    private String variableRead;
    private int read;

    @Override
    String executeCommand() {
        read = Integer.parseInt(dataCenter.getSapDataMap().getDataMap().get(variableRead));
        setLog();
        return "OK";
    }

    private void setLog() {
        String logToAdd = "Variavel lida: " + read + "\n";
        dataCenter.getController().getTestRoutineLog().setText(dataCenter.getController().getTestRoutineLog().getText() + logToAdd);
    }

    @Override
    public void setTagName() {
        this.tagName = "variableRead";
    }

    public String getVariableRead() {
        return this.variableRead;
    }

    public void setVariableRead(String variableRead) {
        this.variableRead = variableRead;
    }
    
}
