package com.edson.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import com.edson.model.dto.TestStep;
import com.edson.tag.BaseTag;
import com.edson.util.ViewConfigurationPathUtil;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TestSettingsController implements Initializable {

    @FXML
    private TableView<TestStep> testStepTable;
    @FXML
    private TableColumn<TestStep, Integer> idCol;
    @FXML
    private TableColumn<TestStep, String> tagNameCol;

    @FXML
    private MenuButton tagMenu;
    @FXML 
    private Button adicionarButton;
    
    private Stage stage;
    private List<BaseTag> tagList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        tagNameCol.setCellValueFactory(new PropertyValueFactory<>("tagName"));

        //testStepTable.setItems(nameList());
    }

    /* 
    //@TestingCode - Will be changed for a persistence or load files from computer
    private ObservableList<TestStep> nameList() {
        BaseTag tag = new CommunicationTag();
        return FXCollections.observableArrayList(
            new TestStep(0, "read", tag),
            new TestStep(1, "write", tag),
            new TestStep(2, "read", tag),
            new TestStep(3, "compare", tag),
            new TestStep(4, "read", tag)
        );
    }
    */

    @FXML
    private void addStep() throws IOException {
        String formFileName = "tag" + tagMenu.getText() + "Form";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewConfigurationPathUtil.FULL_VIEW_PATH + formFileName + ".fxml"));
        Parent parent = (Parent) loader.load();
        BaseTagForm addFormController = (BaseTagForm) loader.getController();
        addFormController.setId(testStepTable.getItems().size());
        addFormController.setStage(parent);
        if(addFormController.isFieldValidation()) {
            TestStep testStep = new TestStep(testStepTable.getItems().size(), addFormController.getTagName(), addFormController.getTag());
            testStepTable.getItems().add(testStep);
        }
    }

    @FXML
    private void removeSelectedItem() {
        ObservableList<TestStep> testStepList =  testStepTable.getItems();
        int removedId = testStepTable.getSelectionModel().getSelectedItem().getId();
        testStepList.size();
        for (int i =0; i < testStepList.size(); i++) {
            if(removedId < testStepList.get(i).getId()) {
                testStepList.get(i).setId(testStepList.get(i).getId()-1);
            }
        }
        testStepTable.getItems().removeAll(testStepTable.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void moveDown() {
        ObservableList<TestStep> TestStepList =  testStepTable.getItems();
        int id = testStepTable.getSelectionModel().getSelectedItem().getId();
        int idSwap = id+1;
        if(testStepTable.getItems().size()-1 > id) {
            TestStepList.get(id).setId(idSwap);
            TestStepList.get(idSwap).setId(id);
            Collections.swap(TestStepList, id, idSwap);
        }
    }

    @FXML
    private void moveUp() {
        ObservableList<TestStep> TestStepList =  testStepTable.getItems();
        int id = testStepTable.getSelectionModel().getSelectedItem().getId();
        int idSwap = id-1;
        if(id > 0) {
            TestStepList.get(id).setId(idSwap);
            TestStepList.get(idSwap).setId(id);
            Collections.swap(TestStepList, id, idSwap);
        }
    }

    @FXML
    private void switchToPreviousPage() throws IOException {
        tagList = getListFromTable(testStepTable);
        stage.close();
    }

    private List<BaseTag> getListFromTable(TableView<TestStep> testStepTable) {
        List<BaseTag> list = new ArrayList<>();
        for (int i = 0; i < testStepTable.getItems().size(); i++) {
            list.add(testStepTable.getItems().get(i).getTag());
        }
        return list;
    }

    @FXML
    private void switchToEditForm() throws IOException {
        String nameBuffer = testStepTable.getSelectionModel().getSelectedItem().getTagName();
        String nameToUppercase = nameBuffer.substring(0, 1).toUpperCase() + nameBuffer.substring(1);
        String formFileName = "tag" + nameToUppercase + "Form";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewConfigurationPathUtil.FULL_VIEW_PATH + formFileName + ".fxml"));
        Parent part = (Parent) loader.load();
        BaseTagForm addFormController = (BaseTagForm) loader.getController();
        addFormController.setTag(testStepTable.getSelectionModel().getSelectedItem().getTag());
        addFormController.setId(testStepTable.getSelectionModel().getSelectedItem().getTag().getId());
        addFormController.setEditStage(part);
        if(addFormController.isFieldValidation()) {
            testStepTable.getSelectionModel().getSelectedItem().setTag(addFormController.getTag());
        }
    }

    public void setStage(Parent part, List<BaseTag> tagList) {
        stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        setTableContent(tagList);
        stage.showAndWait();
    }

    private void setTableContent(List<BaseTag> tagList) {
        this.tagList = tagList;
        for (int i = 0; i < tagList.size(); i++) {
            testStepTable.getItems().add(new TestStep(i, tagList.get(i).getTagName(), tagList.get(i)));
        }
    }

    public List<BaseTag> getTagList() {
        return this.tagList;
    }

    @FXML
    private void selectCommunication() {
        adicionarButton.setDisable(false);
        tagMenu.setText("Communication");
    }

    @FXML
    private void selectRead() {
        adicionarButton.setDisable(false);
        tagMenu.setText("Read");
    }

    @FXML
    private void selectWrite() {
        adicionarButton.setDisable(false);
        tagMenu.setText("Write");
    }

    @FXML
    private void selectCompare() {
        adicionarButton.setDisable(false);
        tagMenu.setText("Compare");
    }

    @FXML
    private void selectVerify() {
        adicionarButton.setDisable(false);
        tagMenu.setText("Verify");
    }

    @FXML
    private void selectVariableRead() {
        adicionarButton.setDisable(false);
        tagMenu.setText("VariableRead");
    }

    @FXML
    private void selectVariableWrite() {
        adicionarButton.setDisable(false);
        tagMenu.setText("VariableWrite");
    }

    @FXML
    private void selectTest() {
        adicionarButton.setDisable(false);
        tagMenu.setText("Test");
    }

    @FXML
    private void selectCommunicationIOLink() {
        adicionarButton.setDisable(false);
        tagMenu.setText("CommunicationIOLink");
    }

    @FXML
    private void selectWriteString() {
        adicionarButton.setDisable(false);
        tagMenu.setText("WriteString");
    }

    @FXML
    private void selectReadMultiple() {
        adicionarButton.setDisable(false);
        tagMenu.setText("ReadMultiple");
    }
}
