package com.edson.tag;

import java.util.concurrent.TimeUnit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.edson.communication.BaseCommunication;
import com.edson.exception.CommunicationException;

@XmlRootElement(name = "write")
@XmlAccessorType (XmlAccessType.FIELD)
public class WriteTag extends BaseTag {

    String communicationName;
    int register;
    int value;
    int timeOut;
    int waitBefore;
    int waitAfter;

    @Override
    String executeCommand() {
        BaseCommunication communication = getCommunicationByName(communicationName);
        delayMilliseconds(waitBefore);                                 
        if(communication == null) {
            testResult = "Objeto não encontrado";
        } else {
            try {
                communication.writeSingleRegister(register, value);
                testResult = "OK";
            } catch (CommunicationException e) {
                testResult = "Falha na comunicação";
            }
        }
        delayMilliseconds(waitAfter); 
        return testResult;
    }

    private void delayMilliseconds(int wait) {
        try {
            TimeUnit.MILLISECONDS.sleep(wait);
        } catch (InterruptedException e) {
        }
    }

    private BaseCommunication getCommunicationByName(String name) {
        CommunicationTag communicationTag;
        for (int i = 0; i < tagList.size(); i++) {
            if (tagList.get(i).tagName.equals("communication")) {
                communicationTag = (CommunicationTag) tagList.get(i);
                if(communicationTag.getCommunicationName().equals(name)) {
                    return communicationTag.getConnection();
                }
            }
        }
        return null;
    }

    @Override
    public void setTagName() {
        this.tagName = "write";
    }
}
