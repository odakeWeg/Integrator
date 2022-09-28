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
public class CommunicationTag extends BaseTag{

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
            return "OK";
        } catch (CommunicationException e) {
            return "Falha de comunicação";
        }
    }


    public String getCommunicationName() {
        return this.communicationName;
    }

    public BaseCommunication getConnection() {
        return this.connection;
    }

}
