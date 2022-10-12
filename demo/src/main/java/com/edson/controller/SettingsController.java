package com.edson.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Document;

import com.edson.App;
import com.edson.model.dto.TestRoutine;
import com.edson.tag.BaseTag;
import com.edson.tag.TagList;
import com.edson.util.ViewConfigurationPathUtil;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

public class SettingsController implements Initializable {

    @FXML
    private TableView<TestRoutine> testTable;
    @FXML
    private TableColumn<TestRoutine, Integer> idCol;
    @FXML
    private TableColumn<TestRoutine, Integer> criadorCol;
    @FXML
    private TableColumn<TestRoutine, String> produtoCol;
    @FXML
    private TableColumn<TestRoutine, Integer> etapasCol;
    @FXML
    private TableColumn<TestRoutine, String> dataCol;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        criadorCol.setCellValueFactory(new PropertyValueFactory<>("criador"));
        produtoCol.setCellValueFactory(new PropertyValueFactory<>("produto"));
        etapasCol.setCellValueFactory(new PropertyValueFactory<>("etapas"));
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));

        //testTable.setItems(nameList());
        uploadTestToTable();
    }

    private void uploadTestToTable() {
        JAXBContext jaxbContext;
        TagList tagList;
        File dir = new File(ViewConfigurationPathUtil.TEST_ROUTINE_PATH);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            try {
                jaxbContext = JAXBContext.newInstance(TagList.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                for (File child : directoryListing) {
                    tagList = (TagList) jaxbUnmarshaller.unmarshal(child);
                    tagList.setBaseTagManager();
                    Path file = Paths.get(child.getPath());
                    BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
                    testTable.getItems().add(new TestRoutine(testTable.getItems().size(), 7881, child.getName().split("\\.")[0], tagList.getBaseTagManager().size(), attr.creationTime().toString().split("T")[0], tagList.getBaseTagManager()));
                }
            } catch (JAXBException | IOException e) {
                Alert userAlert = new Alert(AlertType.ERROR);
                userAlert.setTitle("Upload de teste");
                userAlert.setHeaderText("Falha ao fazer o upload dos testes");
                userAlert.setContentText("Caminho do arquivo não encontrado ou arquivo fora do padrão");
                userAlert.showAndWait();
                e.getStackTrace();
            }
        } else {
            
        }
    }



    /* 
    //@TestingCode - Will be changed for a persistence or load files from computer
    private ObservableList<TestRoutine> nameList() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        String dataNow  = dtf.format(localDate);
        List<BaseTag> tagList = new ArrayList<>();
        return FXCollections.observableArrayList(
            new TestRoutine(0, 7881, "SSW900", 8, dataNow, tagList),
            new TestRoutine(1, 7881, "SSW07", 6, dataNow, tagList),
            new TestRoutine(2, 7881, "SSW08", 6, dataNow, tagList),
            new TestRoutine(3, 7881, "SSW900", 5, dataNow, tagList)
        );
    }
    */
    
    @FXML
    private void addTest() throws IOException {
        String name = requestName();
        if(name.equals("")) {
            Alert userAlert = new Alert(AlertType.ERROR);
			userAlert.setTitle("Validação de entrada");
			userAlert.setHeaderText("Nenhum nome inserido");
			userAlert.setContentText("Favor inserir valor para adição de teste novo");
			userAlert.showAndWait();
        } else {
            List<BaseTag> tagList = new ArrayList<>();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
            LocalDate localDate = LocalDate.now();
            String dataNow  = dtf.format(localDate);
            TestRoutine testRoutine = new TestRoutine(testTable.getItems().size(), 22, name, tagList.size(), dataNow, tagList);
            testTable.getItems().add(testRoutine);
        }
    }

    @FXML
    private void editForm() throws IOException {
        
        if(!testTable.getSelectionModel().getSelectedItems().isEmpty()) {
            String name = requestName();
            if(name.equals("")) {
                Alert userAlert = new Alert(AlertType.ERROR);
                userAlert.setTitle("Validação de entrada");
                userAlert.setHeaderText("Nenhum nome inserido");
                userAlert.setContentText("Favor inserir valor para adição de teste novo");
                userAlert.showAndWait();
            } else {
                testTable.getSelectionModel().getSelectedItems().get(0).setProduto(name);
            }
        }
    }

    private String requestName() {
        TextInputDialog nameRequest = new TextInputDialog();
		nameRequest.setTitle("Test Name");
		nameRequest.setHeaderText("Digite nome do produto:");
		nameRequest.setContentText("Produto:");
		Optional<String> name = nameRequest.showAndWait();
        if(name.isPresent()){
            return name.get();
        } else {
            return "";
        }
    }

    @FXML
    private void removeSelectedItem() throws IOException {
        ObservableList<TestRoutine> testRoutineList =  testTable.getItems();
        int removedId = testTable.getSelectionModel().getSelectedItem().getId();
        testRoutineList.size();
        for (int i =0; i < testRoutineList.size(); i++) {
            if(removedId < testRoutineList.get(i).getId()) {
                testRoutineList.get(i).setId(testRoutineList.get(i).getId()-1);
            }
        }
        testTable.getItems().removeAll(testTable.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void switchToPreviousPage() throws IOException {
        App.setRoot(ViewConfigurationPathUtil.VIEW_PATH + "mainScreen");
    }

    @FXML
    private void moveDown() {
        ObservableList<TestRoutine> testRoutineList =  testTable.getItems();
        int id = testTable.getSelectionModel().getSelectedItem().getId();
        int idSwap = id+1;
        if(testTable.getItems().size()-1 > id) {
            testRoutineList.get(id).setId(idSwap);
            testRoutineList.get(idSwap).setId(id);
            Collections.swap(testRoutineList, id, idSwap);
        }
    }

    @FXML
    private void moveUp() {
        ObservableList<TestRoutine> testRoutineList =  testTable.getItems();
        int id = testTable.getSelectionModel().getSelectedItem().getId();
        int idSwap = id-1;
        if(id > 0) {
            testRoutineList.get(id).setId(idSwap);
            testRoutineList.get(idSwap).setId(id);
            Collections.swap(testRoutineList, id, idSwap);
        }
    }

    @FXML
    private void enterTest() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewConfigurationPathUtil.FULL_VIEW_PATH + "testSettings.fxml"));
        Parent part = (Parent) loader.load();
        TestSettingsController testSettingsController = (TestSettingsController) loader.getController();
        testSettingsController.setStage(part, testTable.getSelectionModel().getSelectedItem().getTagList());
        testTable.getSelectionModel().getSelectedItem().setTagList(testSettingsController.getTagList());
    }

    @FXML
    private void downloadSelectedTest() {
        TagList list;
        try {
            list = new TagList(testTable.getSelectionModel().getSelectedItem().getTagList());
            marshalingToXML(list);
        } catch (ClassNotFoundException | JAXBException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    
    public void marshalingToXML(TagList listToMarshall) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TagList.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //jaxbMarshaller.marshal(listToMarshall, System.out);
        jaxbMarshaller.marshal(listToMarshall, new File(ViewConfigurationPathUtil.TEST_ROUTINE_PATH + testTable.getSelectionModel().getSelectedItem().getProduto() + ".xml"));
    }

}



















    //@TODO
    /* 
    @FXML
    private void duplicateTest() throws CloneNotSupportedException {
        //@TODO: Deep clone no testRoutine, na Lista e em, todas as Tags
        //TestRoutine testDuplicate = new TestRoutine(testTable.getSelectionModel().getSelectedItem());
        //testDuplicate.setId(testTable.getItems().size());
        //testTable.getItems().add(testDuplicate);      
    }
    */

