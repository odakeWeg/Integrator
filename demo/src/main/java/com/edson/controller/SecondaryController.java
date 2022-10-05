package com.edson.controller;

import com.edson.communication.IOLinkCommunication;
import com.edson.exception.CommunicationException;
import com.edson.test.data.SapData;

import javafx.fxml.FXML;
import net.weg.searchsap.Caract;
import net.weg.searchsap.ProdutoBrutoSAP;

public class SecondaryController {

    @FXML
    private void switchToPrimary() {
        IOLinkCommunication iol = new IOLinkCommunication("192.168.1.250", 502, 1);
        try {
            iol.startConnection();
            System.out.println(iol.readSingleRegister(1));
        } catch (CommunicationException e) {
            System.out.println("ERRO!!!");
        }
        

        /* 
        String barCode = "017894171148035 211076034736 10 911125304649 24010194173";
        SapData data = new SapData(barCode);
        
        for (String name: data.getDataMap().keySet()) {
            String key = name.toString();
            String value = data.getDataMap().get(name).toString();
            System.out.println(key + ": " + value);
        }
        */
        /* 
        ProdutoBrutoSAP produtoBrutoSAP = new ProdutoBrutoSAP(barCode);
        produtoBrutoSAP.importarCaracteristicas();
        String result = produtoBrutoSAP.getCaract(Caract.REF_PRODUTO_AUTOMACAO);
        System.out.println(result);
        */

        //TestInitializer testInitializer = new TestInitializer("1234567890");
        //testInitializer.start();
        //App.setRoot("primary");
    }
}