package com.edson.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.edson.communication.BaseCommunication;
import com.edson.communication.IOLinkCommunication;
import com.edson.exception.CommunicationException;

@XmlRootElement(name = "communicationIOLink")
@XmlAccessorType (XmlAccessType.FIELD)
public class CommunicationIOLinkTag extends BaseCommunicationTag {
    private String communicationName;
    private String ip;
    private int port;
    private int address;
    private int timeBetweenCommand;

    @XmlTransient
    BaseCommunication connection;

    @Override
    public String executeCommand() {
        connection = (BaseCommunication) new IOLinkCommunication(ip, port, address, timeBetweenCommand);
        try {
            connection.startConnection();
            setLog();
            return "OK";
        } catch (CommunicationException e) {
            return "Falha de comunicação";
        }
    }

    private void setLog() {
        String logToAdd = id + ") Configurações de comunicação com " + communicationName + " - OK\n";
        dataCenter.getController().getTestRoutineLog().setText(dataCenter.getController().getTestRoutineLog().getText() + logToAdd);
    }

    @Override
    public void setTagName() {
        tagName = "communicationIOLink";
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
