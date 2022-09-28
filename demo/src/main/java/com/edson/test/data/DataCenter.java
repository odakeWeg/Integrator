package com.edson.test.data;

public class DataCenter {
    private BaseData sapDataMap;
    private BaseData inlineDataMap;

    public DataCenter(String barCode) {
        sapDataMap = new SapData(barCode);
        inlineDataMap = new InlineData();
    }


    public BaseData getSapDataMap() {
        return this.sapDataMap;
    }

    public BaseData getInlineDataMap() {
        return this.inlineDataMap;
    }

}
