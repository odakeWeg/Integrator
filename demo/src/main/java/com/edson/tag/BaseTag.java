package com.edson.tag;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "baseTag")
public interface BaseTag {
    public String command();
    public void updateList();
    public String executeCommand();
    public int getId(); 
}
