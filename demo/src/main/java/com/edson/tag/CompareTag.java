package com.edson.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "compare")
@XmlAccessorType (XmlAccessType.FIELD)
public class CompareTag implements BaseTag {

    int id;
    String testResult;

    int referenceStep;
    int tagStep;
    int referenceScale;
    int measureScale;
    int[] tolerancyPercentage;

    @Override
    public String command() {
        testResult = executeCommand();
        updateList();
        return testResult;
    }

    @Override
    public void updateList() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String executeCommand() {
        return testResult;
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
