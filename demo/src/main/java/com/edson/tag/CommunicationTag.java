package com.edson.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.edson.communication.BaseCommunication;
import com.edson.communication.ModbusCommunication;
import com.edson.exception.CommunicationException;

@XmlRootElement(name = "communication")
@XmlAccessorType (XmlAccessType.FIELD)
public class CommunicationTag implements BaseTag{

    private int id;
    private String testResult;

    private String portName;
    private int baudRate;
    private int dataBits;
    private int stopBits;
    private String parity;
    private int timeout;
    private int address;

    @Override
    public HashMap<String, List<BaseTag>> command(List<BaseTag> tagList) {
        List<BaseTag> updatedList = new ArrayList<>();
        HashMap<String, List<BaseTag>> resultMap = new HashMap<String, List<BaseTag>>();
        
        testResult = executeCommand();
        updatedList = updateList(tagList);
        
        resultMap.put(testResult, updatedList);
        
        return resultMap;
    }

    @Override
    public List<BaseTag> updateList(List<BaseTag> tagList) {
        tagList.set(id, this);
        return tagList;
    }

    @Override
    public String executeCommand() {
        BaseCommunication connection = (BaseCommunication) new ModbusCommunication(portName, baudRate, dataBits, stopBits, parity, timeout, address);
        try {
            connection.startConnection();
            return "OK";
        } catch (CommunicationException e) {
            return "Falha de comunicação";
        }
    } 

    public String getTestResult() {
        return this.testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Override
    public int getId() {
        return id;
    }
}
