package com.edson.test.data;

import com.edson.controller.AutomatedTestController;

public class DataCenter {
    private BaseData sapDataMap;
    private InlineConnector inlineConnector;
    private AutomatedTestController controller;

    public DataCenter(String barCode, AutomatedTestController controller) {
        sapDataMap = new SapData(barCode);
        inlineConnector = new InlineConnector();
        this.controller = controller;
    }

    public AutomatedTestController getController() {
        return this.controller;
    }

    public BaseData getSapDataMap() {
        return this.sapDataMap;
    }

    public InlineConnector getInlineConnector() {
        return this.inlineConnector;
    }

}
