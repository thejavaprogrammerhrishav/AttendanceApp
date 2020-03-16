/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.user.controller;

import com.attendance.login.activity.model.LoginActivity;
import com.attendance.login.activity.service.LoginActivityService;
import com.attendance.user.model.PersonalDetails;
import com.attendance.user.model.User;
import com.attendance.user.service.LoginService;
import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author pc
 */
public class LoginController extends View {

    @FXML
    private Label department;

    @FXML
    private Label usertype;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label status;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton back;

    private FXMLLoader fxml;
    private String type;
    private LoginService service;
    private ExceptionDialog dialog;
    private LoginActivity activity;
    private LoginActivityService activityservice;

    public LoginController(String type) {
        this.type = type;
        fxml = Fxml.getLoginFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        back.setOnAction(this::back);
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, e -> {
            SystemUtils.getApplication().switchView(AppView.SELECT_DEPARTMENT_VIEW);
        });

        department.setText("Department: " + SystemUtils.getDepartment());
        usertype.setText("User Type: " + type);

        service = (LoginService) SystemUtils.getContext().getBean("loginservice");
        service.setParent(this);
        
        activityservice = (LoginActivityService) SystemUtils.getContext().getBean("loginactivityservice");
        activityservice.setParent(this);
        login.setOnAction(this::login);

    }

    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.SELECT_DEPARTMENT_VIEW);
    }

    private void login(ActionEvent evt) {
        User user;
        PersonalDetails details;
        SystemUtils.setType(type);
        boolean log = service.login(username.getText(), password.getText(), type);
        if (log) {
            status.setText("Login Success");
            status.setTextFill(Color.GREEN);
            SystemUtils.setUser(service.findByUsernameDepartmentType(username.getText(), SystemUtils.getDepartment(), type));
            user = SystemUtils.getUser();
            details = user.getDetails();
            activity = new LoginActivity(details.getName(),user.getUsername(),type,"Active",DateTime.now().toString(DateTimeFormat.forPattern("dd-MM-yyyy")),DateTime.now().toString(DateTimeFormat.forPattern("hh:mm:ss a")),"",SystemUtils.getDepartment());
            activityservice.saveactivity(activity);
            SystemUtils.setActivity(activity);
            redirect();
        } else {
            status.setText("Login Failed");
            status.setTextFill(Color.RED);
        }
    }

    private void redirect() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 3; i <= 1; i--) {
                    final int abc = i;
                    Platform.runLater(() -> status.setText("Dashboard in " + abc + " Sec."));

                    Thread.sleep(700);
                }
                Platform.runLater(() -> {
                    SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
                });
                return null;
            }
        };
        Thread t = new Thread(task);
        t.start();
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }

}
