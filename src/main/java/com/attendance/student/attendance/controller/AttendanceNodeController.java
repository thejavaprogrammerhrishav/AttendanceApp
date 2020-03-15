/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.attendance.controller;

import com.attendance.student.model.Student;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.jfoenix.controls.JFXCheckBox;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author pc
 */
public class AttendanceNodeController extends AnchorPane{
    
    
    @FXML
    private Label name;

    @FXML
    private Label rollno;

    @FXML
    private JFXCheckBox present;

    @FXML
    private JFXCheckBox absent;
    
    private FXMLLoader fxml;
    
    private Student student;
    private ExceptionDialog dialog;

    public AttendanceNodeController(Student student) {
        this.student = student;
        fxml = Fxml.getAttendanceNodeFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(AttendanceNodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        name.setText(student.getName());
        rollno.setText(""+student.getRollno());
        present.selectedProperty().addListener((ol,o,n)-> {
            if(n) {
                present.setSelected(true);
                absent.setSelected(false);
            }
        });
        absent.selectedProperty().addListener((ol,o,n)-> {
            if(n) {
                present.setSelected(false);
                absent.setSelected(true);
            }
        });
    }
    
    public boolean getStatus() {
        if(present.isSelected()) {
            return true;
        }else {
            return false ;
        }
    }
    
    public Student getStudent() {
        return student;
    }
    
}
