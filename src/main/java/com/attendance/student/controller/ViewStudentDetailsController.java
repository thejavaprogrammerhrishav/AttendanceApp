/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.controller;

import com.attendance.student.model.Student;
import com.attendance.util.Fxml;
import com.gluonhq.charm.glisten.control.Dialog;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

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
    
    private static Dialog<Void> dialog;

    protected ViewStudentDetailsController(Student student) {
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
        studentname.setText(student.getName());
        contact.setText(student.getContact());
        rollno.setText(""+student.getRollno());
        acadamicyear.setText(student.getAcadamicyear());
        yearofadmission.setText(""+student.getYear());
        coursetype.setText(student.getCourseType());
        if(student.getGender().equalsIgnoreCase("Male")) {
            male.setSelected(true);
        }else {
            female.setSelected(true);
        }
        
        close.setOnAction(e->dialog.hide());
    }
    
    public static void show(Student student) {
        dialog = new Dialog<>(true);
        dialog.setContent(new ViewStudentDetailsController(student));
        dialog.showAndWait();
    }

}
