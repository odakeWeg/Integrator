package com.edson.test.data;

import java.io.File;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.edson.model.dto.TestDataDTO;
import com.edson.persistence.model.TestData;
import com.edson.persistence.repository.TestDataRepository;
import com.edson.tag.BaseTag;
import com.edson.tag.TagList;
import com.edson.util.ApplicationSetup;

public class DataBaseConnector {
    private TestDataRepository testDataRepository;
    private TestDataDTO testDataDTO;

    public DataBaseConnector() {
        this.testDataRepository = ApplicationSetup.getApplicationContext().getBean(TestDataRepository.class);
        testDataDTO = new TestDataDTO();
    }

    public void initialSetup() {
        testDataDTO.startingSetup();
    }

    public void endingSetup(String serial, List<BaseTag> tagList) {
        testDataDTO.endingSetup(serial, saveTest(tagList));
        testDataRepository.save(testDataDTO.getDataToSave());
    }

    public TestDataRepository getTestDataRepository() {
        return this.testDataRepository;
    }

    public String saveTest(List<BaseTag> tagList) {
        TagList tags;
        String stringfiedXML;
        try {
            tags = new TagList(tagList);
            stringfiedXML = readXML(marshalingToXML(tags));
        } catch (ClassNotFoundException | JAXBException e) {
            stringfiedXML = "Falha ao converter documento para String";
        } 
        return stringfiedXML;     
    }

    private File marshalingToXML(TagList listToMarshall) throws JAXBException {
        File file = new File("listMarshalFile.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(TagList.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(listToMarshall, file);

        return file;
    }

    private String readXML(File file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            return writeXmlDocumentToXmlFile(xmlDocument);
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String writeXmlDocumentToXmlFile(Document xmlDocument)
    {
        String xmlString;
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
    
            xmlString = writer.getBuffer().toString(); 
        } 
        catch (Exception e) {
            xmlString = "Falha ao converter documento para String";
        }
        return xmlString;
    }
}
