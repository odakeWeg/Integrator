package com.edson.tag;

import java.util.HashMap;
import java.util.List;

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
    public HashMap<String, List<BaseTag>> command(List<BaseTag> tagList) {
        testResult = executeCommand();
        updateList();
        return testResult;
    }

    @Override
    public List<BaseTag> updateList(List<BaseTag> tagList) {
        tagList.set(id, this);
        return tagList;
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
