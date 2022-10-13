package com.edson.controller;

import java.util.concurrent.TimeUnit;

import com.edson.communication.IOLinkCommunication;
import com.edson.exception.CommunicationException;

import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws InterruptedException {

        /* 
        System.out.println(Long.toBinaryString(1234567890));
        long serialNumber = 1234567890;
        String binarySerialNumber = Long.toBinaryString(serialNumber);
        String[] dataToSendBuffer = new String[2];

        while(binarySerialNumber.length() < 32) {
            binarySerialNumber = "0" + binarySerialNumber;
        }

        System.out.println("Full: " + binarySerialNumber);

        dataToSendBuffer[0] = binarySerialNumber.substring(0, 16).substring(8) + binarySerialNumber.substring(0, 16).substring(0, 8);
        dataToSendBuffer[1] = binarySerialNumber.substring(16).substring(8) + binarySerialNumber.substring(16).substring(0, 8);

        System.out.println("First bin: " + dataToSendBuffer[0]);
        System.out.println("Second bin: " + dataToSendBuffer[1]);

        int[] dataToSend = {Integer.parseInt(dataToSendBuffer[0], 2), Integer.parseInt(dataToSendBuffer[1], 2)};

        System.out.println("First: " + dataToSend[0]);
        System.out.println("Second: " + dataToSend[1]);
        */

        
        IOLinkCommunication iol = new IOLinkCommunication("192.168.1.250", 502, 1, 1000);

        
        try {
            iol.startConnection();
            //System.out.println(iol.readSingleRegister(1));
            TimeUnit.MILLISECONDS.sleep(1000);
            int position = 1;
            int address = 21;
            //System.out.println("Leitura 21p1: " + iol.readMultipleRegisters(address, position)[0]);
            position = -2;
            //System.out.println("Leitura 21p2: " + iol.readMultipleRegisters(address, position)[0]);
            int[] pass = {17495, 8259, 21331};
            iol.writeMultipleRegister(4000, pass);
            iol.writeStringInRegister(4002, "1234567890");
            System.out.println("done");
            //iol.writeStringInRegister(address, );
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