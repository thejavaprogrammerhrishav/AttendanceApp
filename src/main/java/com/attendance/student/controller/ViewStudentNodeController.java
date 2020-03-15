/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.controller;

import com.attendance.student.model.Student;
import com.attendance.util.ExceptionDialog;
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
public class ViewStudentNodeController extends AnchorPane {

    @FXML
    private Label name;

    @FXML
    private Label year;

    @FXML
    private Label academicyear;
    
    @FXML
    private JFXButton view;
    
    private FXMLLoader fxml;
    private Student student;
    private ExceptionDialog dialog;

    public ViewStudentNodeController(Student student) {
        this.student = student;
        fxml = Fxml.getViewStudentsListFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ViewStudentNodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        name.setText(student.getName());
        year.setText(""+student.getYear());
        academicyear.setText(student.getAcadamicyear());
        view.setOnAction(this::view);
    }
    
    private void view(ActionEvent evt) {
        System.out.println("View ........................");
        SystemUtils.getApplication().removeViewFactory("StudentDetails");
        SystemUtils.getApplication().addViewFactory("StudentDetails", () -> new ViewStudentDetailsController(student));
        SystemUtils.getApplication().switchView("StudentDetails");
    }
    
    public Student getStudent() {
        return student;
    }

}
