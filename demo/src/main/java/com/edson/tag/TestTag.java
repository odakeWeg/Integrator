package com.edson.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "test")
@XmlAccessorType (XmlAccessType.FIELD)
public class TestTag implements BaseTag{
    private String ae;
    private String firstName;
    private int id;
    private double income;
    private String lastName;
    private ListaDe lista;
    private int a;


    @Override
    public String command() {
        // TODO Auto-generated method stub
        return "OK";
    }


    public int getA() {
        return this.a;
    }

    public void setA(int a) {
        this.a = a;
    }
    
    public String getAe() {
        return this.ae;
    }
    
    
    public void setAe(String ae) {
        this.ae = ae;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getIncome() {
        return this.income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ListaDe getLista() {
        return this.lista;
    }

    public void setLista(ListaDe lista) {
        this.lista = lista;
    }
    

    @Override
    public String toString() {
        return "{" +
            " ae='" + getAe() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", id='" + getId() + "'" +
            ", income='" + getIncome() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", lista='" + getLista() + "'" +
            "}";
    }

}
