package com.edson.test;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.edson.exception.TestUnmarshalingException;
import com.edson.tag.BaseTag;
import com.edson.tag.TagList;
import com.edson.test.data.DataCenter;
import com.edson.util.ViewConfigurationPathUtil;

public class TestInitializer extends Thread {
    DataCenter dataCenter;
    private String barCode;
    private String result;

    public TestInitializer(String barCode) {
        this.barCode = barCode;
    }

    @Override
    public void run() {
        
        dataCenter = new DataCenter(barCode);
        try {
            result = startTestingRoutine(getList());
        } catch (TestUnmarshalingException e) {
            result = "Erro ao puxar rotina de teste";
        }
        System.out.println(result);
        //@TODO: Mostrar ao operador se falhou ou não dependendo do resultado
    }

    private List<BaseTag> getList() throws TestUnmarshalingException {
        TagList tagList;
        tagList = unmarshalingFromXML();
        tagList.setBaseTagManager();
        return tagList.getBaseTagManager();
    }

    private String startTestingRoutine(List<BaseTag> tagList) {
        TestExecutor testExecutor;
        String result;
        testExecutor = new TestExecutor(tagList, dataCenter);
        result = testExecutor.executeTest();

        return result;
    }

    public TagList unmarshalingFromXML() throws TestUnmarshalingException {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(TagList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File testFile = new File(ViewConfigurationPathUtil.TEST_ROUTINE_PATH + dataCenter.getSapDataMap().getDataMap().get(ViewConfigurationPathUtil.TEST_ROUTINE_NAME) + ".xml");
            return (TagList) jaxbUnmarshaller.unmarshal(testFile);
        } catch (JAXBException e) {
            throw new TestUnmarshalingException("Falha na aquisição da rotina de teste!");
        }
    }
}






























/* 
    public void marshalingToXML() {
        JAXBContext jaxbContext = JAXBContext.newInstance(TestTag.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        //Marshal the employees list in console
        jaxbMarshaller.marshal(test, System.out);
        
        //Marshal the employees list in file
        jaxbMarshaller.marshal(test, new File("C:/Git/TesteXStream/demo/src/main/resources/com/edson/testRoutine/test2.xml"));
    }
    */