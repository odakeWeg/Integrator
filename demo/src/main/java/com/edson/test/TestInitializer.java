package com.edson.test;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.edson.exception.TestUnmarshalingException;
import com.edson.tag.BaseTag;
import com.edson.tag.TagList;

public class TestInitializer extends Thread {
    @Override
    public void run() {
        TagList tagList;
        String result;
        //@TODO: SAP, INLINE AND DEFINITION OF TEST ROUTINE
        try {
            tagList = unmarshalingFromXML();
            result = startTestingRoutine(tagList.getBaseTagManager());
        } catch (TestUnmarshalingException e) {
            return;
        }
    }

    private String startTestingRoutine(List<BaseTag> tagList) {
        TestExecutor testExecutor;
        String result;
        testExecutor = new TestExecutor(tagList);
        result = testExecutor.executeTest();

        return result;
    }

    public TagList unmarshalingFromXML() throws TestUnmarshalingException {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(TagList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            TagList tagList = (TagList) jaxbUnmarshaller.unmarshal(new File("C:/Git/TesteXStream/demo/src/main/resources/com/edson/testRoutine/test3.xml"));
            return tagList;
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