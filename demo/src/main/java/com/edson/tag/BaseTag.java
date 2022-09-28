package com.edson.tag;

import java.util.HashMap;
import java.util.List;

//@XmlRootElement(name = "baseTag")
public abstract class BaseTag {
    protected int id;
    protected String testResult;
    protected String tagName;
    protected List<BaseTag> tagList;

    abstract String executeCommand();
    
    public HashMap<String, List<BaseTag>> command(List<BaseTag> tagList) {
        this.tagList = tagList;
        List<BaseTag> updatedList;
        HashMap<String, List<BaseTag>> resultMap = new HashMap<>();
        
        testResult = executeCommand();
        updatedList = updateList();
        
        resultMap.put(testResult, updatedList);
        
        return resultMap;
    }

    protected List<BaseTag> updateList() {
        tagList.set(id, this);
        return tagList;
    }

    public int getId() {
        return id;
    }
    
}
