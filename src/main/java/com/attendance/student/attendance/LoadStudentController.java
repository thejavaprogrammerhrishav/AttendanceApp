/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.attendance;

import com.attendance.student.service.StudentService;
import com.attendance.studentattendance.service.AttendanceService;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author pc
 */
public class LoadStudentController extends View{
    
    @FXML
    private JFXComboBox<String> academicyear;

    @FXML
    private JFXComboBox<String> year;

    @FXML
    private JFXComboBox<String> coursetype;

    @FXML
    private Label date;

    @FXML
    private Label department;

    @FXML
    private Label faculty;

    @FXML
    private TextField subject;

    @FXML
    private JFXTimePicker classtime;

    @FXML
    private JFXButton load;

    @FXML
    private JFXButton back;
    
    private FXMLLoader fxml;
    private StudentService studentservice;
    private AttendanceService service;

    public LoadStudentController() {
        fxml = Fxml.getLoadStudentFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(LoadStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void initialize() {
        service = (AttendanceService) SystemUtils.getContext().getBean("attendanceservice");
        studentservice  = (StudentService) SystemUtils.getContext().getBean("studentservice");
        academicyear.getItems().setAll("1st","2nd","3rd");
        year.getItems().setAll(studentservice.findAllYear());
        coursetype.getItems().setAll("Honours","Pass");
        department.setText(SystemUtils.getDepartment());
        faculty.setText(SystemUtils.getUser().getDetails().getName());
        date.setText(DateTime.now().toString(DateTimeFormat.forPattern("EEEEE, dd MMMMM yyyy")));
    }
    
    
    
}
