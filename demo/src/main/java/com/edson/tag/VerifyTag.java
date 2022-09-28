package com.edson.tag;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "verify")
@XmlAccessorType (XmlAccessType.FIELD)
public class VerifyTag extends BaseTag{

    int targetStep;
    int targetValue;
    int referenceValue;
    //int targetScale;
    int tolerancyPercentage;

    @Override
    String executeCommand() {
        ReadTag targetRead = (ReadTag) getObjectById(targetStep);

        if (targetRead == null) {
            testResult = "Objeto não encontrado";
        } else {
            targetValue = targetRead.getValueRead();

            boolean upperLimit = targetValue > referenceValue*tolerancyPercentage + referenceValue;
            boolean lowerLimit = targetValue < referenceValue - referenceValue*tolerancyPercentage;
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

}