package com.edson.persistence.model;

import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

//TODO: Verificar o que adicionar aqui
@Document
@Getter
public class Session {
    @Id
    @GeneratedValue
    private int id;
    private int cadastro;
    private int sessionTime;
    private int executingTestTime;
    private int averageTimeBetweenTests;
    private int averageTestTime;
    private int totalTestExecuted;
    private int totalTestFailed;
    private String date;
}
