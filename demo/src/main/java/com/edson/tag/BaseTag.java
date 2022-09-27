package com.edson.tag;

import java.util.HashMap;
import java.util.List;

//@XmlRootElement(name = "baseTag")
public interface BaseTag {
    public HashMap<String, List<BaseTag>> command(List<BaseTag> tagList);
    public List<BaseTag> updateList(List<BaseTag> tagList);
    public String executeCommand();
    public int getId(); 
}
