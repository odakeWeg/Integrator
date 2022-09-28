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

    public String getName() {
        return this.name;
    }

}
