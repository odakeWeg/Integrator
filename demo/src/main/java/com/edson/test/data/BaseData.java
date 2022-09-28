package com.edson.test.data;

import java.util.HashMap;

public interface BaseData {
    public void setSapDataMap();
    public HashMap<String, String> getSapDataMap();

    public void setInlineDataMap();
    public HashMap<String, String> getInlineDataMap();
}
