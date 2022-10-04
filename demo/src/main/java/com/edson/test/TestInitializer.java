package com.edson.test;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.edson.controller.AutomatedTestController;
import com.edson.exception.TestUnmarshalingException;
import com.edson.tag.BaseTag;
import com.edson.tag.TagList;
import com.edson.test.data.DataCenter;
import com.edson.util.ViewConfigurationPathUtil;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TestInitializer extends Thread {
    private DataCenter dataCenter;
    private String barCode;
    private String result;
    private AutomatedTestController controller;

    public TestInitializer(String barCode, AutomatedTestController controller) {
        this.barCode = barCode;
        this.controller = controller;
    }

    @Override
    public void run() {
        
        dataCenter = new DataCenter(barCode, controller);
        initSetup();
        try {
            result = startTestingRoutine(getList());
        } catch (TestUnmarshalingException e) {
            result = "Erro ao puxar rotina de teste";
        }
        endSetup();
    }
    
    public void showResultMessage() {
        Platform.runLater(() -> {
            if(result.equals("OK")) {
                Alert userAlert = new Alert(AlertType.INFORMATION);
                userAlert.setTitle("Resultado do teste");
                userAlert.setHeaderText("Teste realizado com sucesso!");
                userAlert.setContentText("Nenhuma falha encontrada");
                userAlert.showAndWait();
            } else {
                Alert userAlert = new Alert(AlertType.ERROR);
                userAlert.setTitle("Resultado do teste");
                userAlert.setHeaderText("Falha encontrada durante o teste!");
                userAlert.setContentText("Motivo: " + result);
                userAlert.showAndWait();
            }
        });
    }

    private void initSetup() {
        dataCenter.getController().getTestRoutineLog().setText("");
        dataCenter.getController().getStatus().setVisible(true);
        dataCenter.getController().getStatusRectangle().setVisible(true);
    }

    private void endSetup() {
        String logToAdd = "Resultado do teste: " + result + "\n";
        dataCenter.getController().getTestRoutineLog().setText(dataCenter.getController().getTestRoutineLog().getText() + logToAdd);
        dataCenter.getController().getStatus().setVisible(false);
        dataCenter.getController().getStatusRectangle().setVisible(false);
        showResultMessage();
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
            File testFile = new File(ViewConfigurationPathUtil.TEST_ROUTINE_PATH + ViewConfigurationPathUtil.TEST_ROUTINE_NAME + ".xml");
            return (TagList) jaxbUnmarshaller.unmarshal(testFile);
        } catch (JAXBException e) {
            throw new TestUnmarshalingException("Falha na aquisição da rotina de teste!");
        }
    }

    public String getResult() {
        return this.result;
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