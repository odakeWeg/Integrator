package com.edson.tag;

import java.util.concurrent.TimeUnit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.edson.communication.BaseCommunication;
import com.edson.exception.CommunicationException;

//TODO: Not implemented Yet
@XmlRootElement(name = "writeMultiple")
@XmlAccessorType (XmlAccessType.FIELD)
public class WriteMultipleTag extends BaseWriteTag {
    
    private String communicationName;
    private int register;
    private int[] value;
    private int timeOut;
    private int waitBefore;
    private int waitAfter;

    @Override
    String executeCommand() {
        BaseCommunication communication = getCommunicationByName(communicationName);
        delayMilliseconds(waitBefore);                                 
        if(communication == null) {
            testResult = "Objeto não encontrado (Problema na rotina de teste)";
        } else {
            try {
                communication.writeMultipleRegister(register, value);
                testResult = "OK"; 
                setLog();
            } catch (CommunicationException e) {
                testResult = "Falha na comunicação";
            }
        }
        delayMilliseconds(waitAfter);
        return testResult;
    }

    private void setLog() {
        String logToAdd = id + ") Valores escritos nos parâmetros" + register + " até " + (register + value.length) +": (" + convertArrayToString(value) + ") - " + testResult + "\n";
        dataCenter.getController().getTestRoutineLog().setText(dataCenter.getController().getTestRoutineLog().getText() + logToAdd);
    }

    public String convertArrayToString(int[] splited) {
        return StringUtils.join(ArrayUtils.toObject(splited), ",");
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
        this.tagName = "writeMultiple";
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

    public int[] getValueArray() {
        return this.value;
    }

    @Override
    public int getValue() {
        return -1;
    }

    public void setValue(int[] value) {
        this.value = value;
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
