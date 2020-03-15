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
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Dialog;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

/**
 *
 * @author pc
 */
public class ViewStudentDetailsController extends View {

    @FXML
    private JFXButton close;

    @FXML
    private JFXCheckBox male;

    @FXML
    private JFXCheckBox female;

    @FXML
    private TextField studentname;

    @FXML
    private TextField contact;

    @FXML
    private TextField rollno;

    @FXML
    private TextField coursetype;

    @FXML
    private TextField acadamicyear;

    @FXML
    private TextField yearofadmission;

    private FXMLLoader fxml;
    private Student student;

    private ExceptionDialog exdialog;

    public ViewStudentDetailsController(Student student) {
        this.student = student;
        fxml = Fxml.getViewStudentsDetailsFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ViewStudentDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        exdialog = SystemUtils.getDialog();
        studentname.setText(student.getName());
        contact.setText(student.getContact());
        rollno.setText("" + student.getRollno());
        acadamicyear.setText(student.getAcadamicyear());
        yearofadmission.setText("" + student.getYear());
        coursetype.setText(student.getCourseType());
        if (student.getGender().equalsIgnoreCase("Male")) {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }

        close.setOnAction(e -> SystemUtils.getApplication().switchToPreviousView());
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }

}
