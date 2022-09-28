package com.edson.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "compare")
@XmlAccessorType (XmlAccessType.FIELD)
public class CompareTag extends BaseTag {

    int referenceStep;
    int targetStep;
    //int referenceScale;
    //int targetScale;
    int tolerancyPercentage;
    int referenceValue;
    int targetValue;


    @Override
    public String executeCommand() {
        ReadTag referenceRead = (ReadTag) getObjectById(referenceStep);
        ReadTag targetRead = (ReadTag) getObjectById(targetStep);

        if (referenceRead == null || targetRead == null) {
            testResult = "Objeto não encontrado";
        } else {
            referenceValue = referenceRead.getValueRead();
            targetValue = targetRead.getValueRead();
    
            boolean upperLimit = targetValue < referenceValue*tolerancyPercentage + referenceValue;
            boolean lowerLimit = targetValue > referenceValue - referenceValue*tolerancyPercentage;
            if (upperLimit && lowerLimit) {
                testResult = "OK";
            } else {
                testResult = "Comparação fora da tolerância";
            }
        }
        return testResult;
    } 

    private BaseTag getObjectById(int id) {
        for (int i = 0; i < tagList.size(); i++) {
            if (tagList.get(i).id == id) {
                return tagList.get(i);
            }
        }
        return null;
    }

    @Override
    public void setTagName() {
        this.tagName = "compare";
    }

}
