package com.edson.test.data;

import java.util.HashMap;

import net.weg.searchsap.Caract;
import net.weg.searchsap.ProdutoBrutoSAP;

public class SapData implements BaseData {
    private HashMap<String, String> sapDataMap = new HashMap<>();

    public SapData(String barCode) {
        getDataBy2DBarcodeString(barCode);
    }

    @Override
    public HashMap<String, String> getDataMap() {
        return sapDataMap;
    }

    public void getDataBy2DBarcodeString(String barCode) {
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
