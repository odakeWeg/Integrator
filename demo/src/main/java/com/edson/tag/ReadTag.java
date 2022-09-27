package com.edson.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "read")
@XmlAccessorType (XmlAccessType.FIELD)
public class ReadTag implements BaseTag{

    int id;
    String testResult;

    String communicationName;
    int[] registers;
    int timeOut;
    int waitBefore;
    int waitAfter;

    @Override
    public String command() {
        return "OK";
    }

    //@TODO: Method to save useful data

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
