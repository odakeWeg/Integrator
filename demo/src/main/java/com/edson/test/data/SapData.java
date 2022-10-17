package com.edson.test.data;

import java.util.HashMap;

import net.weg.searchsap.Caract;
import net.weg.searchsap.ProdutoBrutoSAP;

public class SapData implements BaseData {
    private HashMap<String, String> sapDataMap = new HashMap<>();

    public SapData(String barCode) {
        /* 
        sapDataMap.put("serial", barCode);
        sapDataMap.put("serial1", sapDataMap.get("serial").substring(0, 4));
        sapDataMap.put("serial2", sapDataMap.get("serial").substring(4, 8));
        sapDataMap.put("serial3", sapDataMap.get("serial").substring(8));
        */
        getDataBy2DBarcodeString(barCode);
    }

    @Override
    public HashMap<String, String> getDataMap() {
        return sapDataMap;
    }

    private void getDataBy2DBarcodeString(String barCode) {
        ProdutoBrutoSAP produtoBrutoSAP = new ProdutoBrutoSAP(barCode);
        produtoBrutoSAP.importarCaracteristicas();
        setDataMap(produtoBrutoSAP);
        sapDataMap.put("serial", Long.toString(produtoBrutoSAP.getSerial()));
        sapDataMap.put("material", Long.toString(produtoBrutoSAP.getMaterial()));
        sapDataMap.put("ordem", Long.toString(produtoBrutoSAP.getOrdemProducao()));
        sapDataMap.put("serial1", sapDataMap.get("serial").substring(0, 4));
        sapDataMap.put("serial2", sapDataMap.get("serial").substring(4, 8));
        sapDataMap.put("serial3", sapDataMap.get("serial").substring(8));
	}

    private void setDataMap(ProdutoBrutoSAP produtoBrutoSAP) {
        for (Caract data: Caract.values()) {
            try {
                if(produtoBrutoSAP.getCaract(data)==null) {
                    //sapDataMap.put(data.name(), "N/A");
                } else {
                    sapDataMap.put(data.name(), produtoBrutoSAP.getCaract(data));
                }
            } catch (NullPointerException e) {

            }
        }
    }

}
