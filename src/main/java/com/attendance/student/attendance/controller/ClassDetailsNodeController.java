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
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author pc
 */
public class ClassDetailsNodeController extends AnchorPane {

    @FXML
    private Label name;

    @FXML
    private Label date;

    @FXML
    private Label semester;

    @FXML
    private JFXButton proceed;

    @FXML
    private Label coursetype;

    private FXMLLoader fxml;
    private MyClassDetails details;
    private ClassDetailsController controller;

    public ClassDetailsNodeController(MyClassDetails details,ClassDetailsController controller) {
        this.details = details;
        this.controller = controller;
        fxml = Fxml.getClassDetailsNodeControllerFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ClassDetailsNodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        name.setText(details.getFacultyName());
        date.setText(details.getDate());
        semester.setText(details.getSemester());
        coursetype.setText(details.getCoursetype());
        
        proceed.setOnAction(this::proceed);
    }
    
    private void proceed(ActionEvent evt) {
        CompleteClassDetailsController c = new CompleteClassDetailsController(details);
        SystemUtils.getApplication().addViewFactory("Complete Class Details", ()->c);
        SystemUtils.getApplication().switchView("Complete Class Details");
    }

    public MyClassDetails getDetails() {
        return details;
    }

       
}
