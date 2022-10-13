package com.edson.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "verify")
@XmlAccessorType (XmlAccessType.FIELD)
public class VerifyTag extends BaseCompareTag {

    private int targetStep;
    private int targetValue;
    private int referenceValue;
    //int targetScale;
    private int tolerancyPercentage;
    private String calculateBy;

    @Override
    String executeCommand() {
        switch(calculateBy) {
            case "percentage":
                percentualCompare();
            break;
            case "absolute":
                absoluteCompare();
            break;
            default:
                testResult = "Falha na rotina de teste";
        }
        setLog();
        return testResult;
    }

    private void percentualCompare() {
        BaseReadTag targetRead = (BaseReadTag) getObjectById(targetStep);

        if (targetRead == null) {
            testResult = "Objeto não encontrado (Problema na rotina de teste)";
        } else {
            targetValue = targetRead.getValueRead();

            boolean upperLimit = targetValue*100 <= referenceValue*100 + referenceValue*tolerancyPercentage;
            boolean lowerLimit = targetValue*100 >= referenceValue*100 - referenceValue*tolerancyPercentage;
            if (upperLimit && lowerLimit) {
                testResult = "OK";
            } else {
                testResult = "Verificação fora da tolerância";
            }
        }
    }

    private void absoluteCompare() {
        BaseReadTag targetRead = (BaseReadTag) getObjectById(targetStep);

        if (targetRead == null) {
            testResult = "Objeto não encontrado (Problema na rotina de teste)";
        } else {
            targetValue = targetRead.getValueRead();

            boolean upperLimit = targetValue <= referenceValue + referenceValue;
            boolean lowerLimit = targetValue >= referenceValue - referenceValue;
            if (upperLimit && lowerLimit) {
                testResult = "OK";
            } else {
                testResult = "Verificação fora da tolerância";
            }
        }
    }

    private void setLog() {
        String logToAdd = id + ") Valor desejado: " + referenceValue + " | " + "Valor lido: " + targetValue + " - " + testResult + "\n";
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

    public String getCalculateBy() {
        return this.calculateBy;
    }

    public void setCalculateBy(String calculateBy) {
        this.calculateBy = calculateBy;
    }

}