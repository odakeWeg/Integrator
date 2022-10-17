package com.edson.model.dto;

import java.sql.Timestamp;

import com.edson.persistence.model.TestData;

public class TestDataDTO {
    private String timestamp;
    //private int id;
    //private int sessionId;
    //private int cadastro;
    private String serial;
    private String result;
    private long duration;
    private String tagList;

    private long startTime;

    public void startingSetup() {
        startTime = System.currentTimeMillis() / 1000;
    }

    public void endingSetup(String serial, String tagList, String result){
        this.tagList = tagList;
        this.serial = serial;
        this.result = result;
        
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.timestamp = String.valueOf(timestamp.getTime()); 

        long endTime = System.currentTimeMillis() / 1000;
        this.duration = endTime - startTime;
    }

    public TestData getDataToSave() {
        return new TestData(serial, result, duration, tagList, timestamp);
    }

}
