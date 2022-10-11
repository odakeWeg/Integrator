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
    @GeneratedValue
    private int id;
    private int sessionId;
    private int cadastro;
    private String serial;
    private String result;
    private int duration;
    private List<BaseTag> tagList;
    private String date;
}
