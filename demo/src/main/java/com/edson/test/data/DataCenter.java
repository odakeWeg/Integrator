package com.edson.test.data;

import com.edson.controller.AutomatedTestController;

public class DataCenter {
    private BaseData sapDataMap;
    private InlineConnector inlineConnector;
    private AutomatedTestController controller;
    private DataBaseConnector dbConnector;

    public DataCenter(String barCode, AutomatedTestController controller) {
        this.sapDataMap = new SapData(barCode);
        this.inlineConnector = new InlineConnector();
        this.controller = controller;
        this.dbConnector = new DataBaseConnector();
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

    public DataBaseConnector getDbConnector() {
        return this.dbConnector;
    }

}
