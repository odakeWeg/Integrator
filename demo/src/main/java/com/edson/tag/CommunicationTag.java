package com.edson.tag;

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
    public String command() {
        executeCommand();
        updateList();
        
        return testResult;
    }

    @Override
    public void updateList() {
        // TODO Auto-generated method stub
        
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
