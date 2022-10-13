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
    private DataCenter dataCenter;
    
    String result;

    public TestExecutor(List<BaseTag> tagList, DataCenter dataCenter) {
        this.tagList = tagList;
        this.dataCenter = dataCenter;
    }

    public String executeTest() {
        startingTestSetup();
        execution();
        closingTestSetup();
        return result;
    }

    private void execution() {
        BaseTag tag;
        HashMap<String, List<BaseTag>> updateMap = new HashMap<String, List<BaseTag>>();
        result = "Erro não esperado";
        for (int i = 0; i < tagList.size(); i++) {
            tag = tagList.get(i);
            updateMap = tag.command(tagList, dataCenter);
            
            result = updateMap.entrySet().iterator().next().getKey();
            tagList = updateMap.entrySet().iterator().next().getValue();
            if(result != "OK") {
                return;
            }
        }
    }

    private void startingTestSetup() {
        dataCenter.getDbConnector().initialSetup();
        ApplicationSetup.getSessionDTO().startingTestSetup();
    }

    private void closingTestSetup() {
        dataCenter.getDbConnector().endingSetup(dataCenter.getSapDataMap().getDataMap().get("serial"), tagList);
        ApplicationSetup.getSessionDTO().endingTestSetup(result);
    }

}
