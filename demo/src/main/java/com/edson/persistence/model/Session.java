package com.edson.persistence.model;

import javax.persistence.GeneratedValue;

//import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

//TODO: Verificar o que adicionar aqui
@Document
@Getter
public class Session {
    @Id
    private String timestamp;
    //private int cadastro;
    private long sessionTime;
    private int executingTestTime;
    //private int averageTimeBetweenTests;
    //private int averageTestTime;
    private int totalTestExecuted;
    private int totalTestApproved;
    private int totalTestFailed;
    private int totalTestCanceled;
    private String date;


    public Session(long sessionTime, int executingTestTime, int totalTestExecuted, int totalTestApproved, int totalTestFailed, int totalTestCanceled, String date) {
        this.sessionTime = sessionTime;
        this.executingTestTime = executingTestTime;
        this.totalTestExecuted = totalTestExecuted;
        this.totalTestApproved = totalTestApproved;
        this.totalTestFailed = totalTestFailed;
        this.totalTestCanceled = totalTestCanceled;
        this.date = date;
    }

}
