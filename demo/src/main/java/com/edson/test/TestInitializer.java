package com.edson.test;

import java.io.File;
import java.util.HashMap;
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
    TestExecutor testExecutor;

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
        if(initSetup()) {
            try {
                result = startTestingRoutine(getList());
            } catch (TestUnmarshalingException e) {
                result = "Erro ao puxar rotina de teste";
            }
        } else {
            result = "Falha no apontamento do Inline";
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
                if(result.equals("Falha na finalização do teste")) {
                    Alert userAlert = new Alert(AlertType.ERROR);
                    userAlert.setTitle("Resultado do teste");
                    userAlert.setHeaderText("Perda de comunicação com o sistema!");
                    userAlert.setContentText("Pressione o botão de emergência");
                    userAlert.showAndWait();
                } else {
                    Alert userAlert = new Alert(AlertType.ERROR);
                    userAlert.setTitle("Resultado do teste");
                    userAlert.setHeaderText("Falha encontrada durante o teste!");
                    userAlert.setContentText("Motivo: " + result);
                    userAlert.showAndWait();
                }
            }
        });
    }

    private boolean initSetup() {
        try {
        dataCenter.getController().getTestRoutineLog().setText("");
        dataCenter.getController().getStatus().setVisible(true);
        dataCenter.getController().getStatusRectangle().setVisible(true);
        dataCenter.getInlineConnector().saveInitialEvent(barCode);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void endSetup() {
        saveInline();
        String logToAdd = "Resultado do teste: " + result + "\n";
        dataCenter.getController().getTestRoutineLog().setText(dataCenter.getController().getTestRoutineLog().getText() + logToAdd);
        dataCenter.getController().getStatus().setVisible(false);
        dataCenter.getController().getStatusRectangle().setVisible(false);
        showResultMessage();
    }

    private void saveInline() {
        try {
            if(result.equals("OK")) {
                dataCenter.getInlineConnector().saveApprovalEvent(barCode);
            } else {
                dataCenter.getInlineConnector().saveDisapprovalEvent(barCode, result);
            }
        } catch (Exception e) {
            result = "Falha no apontamento do Inline";
        }
    }

    private HashMap<String, List<BaseTag>> getList() throws TestUnmarshalingException {
        HashMap<String, TagList> tagList;
        HashMap<String, List<BaseTag>> baseTagList = new HashMap<String, List<BaseTag>>();

        tagList = unmarshalingFromXML();
        tagList.get("routine").setBaseTagManager();
        tagList.get("cancel").setBaseTagManager();

        baseTagList.put("routine", tagList.get("routine").getBaseTagManager());
        baseTagList.put("cancel", tagList.get("cancel").getBaseTagManager());

        return baseTagList;
    }

    private String startTestingRoutine(HashMap<String, List<BaseTag>> tagList) {
        String result;
        testExecutor = new TestExecutor(tagList, dataCenter);
        result = testExecutor.executeTest();

        return result;
    }

    public HashMap<String, TagList> unmarshalingFromXML() throws TestUnmarshalingException {
        HashMap<String, TagList> tagLists = new HashMap<String, TagList>();
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(TagList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            //File testFile = new File(ViewConfigurationPathUtil.TEST_ROUTINE_PATH + ViewConfigurationPathUtil.TEST_ROUTINE_NAME + "Routine.xml");
            //File cancelFile = new File(ViewConfigurationPathUtil.TEST_ROUTINE_PATH + ViewConfigurationPathUtil.TEST_ROUTINE_NAME + "Cancel.xml");
            File testFile = new File(ViewConfigurationPathUtil.TEST_ROUTINE_PATH + dataCenter.getSapDataMap().getDataMap().get("REF_PRODUTO_AUTOMACAO") + "Routine.xml");
            File cancelFile = new File(ViewConfigurationPathUtil.TEST_ROUTINE_PATH + dataCenter.getSapDataMap().getDataMap().get("REF_PRODUTO_AUTOMACAO") + "Cancel.xml");
            
            tagLists.put("routine", (TagList) jaxbUnmarshaller.unmarshal(testFile));
            tagLists.put("cancel", (TagList) jaxbUnmarshaller.unmarshal(cancelFile));
            return tagLists;
        } catch (JAXBException e) {
            throw new TestUnmarshalingException("Falha na aquisição da rotina de teste!");
        }
    }

    public String getResult() {
        return this.result;
    }

    public void interruptRoutine() {
        testExecutor.setExit();
    }
}