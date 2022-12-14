package com.edson.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.edson.util.ViewConfigurationPathUtil;

@XmlRootElement(name = "root")
@XmlAccessorType (XmlAccessType.FIELD)
public class TagList{
    
    @XmlElement(name = "test")
    private List<TestTag> testTagList = new ArrayList<>();

    @XmlElement(name = "communication")
    private List<CommunicationTag> communicationTagList = new ArrayList<>();
    @XmlElement(name = "communicationIOLink")
    private List<CommunicationIOLinkTag> communicationIOLinkTagList = new ArrayList<>();
    @XmlElement(name = "communicationEthernet")
    private List<CommunicationEthernetTag> communicationEthernetTagList = new ArrayList<>();

    @XmlElement(name = "compare")
    private List<CompareTag> compareTagList = new ArrayList<>();
    @XmlElement(name = "verify")
    private List<VerifyTag> verifyTagList = new ArrayList<>();

    @XmlElement(name = "read")
    private List<ReadTag> readTagList = new ArrayList<>();
    @XmlElement(name = "variableRead")
    private List<VariableReadTag> variableReadTagList = new ArrayList<>();
    @XmlElement(name = "readMultiple")
    private List<ReadMultipleTag> readMultipleTagList = new ArrayList<>();
    
    @XmlElement(name = "write")
    private List<WriteTag> writeTagList = new ArrayList<>();
    @XmlElement(name = "variableWrite")
    private List<VariableWriteTag> variableWriteTagList = new ArrayList<>();
    @XmlElement(name = "writeString")
    private List<WriteStringTag> writeStringTagList = new ArrayList<>();
    @XmlElement(name = "writeMultiple")
    private List<WriteMultipleTag> writeMultipleTag = new ArrayList<>();
    

    private List<Object> tagManager = new ArrayList<>();
    private List<BaseTag> baseTagManager = new ArrayList<>();

    public TagList() {
    }

    public TagList(List<BaseTag> tags) throws ClassNotFoundException {
        HashMap<String, List> hm = createHashMapFromLists();
        for (int i = 0; i < tags.size(); i++) {
            String className = tags.get(i).getTagName().substring(0, 1).toUpperCase() + tags.get(i).getTagName().substring(1) + "Tag";
            Class<?> tag = Class.forName(ViewConfigurationPathUtil.TAG_PROJECT_PATH + className);
            hm.get(className).add(tag.cast(tags.get(i)));
        }
    }

    private HashMap<String, List> createHashMapFromLists() {
        HashMap<String, List> hm = new HashMap<>();
        hm.put("TestTag", testTagList);
        hm.put("CommunicationTag", communicationTagList);
        hm.put("CommunicationIOLinkTag", communicationIOLinkTagList);
        hm.put("CommunicationEthernetTag", communicationEthernetTagList);
        hm.put("CompareTag", compareTagList);
        hm.put("ReadTag", readTagList);
        hm.put("WriteTag", writeTagList);
        hm.put("VerifyTag", verifyTagList);
        hm.put("VariableReadTag", variableReadTagList);
        hm.put("VariableWriteTag", variableWriteTagList);
        hm.put("WriteStringTag", writeStringTagList);
        hm.put("WriteMultipleTag", writeMultipleTag);
        hm.put("ReadMultipleTag", readMultipleTagList);

        return hm;
    }

    public void concatenateTagList() {
        tagManager.addAll(testTagList);
        tagManager.addAll(communicationTagList);
        tagManager.addAll(communicationIOLinkTagList);
        tagManager.addAll(communicationEthernetTagList);
        tagManager.addAll(compareTagList);
        tagManager.addAll(readTagList);
        tagManager.addAll(writeTagList);
        tagManager.addAll(verifyTagList);
        tagManager.addAll(variableReadTagList);
        tagManager.addAll(variableWriteTagList);
        tagManager.addAll(writeStringTagList);
        tagManager.addAll(readMultipleTagList);
        tagManager.addAll(writeMultipleTag);
    }

    public List<BaseTag> getBaseTagManager() {
        return this.baseTagManager;
    }

    public void setBaseTagManager() {
        concatenateTagList();
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
