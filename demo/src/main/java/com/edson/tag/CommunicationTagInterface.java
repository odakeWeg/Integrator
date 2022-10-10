package com.edson.tag;

import javax.xml.bind.annotation.XmlTransient;

import com.edson.communication.BaseCommunication;

//@TODO
public abstract class CommunicationTagInterface {
    @XmlTransient
    BaseCommunication connection;
    private String communicationName;
    
    public void setTagName(){}
}
