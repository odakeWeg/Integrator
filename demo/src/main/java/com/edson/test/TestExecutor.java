package com.edson.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.edson.tag.BaseTag;

public class TestExecutor {
    
    private List<BaseTag> tagList = new ArrayList<>();

    public TestExecutor(List<BaseTag> tagList) {
        this.tagList = tagList;

    }

    public String executeTest() {
        BaseTag tag;
        HashMap<String, List<BaseTag>> updateMap = new HashMap<String, List<BaseTag>>();
        String result = "Erro não esperado";
        for (int i = 0; i < tagList.size(); i++) {
            tag = tagList.get(i);
            updateMap = tag.command(tagList);
            
            result = updateMap.entrySet().iterator().next().getKey();
            tagList = updateMap.entrySet().iterator().next().getValue();
            if(result != "OK") {
                return result;
            }
        }
        return result;
    }
}
