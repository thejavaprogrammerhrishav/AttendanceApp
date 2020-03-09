/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.controller;

import com.attendance.util.Fxml;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

/**
 *
 * @author pc
 */
public class ViewStudentFilterController extends View {

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton apply;

    @FXML
    private JFXComboBox<String> coursetype;

    @FXML
    private CheckBox filterbyid;

    @FXML
    private CheckBox filterbyacayear;

    @FXML
    private CheckBox filterbyyear;

    @FXML
    private CheckBox filterbyname;

    @FXML
    private CheckBox filterbycousetype;

    @FXML
    private JFXComboBox<String> academicyear;

    @FXML
    private JFXComboBox<String> year;

    @FXML
    private JFXTextField student;

    @FXML
    private JFXTextField studentname;

    private FXMLLoader fxml;
    private ViewStudentsController controller;

    public ViewStudentFilterController(ViewStudentsController controller) {
        this.controller = controller;
        fxml = Fxml.getViewStudentsFilterFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ViewStudentFilterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void intialize() {
        student.disableProperty().bind(filterbyid.selectedProperty().not());
        studentname.disableProperty().bind(filterbyname.selectedProperty().not());
        year.disableProperty().bind(filterbyyear.selectedProperty().not());
        academicyear.disableProperty().bind(filterbyacayear.selectedProperty().not());
        coursetype.disableProperty().bind(filterbycousetype.selectedProperty().not());

        academicyear.getItems().setAll("1st", "2nd", "3rd");
        coursetype.getItems().setAll("Honours", "Pass");
        List<String> years = controller.getService().findAllYear();
        year.getItems().setAll(years);
        
        apply.setOnAction(this::applyfilter);
    }

    private void applyfilter(ActionEvent evt) {
        List<Node> l = controller.getNodeList();
        List<ViewStudentNodeController> list = l.stream().map(m->(ViewStudentNodeController)m).collect(Collectors.toList());
        if (filterbyid.isSelected()) {
            list = list.stream().filter(f->f.getStudent().getId().equals(student.getText())).collect(Collectors.toList());
        }

        if (filterbyname.isSelected()) {
            list = list.stream().filter(f->f.getStudent().getName().toLowerCase().contains(studentname.getText().toLowerCase())).collect(Collectors.toList());
        }

        if (filterbyyear.isSelected()) {
            list = list.stream().filter(f->f.getStudent().getYear()==Integer.parseInt(year.getSelectionModel().getSelectedItem())).collect(Collectors.toList());
        }

        if (filterbyacayear.isSelected()) {
            list = list.stream().filter(f->f.getStudent().getAcadamicyear().equals(academicyear.getSelectionModel().getSelectedItem())).collect(Collectors.toList());
        }

        if (filterbycousetype.isSelected()) {
            list = list.stream().filter(f->f.getStudent().getCourseType().equals(coursetype.getSelectionModel().getSelectedItem())).collect(Collectors.toList());
        }
        
        controller.load(list);
    }

}
