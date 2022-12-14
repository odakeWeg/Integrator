package com.edson.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.edson.communication.BaseCommunication;
import com.edson.communication.EthernetCommunication;
import com.edson.communication.IOLinkCommunication;
import com.edson.exception.CommunicationException;

@XmlRootElement(name = "communicationEthernet")
@XmlAccessorType (XmlAccessType.FIELD)
public class CommunicationEthernetTag extends BaseCommunicationTag {
    
    private String communicationName;
    private String ip;
    private int port;
    private int address;
    private int timeBetweenCommand;

    @XmlTransient
    BaseCommunication connection;

    @Override
    public String executeCommand() {
        connection = (BaseCommunication) new EthernetCommunication(ip, port, address, timeBetweenCommand);
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
    public void setTagName() {
        tagName = "communicationEthernet";
    }

    @Override
    public String getCommunicationName() {
        return this.communicationName;
    }

    public void setCommunicationName(String communicationName) {
        this.communicationName = communicationName;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getAddress() {
        return this.address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getTimeBetweenCommand() {
        return this.timeBetweenCommand;
    }

    public void setTimeBetweenCommand(int timeBetweenCommand) {
        this.timeBetweenCommand = timeBetweenCommand;
    }

    @Override
    public BaseCommunication getConnection() {
        return this.connection;
    }

    public void setConnection(BaseCommunication connection) {
        this.connection = connection;
    }

}
