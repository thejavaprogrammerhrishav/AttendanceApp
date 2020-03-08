/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.attendance;

import com.attendance.papers.model.Paper;
import com.attendance.papers.service.PapersService;
import com.attendance.student.attendance.model.Details;
import com.attendance.student.model.Student;
import com.attendance.student.service.StudentService;
import com.attendance.studentattendance.service.AttendanceService;
import com.attendance.util.AppView;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author pc
 */
public class UpdateAttendanceController extends View{
    
    
    @FXML
    private JFXComboBox<String> semester;

    @FXML
    private JFXComboBox<String> paper;

    @FXML
    private FlowPane list;

    @FXML
    private JFXButton update;

    @FXML
    private JFXButton back;
    
    private FXMLLoader fxml;
    private Details details;
    private PapersService service;
    private AttendanceService attendanceService;
    private StudentService studentService;

    public UpdateAttendanceController(Details details) {
        this.details = details;
        fxml = Fxml.getUpdateAttendanceFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(UpdateAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void initialize() {
        service  = (PapersService) SystemUtils.getContext().getBean("papersservice");
        attendanceService =  (AttendanceService) SystemUtils.getContext().getBean("attendanceservice");
        studentService = (StudentService) SystemUtils.getContext().getBean("studentservice");
        if(details.getAcademicyear().equals("1st")) {
            semester.getItems().setAll("1st","2nd");
        }else if(details.getAcademicyear().equals("2nd")) {
            semester.getItems().setAll("3rd","4th");
        }else {
            semester.getItems().setAll("5th","6th");
        }
        
        semester.getSelectionModel().selectedItemProperty().addListener((ol,o,n)-> {
            if(n!=null || !n.isEmpty()) {
                List<Paper> list = service.findByDepartmentAndCourseType(details.getDepartment(), details.getCoursetype());
                List<String> filtered = list.stream().filter(p->p.getSemester().equals(n)).map(Paper::getPaperCode).collect(Collectors.toList());
                paper.getItems().setAll(filtered);
            }
        });
        
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, eh->{
            SystemUtils.getApplication().switchView(AppView.ATTENDANCE_VIEW);
        });
        
        back.setOnAction(this::back);
        update.setOnAction(this::update);
    }
    
    private void load() {
        List<Student> list = studentService.findByDepartment(details.getDepartment());
        List<Student> students = list.stream().filter(f->f.getYear()== Integer.parseInt(details.getYear())).filter(f->f.getAcadamicyear().equals(details.getAcademicyear()))
                .filter(f->f.getCourseType().equals(details.getCoursetype())).collect(Collectors.toList());
        List<AttendanceNodeController> nodes = students.stream().map(AttendanceNodeController:: new ).collect(Collectors.toList());
        this.list.getChildren().setAll(nodes);
    }
    
    private void update(ActionEvent evt) {
        List<Node> nodes = this.list.getChildren();
        List<AttendanceNodeController> students = nodes.stream().map(m->(AttendanceNodeController)m).collect(Collectors.toList());
        
        
    }
    
    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.ATTENDANCE_VIEW);
    }
     
}
