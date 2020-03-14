/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.attendance.controller;

import com.attendance.student.attendance.model.MyClassDetails;
import com.attendance.util.AppView;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

/**
 *
 * @author pc
 */
public class CompleteClassDetailsController extends View {

    @FXML
    private JFXButton back;

    @FXML
    private Label name;

    @FXML
    private Label classid;

    @FXML
    private Label subject;

    @FXML
    private Label date;

    @FXML
    private Label department;

    @FXML
    private Label paper;

    @FXML
    private Label semester;

    @FXML
    private Label acayear;

    @FXML
    private Label coursetype;

    @FXML
    private Label time;

    @FXML
    private Label year;

    private FXMLLoader fxml;
    private MyClassDetails details;
    

    public CompleteClassDetailsController(MyClassDetails details) {
        this.details = details;
        fxml = Fxml.getCompleteClassDetailsFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(CompleteClassDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        name.setText(details.getFacultyName());
        classid.setText(details.getClassId());
        subject.setText(details.getSubjectTaught());
        date.setText(details.getDate());
        department.setText(details.getDepartment());
        paper.setText(details.getPaper());
        semester.setText(details.getSemester());
        acayear.setText(details.getAcadamicyear());
        coursetype.setText(details.getCoursetype());
        time.setText(details.getTime());
        year.setText(""+details.getYear());
        
        
        back.setOnAction(this::back);
    }
    
    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchToPreviousView();
    }

}
