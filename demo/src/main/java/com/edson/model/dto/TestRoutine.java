package com.edson.model.dto;

import java.sql.Date;
import java.util.List;

import com.edson.tag.BaseTag;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TestRoutine{
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty criador;
    private final SimpleStringProperty produto;
    private final SimpleIntegerProperty etapas;
    private final SimpleStringProperty data;
    public List<BaseTag> tagList;

    public TestRoutine(Integer id, Integer criador, String produto, Integer etapas, String data, List<BaseTag> tagList) {
        this.id = new SimpleIntegerProperty(id);
        this.criador = new SimpleIntegerProperty(criador);
        this.produto = new SimpleStringProperty(produto);
        this.etapas = new SimpleIntegerProperty(etapas);
        this.data = new SimpleStringProperty(data);
        this.tagList = tagList;
    }

    //@TODO: Clone properties
    public TestRoutine(TestRoutine testRoutine) {
        this.id = testRoutine.idProperty();
        this.criador = testRoutine.criadorProperty();
        this.produto = testRoutine.produtoProperty();
        this.etapas = testRoutine.etapasProperty();
        this.data = testRoutine.dataProperty();
        this.tagList = testRoutine.tagList;
    }

    public Integer getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    /* ---------------------------------------------------- */

    public Integer getCriador() {
        return criador.get();
    }

    public SimpleIntegerProperty criadorProperty() {
        return criador;
    }

    public void setCriador(Integer criador) {
        this.criador.set(criador);
    }

    /* ---------------------------------------------------- */
    
    public String getProduto() {
        return produto.get();
    }

    public SimpleStringProperty produtoProperty() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto.set(produto);
    }

    /* ---------------------------------------------------- */

    public Integer getEtapas() {
        return etapas.get();
    }

    public SimpleIntegerProperty etapasProperty() {
        return etapas;
    }

    public void setEtapas(Integer etapas) {
        this.etapas.set(etapas);
    }

    /* ---------------------------------------------------- */

    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }

    /* ---------------------------------------------------- */

}
