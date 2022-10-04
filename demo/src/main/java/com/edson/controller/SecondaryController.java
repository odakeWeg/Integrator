package com.edson.controller;

import javafx.fxml.FXML;
import net.weg.searchsap.Caract;
import net.weg.searchsap.ProdutoBrutoSAP;

public class SecondaryController {

    @FXML
    private void switchToPrimary() {
        String barCode = "017894171148035 211076034736 10 911125304649 24010194173";
        ProdutoBrutoSAP produtoBrutoSAP = new ProdutoBrutoSAP(barCode);
        produtoBrutoSAP.importarCaracteristicas();
        String result = produtoBrutoSAP.getCaract(Caract.REF_PRODUTO_AUTOMACAO);
        System.out.println(result);

        //TestInitializer testInitializer = new TestInitializer("1234567890");
        //testInitializer.start();
        //App.setRoot("primary");
    }
}