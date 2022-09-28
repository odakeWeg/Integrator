package com.edson.tag;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.edson.communication.BaseCommunication;
import com.edson.exception.CommunicationException;
import com.edson.test.data.DataCenter;

@XmlRootElement(name = "variableWrite")
@XmlAccessorType (XmlAccessType.FIELD)
public class VariableWriteTag extends BaseTag {

    String communicationName;
    int register;
    String variableWriteName;
    int timeOut;
    int waitBefore;
    int waitAfter;
    int value;

    @Override
    String executeCommand() {
        BaseCommunication communication = getCommunicationByName(communicationName);
        delayMilliseconds(waitBefore);                               
        if(communication == null) {
            testResult = "Objeto não encontrado";
        } else {
            try {
                value = Integer.parseInt(dataCenter.getSapDataMap().getDataMap().get(variableWriteName));
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

}
