package net.weg.wdc.stf;

import org.junit.Test;

import net.weg.searchsap.Caract;
import net.weg.searchsap.ProdutoBrutoSAP;

//@TODO: Implementar todos os testes unitários
public class SapInterfaceTests {

    @Test
    public void shouldExecuteSapCommunitcationAndReturnObject() {
        String barCode = "017894171148035 211076034736 10 911125304649 24010194173";
        ProdutoBrutoSAP produtoBrutoSAP = new ProdutoBrutoSAP(barCode);
        produtoBrutoSAP.importarCaracteristicas();
        String result = produtoBrutoSAP.getCaract(Caract.REF_PRODUTO_AUTOMACAO);
        System.out.println(result);
    }

}
