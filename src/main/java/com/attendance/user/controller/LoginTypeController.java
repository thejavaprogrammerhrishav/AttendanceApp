/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.user.controller;

import com.attendance.util.AppView;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

/**
 *
 * @author pc
 */
public class LoginTypeController extends View {

    @FXML
    private Button back;

    @FXML
    private Button hod;

    @FXML
    private Button faculty;
    
    private FXMLLoader fxml;

    public LoginTypeController() {
        fxml = Fxml.getSelectLoginFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(LoginTypeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void initialize() {
        back.setOnAction(this::back);
        hod.setOnAction(this::hod);
        faculty.setOnAction(this::faculty);
        this.addEventHandler(MobileApplication.MobileEvent.BACK_BUTTON_PRESSED, e->{
            SystemUtils.getApplication().switchView(AppView.SPLASH_VIEW);
        });
    }
    
    private void back(ActionEvent evt){
        SystemUtils.getApplication().switchView(AppView.SPLASH_VIEW);
    }
    
    private void hod(ActionEvent evt) {
        
    }
    
    private void faculty(ActionEvent evt) {
        
    }

}
