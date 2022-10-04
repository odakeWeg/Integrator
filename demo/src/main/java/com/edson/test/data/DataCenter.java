package com.edson.test.data;

import com.edson.controller.AutomatedTestController;

public class DataCenter {
    private BaseData sapDataMap;
    private BaseData inlineDataMap;
    private AutomatedTestController controller;

    public DataCenter(String barCode, AutomatedTestController controller) {
        sapDataMap = new SapData(barCode);
        inlineDataMap = new InlineData();
        this.controller = controller;
    }

    public AutomatedTestController getController() {
        return this.controller;
    }

    public BaseData getSapDataMap() {
        return this.sapDataMap;
    }

    public BaseData getInlineDataMap() {
        return this.inlineDataMap;
    }

}
