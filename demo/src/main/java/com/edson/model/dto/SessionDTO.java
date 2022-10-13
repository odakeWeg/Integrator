package com.edson.model.dto;

import java.util.Date;

import com.edson.persistence.model.Session;
import com.edson.persistence.repository.SessionRepository;
import com.edson.util.ApplicationSetup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SessionDTO {

    private SessionRepository sessionRepository;

    //private int id;
    //private int cadastro;
    private long sessionTime;
    private int executingTestTime = 0;
    //private int averageTimeBetweenTests;
    //private int averageTestTime;
    private int totalTestExecuted = 0;
    private int totalTestApproved = 0;
    private int totalTestFailed = 0;
    private int totalTestCanceled = 0;
    private String date;

    private long startTime;
    private long endTime;
    private long startTestTime;
    private long endTestTime;

    public SessionDTO() {
        sessionRepository = ApplicationSetup.getApplicationContext().getBean(SessionRepository.class);

        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        this.date = dateFormat.format(date);  

        startTime = System.currentTimeMillis() / 1000;
    }

    public void save() {
        endTime = System.currentTimeMillis() / 1000;
        sessionTime = endTime - startTime;

        Session session = new Session(sessionTime, executingTestTime, totalTestExecuted, totalTestApproved, totalTestFailed, totalTestCanceled, date);
        sessionRepository.save(session);
    }

    public void startingTestSetup() {
        startTestTime = System.currentTimeMillis() / 1000;
    }

    public void endingTestSetup(String testResult) {
        endTestTime = System.currentTimeMillis() / 1000;
        executingTestTime += endTestTime - startTestTime;

        totalTestExecuted += 1;

        if (testResult == "OK") {
            totalTestApproved += 1;
        } else {
            totalTestFailed += 1;
        }
    }

    public void cancellingTestSetup() {
        endTestTime = System.currentTimeMillis() / 1000;
        executingTestTime += endTestTime - startTestTime;

        totalTestExecuted += 1;
        totalTestCanceled += 1;
    }

}
