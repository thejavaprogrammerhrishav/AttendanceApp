/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.attendance.controller;

import com.attendance.papers.model.Paper;
import com.attendance.papers.service.PapersService;
import com.attendance.student.attendance.model.Attendance;
import com.attendance.student.attendance.model.ClassDetails;
import com.attendance.student.attendance.model.DailyStats;
import com.attendance.student.attendance.model.Details;
import com.attendance.student.model.Student;
import com.attendance.student.service.StudentService;
import com.attendance.studentattendance.service.AttendanceService;
import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.attendance.util.ValidationUtils;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javax.validation.ConstraintViolation;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

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
    
    @FXML
    private JFXButton load;
    
    private FXMLLoader fxml;
    private Details details;
    private PapersService service;
    private AttendanceService attendanceService;
    private StudentService studentService;
    private ExceptionDialog dialog;

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
        dialog = SystemUtils.getDialog();
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
        load.setOnAction(this::load);
    }
    
    private void load(ActionEvent evt) {
        List<Student> list = studentService.findByDepartment(details.getDepartment());
        List<Student> students = list.stream().filter(f->f.getYear()== Integer.parseInt(details.getYear())).filter(f->f.getAcadamicyear().equals(details.getAcademicyear()))
                .filter(f->f.getCourseType().equals(details.getCoursetype())).collect(Collectors.toList());
        List<AttendanceNodeController> nodes = students.stream().map(AttendanceNodeController:: new ).collect(Collectors.toList());
        this.list.getChildren().setAll(nodes);
    }
    
    private void update(ActionEvent evt) {
        List<Node> nodes = this.list.getChildren();
        List<AttendanceNodeController> students = nodes.stream().map(m->(AttendanceNodeController)m).collect(Collectors.toList());
        
        String t = details.getTime();
        if(t.contains("AM")) {
            t = t.replace(" AM", "");
        }
        if(t.contains("PM")) {
            t = t.replace(" PM", "");
        }
        DateTime parse = DateTime.parse(details.getDate(),DateTimeFormat.forPattern("EEEEE, dd MMMMM yyyy"));
        String classid = details.getDepartment()+"/"+parse.toString(DateTimeFormat.forPattern("dd-MM-yyyy"))
                +"@"+t+"#"+details.getAcademicyear()+"__"+semester.getSelectionModel().getSelectedItem()
                +"_"+details.getYear()+"&"+details.getCoursetype().charAt(0);
        
        ClassDetails cd = new ClassDetails();
        cd.setClassId(classid);
        cd.setDate(details.getDate());
        cd.setFacultyName(details.getFaculty());
        cd.setSemester(semester.getSelectionModel().getSelectedItem());
        cd.setAcadamicyear(details.getAcademicyear());
        cd.setSubjectTaught(details.getSubject());
        cd.setTime(details.getTime());
        cd.setYear(Integer.parseInt(details.getYear()));
        cd.setPaper(paper.getSelectionModel().getSelectedItem());
        cd.setDepartment(details.getDepartment());
        cd.setCoursetype(details.getCoursetype());
        
        DailyStats daily = new DailyStats();
        int total  = students.size();
        int present = students.stream().filter(f->f.getStatus()).collect(Collectors.toList()).size();
        int absent =  students.stream().filter(f->!f.getStatus()).collect(Collectors.toList()).size();
        
        float presentper = ((float)present/total)*100;
        float absentper = ((float)absent/total)*100;
        
        daily.setAbsentPercentage(absentper);
        daily.setPresentPercentage(presentper);
        daily.setTotalStudent(total);
        daily.setTotalPresent(present);
        daily.setTotalAbsent(absent);
        
        List<Attendance> attendance = students.stream().map(m->{
            Attendance at = new Attendance();
            if(m.getStatus()) {
                at.setStatus("Present");
            }else {
                at.setStatus("Absent");
            }
            at.setStudentId(m.getStudent().getId());
            return at;   
        }).collect(Collectors.toList());
        
        cd.setDailyStats(daily);
        cd.setAttendance(attendance);
        
        Set<ConstraintViolation<DailyStats>> validate = ValidationUtils.getValidator().validate(daily);
        if(validate.isEmpty()) {
            Set<ConstraintViolation<ClassDetails>> validate1 = ValidationUtils.getValidator().validate(cd);
            if(validate1.isEmpty()) {
                String cid = attendanceService.saveAttendance(cd);
                if(cid == null || cid.isEmpty()) {
                    dialog.showError(this, "Attendance Saving Failed");
                }else {
                    update.setDisable(true);
                    dialog.showSuccess(this, "Attendance Saved Successfully");
                }
            }else {
                validate1.stream().forEach(c->{
                    dialog.showError(this, c.getMessage());
                });
            }
        }else {
            validate.stream().forEach(c->{
                dialog.showError(this, c.getMessage());
            });
        }
    }
    
    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.ATTENDANCE_VIEW);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }
    
    
     
}
