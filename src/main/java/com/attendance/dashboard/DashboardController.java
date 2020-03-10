/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.dashboard;

import com.attendance.student.service.StudentService;
import com.attendance.user.service.LoginService;
import com.attendance.util.AppView;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author pc
 */
public class DashboardController extends View {

    @FXML
    private JFXComboBox<String> year;

    @FXML
    private Label first;

    @FXML
    private Label second;

    @FXML
    private Label third;

    @FXML
    private JFXButton student;

    @FXML
    private JFXButton faculty;

    @FXML
    private JFXButton update;

    @FXML
    private JFXButton notes;

    @FXML
    private JFXButton upload;

    @FXML
    private JFXButton download;

    @FXML
    private JFXButton classdetails;

    @FXML
    private JFXButton logout;

    @FXML
    private Label date;

    @FXML
    private Label time;

    @FXML
    private JFXButton home;

    @FXML
    private JFXButton activity;

    @FXML
    private JFXButton account;

    @FXML
    private JFXButton settings;

    private StudentService studentservice;
    private LoginService service;
    private FXMLLoader fxml;

    public DashboardController() {
        fxml = Fxml.getDashboardFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        logout.setOnAction(e -> SystemUtils.getApplication().switchView(AppView.SELECT_DEPARTMENT_VIEW));
        service = (LoginService) SystemUtils.getContext().getBean("loginservice");
        studentservice = (StudentService) SystemUtils.getContext().getBean("studentservice");
        date.setText("Date: " + DateTime.now().toString(DateTimeFormat.forPattern("EEEEE, dd MMMMM yyyy")));
        timer();
        counter();
        account.setOnAction(e->{
            SystemUtils.getApplication().switchView(AppView.PROFILE_VIEW);
        });
        activity.setOnAction(e->{
            SystemUtils.getApplication().switchView(AppView.LOGIN_ACTIVITY_VIEW);
        });
        student.setOnAction(e->{
            SystemUtils.getApplication().switchView(AppView.STUDENT_DETAILS_VIEW);
        });
        faculty.setOnAction(e->{
            SystemUtils.getApplication().switchView(AppView.FACULTY_DETAILS_VIEW);
        });
    }

    private void timer() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Platform.runLater(() -> {
                        time.setText("Time: " + DateTime.now().toString("hh : mm : ss a"));
                    });
                    Thread.sleep(995);
                }
            }
        };
        Thread t = new Thread(task);
        t.start();
    }

    private void counter() {
        year.getItems().setAll("All");
        year.getItems().addAll(studentservice.findAllYear());
        year.getSelectionModel().selectedItemProperty().addListener((ol, o, n) -> {
            if (n != null) {
                int c1=0,c2=0,c3=0;
                if (n.equals("All")) {
                    c1 = studentservice.countStudents("1st", SystemUtils.getDepartment());
                    c2 = studentservice.countStudents("2nd", SystemUtils.getDepartment());
                    c3 = studentservice.countStudents("3rd", SystemUtils.getDepartment());
                } else {
                    c1 = studentservice.countStudents("1st", Integer.parseInt(n), SystemUtils.getDepartment());
                    c2 = studentservice.countStudents("2nd", Integer.parseInt(n), SystemUtils.getDepartment());
                    c3 = studentservice.countStudents("3rd", Integer.parseInt(n), SystemUtils.getDepartment());
                }
                first.setText("" + c1);
                second.setText(""+c2);
                third.setText(""+c3);
            }
        });
    }

}
