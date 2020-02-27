/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.user.controller;

import com.attendance.user.service.LoginService;
import com.attendance.util.AppView;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.TextField;
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
import javafx.scene.paint.Color;

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
        back.setOnAction(this::back);
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, e->{
            SystemUtils.getApplication().switchView(AppView.SELECT_DEPARTMENT_VIEW);
        });
        
        service = (LoginService) SystemUtils.getContext().getBean("loginservice");
        login.setOnAction(this::login);
        
        department.setText("Department: " + SystemUtils.getDepartment());
        usertype.setText("User Type: " + type);
    }
    
    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.SELECT_DEPARTMENT_VIEW);       
    }
    
    private void login(ActionEvent evt){
        boolean log = service.login(username.getText(), password.getText(), type);
        if(log) {
            status.setText("Login Success");
            status.setTextFill(Color.GREEN);
            SystemUtils.setUser(service.findByUsernameDepartmentType(username.getText(), SystemUtils.getDepartment(), type));
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
                byte count = 3;
                while(count!= 0){
                    final byte abc = count;
                    Platform.runLater(()->status.setText("Dashboard in "+ abc+" Sec."));
                    count--;
                    Thread.sleep(700);
                }
                SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
                return null;
            }
        };
        Thread t = new Thread(task);
        t.start();
    }
}
