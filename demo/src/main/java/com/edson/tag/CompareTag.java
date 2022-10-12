package com.edson.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "compare")
@XmlAccessorType (XmlAccessType.FIELD)
public class CompareTag extends BaseCompareTag {

    private int referenceStep;
    private int targetStep;
    //int referenceScale;
    //int targetScale;
    private int tolerancyPercentage;
    private int referenceValue;
    private int targetValue;
    private String calculateBy;


    @Override
    public String executeCommand() {
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
        BaseReadTag referenceRead = (BaseReadTag) getObjectById(referenceStep);
        BaseReadTag targetRead = (BaseReadTag) getObjectById(targetStep);

        if (referenceRead == null || targetRead == null) {
            testResult = "Objeto não encontrado - Problema na rotina de teste";
        } else {
            referenceValue = referenceRead.getValueRead();
            targetValue = targetRead.getValueRead();
    
            boolean upperLimit = targetValue*100 <= referenceValue*100 + referenceValue*tolerancyPercentage;
            boolean lowerLimit = targetValue*100 >= referenceValue*100 - referenceValue*tolerancyPercentage;
            if (upperLimit && lowerLimit) {
                testResult = "OK";
            } else {
                testResult = "Falha: Comparação fora da tolerância";
            }
        }
    }

    private void absoluteCompare() {
        BaseReadTag referenceRead = (BaseReadTag) getObjectById(referenceStep);
        BaseReadTag targetRead = (BaseReadTag) getObjectById(targetStep);

        if (referenceRead == null || targetRead == null) {
            testResult = "Objeto não encontrado - Problema na rotina de teste";
        } else {
            referenceValue = referenceRead.getValueRead();
            targetValue = targetRead.getValueRead();
    
            boolean upperLimit = targetValue <= referenceValue + tolerancyPercentage;
            boolean lowerLimit = targetValue >= referenceValue - tolerancyPercentage;
            if (upperLimit && lowerLimit) {
                testResult = "OK";
            } else {
                testResult = "Falha: Comparação fora da tolerância";
            }
        }
    }

    private void setLog() {
        String logToAdd = id + ") Valor desejado: " + referenceValue + " | Valor lido: " + targetValue + " - " + testResult + "\n";
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
        this.tagName = "compare";
    }

    public int getReferenceStep() {
        return this.referenceStep;
    }

    public void setReferenceStep(int referenceStep) {
        this.referenceStep = referenceStep;
    }

    public int getTargetStep() {
        return this.targetStep;
    }

    public void setTargetStep(int targetStep) {
        this.targetStep = targetStep;
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
