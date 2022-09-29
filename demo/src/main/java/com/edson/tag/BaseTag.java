package com.edson.tag;

import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.edson.test.data.DataCenter;

//@XmlRootElement(name = "baseTag")
@XmlAccessorType (XmlAccessType.FIELD)
public abstract class BaseTag {
    protected int id;
    protected String testResult;
    protected String tagName;
    
    @XmlTransient
    protected List<BaseTag> tagList;
    @XmlTransient
    protected DataCenter dataCenter;

    abstract String executeCommand();
    abstract void setTagName();
    
    public HashMap<String, List<BaseTag>> command(List<BaseTag> tagList, DataCenter dataCenter) {
        this.tagList = tagList;
        this.dataCenter = dataCenter;
        setTagName();
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
