package com.edson.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.edson.tag.BaseTag;
import com.edson.test.data.DataCenter;
import com.edson.util.ApplicationSetup;

public class TestExecutor {
    private List<BaseTag> tagList = new ArrayList<>();
    private List<BaseTag> cancelTagList = new ArrayList<>();
    private DataCenter dataCenter;

    private boolean exit = false;
    
    String result;

    public TestExecutor(HashMap<String, List<BaseTag>> tagListHM, DataCenter dataCenter) {
        this.tagList = tagListHM.get("routine");
        this.cancelTagList = tagListHM.get("cancel");
        this.dataCenter = dataCenter;
    }

    public String executeTest() {
        startingTestSetup();
        result = execution(tagList);
        closingTestSetup();
        return result;
    }

    private String execution(List<BaseTag> tagList) {
        BaseTag tag;
        HashMap<String, List<BaseTag>> updateMap = new HashMap<String, List<BaseTag>>();
        String result = "Erro não esperado";
        for (int i = 0; i < tagList.size(); i++) {
            if(!exit) {
                tag = tagList.get(i);
                updateMap = tag.command(tagList, dataCenter);
                
                result = updateMap.entrySet().iterator().next().getKey();
                tagList = updateMap.entrySet().iterator().next().getValue();
                if(result != "OK") {
                    return result;
                }
            } else {
                result = "Teste Cancelado";
                return result;
            }
        }
        return result;
    }

    private void startingTestSetup() {
        setDisableButtons(true);
        dataCenter.getDbConnector().initialSetup();
        ApplicationSetup.getSessionDTO().startingTestSetup();
    }

    private void setDisableButtons(boolean disable) {
        dataCenter.getController().getInitButton().setDisable(disable);
        dataCenter.getController().getBackButton().setDisable(disable);
    }

    private void closingTestSetup() {
        if(result.equals("OK")) {
            dataCenter.getDbConnector().endingSetup(dataCenter.getSapDataMap().getDataMap().get("serial"), tagList, result);
            ApplicationSetup.getSessionDTO().endingTestSetup(result);
        } else {
            exit = false;
            String logToAdd;
            int trials = 1;
            logToAdd = "Finalização do teste em andamento... \n";
            dataCenter.getController().getTestRoutineLog().setText(dataCenter.getController().getTestRoutineLog().getText() + logToAdd);
            for (int i = 0; i < trials; i++) {
                logToAdd = "Tentativa " + i+1 + "... \n";
                dataCenter.getController().getTestRoutineLog().setText(dataCenter.getController().getTestRoutineLog().getText() + logToAdd);
                if (execution(cancelTagList).equals("OK")) {
                    logToAdd = "Teste finalizado" + "\n";
                    dataCenter.getController().getTestRoutineLog().setText(dataCenter.getController().getTestRoutineLog().getText() + logToAdd);
                    setDisableButtons(false);
                    return;
                }
            }
            result = "Falha na finalização do teste";
        }
        setDisableButtons(false);
    }

    public void setExit() {
        this.exit = true;
    }

}
