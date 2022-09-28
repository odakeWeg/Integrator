package com.edson.tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
@XmlAccessorType (XmlAccessType.FIELD)
public class TagList{
    
    @XmlElement(name = "test")
    private List<TestTag> testTagList = new ArrayList<>();
    @XmlElement(name = "communication")
    private List<CommunicationTag> communicationTagList = new ArrayList<>();
    @XmlElement(name = "compare")
    private List<CompareTag> compareTagList = new ArrayList<>();
    @XmlElement(name = "read")
    private List<ReadTag> readTagList = new ArrayList<>();
    @XmlElement(name = "write")
    private List<WriteTag> writeTagList = new ArrayList<>();
    @XmlElement(name = "verify")
    private List<VerifyTag> verifyTagList = new ArrayList<>();
    @XmlElement(name = "variableRead")
    private List<VariableReadTag> variableReadTagList = new ArrayList<>();
    @XmlElement(name = "variableWrite")
    private List<VariableWriteTag> variableWriteTagList = new ArrayList<>();

    private List<Object> tagManager = new ArrayList<>();
    private List<BaseTag> baseTagManager = new ArrayList<>();

    public void concatenateTagList() {
        tagManager.addAll(testTagList);
        tagManager.addAll(communicationTagList);
        tagManager.addAll(compareTagList);
        tagManager.addAll(readTagList);
        tagManager.addAll(writeTagList);
        tagManager.addAll(verifyTagList);
        tagManager.addAll(variableReadTagList);
        tagManager.addAll(variableWriteTagList);
    }

    public List<BaseTag> getBaseTagManager() {
        return this.baseTagManager;
    }

    public void setBaseTagManager() {
        baseTagManager = createBaseTagFromObject(baseTagManager);
        baseTagManager = sortById(baseTagManager);
    }

    private List<BaseTag> createBaseTagFromObject(List<BaseTag> baseTagManager) {
        BaseTag buffer;
        for (int i = 0; i < tagManager.size(); i++) {
            buffer = (BaseTag) tagManager.get(i);
            baseTagManager.add(buffer);
        }
        return baseTagManager;
    }
    
    private List<BaseTag> sortById(List<BaseTag> list) {
        List<BaseTag> listSorted = new ArrayList<>();
        while(list.size() > listSorted.size()) {
            for (int i = 0; i< list.size(); i++) {
                if(list.get(i).getId() == listSorted.size()) {
                    listSorted.add(list.get(i));
                }
            }
        }
        return listSorted;
    }
}
