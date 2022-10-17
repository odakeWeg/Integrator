package com.edson.tag;

import java.util.concurrent.TimeUnit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.edson.communication.BaseCommunication;
import com.edson.exception.CommunicationException;

@XmlRootElement(name = "writeString")
@XmlAccessorType (XmlAccessType.FIELD)
public class WriteStringTag extends BaseWriteTag {
    
    private String communicationName;
    private int register;
    private String valueVariable;
    private int timeOut;
    private int waitBefore;
    private int waitAfter;

    private String value;

    @Override
    String executeCommand() {
        BaseCommunication communication = getCommunicationByName(communicationName);
        value = dataCenter.getSapDataMap().getDataMap().get(valueVariable);
        delayMilliseconds(waitBefore);                                 
        if(communication == null) {
            testResult = "Objeto não encontrado (Problema na rotina de teste)";
        } else {
            try {
                communication.writeStringInRegister(register, value);
                testResult = "OK";
            } catch (CommunicationException e) {
                testResult = "Falha na comunicação";
            }
        }
        delayMilliseconds(waitAfter); 
        setLog();
        return testResult;
    }

    private void setLog() {
        String logToAdd = id + ") Valor escrito no parâmetro" + register + ": " + value + " - " + testResult + "\n";
        dataCenter.getController().getTestRoutineLog().setText(dataCenter.getController().getTestRoutineLog().getText() + logToAdd);
    }

    private void delayMilliseconds(int wait) {
        try {
            TimeUnit.MILLISECONDS.sleep(wait);
        } catch (InterruptedException e) {
        }
    }

    private BaseCommunication getCommunicationByName(String name) {
        BaseCommunicationTag communicationTag;
        for (int i = 0; i < tagList.size(); i++) {
            if (tagList.get(i).getTagName().contains("communication")) {
                communicationTag = (BaseCommunicationTag) tagList.get(i);
                if(communicationTag.getCommunicationName().equals(name)) {
                    return communicationTag.getConnection();
                }
            }
        }
        return null;
    }

    @Override
    public void setTagName() {
        this.tagName = "writeString";
    }


    public String getCommunicationName() {
        return this.communicationName;
    }

    public void setCommunicationName(String communicationName) {
        this.communicationName = communicationName;
    }

    public int getRegister() {
        return this.register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    public String getValueVariable() {
        return this.valueVariable;
    }

    public int getValue() {
        return Integer.parseInt(value);
    }

    public void setValueVariable(String valueVariable) {
        this.valueVariable = valueVariable;
    }

    public int getTimeOut() {
        return this.timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getWaitBefore() {
        return this.waitBefore;
    }

    public void setWaitBefore(int waitBefore) {
        this.waitBefore = waitBefore;
    }

    public int getWaitAfter() {
        return this.waitAfter;
    }

    public void setWaitAfter(int waitAfter) {
        this.waitAfter = waitAfter;
    }

}
