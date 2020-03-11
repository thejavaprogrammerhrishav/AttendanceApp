/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.login.forgot;

import com.attendance.user.model.User;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

/**
 *
 * @author pc
 */
public class ForgotPassword1Controller extends View {

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private Label usertype;

    @FXML
    private Label department;

    @FXML
    private JFXButton proceed;

    @FXML
    private JFXButton cancel;

    private FXMLLoader fxml;
    private LoginService service;

    public ForgotPassword1Controller() {
        fxml = Fxml.getForgotPassword1Fxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ForgotPassword1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        department.setText(SystemUtils.getDepartment());
        usertype.setText(SystemUtils.getType());
        
        service = (LoginService) SystemUtils.getContext().getBean("loginservice");
        
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, eh->{
            SystemUtils.getApplication().switchView(AppView.LOGIN_VIEW);
        });
        
        cancel.setOnAction(this::back);
        proceed.setOnAction(this::proceed);
    }
    
    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.LOGIN_VIEW);
    }
    
    
    private void proceed(ActionEvent evt) {
        String user = username.getText();
        String mail = email.getText();
        
        if(user!=null && !user.isEmpty() && mail!=null && !mail.isEmpty()) {
            User forgot = service.findByUsernameDepartmentType(user, SystemUtils.getDepartment(), SystemUtils.getType());
            if(forgot.getDetails().getEmailId().equals(mail)) {
                SystemUtils.setUser(forgot);
                if(forgot.hasSecurityQuestion()) {
                    SystemUtils.getApplication().switchView(AppView.SECURITY_QUESTION_VIEW);
                }else {
                    SystemUtils.getApplication().switchView(AppView.RESET_PASSWORD_VIEW);
                }
            }
        }
    }

}
