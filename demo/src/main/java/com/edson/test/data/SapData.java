package com.edson.test.data;

import java.util.HashMap;

import net.weg.searchsap.Caract;
import net.weg.searchsap.ProdutoBrutoSAP;

public class SapData implements BaseData {
    private HashMap<String, String> sapDataMap = new HashMap<>();

    public SapData(String barCode) {
        //getDataBy2DBarcodeString(barCode);
        populateSapData();
    }

    //@TestingCode
    private void populateSapData() {
        sapDataMap.put("serial", "1234567890");
        sapDataMap.put("corrente", "450A");
        sapDataMap.put("REF_PRODUTO_AUTOMACAO", "test5");
        sapDataMap.put("MAC", "10203040");
        sapDataMap.put("familia", "SSW900");
        sapDataMap.put("versao", "1.30");
    }

    @Override
    public HashMap<String, String> getDataMap() {
        return sapDataMap;
    }

    private void getDataBy2DBarcodeString(String barCode) {
        ProdutoBrutoSAP produtoBrutoSAP = new ProdutoBrutoSAP(barCode);
        produtoBrutoSAP.importarCaracteristicas();
        setDataMap(produtoBrutoSAP);
	}

    private void setDataMap(ProdutoBrutoSAP produtoBrutoSAP) {
        for (Caract data: Caract.values()) {
            sapDataMap.put(data.name(), produtoBrutoSAP.getCaract(data));
        }
    }

    private void checkedBoxArray() {
        //@TODO: front necessary
    }

}
