package com.edson.tag;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "verify")
@XmlAccessorType (XmlAccessType.FIELD)
public class VerifyTag extends BaseTag{

    int targetStep;
    int measureScale;
    int value;
    int tolerance;

    @Override
    String executeCommand() {
        
        return null;
    }

}
