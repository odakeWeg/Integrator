package com.edson.tag;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "variableRead")
@XmlAccessorType (XmlAccessType.FIELD)
public class VariableReadTag extends BaseTag {

    private String variableRead;
    private Object read;

    @Override
    String executeCommand() {
        read = dataCenter.getSapDataMap().getDataMap().get(variableRead);
        return "OK";
    }
    
}
