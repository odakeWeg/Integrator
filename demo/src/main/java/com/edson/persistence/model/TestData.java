package com.edson.persistence.model;

import java.util.List;

import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.edson.tag.BaseTag;

import lombok.Getter;

@Document
@Getter
public class TestData {
    @Id
    private String timestamp;
    //private int id;
    //private int sessionId;
    //private int cadastro;
    private String serial;
    private String result;
    private long duration;
    private String tagList;

    public TestData(String serial, String result, long duration, String tagList, String timestamp) {
        this.serial = serial;
        this.result = result;
        this.duration = duration;
        this.tagList = tagList;
        this.timestamp = timestamp;
    }

}
