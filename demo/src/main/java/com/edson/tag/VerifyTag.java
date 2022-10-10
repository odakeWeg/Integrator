package com.edson.tag;

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
            testResult = "Objeto não encontrado - Problema na rotina de teste";
        } else {
            targetValue = targetRead.getValueRead();

            boolean upperLimit = targetValue <= referenceValue*tolerancyPercentage + referenceValue;
            boolean lowerLimit = targetValue >= referenceValue - referenceValue*tolerancyPercentage;
            if (upperLimit && lowerLimit) {
                testResult = "OK";
            } else {
                testResult = "Verificação fora da tolerância";
            }
        }
        setLog();
        return testResult;
    }

    private void setLog() {
        String logToAdd = id + ") Valor desejado: " + referenceValue + " | " + "Valor lido: " + targetValue + "\n";
        dataCenter.getController().getTestRoutineLog().setText(dataCenter.getController().getTestRoutineLog().getText() + logToAdd);
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
        this.tagName = "verify";
    }


    public int getTargetStep() {
        return this.targetStep;
    }

    public void setTargetStep(int targetStep) {
        this.targetStep = targetStep;
    }

    public int getReferenceValue() {
        return this.referenceValue;
    }

    public void setReferenceValue(int referenceValue) {
        this.referenceValue = referenceValue;
    }

    public int getTolerancyPercentage() {
        return this.tolerancyPercentage;
    }

    public void setTolerancyPercentage(int tolerancyPercentage) {
        this.tolerancyPercentage = tolerancyPercentage;
    }


}