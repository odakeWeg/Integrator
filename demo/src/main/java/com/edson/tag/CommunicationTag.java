package com.edson.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.edson.communication.BaseCommunication;
import com.edson.communication.ModbusCommunication;
import com.edson.exception.CommunicationException;

@XmlRootElement(name = "communication")
@XmlAccessorType (XmlAccessType.FIELD)
public class CommunicationTag extends BaseCommunicationTag {

    private String communicationName;
    private String portName;
    private int baudRate;
    private int dataBits;
    private int stopBits;
    private String parity;
    private int timeout;
    private int address;

    @XmlTransient
    BaseCommunication connection;

    @Override
    public String executeCommand() {
        connection = (BaseCommunication) new ModbusCommunication(portName, baudRate, dataBits, stopBits, parity, timeout, address);
        try {
            connection.startConnection();
            setLog();
            testResult = "OK";
        } catch (CommunicationException e) {
            testResult = "Falha de comunicação";
        }
        return testResult;
    }

    private void setLog() {
        String logToAdd = id + ") Configurações de comunicação com " + communicationName + " - OK\n";
        dataCenter.getController().getTestRoutineLog().setText(dataCenter.getController().getTestRoutineLog().getText() + logToAdd);
    }

    @Override
    public String getCommunicationName() {
        return this.communicationName;
    }

    @Override
    public BaseCommunication getConnection() {
        return this.connection;
    }

    @Override
    public void setTagName() {
        this.tagName = "communication";
    }

    public void setCommunicationName(String communicationName) {
        this.communicationName = communicationName;
    }

    public String getPortName() {
        return this.portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public int getBaudRate() {
        return this.baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public int getDataBits() {
        return this.dataBits;
    }

    public void setDataBits(int dataBits) {
        this.dataBits = dataBits;
    }

    public int getStopBits() {
        return this.stopBits;
    }

    public void setStopBits(int stopBits) {
        this.stopBits = stopBits;
    }

    public String getParity() {
        return this.parity;
    }

    public void setParity(String parity) {
        this.parity = parity;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getAddress() {
        return this.address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public void setConnection(BaseCommunication connection) {
        this.connection = connection;
    }


}
