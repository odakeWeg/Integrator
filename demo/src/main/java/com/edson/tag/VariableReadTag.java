package com.edson.tag;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "variableRead")
@XmlAccessorType (XmlAccessType.FIELD)
public class VariableReadTag implements BaseTag {

    int id;
    String testResult;
    String variableRead;

    @Override
    public String command(List<BaseTag> tagList) {
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
