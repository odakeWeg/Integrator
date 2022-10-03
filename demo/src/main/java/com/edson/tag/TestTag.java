package com.edson.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "test")
@XmlAccessorType (XmlAccessType.FIELD)
public class TestTag extends BaseTag{

    private String name;

    @Override
    String executeCommand() {
        return "OK";
    }

    @Override
    public void setTagName() {
        this.tagName = "test";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
