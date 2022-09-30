package com.edson.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import com.edson.model.dto.TestStep;
import com.edson.tag.BaseTag;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    
    Stage stage;
    List<BaseTag> tagList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        tagNameCol.setCellValueFactory(new PropertyValueFactory<>("tagName"));

        testStepTable.setItems(nameList());
    }

    //@TestingCode - Will be changed for a persistence or load files from computer
    private ObservableList<TestStep> nameList() {
        return FXCollections.observableArrayList(
            new TestStep(0, "read", null),
            new TestStep(1, "write", null),
            new TestStep(2, "read", null),
            new TestStep(3, "compare", null),
            new TestStep(4, "read", null)
        );
    }

    @FXML
    private void addStep() {
        
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
        stage.close();
    }

    @FXML
    private void switchToEditForm() {
        
    }

    public void setStage(Parent part, List<BaseTag> tagList) {
        stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void setTableContent(List<BaseTag> tagList) {
        this.tagList = tagList;
        for (int i = 0; i < tagList.size(); i++) {
            new TestStep(tagList.get(0).getId(), tagList.get(0).getTagName(), tagList.get(0));
        }
        testStepTable.getItems().add(tagList);
    }

    @FXML
    private void selectCommunication() {
        adicionarButton.setDisable(false);
        tagMenu.setText("communication");
    }

    @FXML
    private void selectRead() {
        adicionarButton.setDisable(false);
        tagMenu.setText("read");
    }

    @FXML
    private void selectWrite() {
        adicionarButton.setDisable(false);
        tagMenu.setText("write");
    }

    @FXML
    private void selectCompare() {
        adicionarButton.setDisable(false);
        tagMenu.setText("compare");
    }

    @FXML
    private void selectVerify() {
        adicionarButton.setDisable(false);
        tagMenu.setText("verify");
    }

    @FXML
    private void selectVariableRead() {
        adicionarButton.setDisable(false);
        tagMenu.setText("variableRead");
    }

    @FXML
    private void selectVariableWrite() {
        adicionarButton.setDisable(false);
        tagMenu.setText("variableWrite");
    }

    @FXML
    private void selectTest() {
        adicionarButton.setDisable(false);
        tagMenu.setText("test");
    }
}
